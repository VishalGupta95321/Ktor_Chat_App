package com.example.ktor_chat_app.presentation.login_signup_screen

import android.app.Activity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_chat_app.data.remote.model.InvalidRequestException
import com.example.ktor_chat_app.data.remote.model.RegisterUserRequest
import com.example.ktor_chat_app.data.repository.AuthResponse
import com.example.ktor_chat_app.screen_auth.domain.use_case.AuthUseCases
import com.example.ktor_chat_app.utility.Constants.INDIA_TELECOM_PREFIX
import com.example.ktor_chat_app.utility.Constants.MAX_PHONE_NO_LENGTH
import com.example.ktor_chat_app.utility.dataStore
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    ): ViewModel(){

    private val _name = mutableStateOf(TextFieldState(
        label = "Your Name"
    ))
    val name : State<TextFieldState> = _name

    private val _contact = mutableStateOf(TextFieldState(
        text = INDIA_TELECOM_PREFIX,
        label = "Your Contact no."
    ))
    val  contact : State<TextFieldState> = _contact


    private val _smsCode = mutableStateOf(TextFieldState(
        label = "Enter Your Otp.."
    ))
    val  smsCode : State<TextFieldState> = _smsCode


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var credential : PhoneAuthCredential? = null
    private var verificationID : String? = null
    private var datastore:DataStore<Preferences>? = null

    fun onEvent(event:LoginSignUpEvents){
        when(event){

            is LoginSignUpEvents.EnteredNameEvent -> {
                _name.value = _name.value.copy(
                    text = event.value
                )
            }

            is LoginSignUpEvents.EnteredPhoneNoEvent -> {
                _contact.value = _contact.value.copy(
                    text = event.value
                )
            }

            is LoginSignUpEvents.EnteredOtpEvent ->{
                _smsCode.value = _smsCode.value.copy(
                    text = event.value
                )
            }

            is LoginSignUpEvents.GenerateOtpEvent -> {
                datastore = event.activity.dataStore

                viewModelScope.launch {
                    try {
                        authUseCases.generateOrResendOtp(
                            RegisterUserRequest(
                                name = _name.value.text,
                                id = _contact.value.text
                            ), phoneAuthOptions(
                                _contact.value.text,
                                event.activity
                            )
                        )
                    } catch (e: InvalidRequestException) {
                        _eventFlow.emit(
                            UiEvent.OnError(
                                message = e.message ?: "Something went wrong"
                            )
                        )
                    }
                }
            }

            is LoginSignUpEvents.ResendOtpEvent -> {
                viewModelScope.launch {
                    try {
                        authUseCases.generateOrResendOtp(
                            RegisterUserRequest(
                                name = _name.value.text,
                                id = _contact.value.text
                            ),phoneAuthOptions(
                                _contact.value.text,
                                event.activity
                            )
                        )
                    } catch (e: InvalidRequestException) {
                        _eventFlow.emit(
                            UiEvent.OnError(
                                message = e.message ?: "Something went wrong"
                            ))
                    }
                }
            }

            is LoginSignUpEvents.CompleteLoginEvent -> {
                if (credential == null){
                    credential = PhoneAuthProvider.getCredential(verificationID!!,event.value)
                }
                viewModelScope.launch {
                    authUseCases.otpVerification(credential!!).collect{ response->
                       when(response){
                           is AuthResponse.Loading ->{
                               _eventFlow.emit(UiEvent.Loading)
                           }
                           is AuthResponse.Error -> {
                               _eventFlow.emit(UiEvent.OnError(response.message))
                           }
                           is AuthResponse.Success -> {
                               _eventFlow.emit(UiEvent.LoginSuccessful)
                                authUseCases.registerToServer(RegisterUserRequest(_name.value.text,_contact.value.text))
                                authUseCases.saveUserToDatabase(_contact.value.text)
                           }
                           else -> _eventFlow.emit(UiEvent.OnError("Something went wrong !!"))
                       }
                    }
                }
            }

            is LoginSignUpEvents.SignOut -> {
                viewModelScope.launch{
                    authUseCases.signOut
                }
            }
        }
    }

    fun isUserAuthenticated():Boolean{
        var value : Boolean? = null
        viewModelScope.launch {
            value = authUseCases.isUserAuthenticated.invoke()
            while (value!=null){
                delay(100L)
            }
        }
        return value ?: false
    }

    private fun phoneAuthOptions(phoneNo: String, activity: Activity): PhoneAuthOptions? {
        if (_contact.value.text.length != MAX_PHONE_NO_LENGTH)
            return null
            return PhoneAuthOptions.newBuilder(Firebase.auth)
                .setPhoneNumber(phoneNo)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onCodeSent(
                        verificationId: String,
                        forceResendingToken: PhoneAuthProvider.ForceResendingToken
                    ) {
                        verificationID = verificationId
                        viewModelScope.launch {
                            _eventFlow.emit(UiEvent.OtpSent)
                        }
                    }

                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        credential = phoneAuthCredential
                        viewModelScope.launch{
                            onEvent(LoginSignUpEvents.CompleteLoginEvent(_smsCode.value.text))
                        }
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        viewModelScope.launch {
                            _eventFlow.emit(UiEvent.OnError(e.message ?: "Something went wrong "))
                        }
                    }
                })
                .build()
           }
    }

sealed class UiEvent {
    data class OnError(val message: String) : UiEvent()
    object OtpSent : UiEvent()
    object LoginSuccessful : UiEvent()
    object Loading : UiEvent()
}
