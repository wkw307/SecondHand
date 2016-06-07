package com.finaldemo.secondhand;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignActivity extends AppCompatActivity implements AsyncResponse {

    EditText etUsername, etPassword,etPassword2,etEmail;
    Button btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword2 = (EditText)findViewById(R.id.etPassword2);
        etEmail =(EditText)findViewById(R.id.etEmail);
        btnSign = (Button)findViewById(R.id.btnSign);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().equals("")){
                    Toast.makeText(SignActivity.this, "Password invalid!",
                            Toast.LENGTH_LONG).show();
                }
                else if(!etPassword.getText().toString().equals(etPassword.getText().toString()))
                {
                    Toast.makeText(SignActivity.this, "Passwords not same!",
                            Toast.LENGTH_LONG).show();
                }
                else if(isEmail(etEmail.getText().toString())) {
                    HashMap postData = new HashMap();
                    //postData.put("btnLogin", "Login");
                   // postData.put("mobile", "android");
                    postData.put("txtUsername", etUsername.getText().toString());
                    postData.put("txtPassword", etPassword.getText().toString());
                    postData.put("txtEmail", etEmail.getText().toString());
                    PostResponseAsyncTask loginTask =
                            new PostResponseAsyncTask(SignActivity.this, postData);
                    loginTask.execute("http://13.67.105.113/connection/sign2.php");
                }
                else{
                    Toast.makeText(SignActivity.this, "Invalid Email!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void processFinish(String output) {

        if(Integer.parseInt(output)>=0){
            Toast.makeText(this, "Sign Successfully",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass( SignActivity.this , LoginActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Username exists!",
                    Toast.LENGTH_LONG).show();
        }


    }
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
