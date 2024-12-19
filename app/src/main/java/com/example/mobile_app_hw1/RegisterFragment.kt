package com.example.mobile_app_hw1

import android.content.Context
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

class RegisterFragment : Fragment() {

    private var parentActivity: FragmentsActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentsActivity) {
            parentActivity = context
        } else {
            throw IllegalStateException("$context must be an instance of FragmentsActivity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val signInLabel = view.findViewById<TextView>(R.id.loginText)
        val emailText = view.findViewById<TextInputEditText>(R.id.emailInputText)
        val passwordText = view.findViewById<TextInputEditText>(R.id.passwordInputText)
        val emailLayout = view.findViewById<TextInputLayout>(R.id.emailInput)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.passwordInput)
        val buttonRegister = view.findViewById<Button>(R.id.buttonNext)

        signInLabel.setOnClickListener {
            (activity as FragmentsActivity).navigateToLogin()
        }

        buttonRegister.setOnClickListener {
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

            if (CredentialsManager.register(email, password)) {
                Snackbar.make(
                    view,
                    "Registration Successful! You can now log in.",
                    Snackbar.LENGTH_SHORT
                ).show()
                (activity as FragmentsActivity).navigateToLogin()
            } else {
                Snackbar.make(view, "Registration Failed!", Snackbar.LENGTH_SHORT).show()
            }
        }


        return view
    }

    override fun onDetach() {
        super.onDetach()
        parentActivity = null
    }
}
