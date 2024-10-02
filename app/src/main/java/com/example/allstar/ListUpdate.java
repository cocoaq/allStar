package com.example.allstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
import com.google.android.material.textfield.TextInputEditText;

public class ListUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_update);


        MemoDatabase Mdb;
        Mdb = Room.databaseBuilder(this, MemoDatabase.class, "Memo-db").build();

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String date = getIntent().getStringExtra("date");
        String id = getIntent().getStringExtra("id");

        TextView dateView, idView;
        EditText titleEd, contentEd;
        Button okBtn;

        dateView = (TextView)findViewById(R.id.dateUpdateNo);
        idView = (TextView)findViewById(R.id.idUpdateNo);

        titleEd = (EditText)findViewById(R.id.updateTitle);
        contentEd = (EditText)findViewById(R.id.updateContent);

        dateView.setText(date);
        idView.setText(id);
        titleEd.setText(title);
        contentEd.setText(content);


       okBtn = (Button) findViewById(R.id.updateOkBtn);
       okBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String newTitle = titleEd.getText().toString();
               String newContent = contentEd.getText().toString();
               String newDate = dateView.getText().toString();
               int newId = Integer.parseInt(idView.getText().toString());

               Memo newMemo = new Memo();
               newMemo.setMemoDate(newDate);
               newMemo.setMemoTitle(newTitle);
               newMemo.setMemoContent(newContent);
               newMemo.setMemoId(newId);
               Log.e("확인00145 : ", newMemo.toString());
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       if(newTitle.equals("")){
                           toastAdd("공란을 입력할 수 없습니다.");
                       }else {
                          // Mdb.memoDao().updateMemo(newContent, newTitle, newDate, newId);
                           Mdb.memoDao().updateNewMemo(newMemo);
                           toastAdd("업데이트 되었습니다.");
                       }
                       finish();
                   }
               }).start();
           }
       });

        ImageButton bBun = (ImageButton)findViewById(R.id.updateBackBtn);
        bBun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void toastAdd(String makeTxt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ListUpdate.this, makeTxt, Toast.LENGTH_SHORT).show();
            }
        });
    }
}