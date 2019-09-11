package com.example.ml_kit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        girisButonu();
    }


    private fun girisButonu()
    {
        val buton = findViewById<Button>(R.id.gecis)
        buton.setOnClickListener{
            val intent = Intent(this, Language::class.java)
            startActivity(intent)
        }
    }

}
