package com.example.parcial2.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import coil.compose.AsyncImage
import com.example.parcial2.nav.Rutas
import com.example.parcial2.data.Producto

@Composable
fun CatalogoProductos(navController: NavController, gestion: GestionProductos) {
    var showDialog by remember { mutableStateOf(false) }
    var productoSeleccionado by remember { mutableStateOf<Producto?>(null) }
    var cantidad by remember { mutableStateOf("1") }

    Scaffold(
        backgroundColor = Color(0xFFF5F5F5),
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos", color = Color.Black) },
                backgroundColor = Color(0xFFEEEEEE),
                actions = {
                    IconButton(onClick = { navController.navigate(Rutas.Carrito.ruta) }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito", tint = Color.Black)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Rutas.Agregar.ruta) }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Producto")
            }
        }
    ) { paddingValues ->
        val topPadding = 16.dp

        LazyColumn(modifier = Modifier.padding(paddingValues).padding(top = topPadding)) {
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
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            AsyncImage(
                                model = producto.imagenUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 16.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column {
                                Text(producto.nombre, style = MaterialTheme.typography.h6)
                                Text("\$${String.format("%.2f", producto.precio)}", style = MaterialTheme.typography.body2)
                            }
                        }
                        IconButton(onClick = {
                            productoSeleccionado = producto
                            showDialog = true
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Agregar al carrito")
                        }
                    }
                }
            }
        }

        if (showDialog && productoSeleccionado != null) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Agregar cantidad", style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = cantidad,
                            onValueChange = {
                                cantidad = it.filter { char -> char.isDigit() } // Solo números
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Cantidad") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            val cantidadInt = cantidad.toIntOrNull() ?: 1
                            repeat(cantidadInt) {
                                gestion.agregarAlCarrito(productoSeleccionado!!)
                            }
                            showDialog = false
                            cantidad = "1"
                        }) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}
