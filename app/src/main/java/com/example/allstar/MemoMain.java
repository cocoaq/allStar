package com.example.allstar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.allstar.listData.Memo;
import com.example.allstar.listData.MemoDatabase;
import com.example.allstar.recycle.MemoFragAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MemoMain extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static  MemoDatabase Mdb;

    Button makeBtn,importBtn;
    testFrag frag;

    public static Context context;

    private ListView dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_main);

        //아이템 클릭 리스너
        //dateList.setOnItemClickListener();

        Mdb = Room.databaseBuilder(this, MemoDatabase.class, "Memo-db").build();
        frag = (testFrag) getSupportFragmentManager().findFragmentById(R.id.fragment);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addFragment(Mdb);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        SwipeRefreshLayout mSwipeRefreshLayout;
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipLay);
        mSwipeRefreshLayout.setDistanceToTriggerSync(200);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                replaceFrag();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addFragment(Mdb);
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

//Json형식으로 파일 import
        importBtn = findViewById(R.id.importJson);
        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Memo> Jmemo = Mdb.memoDao().allMemo();

                    Gson toJson = new Gson();
                    String jsonTest = toJson.toJson(Jmemo);
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() + "test.json", false));
                            bw.write(jsonTest);
                            bw.close();
//                            BufferedReader br = new BufferedReader(new FileReader(getFilesDir()+ "test.json"));
//                            Log.v("test",br.readLine());
//                            Log.v("test",getFilesDir() + "");
                            toastAdd("저장되었습니다.");
                        } catch (IOException e) {
                            e.printStackTrace();
                            toastAdd("실패");
                        }

                    }
                }).start();
            }
        });


//add 이벤트
        makeBtn = findViewById(R.id.addBtn);
        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeMemo mkmm = new MakeMemo(MemoMain.this);
                mkmm.setCancelable(false);
                WindowManager.LayoutParams params = mkmm.getWindow().getAttributes();
                params.copyFrom(mkmm.getWindow().getAttributes());
                params.width = (int) (getApplicationContext().getResources().getDisplayMetrics().widthPixels);
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mkmm.show();
            }
        });


    }



    //toart 만드는 메소드
void toastAdd(String makeTxt){
    runOnUiThread(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MemoMain.this, makeTxt, Toast.LENGTH_SHORT).show();
        }
    });
}



//replace
    public void replaceFrag(){
        testFrag frag;
        frag = (testFrag) getSupportFragmentManager().findFragmentById(R.id.fragment);
        new Thread(new Runnable() {
            @Override
            public void run() {
                    frag.replaceItem();
            }
        }).start();
    }


//프래그먼트 추가
public void addFragment(MemoDatabase Mdb) {
    testFrag frag;
    frag = (testFrag) getSupportFragmentManager().findFragmentById(R.id.fragment);
    new Thread(new Runnable() {
        @Override
        public void run() {
            List<Memo> memo = Mdb.memoDao().allMemo();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    for (Memo m : memo) {
                        frag.addItem(m.getMemoTitle(), m.getMemoContent(), m.getMemoDate(), m.getMemoId());
                    }
                }
            });
        }
    }).start();
    ref();
}
public void ref(){
    MemoFragAdapter adapter = new MemoFragAdapter();
    new Thread(new Runnable() {
        @Override
        public void run() {
            adapter.notifyDataSetChanged();
        }
    }).start();
}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}