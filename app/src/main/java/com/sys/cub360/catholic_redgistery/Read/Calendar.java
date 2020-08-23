package com.sys.cub360.catholic_redgistery.Read;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.sys.cub360.catholic_redgistery.R;

public class Calendar extends AppCompatActivity {
    private CalendarView mcal;
    private String province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        province=getIntent().getStringExtra("province");



        mcal = (CalendarView) findViewById(R.id.cal);
        mcal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String year = String.valueOf(i);
                String month = String.valueOf(i1 + 1);
                String day = String.valueOf(i2);

                String com = day + "-" + month + "-" + year;


                Intent id = new Intent(getApplicationContext(), Read_province.class);
                id.putExtra("province", province);
                id.putExtra("date", com);
                finish();
                startActivity(id);
                Toast.makeText(getApplicationContext(),com,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), PickActivity.class));
        finish();
    }
}
