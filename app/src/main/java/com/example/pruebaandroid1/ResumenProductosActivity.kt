package com.example.pruebaandroid1

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityResumenProductosBinding
import com.example.pruebaandroid1.models.Producto

class ResumenProductosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResumenProductosBinding
    private val productosResumen = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumenProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvResumenProductosTitulo.text = "Resumen de Productos"

        val productosRecibidos = intent.getParcelableArrayListExtra<Producto>("PRODUCTOS_RESUMEN")
        if (productosRecibidos != null) {
            productosResumen.addAll(productosRecibidos)
        }


        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            productosResumen.map { formatProducto(it) }
        )
        binding.listViewResumenProductos.adapter = adaptador


        val totalPrecio = productosResumen.sumOf { it.precio * it.cantidad }
        binding.tvTotalPrecio.text = "Total: €${"%.2f".format(totalPrecio)}"
    }


    private fun formatProducto(producto: Producto): String {
        return "Nombre: ${producto.nombre}, Cantidad: ${producto.cantidad}, Precio: €${producto.precio}"
    }
}