package com.allen.android.lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liyong on 2018/9/14.
 */
public class RxActivityResultFragment extends Fragment {
  static final String TAG = "RxActivityResult";
  static final Object TRIGGER = new Object();
  Map<Integer, PublishSubject<ActivityResult>> mSubjects = new HashMap<>();

  public RxActivityResultFragment() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }


  public Observable<ActivityResult> startForResult(final Intent intent, final int requestCode, final Bundle options) {
    return Observable.just(TRIGGER).flatMap(new Function<Object, Observable<ActivityResult>>() {
      @Override
      public Observable<ActivityResult> apply(Object o) throws Exception {
        Log.d(TAG, "startForResult requestCode=" + requestCode);
        return start(intent, requestCode, options);
      }
    });
  }

  private Observable<ActivityResult> start(final Intent intent, final int requestCode, final Bundle options) {
    final PublishSubject<ActivityResult> subject = PublishSubject.create();
    mSubjects.put(requestCode, subject);
    startActivityForResult(intent, requestCode, options);
    return subject;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    PublishSubject<ActivityResult> subject = mSubjects.remove(requestCode);
    if (subject != null) {
      subject.onNext(new ActivityResult(requestCode, resultCode, data));
      subject.onComplete();
      Log.d(TAG, "onActivityResult resultCode=" + requestCode);
    }
  }
}
