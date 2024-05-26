package com.example.servicebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicebook.databinding.ActivityJobdescriptionBinding

class jobdescription : AppCompatActivity() {
    private lateinit var binding:ActivityJobdescriptionBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJobdescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val jobname=intent.getStringExtra("jobname").toString()
        val jobdesc=intent.getStringExtra("jobdesc").toString()
        val jonimg=intent.getIntExtra("profilepic",-1)
        val joblocation=intent.getStringExtra("loc").toString()
        binding.textView3.setText(jobname)
        binding.textView4.setText(jobdesc)
        if(jonimg!=-1){
            binding.descbg.setImageResource(jonimg)
        }
        binding.button.setOnClickListener{
        val i= Intent(this,allserviceproviders::class.java)
            i.putExtra("loc",joblocation)
            i.putExtra("job",jobname)
            startActivity(i)
        }


    }
}