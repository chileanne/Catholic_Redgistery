package com.sys.cub360.catholic_redgistery.News;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class newsDiocesadapter extends RecyclerView.Adapter< newsDiocesadapter.Myholder> {
    @NonNull
    @Override
    public newsDiocesadapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull newsDiocesadapter.Myholder myholder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Myholder extends RecyclerView.ViewHolder {
        public Myholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
