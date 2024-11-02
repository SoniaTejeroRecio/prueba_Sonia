package com.example.pruebaandroid1

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityShoppingListBinding
import com.example.pruebaandroid1.models.Producto
import com.example.pruebaandroid1.repositories.ProductoRepositorioImpl

class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    private val repositorio = ProductoRepositorioImpl()
    private lateinit var adaptador: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adaptador = ArrayAdapter(this, R.layout.simple_list_item_1, repositorio.obtenerListaDeProductos().map { it.nombre })
        binding.listViewProductos.adapter = adaptador

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

        binding.tvPrecioTotal.text = "Total: ${repositorio.calcularPrecioTotal()}€"
    }

    private fun actualizarLista() {
        adaptador.clear()
        adaptador.addAll(repositorio.obtenerListaDeProductos().map { it.nombre })
        binding.tvPrecioTotal.text = "Total: ${repositorio.calcularPrecioTotal()}€"
    }
}
