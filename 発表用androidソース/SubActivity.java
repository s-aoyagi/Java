package com.example.user.intro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.widget.TextView;

public class SubActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = this.getIntent();
        //MainActivityのintentで入力したデータを受け取り
        String name = intent.getStringExtra("sendName");
        int age = intent.getIntExtra("sendAge",0);
        String hobby = intent.getStringExtra("sendHobby");
        String point = intent.getStringExtra("sendPoint");
        //データをセット
        TextView textView = (TextView)this.findViewById(R.id.textView2);
        textView.setText(name);
        TextView textView1 = (TextView)this.findViewById((R.id.textView5));
        textView1.setText(String.valueOf(age));
        TextView textView2 = (TextView)this.findViewById(R.id.textView7);
        textView2.setText(hobby);
        TextView textView3 = (TextView)this.findViewById(R.id.textView10);
        textView3.setText(point);
    }
}
