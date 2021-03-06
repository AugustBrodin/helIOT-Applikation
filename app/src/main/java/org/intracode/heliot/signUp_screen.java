package org.intracode.heliot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signUp_screen extends AppCompatActivity implements View.OnClickListener {

    EditText UserName_editText, Password_editText;

    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        UserName_editText = (EditText)findViewById(R.id.SignUp_EnterEmailSpace_editText);
        Password_editText = (EditText)findViewById(R.id.SignUp_EnterPasswordSpace_editText);

        progressBar = (ProgressBar)findViewById(R.id.SignUp_progressBar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.SignUp_CreateAccount_textClick).setOnClickListener(this);
        findViewById(R.id.SignUp_LogIn_button).setOnClickListener(this);
    }

    public void registerUser(){

        String username = UserName_editText.getText().toString().trim();
        String password = Password_editText.getText().toString().trim();

        if(username.isEmpty()){
            UserName_editText.setError("Email saknas");
            UserName_editText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
             UserName_editText.setError("Skriv in en giltlig email");
             UserName_editText.requestFocus();
             return;
        }

        if(password.isEmpty()) {
            Password_editText.setError("Password saknas");
            Password_editText.requestFocus();
            return;

        }

        if(password.length()<6){
            Password_editText.setError("Lösenord måste innehålla fler än 6 tecken");
            Password_editText.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registrering lyckades!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signUp_screen.this, Launcher_screen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Redan registrerad", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.SignUp_LogIn_button:

                registerUser();
                break;

            case R.id.SignUp_CreateAccount_textClick:
                startActivity(new Intent(this, Launcher_screen.class));
                break;
        }

    }

}


