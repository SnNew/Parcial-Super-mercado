package com.example.parcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.parcial2.items.Navegacion
import com.example.parcial2.items.GestionProductos
import com.example.parcial2.ui.theme.Parcial2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial2Theme {
                // Instancia de GestionProductos
                val gestion = GestionProductos()

                val navController = rememberNavController()
                // Se pasa el parámetro gestion a Navegacion
                Navegacion(navController = navController, modifier = Modifier.fillMaxSize(), gestion = gestion)
            }
        }
    }
}
