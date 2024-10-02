package com.example.allstar;

import androidx.annotation.NonNull;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.allstar.listData.Memo;
import com.example.allstar.listData.MemoDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeMemo extends Dialog  implements View.OnClickListener {
MemoMain mMain;
    public MakeMemo(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_make_memo);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        init();

        Context mainContest = context;
    }

    private void init() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_memo);


        EditText newTitle = findViewById(R.id.newmemoTitle);
        EditText newContent = findViewById(R.id.newMemoContent);
        Button makeBtn;

        makeBtn = findViewById(R.id.makeMemoBtn);



        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MemoDatabase Mdb = Room.databaseBuilder(v.getContext(), MemoDatabase.class, "Memo-db").build();

                String titleT = newTitle.getText().toString();
                String contentT = newContent.getText().toString();

                Date mDate = new Date(System.currentTimeMillis());
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd\nhh:mm");

                String dateT = simpleDate.format(mDate);

                Memo newMemo = new Memo();
                newMemo.setMemoTitle(titleT);
                newMemo.setMemoContent(contentT);
                newMemo.setMemoDate(dateT);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(titleT.equals("")){
                            dismiss();
                        }else{
                            Mdb.memoDao().addMemo(newMemo);
                        }
                    }
                }).start();
                dismiss();
            }
        });



        ImageButton closeBtn = findViewById(R.id.imageCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}