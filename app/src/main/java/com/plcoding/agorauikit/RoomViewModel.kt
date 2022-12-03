package com.plcoding.agorauikit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {

    private val _roomNo = mutableStateOf(TextFieldState())
    val roomNo: State<TextFieldState> = _roomNo

    private val _onJoinEvent = MutableSharedFlow<String>()
    val onJoinEvent = _onJoinEvent.asSharedFlow()

    fun onRoomEnter(name: String) {
        _roomNo.value = roomNo.value.copy(
            text = name
        )
    }

    fun onJoinRoom() {
        if(roomNo.value.text.isBlank()) {
            _roomNo.value = roomNo.value.copy(
                error = "Room can't be empty"
            )
            return
        }
        viewModelScope.launch {
            _onJoinEvent.emit(roomNo.value.text)
        }
    }
}