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
import com.sys.cub360.catholic_redgistery.Readadaptes.provinceadapter;
import com.sys.cub360.catholic_redgistery.Readmodels.provinces;

import java.util.ArrayList;
import java.util.List;

public class Read_dioces extends AppCompatActivity {
    private DatabaseReference mref;
    private List<provinces> mlist;
    private provinceadapter madapter;
    private RecyclerView mrecyclervies;
    private provinces mstationmodel;
    private String provinced,m;
    private ProgressDialog mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_dioces);

        mp=new ProgressDialog(this);

        provinced=getIntent().getStringExtra("province");
        m=getIntent().getStringExtra("date");

        mlist=new ArrayList<>();
        mrecyclervies=(RecyclerView)findViewById(R.id.recystation);
        mrecyclervies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //madapter=new provinceadapter(getApplicationContext(),mlist, name);

        mp.setMessage("Loading");
        mp.show();

        mlist.clear();

        mref= FirebaseDatabase.getInstance().getReference().child("Diocesblog");
        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String province=dataSnapshot.child("Province").getValue().toString();
                String date=dataSnapshot.child("date").getValue().toString();
                String totalno=dataSnapshot.child("Totalno").getValue().toString();
                String totalc=dataSnapshot.child("totaalc").getValue().toString();
                String dio=dataSnapshot.child("Dioces").getValue().toString();

                //Toast.makeText(getApplicationContext(),"no match",Toast.LENGTH_SHORT).show();
                if(province.equals(provinced)) {
                    if(date.equals(m)) {

                        mstationmodel = new provinces(province, dio, date, totalno, totalc);
                        mlist.add(mstationmodel);
                        madapter = new provinceadapter(getApplicationContext(), mlist, m, provinced);
                        mrecyclervies.setAdapter(madapter);
                        mp.dismiss();
                    }

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

