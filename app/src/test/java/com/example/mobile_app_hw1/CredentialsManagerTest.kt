package com.example.mobile_app_hw1

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    //Test Empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager.instance
        val isEmailValid = credentialsManager.isEmailValid("")

        assertEquals(false, isEmailValid)
    }

    //Test Wrong format email
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager.instance
        val isEmailValid = credentialsManager.isEmailValid("wrongEmailFormat")

        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenWellFormattedEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager.instance
        val isEmailValid = credentialsManager.isEmailValid("test@te.st")

        assertEquals(true, isEmailValid)
    }

    //Test Empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager.instance
        val isPasswordValid = credentialsManager.isPasswordValid("")

        assertEquals(false, isPasswordValid)
    }

    //Test Filled password
    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager.instance
        val isPasswordValid = credentialsManager.isPasswordValid("Some!password1")

        assertEquals(true, isPasswordValid)
    }

    @Test
    fun givenNewEmailAndPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager.instance
        val isRegistered = credentialsManager.register("newuser@example.com", "Password@123")

        assertEquals(true, isRegistered)
    }

    @Test
    fun givenRegisteredEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager.instance

        credentialsManager.register("example@gmail.com", "!Password123")

        val testLogin = credentialsManager.login("example@gmail.com", "!Password123")

        assertEquals(true, testLogin)
    }

    @Test
    fun givenUniqueEmails_thenReturnTrue() {
        val credentialsManager = CredentialsManager.instance

        val isRegistered1 = credentialsManager.register("user1@example.com", "!Password123")
        val isRegistered2 = credentialsManager.register("user2@example.com", "!Password123")

        assertEquals(true, isRegistered1)
        assertEquals(true, isRegistered2)
    }

    @Test
    fun givenRegisteredEmailWithDifferentCase_thenLoginSuccessfully() {
        val credentialsManager = CredentialsManager.instance

        credentialsManager.register("another@gmail.com", "!Email15102014")

        val canLogin = credentialsManager.login("ANOTHER@gmail.com", "!Email15102014")

        assertEquals(true, canLogin)
    }
}
