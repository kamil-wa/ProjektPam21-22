package com.example.mojeseriale

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
        Toast.makeText(this, "Witam w aplikacji", Toast.LENGTH_LONG).show()

        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val series1 = Serial("MANDALORIAN", 0, 8)
        val series2 = Serial("LOKI", 0, 9)
        val series3 = Serial("DOM Z PAPIERU", 0, 7)

        dataBaseHandler.addSeries(series1)
        dataBaseHandler.addSeries(series2)
        dataBaseHandler.addSeries(series3)


        }

    private fun initListeners(){
        val buttonMando = findViewById<Button>(R.id.mando_button)
        buttonMando.setOnClickListener(buttonMandoListener)

        val buttonLoki = findViewById<Button>(R.id.loki_button)
        buttonLoki.setOnClickListener(buttonLokiListener)

        val buttonDomZPapieru = findViewById<Button>(R.id.domZpapieru_button)
        buttonDomZPapieru.setOnClickListener(buttonDomZPapieruListener)

        val ButtonOcenaMando = findViewById<Button>(R.id.button_ocena_mando)
        ButtonOcenaMando.setOnClickListener(ButtonOcenaMandoListener)

        val ButtonOcenaLoki = findViewById<Button>(R.id.button_ocena_loki)
        ButtonOcenaLoki.setOnClickListener(ButtonOcenaLokiListener)

        val ButtonOcenaDomZPapieru = findViewById<Button>(R.id.button_ocena_domZpapieru)
        ButtonOcenaDomZPapieru.setOnClickListener(ButtonOcenaDomZPapieruListener)
    }

    private val buttonMandoListener = View.OnClickListener { callMandoActivity() }
    private val buttonLokiListener = View.OnClickListener { callLokiActivity() }
    private val buttonDomZPapieruListener = View.OnClickListener { callDomZPapieruActivity() }

    private val ButtonOcenaMandoListener = View.OnClickListener { setGradeToMando() }
    private val ButtonOcenaLokiListener = View.OnClickListener { setGradeToLoki() }
    private val ButtonOcenaDomZPapieruListener = View.OnClickListener { setGradeToOcenaDomZPapieru() }

    private fun callMandoActivity() {
        val mandoIntent = Intent(this, MandoActivity::class.java)
        startActivity(mandoIntent)
    }


    private fun callLokiActivity() {
        val lokiIntent = Intent(this, LokiActivity::class.java)
        startActivity(lokiIntent)
    }
    private fun callDomZPapieruActivity() {
        val DomZPapieruIntent = Intent(this, domZpapieruActivity::class.java)

        startActivity(DomZPapieruIntent)
    }

    private fun toast(){
        Toast.makeText(this, "Dziękuję za ocenę", Toast.LENGTH_LONG).show()
    }




    private fun setGradeToMando(){
    val dataBaseHandler = DataBaseHelper(this, null, null, 1)
    val newGrate  = findViewById<EditText>(R.id.ocena_mando)
        toast()
    dataBaseHandler.setGradeTo("MANDALORIAN", Integer.parseInt(newGrate.text.toString()))
    }

    private fun setGradeToLoki(){
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val newGrate  = findViewById<EditText>(R.id.ocena_loki)
        toast()
        dataBaseHandler.setGradeTo("LOKI", Integer.parseInt(newGrate.text.toString()))
    }

    private fun setGradeToOcenaDomZPapieru(){
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val newGrate  = findViewById<EditText>(R.id.ocena_domZpapieru)
        toast()
        dataBaseHandler.setGradeTo("DOM Z PAPIERU", Integer.parseInt(newGrate.text.toString()))
    }



}


