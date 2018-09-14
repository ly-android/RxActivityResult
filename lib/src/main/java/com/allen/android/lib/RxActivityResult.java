package com.allen.android.lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import io.reactivex.Observable;

/**
 * Created by liyong on 2018/9/14.
 */
public class RxActivityResult {

  public static final String TAG = "RxActivityResult";
  Lazy<RxActivityResultFragment> mRxActivityResultFragment;

  public RxActivityResult(@NonNull FragmentActivity activity) {
    mRxActivityResultFragment = getLazySingleton(activity.getSupportFragmentManager());
  }


  public RxActivityResult(@NonNull Fragment fragment) {
    mRxActivityResultFragment = getLazySingleton(fragment.getChildFragmentManager());
  }

  private Lazy<RxActivityResultFragment> getLazySingleton(final FragmentManager supportFragmentManager) {
    return new Lazy<RxActivityResultFragment>() {
      private RxActivityResultFragment rxActivityResultFragment;

      @Override
      public synchronized RxActivityResultFragment get() {
        if (rxActivityResultFragment == null) {
          rxActivityResultFragment = getRxPermissionsFragment(supportFragmentManager);
        }
        return rxActivityResultFragment;
      }
    };
  }

  private RxActivityResultFragment getRxPermissionsFragment(@NonNull final FragmentManager fragmentManager) {
    RxActivityResultFragment rxActivityResultFragment = (RxActivityResultFragment) fragmentManager.findFragmentByTag(TAG);
    boolean isNewInstance = rxActivityResultFragment == null;
    if (isNewInstance) {
      rxActivityResultFragment = new RxActivityResultFragment();
      fragmentManager
        .beginTransaction()
        .add(rxActivityResultFragment, TAG)
        .commitNow();
    }
    return rxActivityResultFragment;
  }

  public Observable<ActivityResult> startActivityForResult(Intent intent, int requestCode) {
    return startActivityForResult(intent, requestCode, null);
  }

  public Observable<ActivityResult> startActivityForResult(Intent intent, int requestCode, Bundle options) {
    return mRxActivityResultFragment.get().startForResult(intent, requestCode, options);
  }


  public interface Lazy<V> {
    V get();
  }
}
