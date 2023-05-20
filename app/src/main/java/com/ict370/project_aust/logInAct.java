package com.ict370.project_aust;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class logInAct extends AppCompatActivity {
    EditText login,pass;
    Button logB, regB;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        login=findViewById(R.id.userlogin);
        pass=findViewById(R.id.passlogin);
        logB=findViewById(R.id.logmail);
        regB=findViewById(R.id.regmail);
        mAuth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        regB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),signUpmail.class);
                startActivity(i);
                finish();
            }
        });
        logB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
    private void loginUser() {
        String email = login.getText().toString();
        String passd = pass.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(passd)) {
            Toast.makeText(getApplicationContext(), "empty field", Toast.LENGTH_SHORT).show();
            login.setText("");
            pass.setText("");
        }else{
            mAuth.signInWithEmailAndPassword(email,passd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i=new Intent(getApplicationContext(),captcha.class);

                        Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                        String s=email.toLowerCase();
                        int j=s.indexOf("@");
                        String s1=s.substring(0,j);
                        i.putExtra("mail",s1);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        login.setText("");
                        pass.setText("");
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),login.class);
        startActivity(intent);
        finish();
    }
}