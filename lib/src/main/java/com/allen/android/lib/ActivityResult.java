package com.allen.android.lib;

import android.content.Intent;

/**
 * Created by liyong on 2018/9/14.
 */
public class ActivityResult {
  public int requestCode;
  public int resultCode;
  public Intent data;

  public ActivityResult(int requestCode, int resultCode, Intent data) {
    this.requestCode = requestCode;
    this.resultCode = resultCode;
    this.data = data;
  }
}
