package com.example.samplejetpackcompose.data.remote.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String?,
)