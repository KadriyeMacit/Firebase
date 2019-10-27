package com.example.loginactivityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText yeniUserEmail;
    private EditText yeniUserParola;
    private EditText yeniUserParolaTekrar;
    private Button yeniKayit;

    private FirebaseAuth mAuth;

    private String alinanMail;
    private String alinanParola;
    private String alinanParolaTekrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        yeniUserEmail = findViewById(R.id.newUserName);
        yeniUserParola = findViewById(R.id.newUserpassword);
        yeniUserParolaTekrar = findViewById(R.id.againUserpassword);
        yeniKayit = findViewById(R.id.kayitButonu);


        mAuth = FirebaseAuth.getInstance();


        yeniKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kontrol();

            }
        });

    }

    private void kontrol()
    {
        alinanMail = yeniUserEmail.getText().toString().trim();
        alinanParola = yeniUserParola.getText().toString().trim();
        alinanParolaTekrar = yeniUserParolaTekrar.getText().toString().trim();

        if(!alinanMail.isEmpty() && !alinanParola.isEmpty() && !alinanParolaTekrar.isEmpty())
        {
            if(alinanParola.equals(alinanParolaTekrar))
            {
                kayıtIslemi();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Kayıt için tüm alanları doldurunuz",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void kayıtIslemi()
    {
        mAuth.createUserWithEmailAndPassword(alinanMail, alinanParola)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            Toast.makeText(getApplicationContext(),
                                    "Kayıt işlemi yapıldı",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                    }
                });
    }




}
