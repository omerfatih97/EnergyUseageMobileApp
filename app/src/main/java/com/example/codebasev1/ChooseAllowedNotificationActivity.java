package com.example.codebasev1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class ChooseAllowedNotificationActivity extends AppCompatActivity {


    private SharedPreferences notificationPreferences;
    private SharedPreferences.Editor notificationPrefsEditor;
    private Boolean saveFatura;
    private Boolean saveOdemeGecikmesi;
    private Boolean saveFaturaOdenmedi;
    private Boolean saveKampanyalar;
    private Boolean saveKesinti;
    private Boolean saveAcilma;
    private Boolean saveTamir;
    private Switch FaturaCheckBox,OdemeGecikmesiCheckBox,FaturaOdenmediCheckBox,KampanyalarCheckBox,KesintiCheckBox,AcilmaCheckBox,TamirCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_allowed_notification);
        FaturaCheckBox=findViewById(R.id.switchFatura);
        OdemeGecikmesiCheckBox=findViewById(R.id.switchOdeme);
        FaturaOdenmediCheckBox=findViewById(R.id.switchOdenmedi);
        KampanyalarCheckBox=findViewById(R.id.switchKampanya);
        KesintiCheckBox=findViewById(R.id.switchKesme);
        AcilmaCheckBox=findViewById(R.id.switchActirma);
        TamirCheckBox=findViewById(R.id.switchTamir);

        try{

        notificationPreferences = getSharedPreferences("bildirimPrefs", MODE_PRIVATE);
        notificationPrefsEditor = notificationPreferences.edit();

        saveFatura = notificationPreferences.getBoolean("saveFatura", false);
        saveOdemeGecikmesi = notificationPreferences.getBoolean("saveOdemeGecikmesi", false);
        saveFaturaOdenmedi = notificationPreferences.getBoolean("saveFaturaOdenmedi", false);
        saveKampanyalar = notificationPreferences.getBoolean("saveKampanyalar", false);
        saveKesinti = notificationPreferences.getBoolean("saveKesinti", false);
        saveAcilma = notificationPreferences.getBoolean("saveAcilma", false);
        saveTamir = notificationPreferences.getBoolean("saveTamir", false);

        if (saveFatura == true) {
            FaturaCheckBox.setChecked(true);
        }else {FaturaCheckBox.setChecked(false);}

        if (saveOdemeGecikmesi == true) {
            OdemeGecikmesiCheckBox.setChecked(true);
        }else {OdemeGecikmesiCheckBox.setChecked(false);}
        if (saveFaturaOdenmedi == true) {
            FaturaOdenmediCheckBox.setChecked(true);
        }else {FaturaOdenmediCheckBox.setChecked(false);}
        if (saveKampanyalar == true) {
            KampanyalarCheckBox.setChecked(true);
        }else {KampanyalarCheckBox.setChecked(false);}
        if (saveKesinti == true) {
            KesintiCheckBox.setChecked(true);
        }else {KesintiCheckBox.setChecked(false);}
        if (saveAcilma == true) {
            AcilmaCheckBox.setChecked(true);
        }else {AcilmaCheckBox.setChecked(false);}
        if (saveTamir == true) {
            TamirCheckBox.setChecked(true);
        }else {TamirCheckBox.setChecked(false);}

       }catch (Exception e){

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

    @Override
    protected void onStop() {
        super.onStop();

        notificationPrefsEditor.clear();
        notificationPrefsEditor.commit();

        if (FaturaCheckBox.isChecked()) {
            notificationPrefsEditor.putBoolean("saveFatura", true);
            abone("Fatura",true);
        }
        else {
            notificationPrefsEditor.putBoolean("saveFatura", false);
            abone("Fatura",false);
        }

        if (OdemeGecikmesiCheckBox.isChecked()) {
            notificationPrefsEditor.putBoolean("saveOdemeGecikmesi", true);
            abone("Gecikme",true);
        }
        else  {
            notificationPrefsEditor.putBoolean("saveOdemeGecikmesi", false);
            abone("Gecikme",false);
        }

        if (FaturaOdenmediCheckBox.isChecked())  {
            notificationPrefsEditor.putBoolean("saveFaturaOdenmedi", true);
            abone("Odenmedi",true);
        }
        else  {
            notificationPrefsEditor.putBoolean("saveFaturaOdenmedi", false);
            abone("Odenmedi",false);
        }

        if (KampanyalarCheckBox.isChecked())  {
            notificationPrefsEditor.putBoolean("saveKampanyalar", true);
            abone("Kampanyalar",true);
        }
        else  {
            notificationPrefsEditor.putBoolean("saveKampanyalar", false);
            abone("Kampanyalar",false);
        }

        if (KesintiCheckBox.isChecked()) {
            notificationPrefsEditor.putBoolean("saveKesinti", true);
            abone("Kesinti",true);
        }
        else  {
            notificationPrefsEditor.putBoolean("saveKesinti", false);
            abone("Kesinti",false);
        }

        if (AcilmaCheckBox.isChecked()) {
            notificationPrefsEditor.putBoolean("saveAcilma", true);
            abone("Actirma",true);
        }
        else  {
            notificationPrefsEditor.putBoolean("saveAcilma", false);
            abone("Actirma",false);}

        if (TamirCheckBox.isChecked()) {
            notificationPrefsEditor.putBoolean("saveTamir", true);
            abone("Tamir",true);
        }
        else  {
            notificationPrefsEditor.putBoolean("saveTamir", false);
            abone("Tamir",false);
        }

        notificationPrefsEditor.commit();
    }

    private void abone(final String subject, boolean result) {
        if(result){
            FirebaseMessaging.getInstance().subscribeToTopic(subject)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Can Taken.";
                            if (!task.isSuccessful()) {
                                msg = "Cannot Taken!";
                            }
                            Log.e("Result:",subject+" Notification "+ msg);
                        }
                    });
        }
        else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(subject)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Unsubscribed.";
                            if (!task.isSuccessful()) {
                                msg = "Subscribtion Will Be Continue!";
                            }
                            Log.e("Result:",subject+" Notification "+ msg);
                        }
                    });
        }

    }
}