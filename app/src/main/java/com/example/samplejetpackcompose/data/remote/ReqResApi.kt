package com.example.samplejetpackcompose.data.remote

import com.example.samplejetpackcompose.data.remote.request.LoginRequest
import com.example.samplejetpackcompose.data.remote.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqResApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

}