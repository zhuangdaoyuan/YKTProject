package com.mm.zdy.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;

public class PluginActivity extends Activity implements IPlugin {
    private int mFROM = FROM_INTERNAL;

    private Activity mProxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
        this.mProxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            mFROM = saveInstanceState.getInt("FROM");
        }
        if (mFROM == FROM_EXTERNAL) {
            super.onCreate(saveInstanceState);
            mProxyActivity = this;
        }

    }

    @Override
    public void setContentView(int layoutResId) {
        if (mFROM == FROM_EXTERNAL) {
            super.setContentView(layoutResId);
        } else {
            mProxyActivity.setContentView(layoutResId);
        }


    }
}
