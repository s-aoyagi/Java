package com.example.user.intro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnKeyListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button01=(Button)findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onEnter();
            }
        });

        EditText editText01=(EditText)findViewById(R.id.editText01);
        editText01.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                TextView textView01 = (TextView)findViewById(R.id.textView);
                textView01.setText("入力された");
                return false;
            }
        });
    }
}
