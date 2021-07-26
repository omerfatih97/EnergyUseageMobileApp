package com.example.codebasev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.codebasev1.data.DataFactory;
import com.example.codebasev1.data.Fatura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;

public class FaturaListWithDetails extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private List<Fatura> temp;


    private List<String> donem= new ArrayList<>();
    private List<String> tutar= new ArrayList<>();
    private List<String> durum= new ArrayList<>();
    private DBConnection db = new DBConnection();

    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    List<String> list3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura_list_with_details);


        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);


        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                /*Toast.makeText(FaturaListWithDetails.this, listHash.get(listDataHeader.get(groupPosition)).toString(),
                        Toast.LENGTH_LONG).show();*/

                /*String tutar=listHash.get(listDataHeader.get(groupPosition)).toString();
                String donem,durum=tutar;

                donem=listDataHeader.get(groupPosition).toString();
                tutar=tutar.substring(tutar.indexOf("DURUM")+7,tutar.indexOf("TL"));
                durum=durum.substring(durum.indexOf("TL")+3,durum.indexOf("]"));
                try{
                    durum=durum.replaceAll(" ","");
                    tutar=tutar.replaceAll(" ","");
                }catch (Exception e){}
                if (tutar.isEmpty())tutar="Tutar Bulunamadı!";
                Log.d("Test tutar",tutar);
                Log.d("Test durum",durum);
                Log.d("Test donem",donem);
                db.setFatura(Integer.valueOf(tutar));

                Intent intent = new Intent(getBaseContext(), FaturaDetayliGosterActivity.class);
                intent.putExtra("tutar",tutar);
                intent.putExtra("durum",durum);
                intent.putExtra("donem",donem);
                startActivity(intent);*/
                return true;  // i missed this
            }
        });
    }

    public static String padRight(String s, int n) {
        return String.format("%" + n + "s", s);
    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        int tutar1;
        String durumu;

        listDataHeader.add("Ocak 2020");
        listDataHeader.add("Şubat 2020");
        listDataHeader.add("Haziran 2020");


        tutar1=15;
        durumu="Paid";
        list1.add("Bill Amount"+padRight("Status",45)+"\n"+ padRight(tutar1+" TL",15)+padRight(durumu,55));

        tutar1=24;
        durumu="Paid";
        list2.add("Bill Amount"+padRight("Status",45)+"\n"+ padRight(tutar1+"TL",15)+padRight(durumu,55));

        tutar1=12;
        durumu="Not Paid";
        list3.add("Bill Amount"+padRight("Status",45)+"\n"+ padRight(tutar1+"TL",15)+padRight(durumu,55));

        listHash.put(listDataHeader.get(0),list1);
        listHash.put(listDataHeader.get(1),list2);
        listHash.put(listDataHeader.get(2),list3);
    }
}