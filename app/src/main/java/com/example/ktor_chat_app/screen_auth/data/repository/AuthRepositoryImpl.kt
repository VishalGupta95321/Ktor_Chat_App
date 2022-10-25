package com.example.ktor_chat_app.data.repository


import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.ktor_chat_app.core.utility.credentials
import com.example.ktor_chat_app.core.utility.saveCredentials
import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import com.example.ktor_chat_app.web_socket.data.remote.webScoketApi.ChatApi
import com.google.firebase.auth.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api : ChatApi,
    private val datastore:DataStore<Preferences>,
    private val auth: FirebaseAuth,
) : AuthRepository {

    override fun generateOrResendOtp(options: PhoneAuthOptions) {
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override suspend fun createUserRequest(request: BaseModel) {
        api.sendBaseModel(request)
    }

    override suspend fun firebaseSignIn(credential: PhoneAuthCredential) = flow {
        var success : Boolean? = null
        var result : AuthResult? = null
        var error : Exception? = null
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                result = it
                success = true
                Log.d("fire","done")
            }.addOnFailureListener {
                success = false
                error = it
            }

        while (success==null){
            delay(500L)
        }
        if (success==true){
            emit(AuthResponse.Success(result!!))
        } else if(error==FirebaseAuthInvalidCredentialsException::class){
            emit(AuthResponse.Error("Invalid Otp !!"))
        }else{
            emit(AuthResponse.Error(error?.message?:"Something went wrong"))
        }
    }


    override suspend fun saveUserToDatabase(userId:String, userName: String){
        return datastore.saveCredentials(userId,userName)
    }

    override suspend fun isUserAuthenticated() : Boolean {
        if (datastore.credentials().isEmpty()){
            println("empty")
           return false
        }
        println("not empty")
           return true
    }

    override suspend fun signOut() {
        datastore.edit {
            it.clear()
        }
    }
}

sealed class AuthResponse {
    object  Loading : AuthResponse()
    data class IsAuthenticated(val value : Boolean) : AuthResponse()
    data class Success(val result : AuthResult) : AuthResponse()
    data class Error(val message: String) : AuthResponse()
}
