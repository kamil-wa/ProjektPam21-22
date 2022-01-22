package com.example.mojeseriale

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.EditText

class DataBaseHelper(context: Context, name: String?, factory:
SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context,
    DATABASE_NAME, factory, DATABASE_VERSION){
    val TABLE_SERIES = "series"
    val COLUMN_ID = "series_id"
    val COLUMN_SERIES_NAME = "series_name"
    val COLUMN_GRADE = "series_grade"
    val COLUMN_GRADE_FILMWEB = "series_grade_filmweb"
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "playersDataBase.db"
    }

    override fun onCreate(database: SQLiteDatabase) {
        val CREATE_TABLE_SERIES = ("CREATE TABLE " +
                TABLE_SERIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_SERIES_NAME + " TEXT,"
                + COLUMN_GRADE + " INTEGER,"
                + COLUMN_GRADE_FILMWEB + " INTEGER" + ")"
                )
        database.execSQL(CREATE_TABLE_SERIES)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIES)
        onCreate(database)
    }

    fun addSeries(serial: Serial) {
        val data = ContentValues()
        data.put(COLUMN_SERIES_NAME, serial.name)
        data.put(COLUMN_GRADE, serial.ocena)
        data.put(COLUMN_GRADE_FILMWEB, serial.ocenaFilmWeb)
        val database = this.writableDatabase
        database.insert(TABLE_SERIES, null, data)
        database.close()
    }

    fun findSeries(seriesName: String): Serial? {
        val query =
            "SELECT * FROM $TABLE_SERIES WHERE $COLUMN_SERIES_NAME =\"$seriesName\" "
        val database = this.writableDatabase
        val cursor = database.rawQuery(query, null)
        var serial: Serial? = null
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val seriesName2 = cursor.getString(1)
            val seriesGrade = Integer.parseInt(cursor.getString(2))
            val seriesFilmWebGrade = Integer.parseInt(cursor.getString(3))

            serial = Serial(seriesName2, seriesGrade, seriesFilmWebGrade)
            cursor.close()
        }
        database.close()
        return serial
    }
    fun setGradeTo(name: String, grade: Int){
        val query =
            "UPDATE $TABLE_SERIES SET $COLUMN_GRADE = $grade WHERE $COLUMN_SERIES_NAME =\"$name\" "
        val database = this.writableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            cursor.close()
        }
        database.close()
    }


}
