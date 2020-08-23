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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class foundyes extends AppCompatActivity {

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
        setContentView(R.layout.activity_foundyes);

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
        mbtn=findViewById(R.id.kjsd);
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
        if(found.equals("yes")&& !TextUtils.isEmpty(firstno) && !TextUtils.isEmpty(secondno)){

            //Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT).show();
            final int cfirsno=Integer.parseInt(firstno);
            final int csecondno=Integer.parseInt(secondno);

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
            updatedd.put("communtant",mcomtant);
            updatedd.put("timeof mass",mtimemass);

            DatabaseReference user_message_push = mparish.push();
            parishpush = user_message_push.getKey();

                       /* Map bd = new HashMap();
                        bd.put("/" + parishpush, updatedd);*/

            mparish.child(parishpush).updateChildren(updatedd).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        //adding initial score to denary
                        //covert totalno and totalc variables to int
                        int denaryno = Integer.parseInt(totalnoi);
                        int dencaryc = Integer.parseInt(totalci);

                        //sumup
                        final int firstsum=denaryno +cfirsno;
                        final int secondsum=dencaryc + csecondno;

                        //letsupdate denary values to the current
                        HashMap usermaps=new HashMap<>();
                        usermaps.put("Totalno",firstsum);
                        usermaps.put("totaalc",secondsum);
                        mdenary.child(idi).updateChildren(usermaps).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful()){
                                    //adding initial score to dioces
                                    //adding initial score to denary
                                    //covert totalno and totalc variables to int
                                    int diocesno = Integer.parseInt(diototalno);
                                    int diocesc = Integer.parseInt(diototalc);

                                    //sumup
                                    int diocesfirstsum = cfirsno + diocesno;
                                    int diocessecondsum = csecondno + diocesc;

                                    //letsupdate dioces values to the current
                                    HashMap usermaps=new HashMap<>();
                                    usermaps.put("Totalno",diocesfirstsum);
                                    usermaps.put("totaalc",diocessecondsum);
                                    mdioces.child(dioid).updateChildren(usermaps).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            if(task.isSuccessful()){
                                                //corresponding date was found
                                                //covert totalno and totalc variables to int
                                                int prdiocesnos = Integer.parseInt(prtotalno);
                                                int prdiocescs = Integer.parseInt(prtotalc);

                                                //sumup
                                                int provincefirstsum = cfirsno + prdiocesnos;
                                                int provincesecondsum = csecondno+ prdiocescs;

                                                //letsupdate dioces values to the current
                                                HashMap usermaps = new HashMap<>();
                                                usermaps.put("Totalno", provincefirstsum);
                                                usermaps.put("totaalc", provincesecondsum);
                                                mprovince.child(prid).updateChildren(usermaps).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        if(task.isSuccessful()){
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
