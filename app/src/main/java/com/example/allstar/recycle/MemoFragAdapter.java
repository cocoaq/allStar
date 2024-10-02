package com.example.allstar.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allstar.MemoMain;
import com.example.allstar.R;
import com.example.allstar.listData.Memo;

import java.util.ArrayList;

public class MemoFragAdapter extends BaseAdapter{

    private ArrayList<Memo> item = new ArrayList<>();
    TextView title, content, date;

    public MemoFragAdapter(){
    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int posi = position;
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_test, parent, false);
        }

        title = (TextView)convertView.findViewById(R.id.titleV);
        content = (TextView) convertView.findViewById(R.id.contentV);
        date = (TextView) convertView.findViewById(R.id.dateV);

        Memo newItem = item.get(position);

        title.setText(newItem.getMemoTitle());
        content.setText(newItem.getMemoContent());
        date.setText(newItem.getMemoDate());

        return convertView;
    }



    public void addItem(String title, String content, String date, int id){
        Memo memo = new Memo();

        memo.setMemoTitle(title);
        memo.setMemoContent(content);
        memo.setMemoDate(date);
        memo.setMemoId(id);

        item.add(memo);
        notifyDataSetChanged();
    }


    public void clear(){
        item.clear();
    }

}
