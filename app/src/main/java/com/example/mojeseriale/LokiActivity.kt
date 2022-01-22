package com.example.mojeseriale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class LokiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loki)
        initListeners()
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val twojaOcenaS: Serial? = dataBaseHandler.findSeries("LOKI")
        val textView = findViewById<TextView>(R.id.twoja_ocena_loki)
        if (twojaOcenaS != null) {
            textView.setText(twojaOcenaS.ocena.toString())
        }
    }

    private fun initListeners() {
        val buttonZmienOcene = findViewById<Button>(R.id.zmien_ocene_loki)
        buttonZmienOcene.setOnClickListener(buttonZmienOceneLokiistener)
    }
    private val buttonZmienOceneLokiistener = View.OnClickListener { callZmienOceneLoki() }

    private fun callZmienOceneLoki() {
        val intentZmienOceneLoki = Intent(this, ZmianaOceny::class.java)
        startActivityForResult(intentZmienOceneLoki, 2)
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
        val textView = findViewById<TextView>(R.id.twoja_ocena_loki)
        textView.text = result
    }
}