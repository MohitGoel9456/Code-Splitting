package com.codesplitting;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SplittingBundleModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext reactContext;
    public SplittingBundleModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "SplittingBundleModule";
    }

    @ReactMethod
    public void startAnotherBundle(boolean bundle) {
       if(bundle){
           Intent intent = new Intent(reactContext, MultiBundleRnAppActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           reactContext.startActivity(intent);
       }else {
           Intent intent = new Intent(reactContext, SingleBundleRnAppActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           reactContext.startActivity(intent);
       }
    }
}
