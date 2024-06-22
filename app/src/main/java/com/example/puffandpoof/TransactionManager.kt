package com.example.puffandpoof

import com.example.puffandpoof.model.Transaction

object TransactionManager {
    private val transaction: MutableList<Transaction> = mutableListOf()

    fun add(item: Transaction) {
        val existingItem = transaction.find { it.id == item.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            transaction.add(item)
        }
    }


    fun remove(id: String) {
        transaction.removeAll { it.id == id }
    }

    fun get(): List<Transaction> {
        return transaction.toList()
    }
}
