package com.example.pruebaandroid1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.pruebaandroid1.R
import com.example.pruebaandroid1.models.Tarea

class TareaAdapter(private val context: Context, private val tareas: List<Tarea>) : BaseAdapter() {

    override fun getCount(): Int = tareas.size

    override fun getItem(position: Int): Any = tareas[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false)

        val tvDescripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val tvPrioridad = view.findViewById<TextView>(R.id.tvPrioridad)
        val tvFecha = view.findViewById<TextView>(R.id.tvFecha)

        val tarea = getItem(position) as Tarea
        tvDescripcion.text = tarea.descripcion
        tvPrioridad.text = tarea.prioridad
        tvFecha.text = tarea.fecha

        if (tarea.prioridad == "URGENTE") {
            tvPrioridad.setTextColor(context.getColor(android.R.color.holo_red_dark))
        }

        return view
    }
}
