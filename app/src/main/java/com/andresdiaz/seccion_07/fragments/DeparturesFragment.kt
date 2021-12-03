package com.andresdiaz.seccion_07.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresdiaz.seccion_07.R
import com.andresdiaz.seccion_07.adapters.FlightAdapter
import com.andresdiaz.seccion_07.listeners.RecyclerFlightListener
import com.andresdiaz.seccion_07.models.Flight
import com.andresdiaz.seccion_07.toast
import kotlinx.android.synthetic.main.fragment_departures.view.*


class DeparturesFragment : Fragment() {
    private val list :ArrayList<Flight> by lazy{ getFlights() }
    private lateinit var  recycler: RecyclerView
    private  lateinit var adapter: FlightAdapter
    private val layoutManager by lazy{ LinearLayoutManager(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        activity!!.setTitle(R.string.departures_fragment_title)
        val rootView=inflater.inflate(R.layout.fragment_departures, container, false)
        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()
        return rootView
    }
    private fun setRecyclerView(){
        recycler.setHasFixedSize(true)
        recycler.itemAnimator=DefaultItemAnimator()
        recycler.layoutManager=layoutManager
        adapter=(FlightAdapter(list,object :RecyclerFlightListener{
            override fun onClick(flight: Flight, position: Int) {
               activity?.toast("Let´s go to ${flight.city}!")
            }

            override fun onDelete(flight: Flight, position: Int) {
               list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city} has been removed!")
            }

        }))
        recycler.adapter=adapter
    }
    private fun getFlights():ArrayList<Flight>{
        return  object : ArrayList<Flight>(){
            init {
                add (Flight(1, "Canada",R.drawable.canada))
                add (Flight(2, "Guanajuato",R.drawable.guanajuato))
                add (Flight(3, "Holanda",R.drawable.holanda))
                add (Flight(4, "Japon",R.drawable.japon))
                add (Flight(5, "Londres",R.drawable.london))
                add (Flight(6, "Mexico",R.drawable.mexico))
                add (Flight(7, "Puebla",R.drawable.puebla))
                add (Flight(8, "Stone",R.drawable.stone))
                add (Flight(9, "Utha",R.drawable.utha))
                add (Flight(10, "New York",R.drawable.newyork))
            }
        }
    }
}
