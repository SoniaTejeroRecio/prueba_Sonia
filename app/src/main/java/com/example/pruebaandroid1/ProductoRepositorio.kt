package com.example.pruebaandroid1.repositories

import com.example.pruebaandroid1.models.Producto

interface ProductoRepositorio {
    fun agregarProducto(producto: Producto)
    fun obtenerListaDeProductos(): List<Producto>
    fun calcularPrecioTotal(): Double
}
