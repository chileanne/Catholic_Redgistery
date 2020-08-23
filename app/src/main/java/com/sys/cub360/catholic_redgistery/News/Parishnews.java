package com.sys.cub360.catholic_redgistery.News;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sys.cub360.catholic_redgistery.HomeActivity;
import com.sys.cub360.catholic_redgistery.R;

import java.util.ArrayList;
import java.util.List;

public class Parishnews extends AppCompatActivity {
    private DatabaseReference mbulletin;
    private List<parishnewsmodel> mlist;
    private newparishadapter madapter;
    private parishnewsmodel mbulletinmodel;
    private RecyclerView mrecyclerview;
    private ProgressBar mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parishnews);


        mp=(ProgressBar)findViewById(R.id.pps);

        mlist=new ArrayList<>();
        mrecyclerview=(RecyclerView)findViewById(R.id.recybulletins);
        LinearLayoutManager ll=new LinearLayoutManager(this);
        ll.setReverseLayout(true);
        ll.setStackFromEnd(true);
        mrecyclerview.setLayoutManager(ll);
        mrecyclerview.setAdapter(madapter);
        mrecyclerview.setVisibility(View.GONE);
        mp.setVisibility(View.VISIBLE);


        mlist.clear();
        mbulletin= FirebaseDatabase.getInstance().getReference().child("ParishNew");
        mbulletin.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                String parish=dataSnapshot.child("parish").getValue().toString();
                String descparish=dataSnapshot.child("descparish").getValue().toString();
                String infopriest=dataSnapshot.child("infopriest").getValue().toString();
                String aboutparish=dataSnapshot.child("aboutparish").getValue().toString();


                mbulletinmodel=new parishnewsmodel( parish,descparish,infopriest,aboutparish);
                mlist.add(mbulletinmodel);
                madapter=new newparishadapter(getApplicationContext(),mlist);
                mrecyclerview.setAdapter(madapter);

                mp.setVisibility(View.GONE);
                mrecyclerview.setVisibility(View.VISIBLE);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent id = new Intent(getApplicationContext(), HomeActivity.class);
        finish();
        startActivity(id);
    }
}
