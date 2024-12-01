package com.example.mobile_app_hw1

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {


    //Test Empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()


        val isEmailValid = credentialsManager.isEmailValid("")


        assertEquals(false, isEmailValid)
    }


    //Test Wrong format email
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()


        val isEmailValid = credentialsManager.isEmailValid("wrongEmailFormat")


        assertEquals(false, isEmailValid)
    }


    //Test Well formated email
    @Test
    fun givenWellFormattedEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()


        val isEmailValid = credentialsManager.isEmailValid("test@te.st")


        assertEquals(true, isEmailValid)
    }


    //Test Empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()


        val isPasswordValid = credentialsManager.isPasswordValid("")


        assertEquals(false, isPasswordValid)
    }


    //Test Filled password
    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()


        val isPasswordValid = credentialsManager.isPasswordValid("Some!password1")

        assertEquals(true, isPasswordValid)
    }

}
