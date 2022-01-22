package com.example.mojeseriale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class ZmianaOceny : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zmiana_oceny)
        initListeners()
    }

    private fun initListeners() {
        val buttonZmienOceneInsert = findViewById<Button>(R.id.button_zmienOcene)
        buttonZmienOceneInsert.setOnClickListener(buttonZmienOceneListener)
    }

    private val buttonZmienOceneListener = View.OnClickListener { zwrucOcene() }


    private fun zwrucOcene() {
        val editText = findViewById<EditText>(R.id.tekst_zmienOcene)
        val intent = Intent()
        if (editText.text.toString().isEmpty()) {
            setResult(RESULT_CANCELED, intent)
        } else {
            intent.putExtra("RESULT", editText.text.toString())
            setResult(RESULT_OK, intent)
        }
        finish()
    }
}