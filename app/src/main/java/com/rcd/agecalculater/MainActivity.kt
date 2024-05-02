package com.rcd.agecalculater

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var selectDateValue : TextView? = null
    var minCalculateValue : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button : Button = findViewById(R.id.select_date)

        button.setOnClickListener {
            val cd = Calendar.getInstance()
            val year = cd.get(Calendar.YEAR)
            val month = cd.get(Calendar.MONTH)
            val day = cd.get(Calendar.DAY_OF_MONTH)

            val dpv = DatePickerDialog(this,{
                    view, selectedyear, selectedmonth, selecteddate ->
                selectDateValue = findViewById(R.id.date)

                val sel_date = "$selecteddate/${selectedmonth+1}/$selectedyear"
                selectDateValue?.text = sel_date
                minCalculateValue = findViewById(R.id.minutes)

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val selected_date  = sdf.parse(sel_date)

                val current_date = sdf.parse(sdf.format(System.currentTimeMillis()))

                val ans = (current_date.time/60000) - (selected_date.time/60000)


                minCalculateValue?.text = ans.toString()

                Toast.makeText(this,"year was $selecteddate, ${selectedmonth+1}, $selectedyear", Toast.LENGTH_LONG).show()

            }, year, month, day)

            dpv.datePicker.maxDate=System.currentTimeMillis() - (86400000)
            dpv.show()



        }
    }
}