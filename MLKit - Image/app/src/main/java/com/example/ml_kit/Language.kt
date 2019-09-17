package com.example.ml_kit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.languageid.IdentifiedLanguage

class Language : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        dilAlgilama()
    }

    private fun dilAlgilama()
    {
       val button = findViewById<Button>(R.id.tanima)
        button.setOnClickListener {
            val editText = findViewById<EditText>(R.id.kelime)
            val languageIdentifier = FirebaseNaturalLanguage.getInstance().languageIdentification
            val textView = findViewById<TextView>(R.id.yazi)
            textView.text = ""

            languageIdentifier.identifyPossibleLanguages(editText.text.toString())
                .addOnSuccessListener {
                    onSuccess(it)
                }
                .addOnFailureListener{
                    //error
                }
        }

    }

    private fun onSuccess(identifiedLanguages: List<IdentifiedLanguage>)
    {
        for (identifiedLanguage in identifiedLanguages)
        {
            val language = identifiedLanguage.languageCode
            val confidence = identifiedLanguage.confidence
            val textView = findViewById<TextView>(R.id.yazi)
            textView.text = textView.text.toString() + "$language $confidence\n"
        }
    }


}
