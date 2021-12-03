package com.andresdiaz.seccion_07.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresdiaz.seccion_07.R
import com.andresdiaz.seccion_07.inflate
import com.andresdiaz.seccion_07.listeners.RecyclerFlightListener
import com.andresdiaz.seccion_07.loadByResource
import com.andresdiaz.seccion_07.models.Flight
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(private val flights:List<Flight>, private val listener: RecyclerFlightListener)
    : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(parent.inflate(R.layout.recycler_flight))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind(flights[position],listener)

    override fun getItemCount()=flights.size

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind (flight: Flight,listener: RecyclerFlightListener)=with(itemView){
            textViewCityName.text=flight.city
            imageViewBg.loadByResource(flight.imgResource)
            //click events
            setOnClickListener{listener.onClick(flight,adapterPosition)}
            buttonDelete.setOnClickListener{listener.onDelete(flight,adapterPosition)}
        }
    }

}
