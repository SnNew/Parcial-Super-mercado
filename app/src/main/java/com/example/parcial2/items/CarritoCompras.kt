package com.example.parcial2.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parcial2.nav.Rutas
import kotlinx.coroutines.launch

@Composable
fun CarritoCompras(navController: NavController, gestion: GestionProductos) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
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
                                Text(
                                    "\$${String.format("%.2f", producto.precio)} x $cantidad",
                                    style = MaterialTheme.typography.body2
                                )
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
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("¡Compra realizada exitosamente!")
                }
                gestion.limpiarCarrito()
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Finalizar Compra")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                } else {
                    navController.navigate(Rutas.Catalogo.ruta)
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Volver al Catálogo")
            }

        }
    }
}
