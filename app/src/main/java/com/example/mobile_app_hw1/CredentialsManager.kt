package com.example.mobile_app_hw1

class CredentialsManager private constructor() {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")
    private val passwordPattern = ("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@%^&*-]).{8,}$")

    private val credentialsMap = mutableMapOf<String, String>()

    init {
        credentialsMap["test@te.st".lowercase()] = "1234"
    }

    fun isEmailValid(mail: String): Boolean {
        val regex = Regex(emailPattern)
        return regex.matches(mail)
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }

    fun register(email: String, password: String): Boolean {
        val lowerEmail = email.lowercase()
        if (credentialsMap.containsKey(lowerEmail)) {
            return false
        }
        credentialsMap[lowerEmail] = password
        return true
    }

    fun login(email: String, password: String): Boolean {
        val lowerEmail = email.lowercase()
        return credentialsMap[lowerEmail] == password
    }

    companion object {
        val instance: CredentialsManager by lazy { CredentialsManager() }
    }
}
