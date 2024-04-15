package com.example.contacttracer.functions

class ApiUtils {
    companion object {
        fun composeAccessToken(tokenType: String, token: String): String {
            return (tokenType + " " + token)
        }
    }
}