package com.allen.android.rxactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.allen.android.lib.ActivityResult;
import com.allen.android.lib.RxActivityResult;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends FragmentActivity {
  Disposable dis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RxActivityResult rxActivityResult = new RxActivityResult(this);
    dis = rxActivityResult.startActivityForResult(new Intent(this, TwoActivity.class), 0)
      .subscribe(new Consumer<ActivityResult>() {
        @Override
        public void accept(ActivityResult activityResult) throws Exception {
          Log.d("MainActivity", "activityResult data=" + activityResult.data);
        }
      });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (dis != null) {
      dis.dispose();
    }
  }
}
