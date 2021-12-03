package com.andresdiaz.seccion_07.activities

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.andresdiaz.mylibrary.ToolbarActivity
import com.andresdiaz.seccion_07.R
import com.andresdiaz.seccion_07.fragments.ArrivalsFragment
import com.andresdiaz.seccion_07.fragments.DeparturesFragment
import com.andresdiaz.seccion_07.fragments.HomeFragment
import com.andresdiaz.seccion_07.toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : ToolbarActivity(),NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbar as Toolbar)

        setNavDrawer()
        setUserHeaderInformation()
        if(savedInstanceState==null){
            toast("Is Null")
            fragmentTransaction(HomeFragment())
            navView.menu.getItem(0).isChecked=true
        }else{
            toast("Not Null")
        }



    }

    private fun setNavDrawer(){
        val toggle=ActionBarDrawerToggle(this,drawer_layout,_toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled=true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener (this)
    }

    private fun fragmentTransaction(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()

    }

    private fun loadFragmentById(id: Int){
        when(id){
            R.id.nav_home->fragmentTransaction(HomeFragment())
            R.id.nav_departures->fragmentTransaction(DeparturesFragment())
            R.id.nav_arrivals->fragmentTransaction(ArrivalsFragment())
        }
    }
    private fun showMessageNavItemSelectedById(id :Int){
        when(id){
            R.id.nav_profile->toast("PROFILE")
            R.id.nav_settings->toast("SETTINGS")
        }
    }

    private fun setUserHeaderInformation(){
        val name=navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email=navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)
        name?.let{ name.text=getString(R.string.user_name) }
        email?.let{ email.text=getString(R.string.user_email) }
        //name.textViewName.text=getString(R.string.user_name)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {//funcion que se llama en la interfas del navigationview
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentById(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)//sierra el menu al cliquear
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

    }
}
