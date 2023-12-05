package com.example.servicebook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class aspadapter(val arra:ArrayList<asapmodel>):RecyclerView.Adapter<aspadapter.viewholder>() {
    class viewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val img=itemview.findViewById<CircleImageView>(R.id.profile_image)
        val empname=itemview.findViewById<TextView>(R.id.textView7)
        val empprice=itemview.findViewById<TextView>(R.id.textView8)
        val btn=itemview.findViewById<Button>(R.id.button2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val lay=LayoutInflater.from(parent.context).inflate(R.layout.servicemansample,parent,false)
        return viewholder(lay)
    }

    override fun getItemCount(): Int {
        return arra.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val main=arra[position]
        holder.img.setImageResource(main.pp)
        holder.empname.setText(main.name)
        holder.empprice.setText(main.price)
        holder.btn.setOnClickListener {
            val i =Intent(holder.itemView.context,Bookingpage::class.java)
            i.putExtra("name",main.name)
            i.putExtra("empprice",main.price)
            i.putExtra("number",main.number)
            holder.itemView.context.startActivity(i)
        }

    }
}