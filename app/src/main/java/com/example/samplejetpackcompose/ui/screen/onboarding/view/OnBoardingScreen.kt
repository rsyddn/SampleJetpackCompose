package com.example.samplejetpackcompose.ui.screen.onboarding.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.samplejetpackcompose.ui.navigation.Routes
import com.example.samplejetpackcompose.ui.screen.onboarding.view_model.OnBoardingViewModel
import com.example.samplejetpackcompose.ui.theme.Purple700
import com.example.samplejetpackcompose.utils.Response
import timber.log.Timber


@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.collectAsState()

    LaunchedEffect(loginState.value is Response.Success) {
        when (
            val result = loginState.value
        ) {
            is Response.Success -> {
                val routes = Routes.HOMESCREEN.path
                navController.navigate(
                    routes.replace("{email}", viewModel.email.value)
                        .replace("{token}", result.data?.token.orEmpty()),

                    )
                viewModel.clearLoginState()
            }
            else -> {

            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = AnnotatedString("Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Monospace))

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Email") },
            value = viewModel.email.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { viewModel.email.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = viewModel.password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { viewModel.password.value = it })

        when (val result = loginState.value) {
            is Response.Error -> {
                Text(text = result.message)
            }
            Response.Loading -> {
                CircularProgressIndicator()
            }
            else -> {

            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.loginApp(viewModel.email.value, viewModel.password.value)
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) { Text(text = "Login") }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = AnnotatedString("Forgot password?"),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}