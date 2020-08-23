package com.sys.cub360.catholic_redgistery.Read;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sys.cub360.catholic_redgistery.R;

public class Read_fullparish extends AppCompatActivity {
    private TextView parishname,provincename,diocename,denaryname,datename,masstime,menname,womename,childname,youthname,totalname,communicantsname;
    private String pparishname,pprovincename,pdiocename,pdenaryname,pdatename,pmasstime,pmenname,pwomename,pchildname,pyouthname,ptotalname,pcommunicantsname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_fullparish);

        pparishname=getIntent().getStringExtra("parish");
        pprovincename=getIntent().getStringExtra("province");
        pdiocename=getIntent().getStringExtra("dioces");
        pdenaryname=getIntent().getStringExtra("denary");
        pdatename=getIntent().getStringExtra("date");
        pmasstime=getIntent().getStringExtra("masstime");
        pmenname=getIntent().getStringExtra("men");
        pwomename=getIntent().getStringExtra("women");
        pchildname=getIntent().getStringExtra("child");
        pyouthname=getIntent().getStringExtra("youth");
        ptotalname=getIntent().getStringExtra("totalno");
        pcommunicantsname=getIntent().getStringExtra("totalc");

        parishname=findViewById(R.id.parishname);
        provincename=findViewById(R.id.pofsc);
        diocename=findViewById(R.id.ddiosx);
        denaryname=findViewById(R.id.ddex);
        datename=findViewById(R.id.dddax);
        masstime=findViewById(R.id.pofsdd);
        menname=findViewById(R.id.mendx);
        womename=findViewById(R.id.womendx);
        childname=findViewById(R.id.chidfx);
        youthname=findViewById(R.id.youttx);
        totalname=findViewById(R.id.tfghx);
        communicantsname=findViewById(R.id.covx);


        parishname.setText(pparishname);
        provincename.setText(pprovincename);
        diocename.setText(pdiocename);
        denaryname.setText(pdenaryname);
        datename.setText(pdatename);
        masstime.setText(pmasstime);
        menname.setText(pmenname);
        womename.setText(pwomename);
        childname.setText(pchildname);
        youthname.setText(pyouthname);
        totalname.setText(ptotalname);
        communicantsname.setText(pcommunicantsname);



    }
}
