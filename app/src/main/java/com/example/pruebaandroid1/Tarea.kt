package com.example.pruebaandroid1.models

data class Tarea(
    val descripcion: String,
    val prioridad: String,
    var estaCompleta: Boolean = false,
    val fecha: String //La fecha introducida por el usuario
)
