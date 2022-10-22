package com.example.ktor_chat_app.core.data.local

import androidx.room.*
import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat (chat:ChatMessageEntity)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChat(chat:ChatMessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact:UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact:UserEntity)

    @Delete
    suspend fun deleteContact(contact: UserEntity)

    @Query("SELECT * FROM USER_LIST WHERE active = 1")
    fun getAllActiveContacts():Flow<List<UserEntity>>

    @Query("SELECT * FROM USER_LIST")
    fun getAllAvailableContacts():Flow<List<UserEntity>>

    @Query("SELECT * FROM USER_LIST WHERE userId = :userId")
    fun getContactWithId(userId:String):Flow<UserEntity>

    @Query("SELECT * FROM CHAT_LIST")
    fun getAllChat(): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM CHAT_LIST WHERE  messageId = :messageId")
    suspend fun getChatWithId(messageId:String):ChatMessageEntity

    @Query("select * from chat_list where roomId = :roomId")
    fun getAllChatsByRoom(roomId:String):Flow<List<ChatMessageEntity>>

    @Query("delete from chat_list where roomId = :roomId")
    suspend fun deleteAllChatsByRoom(roomId:String)

    @Query("update chat_list set messageSeen =:messageSeen where messageId =:messageId")
    suspend fun updateMessageSeen(messageSeen:Boolean=true,messageId:String)

    @Query("update chat_list set delivered =:messageDelivered where messageId =:messageId")
    suspend fun updateMessageDelivered(messageDelivered:Boolean=true,messageId:String)

    @Query("update chat_list set deleted =:messageDeleted where messageId =:messageId")
    suspend fun updateMessageDeleted(messageDeleted:Boolean=true,messageId:String)

    @Query("update user_list set active = :isActive where userId = :userId")
    suspend fun updateUserActiveStatus(isActive:Boolean=true,userId:String)

    @Query("SELECT * FROM USER_LIST WHERE name LIKE  '%' || :searchQuery || '%' and active = 1 ORDER BY name ASC")
    fun searchActiveContacts(searchQuery : String) : Flow<List<UserEntity>>

}