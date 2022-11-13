package com.example.samplejetpackcompose.utils

sealed interface Response<out T> {
    object Loading: Response<Nothing>
    object Empty: Response<Nothing>

    open class Error(
        val message: String
    ) : Response<Nothing>

    open class Success<T>(
        val data: T? = null
    ) : Response<T>
}
