package com.example.roomdb

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData

@Composable
fun MainScreen(
    onSaveClick:(String, String, String)-> Unit,
    saveContact: (String, String, String) -> Unit,
    checkRegistration: (String, String, String)->Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    var mail by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome User",
            fontSize = 50.sp,
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.labelLarge
        )
        Text(text = "Please enter your details", fontSize = 18.sp, color = Color.Gray,modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(24.dp))
        //Name Input
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {name = it},
            label = {Text(text = "Name:")},
            placeholder = { Text(text = "Enter your Name")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Mobile Input
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = number,
            onValueChange = {number = it},
            label = {Text(text = "Mobile No:")},
            placeholder = { Text(text = "Enter your Number")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Mail Input
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mail,
            onValueChange = {mail = it},
            label = {Text(text = "Mail:")},
            placeholder = { Text(text = "Enter your Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onSaveClick(name, number, mail)
                saveContact(name, number, mail)
                name = ""
                number = ""
                mail = ""
                Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                      },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Check Details?",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.clickable { checkRegistration(name, number, mail) }
        )

    }
}