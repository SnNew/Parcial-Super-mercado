package com.example.parcial2.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial2.nav.Rutas

@Composable
fun Navegacion(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier,
    gestion: GestionProductos
) {
    NavHost(navController = navController, startDestination = "catalogo", modifier = modifier) {
        composable("catalogo") {
            CatalogoProductos(navController = navController, gestion = gestion)
        }
        composable("agregar") {
            AgregarProducto(navController = navController, gestion = gestion)
        }
        composable("carrito") {
            CarritoCompras(navController = navController, gestion = gestion)
        }
        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            DetalleProducto(navController = navController, gestion = gestion, id = id)
        }
    }
}
