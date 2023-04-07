package com.dipen.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dipen.gmailclone.components.GmailDrawerMenu
import com.dipen.gmailclone.components.GmailFab
import com.dipen.gmailclone.components.HomeAppBar
import com.dipen.gmailclone.components.HomeBottomMenu
import com.dipen.gmailclone.components.MailList
import com.dipen.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GmailApp()
                }
            }
        }
    }
}

@Composable
fun GmailApp() {

    val scaffoldState1 = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val openDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        scaffoldState = scaffoldState1,
        topBar = { HomeAppBar(state = scaffoldState1, scope = coroutineScope, openDialog = openDialog) },
        drawerContent = {
            GmailDrawerMenu(scrollState = scrollState)
        },
        bottomBar = { HomeBottomMenu() },
        floatingActionButton = {
            GmailFab(scrollState = scrollState)
        }) {
        MailList(it, scrollState)
    }

    /*Scaffold(
        state = scaffoldState1,
        topBar = { HomeAppBar(scaffoldState1, coroutineScope) }) {
        it.calculateBottomPadding()
    }*/
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GmailCloneTheme {
        GmailApp()
    }
}