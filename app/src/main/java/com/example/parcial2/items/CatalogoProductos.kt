package com.example.parcial2.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parcial2.R
import com.example.parcial2.nav.Rutas

@Composable
fun CatalogoProductos(navController: NavController, gestion: GestionProductos) {
    Scaffold(
        backgroundColor = Color(0xFFF5F5F5),
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos", color = Color.Black) },
                backgroundColor = Color(0xFFEEEEEE),
                actions = {
                    // Icono del carrito
                    IconButton(onClick = { navController.navigate(Rutas.Carrito.ruta) }) {
                        Image(
                            painter = painterResource(id = R.drawable.carrito), // Usar la imagen del carrito
                            contentDescription = "Carrito",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    // Botón de agregar producto
                    IconButton(onClick = { navController.navigate(Rutas.Agregar.ruta) }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar Producto", tint = Color.Black)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(gestion.productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Rutas.Detalle.crearRutaConId(producto.id))
                        },
                    elevation = 4.dp,
                    backgroundColor = Color.White
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        AsyncImage(
                            model = producto.imagenUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 16.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(producto.nombre, style = MaterialTheme.typography.h6)
                            Text("\$${String.format("%.2f", producto.precio)}", style = MaterialTheme.typography.body2)
                        }
                    }
                }
            }
        }
    }
}
