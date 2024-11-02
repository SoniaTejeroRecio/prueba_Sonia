package com.example.pruebaandroid1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pruebaandroid1.models.Tarea

class TareaAdapter(context: Context, private val tareas: List<Tarea>) :
    ArrayAdapter<Tarea>(context, 0, tareas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false)

        val tarea = getItem(position)
        val tvDescripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val tvPrioridad = view.findViewById<TextView>(R.id.tvPrioridad)
        val tvFecha = view.findViewById<TextView>(R.id.tvFecha)

        tvDescripcion.text = tarea?.descripcion
        tvPrioridad.text = tarea?.prioridad
        tvFecha.text = "Fecha: ${tarea?.fecha}" //Modifica  fecha

        //Configuración de color según la prioridad
        when (tarea?.prioridad) {
            "URGENTE" -> {
                tvPrioridad.setTextColor(context.getColor(android.R.color.holo_red_light))
                tvPrioridad.text = tarea.prioridad.uppercase()
            }
            "MEDIA" -> tvPrioridad.setTextColor(context.getColor(android.R.color.holo_orange_light))
            "NORMAL" -> tvPrioridad.setTextColor(context.getColor(android.R.color.holo_green_light))
        }

        return view
    }
}
