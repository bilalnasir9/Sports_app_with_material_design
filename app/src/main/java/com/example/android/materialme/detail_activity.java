package com.example.android.materialme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detail_activity extends AppCompatActivity {
ImageView imageViewdetail;
TextView tvtitle,tvdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageViewdetail=findViewById(R.id.sportsimgesdetail);
        tvtitle=findViewById(R.id.titledetail);
        tvdetail=findViewById(R.id.subTitledetail);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        int imges=intent.getIntExtra("imges",0);
        tvtitle.setText(title);
        imageViewdetail.setImageResource(imges);

        switch (title){
            case  "Baseball":
                tvdetail.setText(R.string.baseball_detail);
                break;
            case  "Badminton":
                tvdetail.setText(R.string.badmintion_detail);
                break;
            case  "Basketball":
                tvdetail.setText(R.string.basketball_detail);
                break;
            case  "Bowling":
                tvdetail.setText(R.string.bowling_detail);
                break;
            case  "Cycling":
                tvdetail.setText(R.string.cycling_detail);
                break;
            case  "Golf":
                tvdetail.setText(R.string.golf_detail);
                break;
            case  "Running":
                tvdetail.setText(R.string.running_detail);
                break;
            case  "Soccer":
                tvdetail.setText(R.string.soccer_detail);
                break;
            case  "Cricket":
                tvdetail.setText(R.string.cricket_detail);
                break;
            case  "Table Tennis":
                tvdetail.setText(R.string.tabletenis_detail);
                break;
            case  "Tennis":
                tvdetail.setText(R.string.tennis_detail);
                break;

        }
    }
}