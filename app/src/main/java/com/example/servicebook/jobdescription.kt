package com.example.servicebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicebook.databinding.ActivityJobdescriptionBinding

class jobdescription : AppCompatActivity() {
    private lateinit var binding:ActivityJobdescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJobdescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val jobname=intent.getStringExtra("jobname").toString()
        val jobdesc=intent.getStringExtra("jobdesc").toString()
        binding.textView3.setText(jobname)
        binding.textView4.setText(jobdesc)
        binding.button.setOnClickListener{
        val i= Intent(this,allserviceproviders::class.java)
            startActivity(i)
        }


    }
}