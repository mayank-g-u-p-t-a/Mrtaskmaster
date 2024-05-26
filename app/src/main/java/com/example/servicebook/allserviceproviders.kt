package com.example.servicebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servicebook.databinding.ActivityAllserviceprovidersBinding

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class allserviceproviders : AppCompatActivity() {
    private lateinit var binding:ActivityAllserviceprovidersBinding
    private var arr=ArrayList<asapmodel>()
    private var totaldata=ArrayList<usermoddel>()
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllserviceprovidersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        reference=FirebaseDatabase.getInstance().reference
        val jobname=intent.getStringExtra("job").toString()
        val loca=intent.getStringExtra("loc").toString()
        fetchfiltereddata(loca,jobname)




        val rv=findViewById<RecyclerView>(R.id.personsrecycler)
        rv.layoutManager= LinearLayoutManager(this)
        rv.adapter=aspadapter(arr)


    }

    private fun fetchfiltereddata(location: String, work: String) {
        reference.child("Users").orderByChild("loc").equalTo(location).addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arr.clear()

                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(usermoddel::class.java)
                    if(user!=null && user.work==work){
                        val id =arr.size+1
                        val asap=asapmodel(id, user.name ?: "", user.price ?: "", user.number ?: "")
                        arr.add(asap)
                    }
                }
                binding.personsrecycler.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}