package com.example.servicebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicebook.databinding.ActivityBookingpageBinding

class Bookingpage : AppCompatActivity() {
    private lateinit var binding:ActivityBookingpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBookingpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val empname=intent.getStringExtra("name").toString()
        val empnumber=intent.getStringExtra("number").toString()
        val empprice=intent.getStringExtra("empprice").toString()+"$"
        binding.textView17.setText(empname)
        binding.textView18.setText(empnumber)
        binding.textView19.setText(empprice)
    }
}