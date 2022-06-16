package com.mrmar.airfryer.core.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrmar.airfryer.core.ui.theme.Purple700

@Composable
fun AppBarScreen(
    title: String,
    errorMessage: String? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    isBackAvailable: Boolean = false,
    onBackPressed: (() -> Unit)? = null,
    logoutButton: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { floatingActionButton?.invoke() },
        topBar = {
            TopAppNavigationBar(
                title,
                isBackAvailable,
                onBackPressed,
                logoutButton
            )
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = it.calculateBottomPadding()
                )
        ) {
            errorMessage?.let { message -> TopSnackbar(message = message) }
            content()
        }
    }

}

@Composable
fun TopAppNavigationBar(
    title: String,
    isBackAvailable: Boolean,
    onBackPressed: (() -> Unit)?,
    logoutButton: (() -> Unit)?
) {

    TopAppBar(
        elevation = 8.dp,
        title = {
            Text(title)
        },
        backgroundColor = Purple700,
        navigationIcon = visibility(isBackAvailable) {
            {
                IconButton(onClick = { onBackPressed?.invoke() }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        }, actions = {
            visibility(condition = logoutButton != null) {
                IconButton(onClick = { logoutButton?.invoke() }) {
                    Icon(Icons.Filled.ExitToApp, null)
                }
            }
        })
}
