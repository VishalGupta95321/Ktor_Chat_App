package com.example.ktor_chat_app.presentation.contacts_screen.composables


import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ktor_chat_app.R
import com.example.ktor_chat_app.screen_contact.presentation.contact_list_screen.HomeContactsViewModel
import com.example.ktor_chat_app.screen_contact.presentation.components.AddContactFloatingButton
import com.example.ktor_chat_app.screen_contact.presentation.components.HomeListItem
import com.example.ktor_chat_app.screen_contact.presentation.components.HomeTopAppBar
import com.example.ktor_chat_app.core.utility.Constants.HOME_APP_BAR_TITLE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContactScreen(
    navController: NavHostController,
    viewModel: HomeContactsViewModel = hiltViewModel(),
    navigateToAddContact:()->Unit,
) {
    val activeContactsList = viewModel.contactList.value
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val requestingContact = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()){ isGranted->
        if (isGranted){
            navigateToAddContact()
        }
    }

    Scaffold(
        snackbarHost = {
        SnackbarHost(
            hostState = snackBarHostState
        )},
        topBar = {
            HomeTopAppBar(title = HOME_APP_BAR_TITLE)
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding
            ){
                items(items = activeContactsList){ item->
                   HomeListItem(
                       name = item.name,
                   ){
                       navController.navigate("chat_screen/"+item.userId)
                   }
                }
            }
        },
        floatingActionButton = {
                AddContactFloatingButton(
                    onClick = {
                        requestingContact.launch(
                        Manifest.permission.READ_CONTACTS
                    )},
                    icon = ImageVector.vectorResource(
                        id = R.drawable.add_contact
                    )
                )
        },
        floatingActionButtonPosition = FabPosition.End
    )
}

