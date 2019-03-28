package com.example.rahul.alert_haiti;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.alert_haiti.Api.CustomCallBack.AsyncLoginResponse;
import com.example.rahul.alert_haiti.Api.LoginApi;

public class login extends AppCompatActivity {

    TextView username,password;
    AlertDialog.Builder alertDialogBuilder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        alertDialogBuilder= new AlertDialog.Builder(this);
    }


    public void onLoginButtonClick(View view)
    {
        String uname,pwd;
        uname=username.getText().toString();
        pwd=password.getText().toString();
        if(uname.length()>0 && pwd.length()>0)
        {
            //api request made over here
            new LoginApi(new AsyncLoginResponse() {
                @Override
                public void success(String id) {
                    startActivity(new Intent(getBaseContext(),HomeScreen.class));
                }

                @Override
                public void fail(String description) {
                    alertDialogBuilder.setPositiveButton(description, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog=alertDialogBuilder.create();
                    dialog.show();
                }
            },uname,pwd);
            //api request ends heree
        }
    }


    public void onSigupButtonClicked(View view)
    {startActivity(new Intent(this,Signup.class));}
}
