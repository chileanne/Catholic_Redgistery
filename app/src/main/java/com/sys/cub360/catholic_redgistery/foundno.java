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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class foundno extends AppCompatActivity {

    private ProgressDialog mp;
    private DatabaseReference mdatabaseref,mparish,mdenary,mdioces,mprovince;
    private DatabaseReference mdenaryadd,mdiocesadd,mprovinceadd;
    private Button mbtn,mlog;
    private  String m,parishpush,denarypush,diocespush,provincepush;
    private   String firstno;
    private String secondno;
    private String province,dioces,denary,parish,found,dateii,totalnoi,totalci,idi,ff;
    private String diototalno,diototalc,dioid,diodenary,diodiocessd;
    private String prtotalno,prtotalc,prid,totald,comsd;
    private String mmens,mwomens,mchildren,myouth,mtimemass,mcomtant;
    private String userprovince,userdioces,userdenary,userparish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundno);
        mmens= getIntent().getStringExtra("men");
        mwomens=getIntent().getStringExtra("women");
        mchildren= getIntent().getStringExtra("children");
        myouth= getIntent().getStringExtra("youths");
        mcomtant=getIntent().getStringExtra("communtant");
        mtimemass= getIntent().getStringExtra("timeof mass");

        totald=getIntent().getStringExtra("total");
        comsd=getIntent().getStringExtra("coms");

       // Toast.makeText(getApplicationContext(),totald,Toast.LENGTH_SHORT).show();

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
        /*prid=getIntent().getStringExtra("iddd");
        prtotalc=getIntent().getStringExtra("totalcdd");
        prtotalno=getIntent().getStringExtra("totalnodd");*/

        //dioces
       /* diototalc=getIntent().getStringExtra("totalcd");
        diototalno=getIntent().getStringExtra("totalnod");
        dioid=getIntent().getStringExtra("idd");*/



        mp=new ProgressDialog(this);
        mbtn=findViewById(R.id.kjx);
        mbtn.setVisibility(View.GONE);

        mdioces= FirebaseDatabase.getInstance().getReference().child("Diocesblog");
        FirebaseUser currentuser= FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentuser.getUid();
        mdatabaseref= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 userprovince=dataSnapshot.child("Province").getValue().toString();
                userdioces=dataSnapshot.child("dioces").getValue().toString();
                 userdenary=dataSnapshot.child("denary").getValue().toString();
                 userparish=dataSnapshot.child("parish").getValue().toString();
                // mp.dismiss();

                diocescall(userdioces,userdenary,userprovince,userparish);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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



            Toast.makeText(getApplicationContext(),diototalno,Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp.dismiss();
                    mbtn.setVisibility(View.VISIBLE);

                }
            }, 9000);



        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.setMessage("Validating Inputs");
                mp.setCanceledOnTouchOutside(false);
                mp.setCancelable(false);
                mp.show();
                call();
            }
        });

    }


    private void diocescall(final String userdioces, String userdenary, final String userprovince, String userparish){
        mdioces.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String dioprovinces = dataSnapshot.child("Province").getValue().toString();
                String diodiocesss = dataSnapshot.child("Dioces").getValue().toString();
               //  String denarys = dataSnapshot.child("denary").getValue().toString();
                // String parishs = dataSnapshot.child("parish").getValue().toString();
                String dates = dataSnapshot.child("date").getValue().toString();
                final String diototalnos = dataSnapshot.child("Totalno").getValue().toString();
                final String diototalcs = dataSnapshot.child("totaalc").getValue().toString();
                final String dioids= dataSnapshot.child("pushid").getValue().toString();

                if(userdioces.equals(diodiocesss) && dates.equals(m)){
                    diototalno=diototalnos;
                    diototalc=diototalcs;
                    dioid=dioids;
                   // diodenary=denarys;
                    diodiocessd=diodiocesss;

                    callprovince(userprovince);


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

    private void callprovince(final String userprovince) {
        mprovinceadd.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String prprovinces = dataSnapshot.child("Province").getValue().toString();
                // String diocess = dataSnapshot.child("Dioces").getValue().toString();
                //  String denarys = dataSnapshot.child("denary").getValue().toString();
                // String parishs = dataSnapshot.child("parish").getValue().toString();
                String dates = dataSnapshot.child("date").getValue().toString();
                String prtotalnos = dataSnapshot.child("Totalno").getValue().toString();
                String prtotalcs = dataSnapshot.child("totaalc").getValue().toString();
                String prids= dataSnapshot.child("pushid").getValue().toString();

                if(userprovince.equals(prprovinces) && dates.equals(m)){
                    prid=prids;
                    prtotalc=prtotalcs;
                    prtotalno=prtotalnos;

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

    private void call() {

        if(found.equals("no") && !TextUtils.isEmpty(firstno) && !TextUtils.isEmpty(secondno)){
          /*  Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"date empty",Toast.LENGTH_SHORT).show();*/
            //if the date wasnt found it means there
            //there is no record of the particular day for deanry,dioces,province
            // therefore we have to add them
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


                        DatabaseReference user_message_push = mdenaryadd.push();
                        denarypush = user_message_push.getKey();

                        //insert into denary
                        Map updatedd = new HashMap();
                        updatedd.put("Province", province);
                        updatedd.put("Dioces", dioces);
                        updatedd.put("denary", denary);
                        updatedd.put("pushid", denarypush);
                        updatedd.put("date", m);
                        updatedd.put("Totalno", firstno);
                        updatedd.put("totaalc", secondno);

                        //individual stuff
                        updatedd.put("men", mmens);
                        updatedd.put("women", mwomens);
                        updatedd.put("children", mchildren);
                        updatedd.put("youth", myouth);
                        updatedd.put("communtant",mcomtant);
                        updatedd.put("timeof mass",mtimemass);


                        Map bd = new HashMap();
                        bd.put("/" + denarypush, updatedd);
                        mdenaryadd.child(denarypush).updateChildren(updatedd).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful()) {
                                    //insert new dioces
                                    if ( diodiocessd ==null ) {


                                    DatabaseReference user_message_push = mdiocesadd.push();
                                    diocespush = user_message_push.getKey();


                                    Map updatedd = new HashMap();
                                    updatedd.put("pushid", diocespush);
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
                                                DatabaseReference user_message_push = mprovinceadd.push();
                                                provincepush = user_message_push.getKey();

                                                Map updatedd = new HashMap();
                                                updatedd.put("pushid", provincepush);
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
                                                            //complete done
                                                            mp.dismiss();
                                                            Toast.makeText(getApplicationContext(), "Inputs Verification Successfull", Toast.LENGTH_SHORT).show();
                                                            finish();//close current profile
                                                            startActivity(new Intent(getApplicationContext(), User_Form.class));
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });

                                }else if( diodiocessd!=null && userdioces.equals(diodiocessd)){
                                        Toast.makeText(getApplicationContext(), "Inbsss", Toast.LENGTH_SHORT).show();
                                        //i saw the dioces for a denary that wouldnt be seen in the denary blog
                                        //so i just wanna add
                                        int diocesno = Integer.parseInt(diototalno);
                                        int diocesc = Integer.parseInt(diototalc);

                                        int ffirstno=  Integer.parseInt(firstno);
                                        int ssecond=Integer.parseInt(secondno);

                                        //sumup
                                        int diocesfirstsum = ffirstno + diocesno;
                                        int diocessecondsum = ssecond + diocesc;

                                        HashMap usermaps=new HashMap<>();
                                        usermaps.put("Totalno",diocesfirstsum);
                                        usermaps.put("totaalc",diocessecondsum);
                                        mdioces.child(dioid).updateChildren(usermaps).addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                if(task.isSuccessful()){
                                                    int prno = Integer.parseInt(prtotalno);
                                                    int prcsc = Integer.parseInt(prtotalc);

                                                    int ffirstno=  Integer.parseInt(firstno);
                                                    int ssecond=Integer.parseInt(secondno);

                                                    //sumup
                                                    int prfirstsum = ffirstno + prno;
                                                    int prsecondsum = ssecond + prcsc;

                                                    HashMap usermaps=new HashMap<>();
                                                    usermaps.put("Totalno",prfirstsum);
                                                    usermaps.put("totaalc",prsecondsum);

                                                    mprovinceadd.child(prid).updateChildren(usermaps).addOnCompleteListener(new OnCompleteListener() {
                                                        @Override
                                                        public void onComplete(@NonNull Task task) {
                                                            if(task.isSuccessful()){
                                                                mp.dismiss();
                                                                Toast.makeText(getApplicationContext(), "wat d fuck", Toast.LENGTH_SHORT).show();
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
