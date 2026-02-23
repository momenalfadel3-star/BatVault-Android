package com.batvault.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.batvault.database.entities.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: Long): Note?

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Long)
}
