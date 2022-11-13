package com.example.samplejetpackcompose.domain.repository

import com.example.samplejetpackcompose.domain.model.Login
import com.example.samplejetpackcompose.utils.Response

interface ReqResRepository {
    /** Do Request Login to API Service **/
    suspend fun login(email: String, password: String) : Response<Login>
}