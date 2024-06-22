package com.example.puffandpoof.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puffandpoof.R
import com.example.puffandpoof.TransactionManager
import com.example.puffandpoof.model.Transaction

class TransactionAdap(private val Transaction: MutableList<Transaction>) :
    RecyclerView.Adapter<TransactionAdap.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_list, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = Transaction[position]
        holder.titleTextView.text = currentItem.title
        holder.idtv.text = currentItem.id
        holder.quanti.text = currentItem.quantity.toString()
        Glide.with(holder.itemView.context)
            .load(currentItem.img)
            .into(holder.img)
        holder.date.text = currentItem.date.toString()
        holder.minusButton.setOnClickListener {
            if (currentItem.quantity > 1) {
                currentItem.quantity--
                notifyDataSetChanged()
            }
        }

        holder.plusButton.setOnClickListener {
            currentItem.quantity++
            notifyDataSetChanged()
        }

        holder.deleteButton.setOnClickListener {

            TransactionManager.remove(currentItem.id)

            Transaction.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, Transaction.size)
        }
    }

    override fun getItemCount(): Int {
        return Transaction.size
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val idtv : TextView = itemView.findViewById(R.id.idTextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val quanti : TextView = itemView.findViewById(R.id.quanti)
        val minusButton : ImageButton = itemView.findViewById(R.id.minusButton)
        val plusButton : ImageButton = itemView.findViewById(R.id.plusButton)
        val img : ImageView = itemView.findViewById(R.id.imgvtrans)
        val date : TextView = itemView.findViewById(R.id.tanggal)
    }
}