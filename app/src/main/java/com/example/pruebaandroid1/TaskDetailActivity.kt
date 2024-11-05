package com.example.pruebaandroid1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityTaskDetailBinding
import com.example.pruebaandroid1.models.Tarea

class TaskDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailBinding
    private var tareaSeleccionada: Tarea? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tareaSeleccionada = intent.getParcelableExtra("TAREA")

        binding.tvDetalleDescripcion.text = tareaSeleccionada?.descripcion
        binding.tvDetallePrioridad.text = tareaSeleccionada?.prioridad
        binding.tvDetalleFecha.text = tareaSeleccionada?.fecha

        binding.btnMarcarComoCompletada.setOnClickListener {
            tareaSeleccionada?.let {
                val intent = Intent(this, TareasHechasActivity::class.java)
                intent.putExtra("TAREA_COMPLETADA", it)
                startActivity(intent)
            }
        }
    }
}
