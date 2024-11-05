package com.example.pruebaandroid1.models

import android.os.Parcel
import android.os.Parcelable

data class Tarea(
    val descripcion: String,
    val prioridad: String,
    var estaCompleta: Boolean = false,
    val fecha: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(descripcion)
        parcel.writeString(prioridad)
        parcel.writeByte(if (estaCompleta) 1 else 0)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Tarea> {
        override fun createFromParcel(parcel: Parcel): Tarea = Tarea(parcel)
        override fun newArray(size: Int): Array<Tarea?> = arrayOfNulls(size)
    }
}
