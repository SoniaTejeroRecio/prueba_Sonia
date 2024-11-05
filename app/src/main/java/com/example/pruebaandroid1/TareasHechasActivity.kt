package com.example.pruebaandroid1

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityTareasHechasBinding
import com.example.pruebaandroid1.models.Tarea

class TareasHechasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTareasHechasBinding
    private val tareasCompletadas = mutableListOf<Tarea>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTareasHechasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvTituloResumen.text = getString(R.string.titulo_resumen_tareas_completadas)


        val tareasRecibidas = intent.getParcelableArrayListExtra<Tarea>("TAREAS_COMPLETADAS")
        if (tareasRecibidas != null) {
            tareasCompletadas.addAll(tareasRecibidas)
        }


        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tareasCompletadas.map { formatTarea(it) }
        )
        binding.listViewTareasCompletadas.adapter = adaptador
    }


    private fun formatTarea(tarea: Tarea): String {
        return "✔ ${tarea.descripcion} - Fecha: ${tarea.fecha}"
    }
}
