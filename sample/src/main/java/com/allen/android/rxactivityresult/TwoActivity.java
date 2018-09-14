package com.allen.android.rxactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by liyong on 2018/9/14.
 */
public class TwoActivity extends FragmentActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.twoactivity_layout);
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent data = new Intent();
        data.putExtra("data", "我来自第二个activity!");
        setResult(0, data);
        finish();
      }
    });
  }
}
