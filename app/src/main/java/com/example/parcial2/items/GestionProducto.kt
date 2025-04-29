package com.example.parcial2.items

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.parcial2.data.Producto

class GestionProductos : ViewModel() {
    private val _productos = mutableStateListOf<Producto>()
    private val _carrito = mutableStateListOf<Producto>()

    val productos: List<Producto> get() = _productos
    val carrito: List<Producto> get() = _carrito

    fun agregarProducto(producto: Producto) {
        _productos.add(producto)
    }

    fun agregarAlCarrito(producto: Producto) {
        _carrito.add(producto)
    }

    fun totalCarrito(): Double {
        return _carrito.sumOf { it.precio }
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return _productos.find { it.id == id }
    }
    fun eliminarProductoDelCarrito(id: Int) {
        _carrito.removeAll(_carrito.filter { it.id == id })
    }

    fun limpiarCarrito() {
        _carrito.clear()
    }
}
