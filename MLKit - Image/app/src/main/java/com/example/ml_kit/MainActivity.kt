package com.example.ml_kit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        girisButonu()
        imageButonu()
    }

    private fun girisButonu()
    {
        val button = findViewById<Button>(R.id.gecis)
        button.setOnClickListener {
            val intent = Intent(this, Language::class.java)
            startActivity(intent)
        }
    }

    private fun imageButonu()
    {
        val button = findViewById<Button>(R.id.gecis2)
        button.setOnClickListener {
            val intent = Intent(this, Image::class.java)
            startActivity(intent)
        }
    }


}
