package com.example.loginactivityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button giris;
    private Button kayit;


   FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEmail = findViewById(R.id.name);
        userPassword = findViewById((R.id.password));
        giris = findViewById(R.id.login);
        kayit = findViewById(R.id.kayit);

        mAuth = FirebaseAuth.getInstance();


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String alinanMail = userEmail.getText().toString().trim();
                String alinanParola = userPassword.getText().toString().trim();

                if(!alinanMail.isEmpty() && !alinanParola.isEmpty())
                {
                    loginFonksiyonu(alinanMail, alinanParola);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Kullanıcı adı ya da parola boş olamaz",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void loginFonksiyonu(String alinanMail, String alinanParola)
    {

        mAuth.signInWithEmailAndPassword(alinanMail, alinanParola)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    startActivity(new Intent(MainActivity.this, Anasayfa.class));
                    finish();

                    Log.d("Email", "signInWithEmail:success");
                }
                else
                {
                    Log.w("Fail","sigInWithEmail:failture", task.getException());
                }

            }
        });
    }

}
