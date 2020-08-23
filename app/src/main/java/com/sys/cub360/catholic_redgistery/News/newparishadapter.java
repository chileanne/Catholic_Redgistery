package com.sys.cub360.catholic_redgistery.News;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sys.cub360.catholic_redgistery.R;

import java.util.List;

public class newparishadapter extends RecyclerView.Adapter< newparishadapter.Myholder> {
    Context mcontext;
    List<parishnewsmodel> mlist;


    public newparishadapter(Context applicationContext, List<parishnewsmodel> mlist) {
        this.mcontext=applicationContext;
        this.mlist=mlist;
    }

    @NonNull
    @Override
    public newparishadapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newparishsingle,viewGroup,false);
        return new newparishadapter.Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull newparishadapter.Myholder myholder, int i) {
        parishnewsmodel g=mlist.get(i);
        final String parishname=g.getParish();
        final String parishdesc=g.getDescparish();
        final String priestinfo=g.getInfopriest();
        final String aboutparish =g.getAboutparish();

        myholder.title.setText(parishname);
        myholder.body.setText(aboutparish);

        myholder.mcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,  parishnewread.class);
                intent.putExtra("ppname",parishname);
                intent.putExtra("ppdesc",parishdesc);
                intent.putExtra("ppinfo",priestinfo);
                intent.putExtra("ppabout",aboutparish);
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
        TextView title,body;
        ImageView bulimages;
        CardView mcard;
        public Myholder(@NonNull View v) {
            super(v);
            title=v.findViewById(R.id.titlex);
            body=v.findViewById(R.id.bodysssx);
            bulimages=v.findViewById(R.id.bulimagesd);
            mcard=v.findViewById(R.id.bullcardsdd);


        }
    }
}
