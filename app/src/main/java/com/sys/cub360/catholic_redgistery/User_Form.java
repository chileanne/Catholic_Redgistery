package com.sys.cub360.catholic_redgistery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User_Form extends AppCompatActivity {
    private EditText mmen,mmwomen,myouth,mchildren,mcom;
    private CardView mbtn,mlogout;
    private ProgressDialog mp;
    private TextView mt;
    private Spinner mspin;
    private String  pcheck,md;
    private TextView kj;
private String paa;
    String[] banknames={"Select Time of Mass","5:30am-6:30am","7:00am-8:30am","9:00am-10:30am","11:00am-12:30am","4:30pm-5:30pm","6:00pm-6:30pm","Sat Mass:5:00pm-6:00pm for Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__form);
        paa=getIntent().getStringExtra("userparish");

        mmen=findViewById(R.id.men);
        mmwomen=findViewById(R.id.women);
        myouth=findViewById(R.id.youth);
        mchildren=findViewById(R.id.children);
        mcom=findViewById(R.id.com);
        mbtn=findViewById(R.id.submit);
        mlogout=findViewById(R.id.logd);
        mspin=findViewById(R.id.choose);
        mt=findViewById(R.id.chooses);

        kj=findViewById(R.id.ol);
        kj.setText(paa);

        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login_Screen.class));
                finish();
            }
        });

        SimpleDateFormat dateformt=new SimpleDateFormat("d-M-yyyy");
        md=dateformt.format(new Date());
        mt.setText(md);

        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), R.layout.spinner_list, banknames);
        aa.setDropDownViewResource(R.layout.spinner_list);
        mspin.setAdapter(aa);
        mspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), banknames[i], Toast.LENGTH_LONG).show();
                final String m=adapterView.getItemAtPosition(i).toString();
                pcheck=m;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String men = mmen.getText().toString().trim();
                String women = mmwomen.getText().toString().trim();
                String children = mchildren.getText().toString().trim();
                String youth = myouth.getText().toString().trim();
                String comm = mcom.getText().toString().trim();

                if (!pcheck.equals("Select Time of Mass")){

                    if (!TextUtils.isEmpty(men) && !TextUtils.isEmpty(women) && !TextUtils.isEmpty(children) && !TextUtils.isEmpty(youth) && !TextUtils.isEmpty(comm)) {

                        int mens = Integer.parseInt(men);
                        int womens = Integer.parseInt(women);
                        int childrens = Integer.parseInt(children);
                        int youths = Integer.parseInt(youth);
                        int comsd = Integer.parseInt(comm);

                        int totadl = mens + womens + childrens + youths;
                        String total = String.valueOf(totadl);
                        String coms = String.valueOf(comsd);
                        Toast.makeText(getApplicationContext(), total, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), ContinueActivity.class);
                        i.putExtra("men", men);
                        i.putExtra("women", women);
                        i.putExtra("children", children);
                        i.putExtra("youths", youth);
                        i.putExtra("communtant", comm);
                        i.putExtra("timeof mass", pcheck);

                        i.putExtra("total", total);
                        i.putExtra("coms", coms);
                        finish();
                        startActivity(i);


                    }
                }else if(pcheck.equals("Select Time of Mass")){
                    Toast.makeText(getApplicationContext(),"please select valid time of mass",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}

