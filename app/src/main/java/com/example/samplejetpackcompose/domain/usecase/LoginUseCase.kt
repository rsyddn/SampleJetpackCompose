package com.example.samplejetpackcompose.domain.usecase

import com.example.samplejetpackcompose.domain.model.Login
import com.example.samplejetpackcompose.domain.repository.ReqResRepository
import com.example.samplejetpackcompose.utils.Response

class LoginUseCase(
    private val repository: ReqResRepository
) {
    suspend fun login(email: String, password: String): Response<Login> {
        return repository.login(email, password)
    }
}