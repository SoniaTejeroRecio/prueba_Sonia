package com.example.pruebaandroid1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaandroid1.adapters.ProductoAdapter
import com.example.pruebaandroid1.databinding.ActivityShoppingListBinding
import com.example.pruebaandroid1.models.Producto
import com.example.pruebaandroid1.repositories.ProductoRepositorioImpl

class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    private val repositorio = ProductoRepositorioImpl()
    private lateinit var adaptador: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        actualizarLista()

        binding.btnAgregarProducto.setOnClickListener {
            val nombre = binding.etNombreProducto.text.toString()
            val cantidad = binding.etCantidadProducto.text.toString().toIntOrNull() ?: 0
            val precio = binding.etPrecioProducto.text.toString().toDoubleOrNull() ?: 0.0

            if (nombre.isNotBlank()) {
                repositorio.agregarProducto(Producto(nombre, cantidad, precio))
                actualizarLista()
                binding.etNombreProducto.text.clear()
                binding.etCantidadProducto.text.clear()
                binding.etPrecioProducto.text.clear()
            }
        }
    }

    private fun actualizarLista() {
        val productos = repositorio.obtenerListaDeProductos()
        adaptador = ProductoAdapter(productos)
        binding.recyclerViewProductos.adapter = adaptador
        binding.tvPrecioTotal.text = "Total: ${repositorio.calcularPrecioTotal()}€"
    }
}
