package com.example.mobile_app_hw1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val registerNowLabel = findViewById<TextView>(R.id.registerText)
        registerNowLabel.setOnClickListener {
            val goToRegisterIntent = Intent(this@SignInActivity, RegisterActivity::class.java)
            goToRegisterIntent.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            )
            startActivity(goToRegisterIntent)
        }

        val emailText = findViewById<TextInputEditText>(R.id.emailInputText)
        val passwordText = findViewById<TextInputEditText>(R.id.passwordInputText)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailInput)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordInput)
        val buttonLogin = findViewById<Button>(R.id.buttonNext)
        val credentialManager = CredentialsManager()

        buttonLogin.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            if (!credentialManager.isEmailValid(email)){
                emailLayout.error = "Wrong Email"
                return@setOnClickListener
            }
            else{
                emailLayout.error = null;
            }
            if(!credentialManager.isPasswordValid(password)) {
                passwordLayout.error = "Wrong Password"
                return@setOnClickListener
            }
            else{
                emailLayout.error = null;
            }

            login(email,password)
        }

    }

    private fun login(email:String,password:String){
        val buttonLogin = findViewById<Button>(R.id.buttonNext)
        val loginErrorPopup = Snackbar.make(buttonLogin,"Wrong email or password",10000)
        val goToMainActivity = Intent(this@SignInActivity,MainActivity::class.java)

        if(CredentialsManager().login(email,password)){
            goToMainActivity.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            )
            startActivity(goToMainActivity)
        }
        else{
            buttonLogin.setError("Wrong password or email!")
            loginErrorPopup.show()
        }
    }
}
