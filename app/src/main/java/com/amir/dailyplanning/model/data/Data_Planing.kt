package com.amir.dailyplanning.model.data

import com.google.gson.annotations.SerializedName

data class Data_Planing(
    @SerializedName("status")
    val status: Int,
    @SerializedName("result")
    val result: String,
    @SerializedName("by")
    val `by`: String,
    @SerializedName("channel")
    val channel: String,
    @SerializedName("website")
    val website: String
)