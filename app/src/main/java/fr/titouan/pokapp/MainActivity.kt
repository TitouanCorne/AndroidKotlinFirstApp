package fr.titouan.pokapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var btnCompare : Button
    lateinit var progressBar: ProgressBar
    lateinit var txtHistory : TextView
    lateinit var txtNumber : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialisation des vues
        btnCompare = findViewById<Button>(R.id.btnCompare)
        txtNumber = findViewById(R.id.txtNumber)
        progressBar = findViewById(R.id.progressBar)
        txtHistory = findViewById(R.id.txtHistory)

        val numberToGuess = 1 + (Math.random() * 100).toInt()
        var numberTries = 0
        val maxTries = 10
        var retour : String

        btnCompare.setOnClickListener{
            val number = txtNumber.text.toString().toIntOrNull()
            if(number != null){
                if(number == numberToGuess){
                    retour = "Bravo ! C'est gagné"
                }else if (number < numberToGuess){
                    retour = "Le nombre à trouver est plus grand !"
                }else {
                    retour = "Le nombre à trouver est plus petit !"
                }
            }else {
                retour = "Veuillez saisir un nombre !"
            }
            numberTries++ //on incrémente la barre de progression
            progressBar.progress = numberTries


            if(numberTries == maxTries && number != numberToGuess) {
                retour = "Désolé, vous avez atteint le nombre maximum d'essais. Le nombre était $numberToGuess."
                btnCompare.isEnabled = false  // Désactive le bouton après avoir atteint le max d'essais
            }

            txtHistory.text = retour
        }
    }
}