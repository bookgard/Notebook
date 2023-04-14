package jp.co.sony.mc.cast.notebook.mvvm.dp.dao;
/*
 * © 2021 Sony Corporation.
 */

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import jp.co.sony.mc.cast.notebook.mvvm.dp.bean.Notebook;

@Dao
public interface NoteBookDao {

       @Query("SELECT * FROM notebook")
       Flowable<List<Notebook>> getAll();

       @Query("SELECT * FROM notebook WHERE uid=:uid")
       Flowable<Notebook> findById(int uid);

       @Update
       Completable update(Notebook notebook);

       @Insert(onConflict = OnConflictStrategy.REPLACE)
       Completable insert(Notebook notebook);

       @Delete
       Completable delete(Notebook notebook);
}
