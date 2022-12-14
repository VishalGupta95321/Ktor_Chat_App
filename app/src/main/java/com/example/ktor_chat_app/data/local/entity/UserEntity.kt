package com.example.ktor_chat_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ktor_chat_app.core.utility.Constants.TABLE_NAME2
import com.example.ktor_chat_app.web_socket.data.remote.responce.User

@Entity(tableName = TABLE_NAME2)
data class UserEntity (
    @PrimaryKey(autoGenerate = false )
    val userId:String,
    val name: String,
    val about: String? = null,
    var lastOnline : Long? = null,
    var profilePhotoUri : String? = null,
    var active : Boolean = false
)

fun UserEntity.asDomainModel() = User (
     userId = userId, name = name, about = about, lastOnline = lastOnline, profilePhotoUri = profilePhotoUri
)

fun List<UserEntity>.asDomainModel() : List<User> {
    return map { User(
        userId = it.userId,
        name = it.name,
        about = it.about,
        lastOnline = it.lastOnline,
        profilePhotoUri = it.profilePhotoUri
    ) }
}