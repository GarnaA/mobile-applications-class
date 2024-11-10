package com.example.mobile_app_hw1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val registerNowLabel = findViewById<TextView>(R.id.registerText)
        registerNowLabel.setOnClickListener {
            val goToRegisterIntent = Intent(this@SignInActivity, RegisterActivity::class.java)
            goToRegisterIntent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            startActivity(goToRegisterIntent)
        }
    }
}
