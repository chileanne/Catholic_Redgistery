package com.sys.cub360.catholic_redgistery.Read;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.sys.cub360.catholic_redgistery.HomeActivity;
import com.sys.cub360.catholic_redgistery.Login_Screen;
import com.sys.cub360.catholic_redgistery.R;

public class PickActivity extends AppCompatActivity {
    private CardView mbenin,mabuja,mcalabar,mibadan,mjos,mkaduna,mlagos,monitsha,mowerri,mlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        mlogout=findViewById(R.id.logdd);
        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login_Screen.class));
                finish();
            }
        });

        mbenin=findViewById(R.id.benin);
        mbenin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Benin");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });

        mabuja=findViewById(R.id.abuja);
        mabuja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Abuja");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });

        mcalabar=findViewById(R.id.calabar);
        mcalabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Calabar");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });

        mibadan=findViewById(R.id.ibadan);
        mibadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Ibadan");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });

        mjos=findViewById(R.id.Jos);
        mjos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Jos");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });


        mkaduna=findViewById(R.id.kaduna);
        mkaduna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Kaduna");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });


        mlagos=findViewById(R.id.lagos);
        mlagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Lagos");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });

        monitsha=findViewById(R.id.onitsha);
        monitsha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Onitsha");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
            }
        });

        mowerri=findViewById(R.id.owerri);
        mowerri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);
                i.putExtra("province", "Owerri");
                // i.putExtra("diocesi", dioces);
                finish();
                startActivity(i);
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
