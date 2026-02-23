package com.batvault.security

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricManager as AndroidXBiometricManager
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

class BiometricManager(private val context: Context) {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    fun setupBiometricPrompt(activity: FragmentActivity, onAuthSuccess: () -> Unit, onAuthFailure: () -> Unit) {
        executor = context.mainExecutor

        biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onAuthFailure()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onAuthSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    // Authentication failed but can be retried
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("فتح BatVault")
            .setSubtitle("استخدم بصمتك أو Face ID للدخول")
            .setNegativeButtonText("إلغاء")
            .build()
    }

    fun authenticate() {
        val androidXBiometricManager = AndroidXBiometricManager.from(context)
        if (androidXBiometricManager.canAuthenticate(AndroidXBiometricManager.Authenticators.BIOMETRIC_WEAK or AndroidXBiometricManager.Authenticators.DEVICE_CREDENTIAL) == AndroidXBiometricManager.BIOMETRIC_SUCCESS) {
            biometricPrompt.authenticate(promptInfo)
        } else {
            // Biometric not available or not set up
            // TODO: Handle this case, e.g., fallback to PIN/password
        }
    }
}
