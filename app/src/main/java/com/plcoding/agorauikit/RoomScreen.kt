package com.plcoding.agorauikit

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RoomScreen(
    onRoomNavigate: (String) -> Unit,
    roomViewModel: RoomViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    LaunchedEffect(key1 = true) {
        roomViewModel.onJoinEvent.collectLatest { name ->
            onRoomNavigate("video_screen/$name")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        TextField(
            value = roomViewModel.roomNo.value.text,
            onValueChange = roomViewModel::onRoomEnter,
            modifier = Modifier.fillMaxWidth(),
            isError = roomViewModel.roomNo.value.error != null,
            placeholder = {
                Text(text = "Enter a room name")
            }
        )
        roomViewModel.roomNo.value.error?.let {
            Text(text = it, color = MaterialTheme.colors.error)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = roomViewModel::onJoinRoom) {
            Text(text = "Join")
        }
    }
}