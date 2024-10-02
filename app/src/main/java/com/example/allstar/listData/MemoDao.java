package com.example.allstar.listData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface MemoDao {
//insert용
    @Insert
    void addMemo(Memo memo);

//삭제 기능용
    @Query("SELECT COUNT(memoId) FROM Memo WHERE memoId = :id")
    int selectDelMemo(int id);
    @Query("DELETE FROM Memo WHERE memoId = :id")
    void deleteMemo(int id);

//리스트용
    @Query("SELECT * FROM Memo ORDER BY memoId DESC")
    List<Memo> allMemo();

//업데이트용
    @Query("UPDATE Memo SET memoContent = :content AND memoTitle = :title AND memoDate = :date WHERE memoId = :id")
    void updateMemo(String content, String title, String date, int id);
    @Update
    void updateNewMemo(Memo memo);

//검색용
    @Query("SELECT * FROM Memo WHERE LOWER(memoContent) LIKE LOWER(:str) OR LOWER(memoTitle) LIKE LOWER(:str)")
    List<Memo> searchMemo(String str);
}
