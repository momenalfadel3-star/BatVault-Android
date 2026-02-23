package com.batvault.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.batvault.database.entities.Password

@Dao
interface PasswordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(password: Password)

    @Update
    suspend fun updatePassword(password: Password)

    @Query("SELECT * FROM passwords ORDER BY createdAt DESC")
    suspend fun getAllPasswords(): List<Password>

    @Query("SELECT * FROM passwords WHERE id = :passwordId")
    suspend fun getPasswordById(passwordId: Long): Password?

    @Query("DELETE FROM passwords WHERE id = :passwordId")
    suspend fun deletePassword(passwordId: Long)
}
