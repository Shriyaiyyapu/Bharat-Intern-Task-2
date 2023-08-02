package com.example.temperature

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var selectedUnitLayout: LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var editInput: EditText
    private lateinit var resultText: TextView
    private lateinit var resultTypeText: TextView

    private lateinit var selectedUnit: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedUnitLayout = findViewById(R.id.selectType)
        selectedUnitText = findViewById(R.id.selectedUnitText)
        editInput = findViewById(R.id.editInput)
        resultText = findViewById(R.id.textResult)
        resultTypeText = findViewById(R.id.textResultType)

        selectedUnit = "Fahrenheit"

        selectedUnitLayout.setOnClickListener {
            showAlertDialog()
        }

        editInput.addTextChangedListener {
            val inputVal = editInput.text.toString()
            val df = DecimalFormat("#.##")

            if (inputVal.isNotEmpty()) {
                val doubleInput = inputVal.toDouble()

                if (selectedUnit == "Fahrenheit") {
                    val result = df.format((doubleInput - 32) * 5 / 9)
                    resultText.text = result
                    resultTypeText.text = "Celsius"
                } else {
                    val result = df.format((doubleInput * 9 / 5) + 32)
                    resultText.text = result
                    resultTypeText.text = "Fahrenheit"
                }
            } else {
                resultText.text = ""
                resultTypeText.text = ""
            }
        }
    }

    private fun showAlertDialog() {
        val items = arrayOf("Fahrenheit", "Celsius")
        val checkedItem = if (selectedUnit == "Fahrenheit") 0 else 1

        val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
        alertDialogBuilder.setTitle("Select Input Unit")
        alertDialogBuilder.setSingleChoiceItems(items, checkedItem) { dialog, which ->
            selectedUnit = items[which]
            selectedUnitText.text = selectedUnit
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
