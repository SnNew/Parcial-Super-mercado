package com.example.parcial2.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun CarritoCompras(navController: NavController, gestion: GestionProductos) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Carrito de Compras") }, backgroundColor = Color(0xFFEEEEEE)) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(gestion.carrito.groupBy { it.id }.toList()) { (id, productos) ->
                    val producto = productos.first()
                    val cantidad = productos.size
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = producto.imagenUrl,
                                contentDescription = null,
                                modifier = Modifier.size(80.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(producto.nombre, style = MaterialTheme.typography.h6)
                                Text("\$${String.format("%.2f", producto.precio)} x $cantidad", style = MaterialTheme.typography.body2)
                            }
                            IconButton(onClick = {
                                gestion.eliminarProductoDelCarrito(id)
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Total: \$${String.format("%.2f", gestion.totalCarrito())}")
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Finalizar Compra")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Volver al Catálogo")
            }
        }
    }
}
