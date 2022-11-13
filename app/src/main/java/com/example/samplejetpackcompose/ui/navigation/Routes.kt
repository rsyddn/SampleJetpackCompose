package com.example.samplejetpackcompose.ui.navigation

enum class Routes(val path: String) {
    ONBOARDING("onboarding"),
    HOMESCREEN("home/{email}/{token}")
}