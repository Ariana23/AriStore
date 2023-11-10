package com.example.aristore

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
// Write a message to the database
    val database = Firebase.database


    var name by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name,
            onValueChange = {newText -> name= newText},
            label = { Text(text ="USUARIO:" )}
            )
        TextField(value = titulo,
            onValueChange = {newText -> titulo= newText},
            label = { Text(text ="Titulo:" )}
        )
        TextField(value = descripcion,
            onValueChange = {newText -> descripcion= newText},
            label = { Text(text ="Descripción:" )}
        )
        val context = LocalContext.current
        Button(
            onClick = {
                      val contactsRef = database.reference.child("Contacts")
                    val contactRef = contactsRef.child(name)
                    val contact = Nota(titulo, descripcion)
                contactRef.setValue(contact)
                Toast.makeText(context,"Save Note", Toast.LENGTH_SHORT).show()
                name = ""
                titulo = ""
                descripcion = ""
            },
            modifier = Modifier.padding(16.dp)
            ) {
            Text(text = "AGREGAR")
        }
        //LOGICA DE LA IMPLEMENTACION DE CRASHLYTICS
        Spacer(modifier = Modifier.height(15.dp))
        ClickableText(
            text = AnnotatedString("Formar Cierre Crashlytics"),
            onClick ={

                //ESTO HACE QUE MI APP SE CIERRE
                throw RuntimeException("Error forzado desde Home")
            }
        )
    }
}
data class Nota (val titulo: String, val descripcion: String)

