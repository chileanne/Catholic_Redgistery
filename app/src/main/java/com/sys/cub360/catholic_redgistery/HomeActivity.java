package com.sys.cub360.catholic_redgistery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sys.cub360.catholic_redgistery.News.Appreciation;
import com.sys.cub360.catholic_redgistery.News.Diocesnews;
import com.sys.cub360.catholic_redgistery.News.Parishnews;
import com.sys.cub360.catholic_redgistery.Read.PickActivity;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout pparishnews,ddiocesnews,registryy,appreciations,hymns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        pparishnews=findViewById(R.id.ppnews);
        ddiocesnews=findViewById(R.id.ddnews);
        registryy=findViewById(R.id.rreg);
        appreciations=findViewById(R.id.appc);
        hymns=findViewById(R.id.ccr);


        pparishnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id = new Intent(getApplicationContext(), Parishnews.class);
               // finish();
                startActivity(id);

            }
        });

        ddiocesnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id = new Intent(getApplicationContext(), Diocesnews.class);
               // finish();
                startActivity(id);

            }
        });


        registryy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id = new Intent(getApplicationContext(), Send_Account.class);
               // finish();
                startActivity(id);

            }
        });


        appreciations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id = new Intent(getApplicationContext(), Appreciation.class);
               // finish();
                startActivity(id);

            }
        });


      /* hymns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id = new Intent(getApplicationContext(), .class);
                finish();
                startActivity(id);

            }
        });*/




    }
}
