package com.example.servicebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servicebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val arr=ArrayList<users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

arr.add(users(1,"Cleaning Service","cgdfhgwkfhewofjghnekhg"))
arr.add(users(1,"Cleaning Service","cgdfhgwkfhewofjghnekhg"))
arr.add(users(1,"Cleaning Service","cgdfhgwkfhewofjghnekhg"))
arr.add(users(1,"Cleaning Service","cgdfhgwkfhewofjghnekhg"))

val rv=findViewById<RecyclerView>(R.id.mainrecycler)
rv.layoutManager=LinearLayoutManager(this)
rv.adapter=mainadapter(arr)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.profile->{
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.logout->{
                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            else-> super.onOptionsItemSelected(item)
        }
    }
}