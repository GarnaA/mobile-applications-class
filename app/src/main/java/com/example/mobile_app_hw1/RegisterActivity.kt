package com.example.mobile_app_hw1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private val credentialManager = CredentialsManager

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val emailText = findViewById<TextInputEditText>(R.id.emailInputText)
        val passwordText = findViewById<TextInputEditText>(R.id.passwordInputText)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailInput)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordInput)
        val buttonRegister = findViewById<Button>(R.id.buttonNext)
        val signInLabel = findViewById<TextView>(R.id.loginText)


        signInLabel.setOnClickListener {
            navigateToSignIn()
        }

        buttonRegister.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            val registrationSuccessful = credentialManager.register(email, password)

            if (!credentialManager.isEmailValid(email)) {
                emailLayout.error = "Invalid Email"
                return@setOnClickListener
            } else {
                emailLayout.error = null
            }

            if (!credentialManager.isPasswordValid(password)) {
                passwordLayout.error = "Weak Password"
                return@setOnClickListener
            } else {
                passwordLayout.error = null
            }

            if (registrationSuccessful) {
                Toast.makeText(this, "Registration is Successful!", Toast.LENGTH_LONG).show()
                navigateToSignIn()
            } else {
                emailLayout.error = "Email is already registered"
            }
        }
    }

    private fun navigateToSignIn() {
        val goToSignInIntent = Intent(this@RegisterActivity, SignInActivity::class.java)
        goToSignInIntent.setFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        )
        startActivity(goToSignInIntent)
    }
}
