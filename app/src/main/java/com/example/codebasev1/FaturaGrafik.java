package com.example.codebasev1;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class FaturaGrafik extends AppCompatActivity {

    private int[] tutarlar =new int[]{ 78, 78,90, 100, 125, 160,150, 130, 130, 68, 85,77,91};
    private int[] graph2  = new int[]{90, 90, 90, 90,90,90,90,90,110,90,90,90,90};
    private final String[] months = new String[]{"Ocak", "Şubat", "Mart", "Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık","Ocak"};
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<Entry> x = new ArrayList<>();
    LineDataSet lineDataSet,lineDataSet2;
    ArrayList<ILineDataSet> sets;
    LineChart lineChart;
    CheckBox checkBoxtutar,checkBoxenerji;

    List<Integer> colors1,colors2;

    private boolean tutar=false,enerji=false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_fatura_grafik);
            lineChart = (LineChart)findViewById(R.id.lineChart2);
            checkBoxtutar = findViewById(R.id.checkBoxTutar2);
            checkBoxenerji = findViewById(R.id.checkBoxEnerji2);
        } else {
            setContentView(R.layout.activity_fatura_grafik);
            lineChart = (LineChart)findViewById(R.id.lineChart);
            checkBoxtutar = findViewById(R.id.checkBoxTutar);
            checkBoxenerji = findViewById(R.id.checkBoxEnerji);
        }
        set_Layout();
        showData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        showData();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_fatura_grafik);
            lineChart = (LineChart)findViewById(R.id.lineChart2);
            checkBoxtutar = findViewById(R.id.checkBoxTutar2);
            checkBoxenerji = findViewById(R.id.checkBoxEnerji2);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_fatura_grafik);
            lineChart = (LineChart)findViewById(R.id.lineChart);
            checkBoxtutar = findViewById(R.id.checkBoxTutar);
            checkBoxenerji = findViewById(R.id.checkBoxEnerji);
        }

        if(tutar&&enerji){
            checkBoxtutar.setChecked(true);
            checkBoxenerji.setChecked(true);
        }else if(!tutar&&enerji){
            checkBoxtutar.setChecked(false);
            checkBoxenerji.setChecked(true);
        }else if(tutar && !enerji){
            checkBoxtutar.setChecked(true);
            checkBoxenerji.setChecked(false);
        }else {
            checkBoxtutar.setChecked(false);
            checkBoxenerji.setChecked(false);
        }

        Listener();
        showData();
    }

    private void fill_data(){
        int temp=0,temp2=0;
        colors1 =new ArrayList<>();
        colors2 =new ArrayList<>();

        for(int i=0;i<months.length;i++){
            x.add(new Entry(i,graph2[i]));
            entries.add(new Entry(i,tutarlar[i]));

            if(i!=0){
                if(tutarlar[i]>temp &&  (tutarlar[i]-temp>30) ){
                    colors1.add(Color.rgb(255, 100, 0));
                }else if(tutarlar[i]>temp && (tutarlar[i]-temp<=30) && (tutarlar[i]-temp>15) ){
                    colors1.add(Color.rgb(255, 150, 0));
                }else if(tutarlar[i]>temp && (tutarlar[i]-temp<=15) && (tutarlar[i]-temp>0) ){
                    colors1.add(Color.rgb(255, 200, 0));
                }else if((tutarlar[i]-temp==0) ){
                    colors1.add(Color.GRAY);
                }else if(temp>tutarlar[i] && (tutarlar[i]-temp<0) && (tutarlar[i]-temp>-15) ){
                    colors1.add(Color.rgb(0, 175, 255));
                }else if(temp>tutarlar[i] && (tutarlar[i]-temp<=-15) && (tutarlar[i]-temp>-30) ){
                    colors1.add(Color.rgb(0, 220, 255));
                }else if(temp>tutarlar[i] && (tutarlar[i]-temp<=-30)){
                    colors1.add(Color.rgb(0, 255, 125));
                }
            }

            if(i!=0){
                if(graph2[i]>temp2 &&  (graph2[i]-temp2>30) ){
                    colors2.add(Color.rgb(255, 100, 0));
                }else if(graph2[i]>temp2 && (graph2[i]-temp2<=30) && (graph2[i]-temp2>15) ){
                    colors2.add(Color.rgb(255, 150, 0));
                }else if(graph2[i]>temp2 && (graph2[i]-temp2<=15) && (graph2[i]-temp2>0) ){
                    colors2.add(Color.rgb(255, 200, 0));
                }else if((graph2[i]-temp2==0) ){
                    colors2.add(Color.GRAY);
                }else if(temp2>graph2[i] && (graph2[i]-temp2<0) && (graph2[i]-temp2>-15) ){
                    colors2.add(Color.rgb(0, 175, 255));
                }else if(temp2>graph2[i] && (graph2[i]-temp2<=-15) && (graph2[i]-temp2>-30) ){
                    colors2.add(Color.rgb(0, 220, 255));
                }else if(temp2>graph2[i] && (graph2[i]-temp2<=-30)){
                    colors2.add(Color.rgb(0, 255, 125));
                }
            }

            temp2=graph2[i];
            temp=tutarlar[i];
        }
    }

    private void showData(){
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);

        LegendEntry l1=new LegendEntry("TL",Legend.LegendForm.CIRCLE,10f,2f,null,Color.CYAN);
        LegendEntry l2=new LegendEntry("kWh", Legend.LegendForm.CIRCLE,10f,2f,null,Color.RED);
        l.setCustom(new LegendEntry[]{l1,l2});
        l.setTextSize(15f);

        if(checkBoxenerji.isChecked()&&checkBoxtutar.isChecked()){
            LineData data = new LineData(sets);
            lineChart.setData(data);
            lineChart.animateX(250);
            lineChart.invalidate();
            tutar=true;
            enerji=true;
        }else if(checkBoxtutar.isChecked()){
            LineData data = new LineData(lineDataSet);
            lineChart.setData(data);
            lineChart.animateX(250);
            lineChart.invalidate();
            tutar=true;
            enerji=false;
        }else if(checkBoxenerji.isChecked()){
            LineData data = new LineData(lineDataSet2);
            lineChart.setData(data);
            lineChart.animateX(250);
            lineChart.invalidate();
            tutar=false;
            enerji=true;
        }else {
            LineData data = new LineData();
            lineChart.setData(data);
            lineChart.animateX(250);
            lineChart.invalidate();
            tutar=false;
            enerji=false;
        }
    }

    public void Listener(){

        checkBoxtutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });

        checkBoxenerji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });
    }

    private void set_Layout(){
        fill_data();

        lineChart.setDrawGridBackground(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.getXAxis().setTextSize(15f);
        lineChart.getAxisLeft().setTextSize(15f);

        lineDataSet = new LineDataSet(entries, "TL");
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.maps_floorpicker_black));
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setColors(colors1);
        lineDataSet.setHighlightLineWidth(5f);
        lineDataSet.setLineWidth(5f);

        lineDataSet2 = new LineDataSet(x, "kWh");
        lineDataSet2.setCircleRadius(4f);
        lineDataSet2.setCircleColor(Color.RED);
        lineDataSet2.setValueTextColor(Color.rgb(255, 135, 0));
        lineDataSet2.setValueTextSize(10f);
        lineDataSet2.setColors(colors2);
        lineDataSet2.setHighlightLineWidth(5f);
        lineDataSet2.setLineWidth(5f);


        sets = new ArrayList<>();
        sets.add(lineDataSet);
        sets.add(lineDataSet2);

        Listener();
    }
}