package com.diary.auth

data class UserSession(val state: String, val token: String)
