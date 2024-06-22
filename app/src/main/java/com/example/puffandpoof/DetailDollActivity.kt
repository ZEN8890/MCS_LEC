package com.example.puffandpoof

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.puffandpoof.databinding.ActivityDetailDollBinding
import com.example.puffandpoof.model.Transaction
import java.util.*

class DetailDollActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDollBinding
    lateinit var ttl: TextView
    lateinit var pricee: TextView
    lateinit var descc: TextView
    lateinit var sizee: TextView
    lateinit var ratee: TextView

    lateinit var addToCartButton: Button
    lateinit var quantityPicker: NumberPicker

    var tittle: String = ""
    var id: String = ""

    private lateinit var ImageView: ImageView
    private lateinit var imageButton: ImageButton

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ttl = findViewById(R.id.ttl)
        ImageView = findViewById(R.id.dtldoll)
        imageButton = findViewById(R.id.backhome)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        quantityPicker = findViewById(R.id.quantity_picker)
        pricee = findViewById(R.id.pricetv)
        descc = findViewById(R.id.desc)
        ratee = findViewById(R.id.rating)
        sizee = findViewById(R.id.size)

        tittle = intent.getStringExtra("tittle") ?: ""
        id = intent.getStringExtra("dollId") ?: ""
        val img = intent.getStringExtra("img")
        val price = intent.getStringExtra("price")
        val size = intent.getStringExtra("size")
        val rate = intent.getStringExtra("rate")
        val desc = intent.getStringExtra("desc")

        pricee.text = price
        descc.text = desc
        sizee.text = size
        ratee.text = rate

        Glide.with(this)
            .load(img)
            .into(ImageView)

        imageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        quantityPicker.minValue = 1
        quantityPicker.maxValue = 10

        addToCartButton.setOnClickListener {
            val quantity = quantityPicker.value
            val currentDate = Date()

            val cartItem = Transaction(id, tittle, quantity, img ?: "", currentDate)
            TransactionManager.add(cartItem)
            Toast.makeText(
                this,
                "$quantity $tittle added to cart",
                Toast.LENGTH_SHORT
            ).show()
        }

        ttl.text = tittle
    }
}





