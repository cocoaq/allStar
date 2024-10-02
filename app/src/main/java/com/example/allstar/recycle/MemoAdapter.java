package com.example.allstar.recycle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allstar.R;
import com.example.allstar.listData.Memo;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    public ArrayList<Memo> mData = null;

    public MemoAdapter(ArrayList<Memo> data){
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.memoitem, parent, false);
        MemoAdapter.ViewHolder vh = new MemoAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memo memo = mData.get(position);

        holder.titleTxt.setText(memo.getMemoTitle());
        holder.contentTxt.setText(memo.getMemoContent());
        holder.dateTxt.setText(memo.getMemoDate());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, contentTxt, dateTxt;

        public ViewHolder(View view) {
          super(view);
          titleTxt = view.findViewById(R.id.titleV);
          contentTxt = view.findViewById(R.id.contentV);
          dateTxt = view.findViewById(R.id.dateV);
        }
    }
}
