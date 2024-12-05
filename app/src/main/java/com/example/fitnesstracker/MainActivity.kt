package com.example.fitnesstracker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var seekTextView: TextView
    lateinit var radioGroup: RadioGroup
    lateinit var kalorie: TextView
    lateinit var dystans: TextView
    lateinit var czas: TextView
    lateinit var button: Button
    lateinit var radioButton : RadioButton
    lateinit var seekBar: SeekBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val treningi = mutableListOf<Trening>(
            Trening("199", "25", "Bieg", "123", 5)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.movieRecycler)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter =  TreningAdapter(treningi) { trening-> showAlertDialog(trening.rodzaj, trening.kalorie, trening.czas)
        }
        recyclerView.adapter = adapter

        radioButton = findViewById(R.id.radioButton)
        radioButton.isChecked=true
        seekTextView = findViewById(R.id.seekText)
        seekBar = findViewById(R.id.seek)
        radioGroup = findViewById(R.id.radio)
        dystans = findViewById(R.id.dystans)
        czas = findViewById(R.id.czas)
        kalorie = findViewById(R.id.kalorie)
        button = findViewById(R.id.dodaj)
        var radioValue = ""

        radioGroup.setOnCheckedChangeListener{ _, checkedId ->
            radioButton = findViewById(checkedId)
            radioValue=radioButton.text.toString()
        }

        button.setOnClickListener{
            val czasT = czas.text.toString()
            val dystansT = dystans.text.toString()
            val kalorieT = kalorie.text.toString()
            val seek = seekTextView.text.toString().toInt()
            val newTrening = Trening(dystansT, czasT, radioValue, kalorieT, seek)
            treningi.add(newTrening)
            adapter.notifyDataSetChanged()
            try {
                JsonManager.savetreningListToJson(this, treningi)
                Toast.makeText(this, "Dane zapisano", Toast.LENGTH_LONG).show()
            } catch (ex: Exception){
                Log.e( "save", "Coś poszło nie tak $ex")
            }
        }



        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                seekTextView.text = p1.toString();
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })


    }

    private fun showAlertDialog(rodzaj: String, kalorie: String, czas: String){
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(rodzaj)
            .setMessage("${kalorie}\n${czas}")
            .setPositiveButton("Ok"){dialog, which ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()

    }
}