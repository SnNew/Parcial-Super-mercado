package com.example.parcial2.items

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun DetalleProducto(navController: NavController, gestion: GestionProductos, id: Int) {
    val producto = gestion.obtenerProductoPorId(id)

    if (producto == null) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Producto no encontrado") }) }
        ) { paddingValues ->
            Text("Producto no encontrado", modifier = Modifier.padding(paddingValues).padding(16.dp))
        }
        return
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(producto.nombre) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Precio: \$${String.format("%.2f", producto.precio)}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Descripción: ${producto.descripcion}")
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                gestion.agregarAlCarrito(producto)
                navController.popBackStack()
            }) {
                Text("Agregar al Carrito")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}
