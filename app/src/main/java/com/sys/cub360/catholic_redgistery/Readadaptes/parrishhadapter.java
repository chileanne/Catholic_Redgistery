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
import com.sys.cub360.catholic_redgistery.Read.Read_fullparish;
import com.sys.cub360.catholic_redgistery.Readmodels.paariish;

import java.util.List;

public class parrishhadapter  extends RecyclerView.Adapter< parrishhadapter.Myholder> {
    private Context mcontext;
    private List<paariish> mlist;
    private String m;
    private String dios,denns;
    public parrishhadapter(Context applicationContext, List<paariish> mlist, String m, String dios, String denns) {
        mcontext=applicationContext;
        this.mlist=mlist;
        this.m=m;
        this.dios=dios;
        this.denns=denns;
    }

    @NonNull
    @Override
    public parrishhadapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parihs,viewGroup,false);
        return new parrishhadapter.Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull parrishhadapter.Myholder myholder, int i) {
        paariish g=mlist.get(i);
        final String denary=g.getDenary().toString().trim();
        final String dioces=g.getDioces().toString().trim();
        final String date=g.getDate().toString().trim();
        final String totalno=g.getTotalno().toString().trim();
        final String totalc=g.getTotaalc().toString().trim();
        final String province=g.getProvince().toString().trim();
        final String men=g.getMen().toString().trim();
        final String women=g.getWomen().toString().trim();
        final String child=g.getChildren().toString().trim();
        final String youth=g.getYouth().toString().trim();
        final String ppa=g.getParish().toString().trim();
        final String masst=g.getTimeofmass().toString().trim();

        myholder.name.setText(ppa);
        myholder.dn.setText(denary);
        myholder.datde.setText(date);
        myholder.ddi.setText(dioces);
        myholder.pprov.setText(province);
        myholder.tm.setText(masst);

        myholder.mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, Read_fullparish.class);
                intent.putExtra("denary",denary);
                intent.putExtra("dioces",dioces);
                intent.putExtra("date",date);
                intent.putExtra("totalno",totalno);
                intent.putExtra("totalc",totalc);
                intent.putExtra("province",province);
                intent.putExtra("men",men);
                intent.putExtra("women",women);
                intent.putExtra("child",child);
                intent.putExtra("youth",youth);
                intent.putExtra("parish",ppa);
                intent.putExtra("masstime",masst);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private CardView mc;
        private TextView name,dn,noc,datde,pprov,ddi,tm;
        public Myholder(@NonNull View v) {
            super(v);
            mc=v.findViewById(R.id.cdcs);
            name=v.findViewById(R.id.parishname);
            dn=v.findViewById(R.id.dde);
            noc=v.findViewById(R.id.comdd);

            datde=v.findViewById(R.id.ddda);
            pprov=v.findViewById(R.id.pofs);
            ddi=v.findViewById(R.id.ddios);
            tm=v.findViewById(R.id.ddeg);
        }
    }
}
