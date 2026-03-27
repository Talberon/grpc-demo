package com.example.androidgrpcclient.webchat

import android.R.attr.name
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import grpc.demo.java.webchat.proto.WebChatGrpcKt
import grpc.demo.java.webchat.proto.WebChatProto
import grpc.demo.java.webchat.proto.chatRoom
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val channel = ManagedChannelBuilder
        .forAddress("10.0.2.2", 9090)
        .usePlaintext() //No encryption
        .build()
    val client = WebChatGrpcKt.WebChatCoroutineStub(channel)
    val chatRoom =
        WebChatProto.ChatRoom.newBuilder().setChatRoomId("My Cool Room For Cool People").build()
    private val _messages = MutableStateFlow<List<WebChatProto.ChatMessage>>(emptyList())
    val messages: StateFlow<List<WebChatProto.ChatMessage>> = _messages

    fun joinSession() {
        viewModelScope.launch {
            client.joinChatRoom(chatRoom).collect { message ->
                _messages.update { current -> current + message } // Append a message to the end of the list
            }
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            if (message.isEmpty()) return@launch
            client.sendMessage(
                WebChatProto.ChatMessage.newBuilder().setMessage(message).setNickname("Andy")
                    .setClientLanguage("Kotlin (Android)").setChatRoom(chatRoom).build()
            )
        }

    }
}