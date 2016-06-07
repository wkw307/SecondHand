package com.finaldemo.secondhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Created by wkw307 on 2016/6/7.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    EditText etUsername,etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etName);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();
        postData.put("btnLogin","Login");
        postData.put("mobile", "android");
        postData.put("txtUsername",etUsername.getText().toString());
        postData.put("txtPassword",etPassword.getText().toString());

        PostResponseAsyncTask task = new PostResponseAsyncTask( this, postData);
        task.execute("http://13.67.105.113/test/login.php");

    }

    @Override
    public void processFinish(String result) {
        Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
        if(result.equals("success")){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

