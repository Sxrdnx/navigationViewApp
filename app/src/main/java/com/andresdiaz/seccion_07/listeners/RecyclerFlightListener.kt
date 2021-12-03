package com.andresdiaz.seccion_07.listeners

import com.andresdiaz.seccion_07.models.Flight
/*Interfas para los eventos de cada item */
interface  RecyclerFlightListener{
    fun onClick(flight: Flight, position: Int)
    fun onDelete(flight: Flight, position: Int)
}