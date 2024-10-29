package com.example.roomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.LiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var contactdatabase: ContactDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        contactdatabase = ContactDataBase.getDataBase(this)
        setContent {
            val allContacts: LiveData<List<Contact>> = contactdatabase.contactDao().getAllContacts()

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "MainScreen") {
                composable("MainScreen"){
                    MainScreen(onSaveClick = {
                            name, number, mail -> navController.navigate(
                        "SecondScreen/$name/$number/$mail"
                            )
                    },
                        saveContact = {name, number, mail -> SaveContact(name, number, mail)},
                        checkRegistration = {
                                name, number, mail -> navController.navigate(
                            "SecondScreen/$name/$number/$mail"
                        )
                        }
                    )
                }
                composable("SecondScreen/{name}/{number}/{mail}"){
                    SecondScreen(
                        contacts = allContacts,
                        onDeleteClick = { id -> DeleteContact(id) })
                }
            }
        }
    }
    private fun SaveContact(name:String, number:String, mail:String){
        val contact = Contact(name = name, number = number, email = mail )
        CoroutineScope(Dispatchers.IO).launch {
            contactdatabase.contactDao().insert(contact)
        }
    }
    private fun DeleteContact(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val contact = contactdatabase.contactDao().getContactById(id)
            contact?.let {
                contactdatabase.contactDao().delete(it)
            }
        }
    }
}

