package com.example.puffandpoof.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.puffandpoof.DetailDollActivity
import com.example.puffandpoof.R
import com.example.puffandpoof.adaptor.DollAdap
import com.example.puffandpoof.database.dbhelper
import com.example.puffandpoof.databinding.FragmentHomeBinding
import com.example.puffandpoof.model.doll
import org.json.JSONException

class HomeFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adap: DollAdap
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbhelper: dbhelper
    private var dollist = ArrayList<doll>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val storedUsername = sharedPreferences.getString("username", "")
        binding.usertv.text = storedUsername
        return binding.root
    }

    fun onDollClick(position: Int) {
        val clickedDoll = dollist[position]
        val intent = Intent(requireContext(), DetailDollActivity::class.java)
        intent.putExtra("dollId", clickedDoll.id.toString())
        intent.putExtra("tittle", clickedDoll.tittle)
        intent.putExtra("img", clickedDoll.image)
        intent.putExtra("rate", clickedDoll.rating.toString())
        intent.putExtra("size", clickedDoll.size)
        intent.putExtra("desc", clickedDoll.descripsi)
        intent.putExtra("price", clickedDoll.price.toString())
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvd)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dbhelper = dbhelper(requireContext())
        StoreData()
        dollist = dbhelper.getAllDolls()
        adap = DollAdap(dollist, this)
        recyclerView.adapter = adap
    }

    private fun StoreData() {
        val url = "https://api.npoint.io/9d7f4f02be5d5631a664"
        val requestQueue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val dollsArray = response.getJSONArray("dolls")
                    for (i in 0 until dollsArray.length()) {
                        val dollObject = dollsArray.getJSONObject(i)
                        val doll = doll(
                            id = 0,
                            tittle = dollObject.getString("name"),
                            image = dollObject.getString("imageLink"),
                            rating = dollObject.getDouble("rating"),
                            size = dollObject.getString("size"),
                            descripsi = dollObject.getString("desc"),
                            price = dollObject.getDouble("price")
                        )
                        dbhelper.insertDoll(doll)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        )
        requestQueue.add(jsonObjectRequest)
    }
}

