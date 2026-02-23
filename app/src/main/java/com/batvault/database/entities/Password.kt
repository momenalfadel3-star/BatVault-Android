package com.batvault.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class Password(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val siteName: String,
    val url: String,
    val email: String,
    val username: String,
    val password: String, // مشفر
    val notes: String,
    val category: String,
    val createdAt: Long = System.currentTimeMillis()
)
