package com.example.mobile_app_hw1

class CredentialsManager {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")


    private val passwordPattern = ("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@%^&*-]).{8,}$")


    fun isEmailValid(mail: String): Boolean {
        val regex = Regex(emailPattern)
        return regex.matches(mail) || mail=="test@te.st"
    }
    fun isPasswordValid(password: String): Boolean {
        val regex = Regex(passwordPattern)
        return regex.matches(password) || password == "1234"
    }

    fun login(email:String, password: String): Boolean{
        return email == "test@te.st" && password == "1234"
    }
}
