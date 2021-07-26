package com.example.codebasev1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class FragmentGiris extends Fragment {

    private final DBConnection db=new DBConnection();
    private ProgressBar progressBar_giris;
    PieChart pieChart;
    TextView textView;
    int tutar=125;
    int kWh=188;
    int[] colorClassArray = new int[]{Color.rgb(0,100,255),Color.rgb(0,175,255)};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_giris_fragment,container,false);
        progressBar_giris=view.findViewById(R.id.progressBar_giris);
        textView=view.findViewById(R.id.textView2);
        pieChart=view.findViewById(R.id.pieChart);

        PieDataSet pieDataSet = new PieDataSet(dataValues1(),"");
        pieDataSet.setColors(colorClassArray);
        pieDataSet.setValueTextSize(15f);
        pieDataSet.setValueTextColor(Color.WHITE);

        PieData pieData = new PieData(pieDataSet);
        Description description = new Description();
        description.setText("Useage Amount, Fee,Total Amount");
        pieChart.setDescription(description);
        pieChart.setData(pieData);
        pieChart.setCenterText(tutar+" TL\n"+kWh+" kWh");
        pieChart.setCenterTextSize(15f);
        pieChart.setCenterTextColor(Color.GRAY);
        pieChart.setUsePercentValues(false);

        Legend l = pieChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        LegendEntry l1=new LegendEntry("Useage Amount",Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(0,100,255));
        LegendEntry l2=new LegendEntry("Fees", Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(0,175,255));
        l.setCustom(new LegendEntry[]{l1,l2});
        l.setTextSize(15f);

        pieChart.invalidate();

        progressBar_giris.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        //animate_pr();
        return view;
    }

    public void animate_pr(){
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar_giris, 0,(int)db.getBill_amount());
        anim.setDuration(2000);
        progressBar_giris.startAnimation(anim);
    }

    private ArrayList<PieEntry> dataValues1(){
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        Double vergi=tutar*0.2;
        Double tuketim=tutar*0.8;

        dataVals.add(new PieEntry(Float.valueOf(tuketim.toString()),""));
        dataVals.add(new PieEntry(Float.valueOf(vergi.toString()),""));

        return dataVals;
    }
}
