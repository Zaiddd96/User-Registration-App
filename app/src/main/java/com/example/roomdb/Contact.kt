package com.example.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val number:String,
    val email:String
)
