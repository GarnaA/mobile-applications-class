package com.example.mobile_app_hw1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInFragment : Fragment() {

    companion object {
        fun newInstance(): SignInFragment = SignInFragment()
    }

    private lateinit var credentialManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        credentialManager = (activity as FragmentsActivity).getCredentialsManager()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val registerNowLabel = view.findViewById<TextView>(R.id.registerText)
        val emailText = view.findViewById<TextInputEditText>(R.id.emailInputText)
        val passwordText = view.findViewById<TextInputEditText>(R.id.passwordInputText)
        val emailLayout = view.findViewById<TextInputLayout>(R.id.emailInput)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.passwordInput)
        val buttonLogin = view.findViewById<Button>(R.id.buttonNext)

        registerNowLabel.setOnClickListener {
            (activity as FragmentsActivity).navigateToRegister()
        }

        buttonLogin.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            if (!CredentialsManager.isEmailValid(email)) {
                emailLayout.error = "Invalid Email"
                return@setOnClickListener
            } else {
                emailLayout.error = null
            }

            if (!CredentialsManager.isPasswordValid(password)) {
                passwordLayout.error = "Invalid Password"
                return@setOnClickListener
            } else {
                passwordLayout.error = null
            }

            if (CredentialsManager.login(email, password)) {
                Snackbar.make(view, "Login Successful!", Snackbar.LENGTH_SHORT).show()
                val goToMainActivity = Intent(activity, MainActivity::class.java)
                startActivity(goToMainActivity)
            } else {
                Snackbar.make(view, "Login Failed: Wrong credentials", Snackbar.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
