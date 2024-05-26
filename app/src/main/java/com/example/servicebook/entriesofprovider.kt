package com.example.servicebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.example.servicebook.databinding.ActivityEntriesofproviderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class entriesofprovider : AppCompatActivity() {

    private lateinit var binding: ActivityEntriesofproviderBinding
    private lateinit var no: String
    private lateinit var database: DatabaseReference
    private lateinit var textview:TextView
    data class User(
        val loc: String = "",
        val name: String = "",
        val number: String = "",
        val price: String = "",
        val work: String = ""
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntriesofproviderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textview=findViewById<TextView>(R.id.textView2)


        no = number.dataSet.toString()
        database = FirebaseDatabase.getInstance().reference
        if (no != null) {
            fetchdata(no)
        }

    }

    private fun fetchdata(number: String) {

        database.child("Users").child(no).addListenerForSingleValueEvent(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                user?.let {
                    // Set text directly using the property
                    textview.text = "Name: ${it.name}\nLocation: ${it.loc}\nNumber: ${it.number}\nPrice: ${it.price}\nWork: ${it.work}"
                    Log.d("FirebaseData", "User data fetched: $it")
                } ?: Log.d("FirebaseData", "User data is null")
            }

            override fun onCancelled(error: DatabaseError) {

                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.providersiggnout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.signout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, firstpage::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Signout Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.updateprofile -> {
                val i = Intent(this, updateddata::class.java)

                startActivity(i)
                Toast.makeText(this, "Updated Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}