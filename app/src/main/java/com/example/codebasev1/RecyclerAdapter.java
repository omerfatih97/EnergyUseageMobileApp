package com.example.codebasev1;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<String> donemler;
    private List<String> tutarlar;
    private List<String> durumlar;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    public RecyclerAdapter(List<String> donemler,List<String> tutarlar,List<String> durumlar,OnItemClickListener onItemClickListener) {
        this.donemler=donemler;
        this.tutarlar=tutarlar;
        this.durumlar=durumlar;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view=inflater.inflate(R.layout.list_fatura_layout,parent,false);

        final RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        viewHolder.container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.donem.setText(donemler.get(position));
        holder.tutar.setText(tutarlar.get(position));
        holder.durum.setText(durumlar.get(position));
    }

    @Override
    public int getItemCount() {
        return donemler.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView donem;
        TextView tutar;
        TextView durum;
        public View container;

        public RecyclerViewHolder(View view){
            super(view);
            donem = (TextView)view.findViewById(R.id.donemTxt);
            tutar = (TextView)view.findViewById(R.id.tutarTxt);
            durum = (TextView)view.findViewById(R.id.durumTxt);
            container=view;
        }

    }
    public class  RecyclerViewHolderWithChild extends RecyclerView.ViewHolder {

        public RecyclerViewHolderWithChild(View itemView) {
            super(itemView);
        }
    }
}


