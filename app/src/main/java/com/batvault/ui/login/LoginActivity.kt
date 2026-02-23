package com.batvault.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.batvault.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Implement login logic (PIN, Biometric)
    }

    fun onBiometricClick(view: android.view.View) {
        // TODO: Implement biometric authentication
    }
}
