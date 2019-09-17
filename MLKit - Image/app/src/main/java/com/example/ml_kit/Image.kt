package com.example.ml_kit

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_image.*

class Image : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        setClickListenerImagePicker()
    }

    private fun setClickListenerImagePicker() {
        val button = findViewById<Button>(R.id.buttonImageLabelling)
        button.setOnClickListener{
            val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                pickImage()
            }
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                } else {
                    Toast.makeText(this,
                        "İzin ver de çalışalım", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        textViewImageLabelling.text = ""
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageViewImageLabelling.setImageURI(data?.data)

            val image = FirebaseVisionImage.fromFilePath(this, data!!.data)
            val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()

            labeler.processImage(image)
                .addOnSuccessListener { labels ->
                    for (label in labels) {
                        val text = label.text
                        val confidence = label.confidence
                        val otherLabel = label.entityId
                        textViewImageLabelling.text =
                            textViewImageLabelling.text.toString() + "$text $otherLabel $confidence\n"
                    }
                }
                .addOnFailureListener { e ->

                }
        }
    }

    companion object {
        private const val PERMISSION_CODE = 1000
        private const val IMAGE_PICK_CODE = 1001
    }



}
