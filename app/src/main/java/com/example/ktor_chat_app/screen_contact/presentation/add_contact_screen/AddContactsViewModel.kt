package com.example.ktor_chat_app.screen_contact.presentation.add_contact_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case.WebSocketUseCases
import com.example.ktor_chat_app.screen_contact.domain.use_case.ContactUseCases
import com.example.ktor_chat_app.core.utility.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactsViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases,
    private val dispatchers: DispatcherProvider,
    private val webSocketUseCases: WebSocketUseCases,
):ViewModel(){


    private val _availableContacts = mutableStateOf(listOf(AvailableContactState()))
    val availableContacts : State<List<AvailableContactState>> = _availableContacts

    fun onEvent(event: AddContactEvent){
            when(event){
                is AddContactEvent.AddContacts -> {
                    checkingIfContactIsAvailableToChat(event.contacts)
                }
            }
    }

    private fun checkingIfContactIsAvailableToChat(contactsList:List<String>){
        viewModelScope.launch(dispatchers.io){
            webSocketUseCases.checkingContactRegistered(contactsList)
        }
    }

    private fun getAllAvailableContacts(){

        viewModelScope.launch {
            contactUseCases.getAllAvailableContacts().collect{ contacts->
                _availableContacts.value = contacts.map {
                        AvailableContactState(
                            name = it.name,
                            about = it.about,
                            userId = it.userId
                        ) }
            }
        }
    }

    init {
        getAllAvailableContacts()
    }
}