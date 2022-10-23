package com.example.ktor_chat_app.core.utility

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("settings")

suspend fun DataStore<Preferences>.credentials(): List<String?> {
    val clientIdKey = stringPreferencesKey("clientId")
    val userNameKey = stringPreferencesKey("userName")
    val preferences = data.first()
    val clientIdExists = preferences[clientIdKey] != null
    return if(clientIdExists) {
        listOf(preferences[clientIdKey],preferences[userNameKey])
    } else listOf("","")
}

suspend fun DataStore<Preferences>.saveCredentials(
    contactId: String,
    contactName: String
){
    val clientIdKey = stringPreferencesKey("clientId")
    val userNameKey = stringPreferencesKey("userName")
    val preferences = data.first()
    val clientIdExists = preferences[clientIdKey] != null
    if (!clientIdExists) {
        edit { setting->
            setting[clientIdKey] = contactId
            setting[userNameKey] = contactName
        }
    }
}
