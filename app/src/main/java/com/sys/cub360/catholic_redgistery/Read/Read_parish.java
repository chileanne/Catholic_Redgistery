package com.sys.cub360.catholic_redgistery.Read;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sys.cub360.catholic_redgistery.R;
import com.sys.cub360.catholic_redgistery.Readadaptes.parrishhadapter;
import com.sys.cub360.catholic_redgistery.Readmodels.paariish;

import java.util.ArrayList;
import java.util.List;

public class Read_parish extends AppCompatActivity {
    private DatabaseReference mref;
    private List<paariish> mlist;
    private parrishhadapter madapter;
    private RecyclerView mrecyclervies;
    private paariish mstationmodel;
    private String denns,m,dios;
    private ProgressDialog mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_parish);

        mp=new ProgressDialog(this);

        dios=getIntent().getStringExtra("dioces");
        m=getIntent().getStringExtra("date");
        denns=getIntent().getStringExtra("denn");

        mlist=new ArrayList<>();
        mrecyclervies=(RecyclerView)findViewById(R.id.recystationsd);
        mrecyclervies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        madapter=new parrishhadapter(getApplicationContext(),mlist, m,dios,denns);

        mp.setMessage("Loading");
        mp.show();


        mlist.clear();

        mref= FirebaseDatabase.getInstance().getReference().child("Parishblog");
        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String province=dataSnapshot.child("Province").getValue().toString();
                String date=dataSnapshot.child("date").getValue().toString();
                String totalno=dataSnapshot.child("Totalno").getValue().toString();
                String totalc=dataSnapshot.child("totaalc").getValue().toString();
                String dio=dataSnapshot.child("Dioces").getValue().toString();
                String den=dataSnapshot.child("denary").getValue().toString();

                String youth=dataSnapshot.child("youth").getValue().toString();
                String women=dataSnapshot.child("women").getValue().toString();
                String men=dataSnapshot.child("men").getValue().toString();

                String parish=dataSnapshot.child("parish").getValue().toString();
                String masstime=dataSnapshot.child("timeof mass").getValue().toString();
                String commd=dataSnapshot.child("communtant").getValue().toString();
                String children=dataSnapshot.child("children").getValue().toString();

                //Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();
                if(dio.equals(dios) && date.equals(m) && den.equals(denns)) {
                    mstationmodel = new paariish(dio,province,totalno,children,commd,date,den,men,parish,masstime,totalc,women,youth);
                    mlist.add(mstationmodel);
                    madapter = new parrishhadapter(getApplicationContext(), mlist, m, dios,denns);
                    mrecyclervies.setAdapter(madapter);

                    mp.dismiss();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), PickActivity.class));
        finish();
    }



}
