package com.example.pruebaandroid1

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.graphics.Color
import android.graphics.Typeface
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityTaskBinding
import com.example.pruebaandroid1.models.Tarea
import java.util.*

class TareaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private val tareas = mutableListOf<Tarea>()
    private lateinit var adaptadorTareas: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configurar prioridades
        val prioridades = arrayOf("URGENTE", "MEDIA", "NORMAL")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prioridades)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPrioridad.adapter = spinnerAdapter


        binding.etFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                binding.etFecha.setText(fechaSeleccionada)
            }, anio, mes, dia)
            datePicker.show()
        }

        //botón gregar una nueva tarea
        binding.btnAgregarTarea.setOnClickListener {
            val descripcion = binding.etTareaInput.text.toString()
            val prioridad = binding.spinnerPrioridad.selectedItem.toString()
            val fecha = binding.etFecha.text.toString()

            if (descripcion.isNotBlank() && fecha.isNotBlank()) {
                val nuevaTarea = Tarea(descripcion, prioridad, fecha = fecha)
                tareas.add(nuevaTarea)
                actualizarVista()
                binding.etTareaInput.text.clear()
                binding.etFecha.text.clear()
            }
        }


        adaptadorTareas = ArrayAdapter(this, android.R.layout.simple_list_item_1, tareas.map { formatTarea(it) })
        binding.listViewTareas.adapter = adaptadorTareas
    }

    private fun formatTarea(tarea: Tarea): CharSequence {
        return when (tarea.prioridad) {
            "URGENTE" -> {
                val spannable = SpannableString("${tarea.prioridad} - ${tarea.descripcion} - ${tarea.fecha}")
                spannable.setSpan(ForegroundColorSpan(Color.RED), 0, tarea.prioridad.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.setSpan(StyleSpan(Typeface.BOLD), 0, tarea.prioridad.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable
            }
            else -> "${tarea.prioridad} - ${tarea.descripcion} - ${tarea.fecha}"
        }
    }

    private fun actualizarVista() {
        adaptadorTareas.clear()
        adaptadorTareas.addAll(tareas.map { formatTarea(it) })
        adaptadorTareas.notifyDataSetChanged()
    }
}
