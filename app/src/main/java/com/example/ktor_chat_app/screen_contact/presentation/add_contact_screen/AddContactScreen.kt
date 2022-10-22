package com.example.ktor_chat_app.presentation.contacts_screen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ktor_chat_app.main.MainActivity
import com.example.ktor_chat_app.R
import com.example.ktor_chat_app.screen_contact.presentation.add_contact_screen.AddContactEvent
import com.example.ktor_chat_app.screen_contact.presentation.add_contact_screen.AddContactsViewModel
import com.example.ktor_chat_app.screen_contact.presentation.components.AddContactFloatingButton
import com.example.ktor_chat_app.screen_contact.presentation.components.HomeListItem
import com.example.ktor_chat_app.screen_contact.presentation.components.HomeTopAppBar
import com.example.ktor_chat_app.core.utility.Constants.ADD_CONTACT_APP_BAR_TITLE
import com.example.ktor_chat_app.core.utility.getUserMobileContactList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    navController: NavHostController,
    viewModel: AddContactsViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as MainActivity
    val snackBarHostState = remember { SnackbarHostState() }
    val availableContactsList = viewModel.availableContacts.value
    val scope = rememberCoroutineScope()

    LaunchedEffect(true){
        scope.launch {
            delay(2000L)
            viewModel.onEvent(AddContactEvent.AddContacts(activity.getUserMobileContactList()))
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            ) },
        topBar = {
            HomeTopAppBar(title = ADD_CONTACT_APP_BAR_TITLE)
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding
            ){
                items(items = availableContactsList){ item->
                    HomeListItem(name = item.name) {
                        navController.navigate("chat_screen/${item.userId}")
                    }
                }
            }
        },
        floatingActionButton = {
            AddContactFloatingButton(
                onClick = {},
                icon = ImageVector.vectorResource(
                    id = R.drawable.share_icon
                )
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
}