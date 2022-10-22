package com.example.ktor_chat_app.core.utility

import android.annotation.SuppressLint
import android.app.Activity
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log

@SuppressLint("Range")
 fun Activity.getUserMobileContactList() : List<String>{
    var contactList : List<String> = listOf()
    val cr = contentResolver
    val cur: Cursor? = cr.query(
        ContactsContract.Contacts.CONTENT_URI,
        null, null, null, null
    )
    if ((cur?.count ?: 0) > 0) {
        while (cur != null && cur.moveToNext()) {
            var id: String? = null
            try {
                id = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID)
                )
            }catch (e:Exception){
                Log.e("Error",e.toString())
            }

            var name : String? = null
            try {
                 name = cur.getString(
                    cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )
            }catch (E:Exception){
                Log.e("Error",E.toString())
            }
            if (cur.getInt(
                    cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER
                    )
                ) > 0
            ) {
                val pCur: Cursor? = cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    arrayOf(id),
                    null
                )
                if (pCur != null) {
                    while (pCur.moveToNext()) {
                        var phoneNo: String? = null
                        try {
                            phoneNo = pCur.getString(
                                pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                                )
                            ).replace(" ","")
                        }catch (E:Exception){
                            Log.e("Error",E.toString())
                        }
                        contactList = contactList + phoneNo.toString()
                    }
                }
                pCur?.close()
            }
        }
    }
    cur?.close()
    return contactList
}