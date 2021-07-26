package com.example.codebasev1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetay extends Fragment {

    private final DBConnection db=new DBConnection();
    private ProgressBar progressBar_detay;
    private TextView detayTxt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_detay_fragment,container,false);
        progressBar_detay=view.findViewById(R.id.progressBarDetay);
        detayTxt = view.findViewById(R.id.buayTxt);

        detayTxt.setText(detayTxt.getText()+" "+db.getBill_amount()+" TL");

        animate_pr();

        return view;

    }

    public void animate_pr(){
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar_detay, 0, (int)db.getBill_amount());
        anim.setDuration(2000);
        progressBar_detay.startAnimation(anim);
    }
}