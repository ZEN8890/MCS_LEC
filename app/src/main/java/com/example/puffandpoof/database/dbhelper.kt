package com.example.puffandpoof.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.puffandpoof.model.doll

class dbhelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dollDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_DOLLS = "dolls"
        private const val ID = "id"
        private const val TITLE = "tittle"
        private const val IMAGE = "image"
        private const val RATING = "rating"
        private const val SIZE = "size"
        private const val DESCRIPTION = "descripsi"
        private const val PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_DOLLS ("
                + "$ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$TITLE TEXT, "
                + "$IMAGE TEXT, "
                + "$RATING REAL, "
                + "$SIZE TEXT, "
                + "$DESCRIPTION TEXT, "
                + "$PRICE REAL)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DOLLS")
        onCreate(db)
    }

    fun insertDoll(doll: doll): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITLE, doll.tittle)
        values.put(IMAGE, doll.image)
        values.put(RATING, doll.rating)
        values.put(SIZE, doll.size)
        values.put(DESCRIPTION, doll.descripsi)
        values.put(PRICE, doll.price)


        val cursor = db.query(
            TABLE_DOLLS, null, "$TITLE = ?",
            arrayOf(doll.tittle), null, null, null
        )
        if (cursor.moveToFirst()) {
            cursor.close()
            return -1
        }
        cursor.close()

        return db.insert(TABLE_DOLLS, null, values)
    }

    fun getAllDolls(): ArrayList<doll> {
        val dollList = ArrayList<doll>()
        val selectQuery = "SELECT * FROM $TABLE_DOLLS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val doll = doll(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                    tittle = cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                    image = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)),
                    rating = cursor.getDouble(cursor.getColumnIndexOrThrow(RATING)),
                    size = cursor.getString(cursor.getColumnIndexOrThrow(SIZE)),
                    descripsi = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow(PRICE))
                )
                dollList.add(doll)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return dollList
    }
}
