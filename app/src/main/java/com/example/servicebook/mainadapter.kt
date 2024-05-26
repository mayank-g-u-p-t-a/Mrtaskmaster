package com.example.servicebook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class mainadapter(var arra:ArrayList<users>):RecyclerView.Adapter<mainadapter.viewholder>() {
    class viewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
val image=itemview.findViewById<ImageView>(R.id.imageView3)
        val servicename=itemview.findViewById<TextView>(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
val lay=LayoutInflater.from(parent.context).inflate(R.layout.sampleshow,parent,false)
return viewholder(lay)
    }

    override fun getItemCount(): Int {
        return arra.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
var main=arra[position]

        holder.servicename.setText(main.job)
        holder.image.setImageResource(main.profile)

        holder.itemView.setOnClickListener{
val i= Intent(holder.itemView.context,jobdescription::class.java)
        i.putExtra("jobname",main.job)
            i.putExtra("jobdesc",main.Desc)
            i.putExtra("profilepic",main.profile)
            i.putExtra("loc",main.location)

holder.itemView.context.startActivity(i)
        }


    }
}