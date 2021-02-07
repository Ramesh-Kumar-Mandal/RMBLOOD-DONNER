package com.example.blooddonnerapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login, register;
ProgressBar progress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);


        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(getApplicationContext(), HOMEMAINActivity.class);
            startActivity(intent);
            finish();
        }else{

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (isFeildEmpty(password) &&
                        isEmailvalueEmpty(email)) {

                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(LoginActivity.this, "Login  sucess" + email, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, HOMEMAINActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterShotActivity.class));
            }
        });
    }

    public  boolean isFeildEmpty(EditText view){
        String value=view.getText().toString();
        if(value.length()>0){
            return  true;
        }else {
            view.setError("Enter Valid Password");
            view.requestFocus();
            return  false;

        }
    }

    public  boolean isEmailvalueEmpty(EditText view){
        String value=view.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            return true;
        }else {
            view.setError("Enter Register Email");
            view.requestFocus();
            return false;
        }
    }

}