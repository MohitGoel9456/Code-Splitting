package com.codesplitting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.ReactContext;
import com.facebook.soloader.SoLoader;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "CodeSplitting";
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onSingleBundleClick(View v) {
    Intent intent = new Intent(this, SingleBundleRnAppActivity.class);
    System.out.println("onSingleBundleClick called, time: " + System.currentTimeMillis());
    startActivity(intent);
  }

  public void onMultiBundleClick(View v) {
    Log.v("Mohit 123","onMultiBundleClick");
    Intent intent = new Intent(this, MultiBundleRnAppActivity.class);
    System.out.println("onMultiBundleClick called, time: " + System.currentTimeMillis());
    startActivity(intent);
  }

}