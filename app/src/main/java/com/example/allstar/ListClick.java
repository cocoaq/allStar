package com.example.allstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allstar.listData.Memo;
import com.example.allstar.listData.MemoDatabase;

public class ListClick extends AppCompatActivity {



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_click);

        MemoDatabase Mdb;
        Mdb = Room.databaseBuilder(this, MemoDatabase.class, "Memo-db").build();

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String date = getIntent().getStringExtra("date");
        String id = getIntent().getStringExtra("id");

        TextView titleView, contentView, dateView, idView;
        Button dBtn, eBtn;

        titleView = (TextView)findViewById(R.id.listTitle);
        contentView = (TextView)findViewById(R.id.listContent);
        dateView = (TextView)findViewById(R.id.listDate);
        idView = (TextView)findViewById(R.id.listId);

        titleView.setText(title);
        contentView.setText(content);
        dateView.setText(date);
        idView.setText(id);
        int intId = Integer.parseInt(id);


        Memo clickMemo = new Memo();

        clickMemo.setMemoId(intId);
        clickMemo.setMemoTitle(title);
        clickMemo.setMemoContent(content);
        clickMemo.setMemoDate(date);

//뒤로가기 버튼
        ImageButton backBtn = (ImageButton)findViewById(R.id.listBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dBtn = (Button) findViewById(R.id.delBtn);
        dBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = Mdb.memoDao().selectDelMemo(intId);
                        if(i > 0){
                            Mdb.memoDao().deleteMemo(intId);
                            toastAdd("삭제하였습니다.");
                            finish();
                        }else{
                            toastAdd("삭제할 항목을 찾을 수 없습니다.");
                        }
                    }
                }).start();
            }
        });


        eBtn = (Button) findViewById(R.id.edBtn);
        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMemo.setMemoId(intId);
                clickMemo.setMemoTitle(title);
                clickMemo.setMemoContent(content);
                clickMemo.setMemoDate(date);

                Intent updateIntent = new Intent(ListClick.this, ListUpdate.class);

                updateIntent.putExtra("title", clickMemo.getMemoTitle());
                updateIntent.putExtra("content", clickMemo.getMemoContent());
                updateIntent.putExtra("date", clickMemo.getMemoDate());
                updateIntent.putExtra("id", Integer.toString(clickMemo.getMemoId()));

                startActivity(updateIntent);
            }
        });

    }

    void toastAdd(String makeTxt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ListClick.this, makeTxt, Toast.LENGTH_SHORT).show();
            }
        });
    }


}