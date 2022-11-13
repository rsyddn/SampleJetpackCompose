package com.example.samplejetpackcompose.ui.screen.onboarding.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplejetpackcompose.domain.model.Login
import com.example.samplejetpackcompose.domain.usecase.LoginUseCase
import com.example.samplejetpackcompose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val loginState = MutableStateFlow<Response<Login>>(Response.Empty)

    fun loginApp(email: String, password: String) {
        viewModelScope.launch {
            loginState.value = Response.Loading
            loginState.value = loginUseCase.login(email, password)
        }
    }

    fun clearLoginState(){
        loginState.value = Response.Empty
    }
}