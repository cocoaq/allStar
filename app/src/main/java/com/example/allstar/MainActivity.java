package com.example.allstar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private long backKetPressedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar aBar = getSupportActionBar();
        aBar.hide();

        ImageView bImage = (ImageView) findViewById(R.id.backgrountV);

        bImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemoMain.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() > backKetPressedTime + 2000){
            backKetPressedTime = System.currentTimeMillis();
            toastAdd("한번만 더 누르면 종료됩니다.");
            return;
        }
        if(System.currentTimeMillis() <= backKetPressedTime + 2000){
            finish();
        }
    }




    void toastAdd(String makeTxt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, makeTxt, Toast.LENGTH_SHORT).show();
            }
        });
    }
}