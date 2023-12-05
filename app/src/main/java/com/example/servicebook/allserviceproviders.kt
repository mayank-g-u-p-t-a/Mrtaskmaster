package com.example.servicebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servicebook.databinding.ActivityAllserviceprovidersBinding

class allserviceproviders : AppCompatActivity() {
    private lateinit var binding:ActivityAllserviceprovidersBinding
    private var arr=ArrayList<asapmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllserviceprovidersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        arr.add(asapmodel(1,"Manvendra","100","999999999"))
        arr.add(asapmodel(2,"Manvendra","100","999999999"))
        arr.add(asapmodel(3,"Manvendra","100","999999999"))
        arr.add(asapmodel(4,"Manvendra","100","999999999"))
        arr.add(asapmodel(6,"Manvendra","100","999999999"))

        val rv=findViewById<RecyclerView>(R.id.personsrecycler)
        rv.layoutManager= LinearLayoutManager(this)
        rv.adapter=aspadapter(arr)
    }
}