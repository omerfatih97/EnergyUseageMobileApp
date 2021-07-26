package com.example.codebasev1;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QR_Scan extends AppCompatActivity {
    private TextView textView;
    private String kullanici;
    private String value;

    DBConnection db=new DBConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r__scan);
        textView = findViewById(R.id.textView);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        try {
            value = getIntent().getStringExtra("kullanici");
        }catch (Exception e){
            value="hasan11";
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ScanButton(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                textView.setText("İşlem İptal Edildi.");
            }else {
                /*
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(intentResult.getContents()));
                startActivity(viewIntent);
                textView.setText(intentResult.getContents());*/
                db.setUsername(value);
                db.findUserId();
                db.put_UUI(intentResult.getContents());
                textView.setText("Başarılı");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}