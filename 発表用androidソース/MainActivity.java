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
    private EditText editText3;
    private EditText editText4;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTextオブジェクト（文字入力をするところ）
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        //レイアウトオブジェクト（キーボードを背景タッチで閉じるための処理用にid取得）
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        //キーボード表示を制御するところ
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //画面遷移用ボタン（押すとSubActivitiへ）
        button = (Button)findViewById(R.id.button);
        //ボタンをを押したときの処理を{}内部に
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            //ボタンをクリックしたときの処理
            public void onClick(View v) {
                //intent（異なるActivityへデータを送るために使用）を呼び出し
                Intent intent=new Intent();
                //遷移先を指定
                intent.setClassName("com.example.user.intro","com.example.user.intro.SubActivity");
                intent.putExtra("sendName",editText.getText().toString());
                intent.putExtra("sendAge",Integer.parseInt(editText2.getText().toString()));
                intent.putExtra("sendHobby",editText3.getText().toString());
                intent.putExtra("sendPoint",editText4.getText().toString());
                //データが入ったので画面遷移
                startActivity(intent);
            }
        }));
    }

    //キーボードを隠すためのイベント
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //背景タッチ時キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //キーボードを閉じたのちにフォーカスを背景へ
        mainLayout.requestFocus();
        return false;
    }
}
