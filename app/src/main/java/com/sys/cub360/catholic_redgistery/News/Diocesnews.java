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

public class Diocesnews extends AppCompatActivity {
    private DatabaseReference mbulletin;
    private List<bulletinmodel> mlist;
    private bulletinadapter madapter;
    private bulletinmodel mbulletinmodel;
    private RecyclerView mrecyclerview;
    private ProgressBar mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diocesnews);


        mp=(ProgressBar)findViewById(R.id.pp);

        mlist=new ArrayList<>();
        mrecyclerview=(RecyclerView)findViewById(R.id.recybulletin);
        LinearLayoutManager ll=new LinearLayoutManager(this);
        ll.setReverseLayout(true);
        ll.setStackFromEnd(true);
        mrecyclerview.setLayoutManager(ll);
        mrecyclerview.setAdapter(madapter);
        mrecyclerview.setVisibility(View.GONE);
        mp.setVisibility(View.VISIBLE);


        mlist.clear();
        mbulletin= FirebaseDatabase.getInstance().getReference().child("DioceanNew");
        mbulletin.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String title=dataSnapshot.child("title").getValue().toString();
                String body=dataSnapshot.child("body").getValue().toString();
                String thumbimage=dataSnapshot.child("thumbimage").getValue().toString();

                mbulletinmodel=new bulletinmodel(title,body,thumbimage);
                mlist.add(mbulletinmodel);
                madapter=new bulletinadapter(getApplicationContext(),mlist);
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
