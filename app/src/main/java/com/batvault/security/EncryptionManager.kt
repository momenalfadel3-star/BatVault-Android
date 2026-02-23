package com.batvault.security

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File

class EncryptionManager(private val context: Context) {
    
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    
    fun encryptData(data: String, fileName: String): String {
        val encryptedFile = EncryptedFile.Builder(
            context,
            File(context.filesDir, fileName),
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
        
        encryptedFile.openFileOutput().use {\n            it.write(data.toByteArray())
        }
        return "encrypted"
    }
    
    fun decryptData(fileName: String): String {
        val encryptedFile = EncryptedFile.Builder(
            context,
            File(context.filesDir, fileName),
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
        
        return encryptedFile.openFileInput().bufferedReader().use { it.readText() }
    }
}
