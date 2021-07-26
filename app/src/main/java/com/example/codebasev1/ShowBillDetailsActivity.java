package com.example.codebasev1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowBillDetailsActivity extends AppCompatActivity {

    //private final DBConnection db=new DBConnection();
    private ProgressBar progressBar;
    private double tutar;
    private String temp;
    private TextView textView,durumTxt,donemTxt,seriTxt;
    private Button mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_show);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            progressBar=findViewById(R.id.progressBar_ftrholo2);
            textView=findViewById(R.id.ftrholo_txt2);
            durumTxt=findViewById(R.id.durum_detayTxt2);
            donemTxt=findViewById(R.id.donem_f_d_Txt2);
            seriTxt=findViewById(R.id.seriNoTxt2);
            mapBtn=findViewById(R.id.mapBtn2);
        } else {
            progressBar=findViewById(R.id.progressBar_ftrholo);
            textView=findViewById(R.id.ftrholo_txt);
            durumTxt=findViewById(R.id.durum_detayTxt);
            donemTxt=findViewById(R.id.donem_f_d_Txt);
            seriTxt=findViewById(R.id.seriNoTxt);
            mapBtn=findViewById(R.id.mapBtn);
        }

        fill_datas();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void fill_datas(){
        try {
            textView.setText(getIntent().getStringExtra("tutar")+"\nTL");
            durumTxt.setText(getIntent().getStringExtra("durum"));
            donemTxt.setText(getIntent().getStringExtra("donem"));
            temp=(String)getIntent().getStringExtra("durum");
            seriTxt.setText(""+generateTicketNumber(10000000, 99999999));
        }catch (Exception e){
            Log.e("Hata","err");
        }

        if (temp.equals("Paid") || temp.equals("PAID")  ){
            durumTxt.setTextColor(Color.parseColor("#07FA11"));
            durumTxt.setTypeface(durumTxt.getTypeface(), Typeface.NORMAL);
            mapBtn.setVisibility(View.GONE);
        }
        else {
            durumTxt.setTextColor(Color.parseColor("#FF0000"));
            durumTxt.setTypeface(durumTxt.getTypeface(), Typeface.BOLD);
            mapBtn.setVisibility(View.VISIBLE);
        }

        animate_pr();

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ShowMap.class);
                startActivity(intent);
            }
        });
    }

    public void animate_pr(){
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, 0, 100);
        anim.setDuration(2000);
        progressBar.startAnimation(anim);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_bill_details_show);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            progressBar=findViewById(R.id.progressBar_ftrholo2);
            textView=findViewById(R.id.ftrholo_txt2);
            durumTxt=findViewById(R.id.durum_detayTxt2);
            donemTxt=findViewById(R.id.donem_f_d_Txt2);
            seriTxt=findViewById(R.id.seriNoTxt2);
            mapBtn=findViewById(R.id.mapBtn2);
        } else {
            progressBar=findViewById(R.id.progressBar_ftrholo);
            textView=findViewById(R.id.ftrholo_txt);
            durumTxt=findViewById(R.id.durum_detayTxt);
            donemTxt=findViewById(R.id.donem_f_d_Txt);
            seriTxt=findViewById(R.id.seriNoTxt);
            mapBtn=findViewById(R.id.mapBtn);
        }
        fill_datas();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fill_datas();
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

    public static int generateTicketNumber(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}