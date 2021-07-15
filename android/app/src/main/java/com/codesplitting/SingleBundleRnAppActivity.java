package com.codesplitting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;

public class SingleBundleRnAppActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;

    // we'll not use Singleton ReactInstanceManager since we want to load
    // whole JS bundle in one go
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("SingleBundleActivity");
        loadReactNativeApp();
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            Log.e("Mohit n","If condition");
            mReactInstanceManager.onBackPressed();
        } else {
            Log.e("Mohit n","else condition");
            super.onBackPressed();
        }
    }

    private void loadReactNativeApp() {
        SoLoader.init(this, false);

        System.out.println("loading Single Bundle RN app");

        mReactRootView = new ReactRootView(this);
        // Remember to include them in `settings.gradle` and `app/build.gradle` too.

        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .setJSBundleLoader(JSBundleLoader.createCachedBundleFromNetworkLoader(
                        "index.android.bundle",
                        "http://localhost:8081/index.bundle?platform=android&dev=false&hot=false&minify=false"))
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .setJSBundleFile("assets://index.android.bundle")
                .addPackages(Arrays.<ReactPackage>asList(
                        new MainReactPackage()
                ))
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        // The string here (e.g. "SingleBundleRnApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        mReactRootView.startReactApplication(mReactInstanceManager, "CodeSplitting", null);

        setContentView(mReactRootView);
    }
}