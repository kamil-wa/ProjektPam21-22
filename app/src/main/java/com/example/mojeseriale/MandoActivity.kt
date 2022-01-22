package com.example.mojeseriale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MandoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mando)
        initListeners()
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val twojaOcenaS: Serial? = dataBaseHandler.findSeries("MANDALORIAN")
        val textView = findViewById<TextView>(R.id.twoja_ocena_mando)
        if (twojaOcenaS != null) {
            textView.setText(twojaOcenaS.ocena.toString())
        }
    }

    private fun initListeners() {
        val buttonZmienOcene = findViewById<Button>(R.id.zmien_ocene_mando)
        buttonZmienOcene.setOnClickListener(buttonZmienOceneMandoListener)
    }
    private val buttonZmienOceneMandoListener = View.OnClickListener { callZmienOceneMando() }

    private fun callZmienOceneMando() {
        val intentZmienOcenetMando = Intent(this, ZmianaOceny::class.java)
        startActivityForResult(intentZmienOcenetMando, 2)
    }


       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode,  data)
        var result: String? = ""
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                result = data!!.getStringExtra("RESULT")
            } else if (resultCode == RESULT_CANCELED) {
                result = "No data"
            }
        }
        val textView = findViewById<TextView>(R.id.twoja_ocena_mando)
        textView.text = result
    }


}