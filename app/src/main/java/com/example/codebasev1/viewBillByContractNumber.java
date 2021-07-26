package com.example.codebasev1;

import de.codecrafters.tableview.listeners.TableDataClickListener;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codebasev1.data.DataFactory;
import com.example.codebasev1.data.Fatura;

import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;

public class viewBillByContractNumber extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {
    private SortableFaturaTableView faturaTableView;
    String[] country = { "261010000565", "261010000153"};

    private List<Fatura> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill_by_contractnumber);


        faturaTableView = (SortableFaturaTableView)findViewById(R.id.tableView);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //Creating the ArrayAdapter instance having the sozleşme id list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        faturaTableView.addDataClickListener(new TableDataClickListener<Fatura>() {
            @Override
            public void onDataClicked(int rowIndex, Fatura clickedData) {
                Intent intent = new Intent(getBaseContext(), ShowBillDetailsActivity.class);
                intent.putExtra("donem", list.get(rowIndex).getPeriod());
                intent.putExtra("tutar",String.valueOf(list.get(rowIndex).getAmount()));
                intent.putExtra("durum",list.get(rowIndex).getStatus());
                startActivity(intent);
            Log.e("Cli",""+list.get(rowIndex).getAmount());
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            ((TextView) adapterView.getChildAt(0)).setPadding(5,5,5,5);
        }catch (Exception e){}
        Toast.makeText(getApplicationContext(),country[i] , Toast.LENGTH_SHORT).show();


        if(i==0){
            list=DataFactory.createFirstList();
        }
        else list=DataFactory.createSecondList();

        if (faturaTableView != null) {
            final FaturaTableDataAdapter faturaTableDataAdapter = new FaturaTableDataAdapter(this, list, faturaTableView);
            faturaTableView.setDataAdapter(faturaTableDataAdapter);
        }
        faturaTableView.setSwipeToRefreshEnabled(true);
        faturaTableView.setSwipeToRefreshListener(new SwipeToRefreshListener() {
            @Override
            public void onRefresh(final RefreshIndicator refreshIndicator) {
                faturaTableView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshIndicator.hide();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}