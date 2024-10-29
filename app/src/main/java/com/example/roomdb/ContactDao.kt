package com.example.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface ContactDao {
    @Upsert
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY id ASC")
    fun getAllContacts():LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id LIMIT 1")
    suspend fun getContactById(id: Int): Contact?

    @Delete
    suspend fun delete(contact: Contact)
}