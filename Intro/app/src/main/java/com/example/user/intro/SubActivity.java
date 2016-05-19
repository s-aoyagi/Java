package com.example.user.intro;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = this.getIntent();
        String name = intent.getStringExtra("sendText");
        int age = intent.getIntExtra("sendAge",0);
        TextView textView = (TextView)this.findViewById(R.id.textView2);
        textView.setText(name);
        TextView textView1 = (TextView)this.findViewById((R.id.textView5));
        assert textView1 != null;
        textView1.setText(String.valueOf(age));
    }
}
