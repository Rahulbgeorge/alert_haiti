package com.example.rahul.alert_haiti;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText email,username,password,password1;
    AlertDialog.Builder alertDialogBuilder ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        password1=findViewById(R.id.password2);
    }

    public void onSignUpClicked(View view)
    {
        String email=this.email.getText().toString();
        String username=this.username.getText().toString();
        String password=this.password.getText().toString();
        String password2=this.password1.getText().toString();
        if(email.length()>0 && username.length()>0)
        {
            if(password.equals(password2))
            {

            }
            else
            {
                Toast.makeText(this, "Invalid password found", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Invalid email or username found", Toast.LENGTH_SHORT).show();
        }
    }
}
