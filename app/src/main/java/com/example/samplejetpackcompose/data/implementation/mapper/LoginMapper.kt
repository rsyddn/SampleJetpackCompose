package com.example.samplejetpackcompose.data.implementation.mapper

import com.example.samplejetpackcompose.data.remote.responses.LoginResponse
import com.example.samplejetpackcompose.domain.model.Login

fun LoginResponse.toLogin(): Login {
    return Login(
        token = token.orEmpty()
    )
}