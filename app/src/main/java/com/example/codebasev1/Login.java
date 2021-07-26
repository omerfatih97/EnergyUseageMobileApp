package com.example.codebasev1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

public class Login extends AppCompatActivity {

    Button login;
    EditText username,password;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private String kullanici,sifre;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    //End Declaring connection variables
    ProgressBar progressbar;

    //DBConnection dbConnection=new DBConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn= findViewById(R.id.buttonLogin);
        Button odemeNoktaBtn= findViewById(R.id.loginOdemeNoktaBtn);
        progressbar=findViewById(R.id.progressBarLogin);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        progressbar.setVisibility(View.GONE);

        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            username.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressbar.setVisibility(View.VISIBLE);

                //dbConnection.setUsername(username.getText().toString());
                //dbConnection.setPassword(password.getText().toString());
                String z;

                boolean isSuccessful=false;
                try {

                    //con = dbConnection.connectionclass();
                    if (false)//(con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        if(true)//dbConnection.login_connection())
                        {   z = "Login Success";
                            Log.e("Bildirim Login Sayfa:",z);
                            if (saveLoginCheckBox.isChecked()) {
                                loginPrefsEditor.putBoolean("saveLogin", true);
                                loginPrefsEditor.putString("username", username.getText().toString());
                                loginPrefsEditor.putString("password", password.getText().toString());
                                loginPrefsEditor.commit();
                            } else {
                                loginPrefsEditor.clear();
                                loginPrefsEditor.commit();
                            }
                            isSuccessful=true;
                        }
                        else
                        {
                            z = "Incorrect Username or Password";//"Şifre veya Kullanıcı Adı Hatalı!";
                            Log.e("Bildirim Login Sayfa:",z);
                        }
                    }
                    //Log.e("Bildirim Login Sayfa:",con.toString());
                }catch (Exception e){
                    Log.e("Error Login Page:",e.toString());
                }
                if (isSuccessful){
                    Intent intent = new Intent(getBaseContext(), MainSideBar.class);
                    intent.putExtra("kullanici",username.getText().toString());
                    startActivity(intent);
                }

            }
        });

        odemeNoktaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ShowMap.class);
                startActivity(intent);
            }
        });
    }

    
    @Override
    protected void onStop () {
        super .onStop() ;
        /*Intent serviceIntent = new Intent(this, MyFirebaseMessagingService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);*/

    }
}