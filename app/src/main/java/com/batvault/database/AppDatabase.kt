package com.batvault.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.batvault.database.dao.NoteDao
import com.batvault.database.dao.PasswordDao
import com.batvault.database.dao.UserDao
import com.batvault.database.entities.Note
import com.batvault.database.entities.Password
import com.batvault.database.entities.User

@Database(entities = [User::class, Password::class, Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun passwordDao(): PasswordDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "batvault_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
