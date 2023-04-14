package jp.co.sony.mc.cast.notebook.mvvm.dp;
/*
 * © 2021 Sony Corporation.
 */

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import jp.co.sony.mc.cast.notebook.mvvm.dp.bean.Notebook;
import jp.co.sony.mc.cast.notebook.mvvm.dp.dao.NoteBookDao;

@Database(entities = {
        Notebook.class
},version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
       private static final String DATABASE_NAME = "mvvm_demo";
       private static volatile AppDatabase mInstance;

       public static AppDatabase getInstance(Context context) {
              if (mInstance == null) {
                     synchronized (AppDatabase.class) {
                            if (mInstance == null) {
                                   mInstance = Room.databaseBuilder(context.getApplicationContext(),
                                                   AppDatabase.class,DATABASE_NAME)
                                           .addMigrations(MIGRATION_1_2)
                                           .build();
                            }
                     }
              }

              return mInstance;
       }

       public abstract NoteBookDao noteBookDao();

       static final Migration MIGRATION_1_2 = new Migration(1,2) {
              @Override
              public void migrate(@NonNull SupportSQLiteDatabase database) {
                     database.execSQL("CREATE TABLE `notebook` " +
                            "(uid INTEGER NOT NULL,"+
                             "title TEXT," +
                             "content TEXT," +
                             "PRIMARY KEY(`uid`))" );
              }
       };
}
