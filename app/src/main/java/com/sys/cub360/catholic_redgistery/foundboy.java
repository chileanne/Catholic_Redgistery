package com.sys.cub360.catholic_redgistery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sys.cub360.catholic_redgistery.Read.PickActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class foundboy extends AppCompatActivity {

    private ProgressDialog mp;
    private DatabaseReference mdatabaseref,mparish,mdenary,mdioces,mprovince;
    private DatabaseReference mdenaryadd,mdiocesadd,mprovinceadd;
    private Button mbtn,mlog;
    private  String m,parishpush,denarypush,diocespush,provincepush;
    private   String firstno;
    private String secondno;
    private String province,dioces,denary,parish,found,dateii,totalnoi,totalci,idi;
    private String diototalno,diototalc,dioid;
    private String prtotalno,prtotalc,prid,totald,comsd;
    private String mmens,mwomens,mchildren,myouth,mtimemass,mcomtant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundboy);

        mmens= getIntent().getStringExtra("men");
        mwomens=getIntent().getStringExtra("women");
        mchildren= getIntent().getStringExtra("children");
        myouth= getIntent().getStringExtra("youths");
        mcomtant=getIntent().getStringExtra("communtant");
        mtimemass= getIntent().getStringExtra("timeof mass");

        totald=getIntent().getStringExtra("total");
        comsd=getIntent().getStringExtra("coms");

        Toast.makeText(getApplicationContext(),totald,Toast.LENGTH_SHORT).show();

        province=getIntent().getStringExtra("provincei");
        dioces=getIntent().getStringExtra("diocesi");
        denary=getIntent().getStringExtra("denaryi");
        parish=getIntent().getStringExtra("parishi");
        found=getIntent().getStringExtra("ffound");
        dateii=getIntent().getStringExtra("datei");
        totalnoi=getIntent().getStringExtra("totalno");
        totalci=getIntent().getStringExtra("totalc");
        idi=getIntent().getStringExtra("id");

        //province
        prid=getIntent().getStringExtra("iddd");
        prtotalc=getIntent().getStringExtra("totalcdd");
        prtotalno=getIntent().getStringExtra("totalnodd");

        //dioces
        diototalc=getIntent().getStringExtra("totalcd");
        diototalno=getIntent().getStringExtra("totalnod");
        dioid=getIntent().getStringExtra("idd");



        mp=new ProgressDialog(this);
        mbtn=findViewById(R.id.kjdx);
        mbtn.setVisibility(View.GONE);

        // mbtn.setVisibility(View.GONE);


        if(found.equals("yes")){
            Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),dioid,Toast.LENGTH_SHORT).show();
        }

        if(found.equals("no")){
            Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT).show();

        }

        if(found.equals("boy")){
            Toast.makeText(getApplicationContext(),"boy",Toast.LENGTH_SHORT).show();
        }


        mdenaryadd= FirebaseDatabase.getInstance().getReference().child("Denaryblog");
        mdiocesadd=FirebaseDatabase.getInstance().getReference().child("Diocesblog");
        mprovinceadd=FirebaseDatabase.getInstance().getReference().child("Provinceblog");


        mparish= FirebaseDatabase.getInstance().getReference().child("Parishblog");
        mdenary=FirebaseDatabase.getInstance().getReference().child("Denaryblog");
        mdioces=FirebaseDatabase.getInstance().getReference().child("Diocesblog");
        mprovince=FirebaseDatabase.getInstance().getReference().child("Provinceblog");

        SimpleDateFormat dateformt=new SimpleDateFormat("d-M-yyyy");
        m=dateformt.format(new Date());

        if(totald!=null && comsd!=null) {
            firstno = totald;
            secondno = comsd;

        }


        mp.setMessage("Loading");
        mp.setCanceledOnTouchOutside(false);
        mp.setCancelable(false);
        mp.show();

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mp.dismiss();
                mbtn.setVisibility(View.VISIBLE);

            }
        },9000);


        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.setMessage("Va");
                mp.setCanceledOnTouchOutside(false);
                mp.setCancelable(false);
                mp.show();
                call();
            }
        });

    }

    private void call() {
        if(found.equals("boy") && !TextUtils.isEmpty(firstno) && !TextUtils.isEmpty(secondno)){
            Map updatedd = new HashMap();
            updatedd.put("Province", province);
            updatedd.put("Dioces", dioces);
            updatedd.put("denary", denary);
            updatedd.put("parish", parish);
            updatedd.put("date", m);
            updatedd.put("Totalno", firstno);
            updatedd.put("totaalc", secondno);

            //individual stuff
            updatedd.put("men", mmens);
            updatedd.put("women", mwomens);
            updatedd.put("children", mchildren);
            updatedd.put("youth", myouth);
            updatedd.put("communtant",mcomtant );
            updatedd.put("timeof mass",mtimemass );

            DatabaseReference user_message_push = mparish.push();
            parishpush = user_message_push.getKey();

                       /* Map bd = new HashMap();
                        bd.put("/" + parishpush, updatedd);*/
            mparish.child(parishpush).updateChildren(updatedd).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){


                        Toast.makeText(getApplicationContext(),"inside denary",Toast.LENGTH_SHORT).show();
                        DatabaseReference user_message_push = mdenaryadd.push();
                        denarypush = user_message_push.getKey();


                        Map updatedd = new HashMap();
                        updatedd.put("Province", province);
                        updatedd.put("Dioces", dioces);
                        updatedd.put("denary", denary);
                        updatedd.put("pushid", denarypush);
                        updatedd.put("date", m);
                        updatedd.put("Totalno", firstno);
                        updatedd.put("totaalc", secondno);


                        Map bd = new HashMap();
                        bd.put("/" + denarypush, updatedd);
                        mdenaryadd.updateChildren(bd).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"iside dioces",Toast.LENGTH_SHORT).show();

                                    //insert new dioces
                                    DatabaseReference user_message_push = mdiocesadd.push();
                                    diocespush = user_message_push.getKey();

                                    Map updatedd = new HashMap();
                                    updatedd.put("pushid",  diocespush);
                                    updatedd.put("Province", province);
                                    updatedd.put("Dioces", dioces);
                                    updatedd.put("date", m);
                                    updatedd.put("Totalno", firstno);
                                    updatedd.put("totaalc", secondno);




                                    Map bd = new HashMap();
                                    bd.put("/" + diocespush, updatedd);
                                    mdiocesadd.updateChildren(bd).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            if (task.isSuccessful()) {
                                                //insert new province
                                                Toast.makeText(getApplicationContext(),"inside province",Toast.LENGTH_SHORT).show();


                                                DatabaseReference user_message_push = mprovinceadd.push();
                                                provincepush = user_message_push.getKey();

                                                Map updatedd = new HashMap();
                                                updatedd.put("pushid",  provincepush);
                                                updatedd.put("Province", province);
                                                updatedd.put("date", m);
                                                updatedd.put("Totalno", firstno);
                                                updatedd.put("totaalc", secondno);



                                                Map bd = new HashMap();
                                                bd.put("/" + provincepush, updatedd);
                                                mprovinceadd.updateChildren(bd).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        if (task.isSuccessful()) {
                                                            //everything good
                                                            mp.dismiss();
                                                            Toast.makeText(getApplicationContext(),"Inputs Verification Successfull",Toast.LENGTH_SHORT).show();
                                                            finish();//close current profile
                                                            startActivity(new Intent(getApplicationContext(), User_Form.class));
                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    });


                                }

                            }
                        });


                    }


                }
            });

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), User_Form.class));
        finish();
    }
}
