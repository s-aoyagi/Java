package com.example.user.intro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private InputMethodManager inputMethodManager;
    private LinearLayout mainLayout;
    private EditText editText;
    private EditText editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTextオブジェクト
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        //レイアウトオブジェクト
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        //キーボード表示を制御
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClassName("com.example.user.intro","com.example.user.intro.SubActivity");
                intent.putExtra("sendText",editText.getText().toString());
                intent.putExtra("sendAge",Integer.parseInt(editText2.getText().toString()));
                startActivity(intent);
            }
        }));
    }

    //キーボードを隠すためのイベント
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        mainLayout.requestFocus();
        return false;
    }
}
