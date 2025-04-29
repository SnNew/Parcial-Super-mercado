package com.example.parcial2.nav

sealed class Rutas(val ruta: String) {
    object Catalogo : Rutas("catalogo")
    object Agregar : Rutas("agregar")
    object Carrito : Rutas("carrito")
    object Detalle : Rutas("detalle/{id}") {
        fun crearRutaConId(id: Int) = "detalle/$id"
    }
    object Registro : Rutas("registro")
}
