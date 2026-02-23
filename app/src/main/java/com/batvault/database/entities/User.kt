package com.batvault.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int = 1,
    val masterPasswordHash: String,
    val pinCode: String, // مشفر
    val securityQuestion: String,
    val securityAnswer: String
)
