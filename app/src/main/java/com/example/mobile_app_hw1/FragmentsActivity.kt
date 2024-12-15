package com.example.mobile_app_hw1

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

class FragmentsActivity : FragmentActivity() {

    private val credentialManager = CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        if (savedInstanceState == null) {
            showFragment(SignInFragment.newInstance())
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentsActivity, fragment)
        }
    }

    fun navigateToRegister() {
        showFragment(RegisterFragment.newInstance())
    }

    fun navigateToLogin() {
        showFragment(SignInFragment.newInstance())
    }

    fun getCredentialsManager(): CredentialsManager {
        return credentialManager
    }
}
