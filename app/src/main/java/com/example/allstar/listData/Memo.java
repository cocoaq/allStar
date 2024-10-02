package com.example.allstar.listData;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Memo")
public class Memo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int memoId;
    @NonNull
    public String memoTitle;
    public String memoContent;
    @NonNull
    public String memoDate;

    public int getMemoId() {
        return memoId;
    }

    public void setMemoId(int memoId) {
        this.memoId = memoId;
    }

    @NonNull
    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(@NonNull String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    @NonNull
    public String getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(@NonNull String memoDate) {
        this.memoDate = memoDate;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "memoId=" + memoId +
                ", memoTitle='" + memoTitle + '\'' +
                ", memoContent='" + memoContent + '\'' +
                ", memoDate='" + memoDate + '\'' +
                '}';
    }
}
