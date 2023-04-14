package jp.co.sony.mc.cast.notebook.mvvm.repostitory;
/*
 * © 2021 Sony Corporation.
 */

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import jp.co.sony.mc.cast.notebook.mvvm.BaseApplication;
import jp.co.sony.mc.cast.notebook.mvvm.dp.bean.Notebook;

public class NotebookRepository {

       private static final String TAG = NotebookRepository.class.getSimpleName();

       @Inject
       NotebookRepository() {}

       private final MutableLiveData<Notebook> notebookLiveData = new MutableLiveData<>();

       private final MutableLiveData<List<Notebook>> notebooksMutableLiveData = new MutableLiveData<>();

       public final MutableLiveData<String> failed = new MutableLiveData<>();

       public final List<Notebook> emptyList = new ArrayList<>();


       public void saveNotebook(Notebook notebook) {
           Completable insert = BaseApplication.getDb().noteBookDao().insert(notebook);
           CustomDisposable.addDisposable(insert,() -> Log.d(TAG,"saveNotebook: 笔记数据保存成功"));
       }


       public MutableLiveData<List<Notebook>> getNotebooks() {
              Flowable<List<Notebook>> listFlowable = BaseApplication.getDb().noteBookDao().getAll();
              CustomDisposable.addDisposable(listFlowable,notebooks -> {
                     if (notebooks.size() > 0) {
                            notebooksMutableLiveData.postValue(notebooks);
                     } else {
                            notebooksMutableLiveData.postValue(emptyList);
                            failed.postValue("暂无数据");
                     }
              });
              return notebooksMutableLiveData;
       }

       /*
       * 根据 id 获取笔记
       * */
       public MutableLiveData<Notebook> getNotebookByID(int uid) {
              Flowable<Notebook> flowable = BaseApplication.getDb().noteBookDao().findById(uid);
              CustomDisposable.addDisposable(flowable,notebook -> {
                     if (notebook != null) {
                            notebookLiveData.postValue(notebook);
                     } else {
                            failed.postValue("未查询到笔记");
                     }
              });
              return notebookLiveData;
       }

       /*
       * 更新笔记
       * */
       public void updateNotebook(Notebook notebook) {
              Completable update = BaseApplication.getDb().noteBookDao().update(notebook);
              CustomDisposable.addDisposable(update,() -> {
                     Log.d(TAG,"笔记更新成功");
                     failed.postValue("200");
              });
       }

       /*
       * 删除笔记
       * */
       public void deleteNotebook(Notebook notebook) {
              Completable delete = BaseApplication.getDb().noteBookDao().delete(notebook);
              CustomDisposable.addDisposable(delete,() -> {
                     Log.d(TAG, "deleteNotebook: 删除成功");
                     failed.postValue("200");
              });
       }
}
