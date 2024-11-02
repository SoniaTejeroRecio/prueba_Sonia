package com.example.pruebaandroid1.repositories

import com.example.pruebaandroid1.models.Tarea

class TareaRepositorioImpl : TareaRepositorio {
    private val tareasPendientes = mutableListOf<Tarea>()
    private val tareasCompletadas = mutableListOf<Tarea>()

    override fun agregarTarea(tarea: Tarea) {
        tareasPendientes.add(tarea)
    }

    override fun obtenerTareasPendientes(): List<Tarea> = tareasPendientes

    override fun obtenerTareasCompletadas(): List<Tarea> = tareasCompletadas

    override fun marcarTareaComoCompleta(posicion: Int) {
        val tarea = tareasPendientes.removeAt(posicion)
        tarea.estaCompleta = true
        tareasCompletadas.add(tarea)
    }

    override fun eliminarTarea(posicion: Int) {
        tareasPendientes.removeAt(posicion)
    }
}
