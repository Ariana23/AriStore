package com.example.aristore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.aristore.ui.theme.AriStoreTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

// AGREGAR UN CRASH, REMOTE, ANALYTICS 2 EVENTOS. Y UN PLUS SI NO LLEGO A UNIFICAR
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AriStoreTheme {
                window.statusBarColor = getColor(R.color.black)
                HomeScreen()

                // LOGICA ANALYTICS - EVENTO
                val analytics = FirebaseAnalytics.getInstance(this)
                val bundle = Bundle()
                bundle.putString("message", "Integracion de Firebase complete")
                analytics.logEvent("InitScreen", bundle)

                //REMOTE CONFIG FIREBASE
                val remoteConfig = Firebase.remoteConfig
                val configSettings = remoteConfigSettings{
                    minimumFetchIntervalInSeconds = 200
                }
                remoteConfig.setConfigSettingsAsync(configSettings)
                remoteConfig.fetchAndActivate()
                    .addOnCompleteListener() {
                        val welcomeMessage = remoteConfig.getString("welcome_message")
                        Toast.makeText(this,welcomeMessage, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}