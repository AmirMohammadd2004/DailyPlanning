package com.amir.dailyplanning.model.data


data class ChatMessage(
    val text: String,
    val isUser: Boolean      // true =کاربر  ، false = AI
)
