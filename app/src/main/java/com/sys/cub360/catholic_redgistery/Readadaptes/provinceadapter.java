package com.sys.cub360.catholic_redgistery.Readadaptes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sys.cub360.catholic_redgistery.R;
import com.sys.cub360.catholic_redgistery.Read.Read_denary;
import com.sys.cub360.catholic_redgistery.Readmodels.provinces;


import java.util.List;

public class provinceadapter  extends RecyclerView.Adapter< provinceadapter.Myholder>{
    private Context mcontext;
    private List<provinces> mlist;
    private String m,po;

    public provinceadapter(Context applicationContext, List<provinces> mlist, String m, String provinced) {
        mcontext=applicationContext;
        this.mlist=mlist;
        this.m=m;
        this.po=provinced;
    }

    @NonNull
    @Override
    public provinceadapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diocessingle,viewGroup,false);
        return new provinceadapter.Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull provinceadapter.Myholder myholder, int i) {
        provinces g=mlist.get(i);
        String province=g.getProvince().toString().trim();
        final String dioces=g.getDioce().toString().trim();
        String date=g.getDate().toString().trim();
        String totalno=g.getTotalno().toString().trim();
        String totalc=g.getTotaalc().toString().trim();

        if(province.equals(po)){
            if(date.equals(m)){
                myholder.name.setText(dioces);
                myholder.no.setText(totalno);
                myholder.noc.setText(totalc);
                myholder.dav.setText(date);


                myholder.mc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mcontext, Read_denary.class);
                        intent.putExtra("dioces",dioces);
                        intent.putExtra("date",m);
                        // intent.putExtra("body",body);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);


                    }
                });

            }
        }


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private CardView mc;
        private TextView name,no,noc,dav;
        public Myholder(@NonNull View v) {
            super(v);
            mc=v.findViewById(R.id.cdcx);
            dav=v.findViewById(R.id.dddax);
            name=v.findViewById(R.id.ddiocesnmaex);
            no=v.findViewById(R.id.wthdsx);
            noc=v.findViewById(R.id.wthdsxx);
        }
    }
}
