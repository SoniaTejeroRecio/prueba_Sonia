package com.example.pruebaandroid1.models


import android.os.Parcel
import android.os.Parcelable

data class Producto(
    val nombre: String,
    val cantidad: Int = 0,
    val precio: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(cantidad)
        parcel.writeDouble(precio)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}