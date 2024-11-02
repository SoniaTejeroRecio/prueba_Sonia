package com.example.pruebaandroid1.repositories

import com.example.pruebaandroid1.models.Tarea

interface TareaRepositorio {
    fun agregarTarea(tarea: Tarea)
    fun obtenerTareasPendientes(): List<Tarea>
    fun obtenerTareasCompletadas(): List<Tarea>
    fun marcarTareaComoCompleta(posicion: Int)
    fun eliminarTarea(posicion: Int)
}
