package com.example.ktor_chat_app.presentation.ContactsScreen.HomeContactScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_chat_app.data.local.entity.asDomainModel
import com.example.ktor_chat_app.presentation.ContactsScreen.use_cases.ContactUseCases
import com.example.ktor_chat_app.utility.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeContactsViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val contactsUseCases: ContactUseCases
) :ViewModel()
{
    private val _contactsList = mutableStateOf(listOf(ActiveContactState()))
    val contactList : State<List<ActiveContactState>> = _contactsList

    private fun observingActiveContacts(){
        viewModelScope.launch{
            contactsUseCases.getAllActiveContacts().collect{ contacts ->
               _contactsList.value = contacts.asDomainModel()
                   .map {
                       ActiveContactState(
                           name = it.name,
                           userId = it.userId,
                           profilePic = it.profilePhotoUri
                       )}
            }
        }
    }

    init {
        observingActiveContacts()
    }
}
