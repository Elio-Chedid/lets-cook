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

public class signUpmail extends AppCompatActivity {
   EditText mail,pass,phone;
   Button signUP;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    static String k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upmail);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.passUP);
        phone=findViewById(R.id.phone);
        signUP=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mail.getText().toString().trim().equals("")||pass.getText().toString().trim().equals("")||phone.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "make sure to fill all the required fields", Toast.LENGTH_SHORT).show();
                    mail.setText("");
                    pass.setText("");
                    phone.setText("");
                }
                else
                {
                    String email = mail.getText().toString();
                    int index=email.indexOf("@");
                    int lastIndex=email.length();
                    String domain=email.substring(index,lastIndex);
                    if (domain.equals("@letscook.com")) {
                        createU();
                        Intent i = new Intent(getApplicationContext(), logInAct.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"make sure to use the correct domain",Toast.LENGTH_SHORT).show();
                        mail.setText("");
                        pass.setText("");
                        phone.setText("");
                    }
                }
            }
        });



    }

    private void createU() {
        String email = mail.getText().toString();
        String passd = pass.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(passd)) {
            Toast.makeText(getApplicationContext(), "empty field", Toast.LENGTH_SHORT).show();
            mail.setText("");
            pass.setText("");
        } else {


                mAuth.createUserWithEmailAndPassword(email, passd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "register success", Toast.LENGTH_SHORT).show();
                            upUser();

                        } else {
                            Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }
    }
    private void upUser(){
        String uname=mail.getText().toString().toLowerCase();
        String phon=phone.getText().toString();
        Users u=new Users(uname,phon);
        int i=uname.indexOf("@");
        k=uname.substring(0,i);
        u.setUserName(k);
        reference.child(k).setValue(u);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),logInAct.class);
        startActivity(intent);
        finish();
    }
}