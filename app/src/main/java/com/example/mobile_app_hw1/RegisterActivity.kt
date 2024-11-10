package com.example.mobile_app_hw1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signInLabel = findViewById<TextView>(R.id.loginText)
        signInLabel.setOnClickListener {
            val goToSignInIntent = Intent(this@RegisterActivity, SignInActivity::class.java)
            goToSignInIntent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            startActivity(goToSignInIntent)
        }
    }
}
