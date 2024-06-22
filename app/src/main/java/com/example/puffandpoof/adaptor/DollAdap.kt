package com.example.puffandpoof.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puffandpoof.Fragment.HomeFragment
import com.example.puffandpoof.R
import com.example.puffandpoof.model.doll
import com.google.android.material.imageview.ShapeableImageView

class DollAdap(private val dolllist: ArrayList<doll>, private val clickListener: HomeFragment) : RecyclerView.Adapter<DollAdap.holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.doll_list, parent, false)
        return holder(itemView)
    }

    override fun getItemCount(): Int {
        return dolllist.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        val crtitem = dolllist[position]
        Glide.with(holder.itemView.context).load(crtitem.image).into(holder.img)
        holder.ttl.text = crtitem.tittle
        holder.price.text = crtitem.price.toString()
//        holder.rate.text = crtitem.rating.toString()

        holder.cardd.setOnClickListener {
            clickListener.onDollClick(position)
        }
    }

    class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ShapeableImageView = itemView.findViewById(R.id.imgdoll)
        val ttl: TextView = itemView.findViewById(R.id.ttldol)
        val price: TextView = itemView.findViewById(R.id.price)
//        val rate: TextView = itemView.findViewById(R.id.rating)
        val cardd: CardView = itemView.findViewById(R.id.carddoll)
    }
}