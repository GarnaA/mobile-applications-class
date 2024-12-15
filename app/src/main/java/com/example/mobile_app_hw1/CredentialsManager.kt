package com.example.mobile_app_hw1

object CredentialsManager {

    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")
    private val passwordPattern = ("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@%^&*-]).{8,}$")

    private val credentialsMap = mutableMapOf<String, String>()

    fun isEmailValid(mail: String): Boolean {
        val regex = Regex(emailPattern)
        return regex.matches(mail)
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }

    fun register(email: String, password: String): Boolean {
        if (isEmailValid(email) && isPasswordValid(password)) {
            credentialsMap[email] = password
            return true
        }
        return false
    }

    fun login(email: String, password: String): Boolean {
        return credentialsMap[email] == password
    }
}
