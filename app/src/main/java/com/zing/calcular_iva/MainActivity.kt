package com.zing.calcular_iva

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()


        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val editTextMont: EditText = findViewById(R.id.editTextNumberDecimal)
        val spinner: Spinner = findViewById(R.id.spinner)
        val buttonCalcular: Button = findViewById(R.id.button)
        val textViewResultado: TextView = findViewById(R.id.textView4)


        val spinnerItems = resources.getStringArray(R.array.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        var ivaRate: Double = 1.16


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, view: View?, position: Int, id: Long) {
                ivaRate = if (position == 0) {
                    1.16
                } else {
                    1.10
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {

            }
        }


        buttonCalcular.setOnClickListener {

            val montoStr = editTextMont.text.toString()

            if (montoStr.isNotEmpty()) {

                val monto = montoStr.toDouble()


                val resultado = monto * ivaRate


                textViewResultado.text = "IVA: %.2f".format(resultado)
            } else {

                textViewResultado.text = "Por favor ingresa un monto válido."
            }
        }
    }
}
