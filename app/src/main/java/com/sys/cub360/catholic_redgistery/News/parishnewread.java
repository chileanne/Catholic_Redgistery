package com.sys.cub360.catholic_redgistery.News;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sys.cub360.catholic_redgistery.R;

public class parishnewread extends AppCompatActivity {
    private TextView parishname,descparish,priestinfos,aboutparishs;
    private Toolbar mtoolbar;
    private ImageView mimage,shre;
    private String mparishname,mdescparish,mpriestinfos,maboutparishs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parishnewread);

        mtoolbar = findViewById(R.id.toolbarparishread);
        setSupportActionBar(mtoolbar);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Parishnews.class));
                finish();
            }
        });


         parishname =(TextView)findViewById(R.id.parishnamesx);
           descparish =(TextView)findViewById(R.id.parishdescdx);
              priestinfos=(TextView)findViewById(R.id.infopriestx);
               aboutparishs =(TextView)findViewById(R.id.parishaboutx);
       // mimage=findViewById(R.id.bullreadimage);
        shre=findViewById(R.id.sharedxc);

        mparishname=getIntent().getStringExtra("ppname");
        mdescparish=getIntent().getStringExtra("ppdesc");
        mpriestinfos=getIntent().getStringExtra("ppinfo");
        maboutparishs=getIntent().getStringExtra("ppabout");

        parishname.setText(mparishname);
        descparish.setText(mdescparish);
        priestinfos.setText(mpriestinfos);
        aboutparishs.setText(maboutparishs);



    }
}
