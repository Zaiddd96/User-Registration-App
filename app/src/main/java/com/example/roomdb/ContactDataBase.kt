package com.example.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDataBase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDataBase? = null

        fun getDataBase(context: Context): ContactDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDataBase::class.java,
                    "Contact"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}