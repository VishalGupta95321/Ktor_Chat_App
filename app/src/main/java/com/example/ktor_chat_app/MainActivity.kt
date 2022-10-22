package com.example.ktor_chat_app.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.ktor_chat_app.data.local.ChatDatabase
import com.example.ktor_chat_app.data.remote.model.RegisterUserRequest
import com.example.ktor_chat_app.data.remote.webScoketApi.ChatApi
import com.example.ktor_chat_app.navgraph.HomeGraph
import com.example.ktor_chat_app.presentation.RootNavGraph
import com.example.ktor_chat_app.ui.theme.Ktor_Chat_AppTheme
import com.example.ktor_chat_app.utility.clientId
import com.example.ktor_chat_app.utility.dataStore
import com.example.ktor_chat_app.utility.saveUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var chatApi: ChatApi

    @Inject
    lateinit var database: ChatDatabase


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : AppViewModel by viewModels()
        viewModel.observeBaseModels()
        viewModel.observeConnectionEvent()

// +919532169104
        // +919682824770
//                 dataStore.clientId()
        //}
       // Log.d("client", "doe" + clientId.toString())
        lifecycleScope.launch {
            database.chatDao().updateUserActiveStatus(userId = "+919532169104")
            Log.d("kkkkk","got it")
            dataStore.saveUser("+918303168968")
            //dataStore.saveUser("+919532169104")
            Toast.makeText(this@MainActivity,dataStore.clientId(),Toast.LENGTH_LONG).show()
            delay(2000L)
            chatApi.sendBaseModel(RegisterUserRequest("ajhfjga",dataStore.clientId()))
        }


        var clientId : String? = null
        runBlocking {
            clientId = dataStore.clientId()
            Log.d("kkk",clientId.toString())
        }
        setContent {
            MaterialTheme {
                window?.statusBarColor = Color(0xFF03DAC5).toArgb()
            }
            val navController = rememberNavController()


            Ktor_Chat_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!clientId.isNullOrEmpty()){
                       HomeGraph(navController = navController)
                    }else {RootNavGraph(navController = navController)}
                }
            }
        }
    }
}


//
//
//
//val data2 = ChatMessage(
//    "777",
//    "777",
//    "JLNKLN",
//    "KJLNBNK",
//    8976689686899
//)
//list = list.plus(data2)
//list  = list.plus(data)
//println(list.toString())
//val user = RegisterUserRequest(
//    "jkbkjb","777"
//)
//
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SimpleScaffoldWithTopBar(
//    chatList: List<ChatMessage>,
//    nav: NavHostController = rememberNavController(),
//    contList: List<User>
//) {
//
//    val navBackStackEntry by nav.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//    val colors = listOf(
//        Color(0xFFffd7d7.toInt()),
//        Color(0xFFffe9d6.toInt()),
//        Color(0xFFfffbd0.toInt()),
//        Color(0xFFe3ffd9.toInt()),
//        Color(0xFFd0fff8.toInt())
//    )
//    Scaffold(
//
//        topBar = {
//
//            if (true){
//                ChatTopAppBar(chatApi)
//            }
//            //AddContactTopAppBar(chatApi)
//        },
//        content = { innerPadding ->
//            NavGraph(nav = nav, p = innerPadding, chatList, contList = contList)
//            // HomeUserList(list = list, padding = innerPadding)
//            //ChatList(list = list, clientId = clientId,innerPadding)
//        }, //bottomBar = { NavigationBarSample() },
////            floatingActionButton = {
////                                   HomeFloatingBar()
//////                if(currentRoute == "matching" || currentRoute == "chat"|| currentRoute == "profile") {
//////                    FloatingActionButtonSample1() }
////            },
////            floatingActionButtonPosition = FabPosition.End,
//        bottomBar = {
//            if (currentRoute == "chat1") {
//                ChatNavigationBar(database, chatApi, "777", "888")
//            } else ChatNavigationBar(database, chatApi, "888", "777")
//        },
//    )
//}
//
//fun observeEvents() {
//    lifecycleScope.launch(dispatchers.io) {
//        chatApi.observeEvents().collect { event ->
//
//            when (event) {
//                is WebSocket.Event.OnConnectionOpened<*> -> {
//                    Log.d("ws", "conected")
//                    chatApi.sendBaseModel(user1)
//                    chatApi.sendBaseModel(user2)
//                    chatApi.sendBaseModel(user3)
//                }
//                is WebSocket.Event.OnConnectionClosing -> {
//                    Log.d("ws", "closing")
//                }
//                is WebSocket.Event.OnConnectionClosed -> {
//                    Log.d("ws", "closed")
//
//                }
//                else -> {}
//            }
//        }
//    }
//}
//
//fun observeBaseModels() {
//    lifecycleScope.launch(dispatchers.io) {
//        chatApi.observeBaseModels().collect { dataa ->
//            when (dataa) {
//
//                is RegisterUserRequest -> {
//                    println("cddsc")
//                }
//
//                is ChatMessage -> {
//                    // Log.d("chat",dataa.toString())
//                    lifecycleScope.launch {
//                        try {
//                            database.chatDao().insertChat(dataa.asDataBaseModel("khvbkjb"))
//                        } catch (e: Exception) {
//                            Log.d("up", "Sending delivered message failed")
//                        }
//                    }
//                    lifecycleScope.launch {
//                        if (!dataa.delivered) {
//                            try {
//                                chatApi.sendBaseModel(
//                                    MessageDelivered(
//                                        dataa.messageId,
//                                        dataa.fromId
//                                    )
//                                )
//                                Log.d("up", "Sending delivered message done")
//
//                            } catch (e: Exception) {
//                                Log.d("up", "Sending delivered message failed")
//                            }
//                        }
//                    }
//                }
//                is MessageSeen -> {
//
//                    //Log.d("up","sid = ${dataa.messageId}")
//                    updateJob?.join()
//                    // Log.d("xyz","got message seen")
//                    updateJob = lifecycleScope.launch {
//                        try {
//                            val message = database.chatDao().getChatWithId(dataa.messageId)
//                            message.messageSeen = true
//                            Log.d("sup", message.toString())
//
//                            database.chatDao().updateChat(message)
//                            Log.d("sup", "updated")
//                        } catch (e: Exception) {
//                            Log.d("sup", "sfailed ")
//                        }
//                    }
//                }
//
//                is MessageDelivered -> {
//                    updateJob?.join()
//                    // Log.d("up","did = ${dataa.messageId}")
//
//                    // Log.d("xyz","got message delivered")
//
//                    updateJob = lifecycleScope.launch {
//                        try {
//                            val message = database.chatDao().getChatWithId(dataa.messageId)
//                            message.delivered = true
//                            Log.d("dup", message.toString())
//                            database.chatDao().updateChat(message)
//                            Log.d("dup", "updated")
//                        } catch (e: Exception) {
//                            Log.d("dup", "dfailed ")
//                        }
//                    }
//                }
//                is User -> {
//                    println("got user")
//                    updateUserJob?.join()
//                    updateUserJob = lifecycleScope.launch {
//                        database.chatDao().insertContact(dataa.asDataBaseModel())
//                    }
//                }
//            }
//        }
//    }
//}

//@Composable
//fun NavGraph(
//    nav: NavHostController,
//    p: PaddingValues,
//    chatList: List<ChatMessage>,
//    contList: List<User>
//) {
//    NavHost(
//        nav, startDestination = "home"
//        //NavHost(navController, startDestination = "login"
//    ) {
//        composable(route = "home") {
//            home(nav, p)
//        }
//        composable(route = "chat1") {
//            ChatList(chatApi, nav, chatList, "777", p)
//        }
//        composable(route = "chat2") {
//            ChatList(chatApi, nav, chatList, "888", p)
//        }
//        composable(route = "addContacts") {
//
//            AddContactList(list = contList, padding = p)
//        }
//    }
//}

//
//@Composable
//fun home(nav: NavHostController, p: PaddingValues) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .padding(p)
//    ) {
//        OutlinedButton(onClick = {
//            try {            // try catch wont work in socket
//                chatApi.sendBaseModel(user1)
//            }catch (e:IOException){
//                Log.d("kkkk",e.message.toString())
//            }
//
//            nav.navigate("chat1")
//        }) {
//            Text(text = "User 1")
//        }
//        OutlinedButton(onClick = {
//            chatApi.sendBaseModel(user2)
//            // chatApi.sendBaseModel(user3)
//            // getContactList(chatApi)
//            //GetAllContacts()
//            nav.navigate("chat2")
//        }) {
//            Text(text = "User 2")
//        }
//    }
//}

//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun RetriveOtp(){
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            Arrangement.SpaceEvenly,
//            Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.login_screen_logo),
//                contentDescription = "chat_logo"
//            )
//
//            val t by remember {
//                mutableStateOf("")
//            }
//            OutlinedTextField(
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color(0xFF03DAC5),
//                    unfocusedBorderColor = Color.Black
//                ),
//                modifier = Modifier
//                    .width(350.dp)
//                    .heightIn(100.dp)
//                    .padding(5.dp),
//                value = ,
//                onValueChange = { },
//                label = { Text("Enter Otp", color = Color(0xFF03DAC5)) },
//                maxLines = 5,
//            )
//
//            OutlinedButton(onClick = { /*TODO*/ }) {
//                Image(
//                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_east_24),
//                    contentDescription = "next"
//                )
//            }
//        }
//    }
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginScreen() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        Arrangement.Top,
//        Alignment.CenterHorizontally
//    ) {
//        Image(
//            modifier = Modifier.padding(top = 100.dp),
//            painter = painterResource(id = R.drawable.login_screen_logo),
//            contentDescription = "chat_logo"
//        )
//
//        Spacer(modifier = Modifier.height(160.dp))
//
//        val t by remember {
//            mutableStateOf("")
//        }
//        OutlinedTextField(
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0xFF03DAC5),
//                unfocusedBorderColor = Color.Black,
//                containerColor =  Color(0xFF03DAC5)
//            ),
//            modifier = Modifier
//                .width(350.dp)
//                .height(90.dp)
//                .padding(5.dp),
//            value = "",
//            onValueChange = { },
//            label = { Text("Phone No..", color = Color.Black) },
//            maxLines = 5,
//        )
//        OutlinedTextField(
//
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0xFF03DAC5),
//                unfocusedBorderColor = Color.Black,
//                containerColor =  Color(0xFF03DAC5)
//            ),
//            modifier = Modifier
//                .width(350.dp)
//                .height(90.dp)
//                .padding(5.dp),
//            value = "",
//            onValueChange = { },
//            label = { Text("Name..", color = Color.Black) },
//            maxLines = 5,
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        OutlinedButton(onClick = {
//        }, colors = ButtonDefaults.outlinedButtonColors(
//            containerColor = Color(0xFF03DAC5)
//        )
//        ) {
//            Image(
//                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_east_24),
//                contentDescription = "next"
//            )
//        }
//    }
//}
//
//
//@OptIn(DelicateCoroutinesApi::class)
//fun sendMessageSeen(messageId: String, toId: String) {
//
//    GlobalScope.launch {
//        try {
//            chatApi.sendBaseModel(MessageSeen(messageId, toId))
//
//        } catch (e: Exception) {
//            Log.d("xyz", "Sending seen message failed")
//        }
//    }
//}
//
//
//
//
//
//@Composable
//@Preview(showBackground = true)
//fun DefaultPreview() {
//    val incoming = ChatMessage(
//        fromId = "777",
//        toId = "kjbkj",
//        "hellacLKNCDScjkbacacdo",
//        "svddvdv",
//        870979790,
//        messageSeen = true,
//        delivered = true,
//        deleted = true
//    )
//    val outgoing = ChatMessage(
//        fromId = "7737",
//        toId = "kjbkj",
//        "hellacLKNCDScjkbjkbkjbjbjjbjbjkbjbjbjbbjbjbjbjjbbjbjbjbbacacdo",
//        "svddvdv",
//        870979790,
//        delivered = true
//    )
//
//    val list = listOf(
//        incoming,
//        outgoing,
//        incoming,
//        outgoing,
//        incoming,
//        outgoing,
//        incoming,
//        outgoing,
//        incoming,
//        outgoing
//    )
//
//    Ktor_Chat_AppTheme {
//
//        // GenerateOtpScreen()
//        //LoginScreen()
/////            RetriveOtp()
//        //ChatNavigationBar()
//        //  SimpleScaffoldWithTopBar()
//    }
//}
//
//
//@SuppressLint("Range", "Recycle")
//fun GetAllContacts() {
//    val cr = contentResolver
//    val cur: Cursor? = cr.query(
//        ContactsContract.Contacts.CONTENT_URI,
//        null, null, null, null
//    )
//    if (cur != null) {
//        if (cur.count > 0) {
//            while (cur.moveToNext()) {
//                val id: String =
//                    cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
//                if (cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
//                        .toInt() > 0
//                ) {
//                    val pCur: Cursor? = cr.query(
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                        arrayOf(id),
//                        null
//                    )
//                    if (pCur != null) {
//                        while (pCur.moveToNext()) {
//                            val phone: String = pCur.getString(
//                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
//                            )
//                            // contactsNumber.add(phone)
//                            Log.d("123", phone)
//                        }
//                    }
//                    pCur?.close()
//                }
//            }
//        }
//    }
//}

@Preview
@Composable
fun aa(){
    Box {

    }
}
