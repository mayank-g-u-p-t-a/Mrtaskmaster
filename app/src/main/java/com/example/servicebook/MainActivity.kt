package com.example.servicebook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servicebook.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val arr = ArrayList<users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val spinner4 = findViewById<Spinner>(R.id.spinner4)
        val items1 = arrayOf("Select Your Location", "Ghaziabad", "Delhi", "Muradnagar", "Mumbai", "Etawah")
        val arrayadapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items1)
        spinner4.adapter = arrayadapter


        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val loc = parent?.getItemAtPosition(position).toString()
                if (loc != "Select Your Location") {
                    populateRecyclerView(loc)
                } else {
                    Toast.makeText(this@MainActivity, "Please select a valid location", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No action needed here
            }
        }
    }

    private fun populateRecyclerView(loc: String) {
        arr.clear() // Clear the existing data
        arr.add(users(R.drawable.cleaning, "Cleaning Service", "Providing meticulous cleaning services to ensure pristine and sanitized environments, promoting hygiene and well-being for residential and commercial spaces. Delivering top-notch cleaning services with attention to detail, ensuring spotless and sanitized spaces for a refreshed and inviting atmosphere", loc))
        arr.add(users(R.drawable.completemaid, "House Help", "Efficient and dedicated house help offering comprehensive domestic support to enhance and maintain a comfortable and organized home environment. Experienced in daily household tasks, adept at multitasking, and committed to creating a welcoming atmosphere for residents", loc))
        arr.add(users(R.drawable.chef, "Chef", "Passionate and creative chef with a flair for crafting exquisite culinary experiences. Expertise in innovative menu development and precision in executing diverse cuisines, dedicated to delivering memorable dining moments.", loc))
        arr.add(users(R.drawable.barber, "Barber", "Skilled barber with a keen eye for detail, specializing in precision haircuts and grooming services. Committed to enhancing clients' confidence through personalized and stylish grooming experiences.", loc))
        arr.add(users(R.drawable.masseuse, "Masseuse", "Experienced and licensed massage therapist adept at providing therapeutic and relaxing treatments, promoting physical well-being and stress relief. Specialized in tailoring massage sessions to meet individual needs, fostering a rejuvenating and soothing experience.", loc))
        arr.add(users(R.drawable.electrician, "Electrician", "Proficient electrician skilled in installing, maintaining, and repairing electrical systems with a focus on safety and efficiency. Capable of diagnosing and troubleshooting electrical issues, ensuring reliable and compliant electrical solutions for residential and commercial clients.", loc))
        arr.add(users(R.drawable.plumber, "Plumber", "Versatile plumber proficient in installing, repairing, and maintaining plumbing systems. Demonstrated expertise in troubleshooting issues, ensuring efficient water distribution, and providing timely solutions for residential and commercial properties.", loc))
        arr.add(users(R.drawable.beautician,"Beautician","A beautician provides beauty treatments such as facials, manicures, and hair styling. They assess clients' needs to recommend suitable beauty regimens and products. With expertise in skincare and aesthetics, beauticians enhance clients' appearance and well-being. They ensure a clean, safe, and relaxing environment in their salon or spa.",loc))
        val rv = findViewById<RecyclerView>(R.id.mainrecycler)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mainadapter(arr)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                val i=Intent(this,MainActivity::class.java)
                startActivity(i)

                true
            }
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, firstpage::class.java)
                startActivity(intent)
                finish()

                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
    }

}
