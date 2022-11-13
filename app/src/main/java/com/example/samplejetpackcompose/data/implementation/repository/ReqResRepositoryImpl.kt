package com.example.samplejetpackcompose.data.implementation.repository

import com.example.samplejetpackcompose.data.implementation.mapper.toLogin
import com.example.samplejetpackcompose.data.remote.ReqResApi
import com.example.samplejetpackcompose.data.remote.request.LoginRequest
import com.example.samplejetpackcompose.data.remote.responses.ErrorResponse
import com.example.samplejetpackcompose.domain.model.Login
import com.example.samplejetpackcompose.domain.repository.ReqResRepository
import com.example.samplejetpackcompose.utils.Response
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ReqResRepositoryImpl(
    private val reqResApi: ReqResApi
) : ReqResRepository {
    override suspend fun login(email: String, password: String): Response<Login> {
        return withContext(context = Dispatchers.IO) {
            try {
                val response = reqResApi.login(
                    loginRequest = LoginRequest(
                        email = email,
                        password = password,
                    )
                )
                when {
                    response.token.isNullOrEmpty() -> Response.Error(message = "Gagal Login")
                    else -> Response.Success(
                        data = response.toLogin()
                    )
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val error = Gson().fromJson(
                            e.response()?.errorBody()?.string().orEmpty(),
                            ErrorResponse::class.java,
                        )

                        Response.Error(message = error?.error ?: e.message())
                    }
                    else -> {
                        Response.Error(message = e.message ?: "Unidentified Error")
                    }
                }
            }
        }
    }
}