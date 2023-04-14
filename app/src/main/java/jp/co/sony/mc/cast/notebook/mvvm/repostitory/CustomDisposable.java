package jp.co.sony.mc.cast.notebook.mvvm.repostitory;
/*
 * © 2021 Sony Corporation.
 */

import io.reactivex.Completable;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CustomDisposable {

 private static final CompositeDisposable compositeDisposable = new CompositeDisposable();

 public static <T> void addDisposable(Flowable<T> flowable, Consumer<T> consumer) {
     compositeDisposable.add(flowable
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(consumer));
 }

 public static <T> void addDisposable(Completable completable, Action action) {
  compositeDisposable.add(completable
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(action));
 }
}
