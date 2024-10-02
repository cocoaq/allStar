package com.example.allstar;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.text.Layout;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.allstar.listData.Memo;
import com.example.allstar.listData.MemoDao;
import com.example.allstar.listData.MemoDatabase;
import com.example.allstar.recycle.MemoFragAdapter;

import java.util.ArrayList;
import java.util.List;

public class testFrag extends ListFragment{

    private MemoFragAdapter adapter;

 public testFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new MemoFragAdapter();
        setListAdapter(adapter);

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_test, container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void addItem(String title, String content, String date, int id){
       adapter.addItem(title,content,date,id);
    }

    public void replaceItem(){
     adapter.clear();
    }

    //각 항목 클릭 이벤트
    @Override
    public void onListItemClick (ListView l, View v, int position, long id){
        Memo memo = (Memo) l.getItemAtPosition(position);

        Bundle cMemo = new Bundle();
        cMemo.putString("title", memo.getMemoTitle());
        cMemo.putString("content", memo.getMemoContent());
        cMemo.putString("date", memo.getMemoDate());
        cMemo.putString("id", memo.getMemoId() + "");

        Intent intent = new Intent(l.getContext(), ListClick.class);

        intent.putExtras(cMemo);

        startActivity(intent);
    }

}