package com.example.puffandpoof.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puffandpoof.R
import com.example.puffandpoof.TransactionManager
import com.example.puffandpoof.adaptor.TransactionAdap
import com.example.puffandpoof.model.Transaction


class TransactionFragment : Fragment() {
   private lateinit var transactionadap: TransactionAdap

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      val view = inflater.inflate(R.layout.fragment_transaction, container, false)
      val recyclerView = view.findViewById<RecyclerView>(R.id.cartRecyclerView)

      val cartItems: MutableList<Transaction> = TransactionManager.get().toMutableList()

      transactionadap = TransactionAdap(cartItems)

      recyclerView.adapter = transactionadap
      recyclerView.layoutManager = LinearLayoutManager(requireContext())

      return view
   }
}
