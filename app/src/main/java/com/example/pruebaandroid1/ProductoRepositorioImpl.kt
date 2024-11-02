package com.example.pruebaandroid1.repositories

import com.example.pruebaandroid1.models.Producto

class ProductoRepositorioImpl : ProductoRepositorio {
    private val listaProductos = mutableListOf<Producto>()

    override fun agregarProducto(producto: Producto) {
        listaProductos.add(producto)
    }

    override fun obtenerListaDeProductos(): List<Producto> = listaProductos

    override fun calcularPrecioTotal(): Double {
        return listaProductos.filter { it.precio > 0 }.sumOf { it.precio * it.cantidad }
    }
}
