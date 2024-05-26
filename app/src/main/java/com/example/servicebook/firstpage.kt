package com.example.servicebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class firstpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstpage)
        val providerbtn=findViewById<Button>(R.id.button5)
        val recieverbtn=findViewById<Button>(R.id.button4)

        recieverbtn.setOnClickListener {
            val i= Intent(this,Signinactivity::class.java)
            startActivity(i)
        }
        providerbtn.setOnClickListener {
            val i=Intent(this,providersignup::class.java)
            startActivity(i)
        }

    }
}