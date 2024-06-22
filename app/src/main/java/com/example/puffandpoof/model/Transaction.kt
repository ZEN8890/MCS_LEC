package com.example.puffandpoof.model

import java.util.Date

data class Transaction(
    val id: String,
    val title: String,
    var quantity: Int,
    var img: String,
    var date: Date
)
