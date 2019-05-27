package com.mm.zdy.pluginlibrary;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginApk pluginApk;
    private IPlugin mPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        pluginApk = PluginManager.getInstanc().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if(pluginApk==null){
            // TODO: 2019/5/27
            return;
        }
        try {
            Class<?> clazz = pluginApk.mClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if(object instanceof IPlugin){
                mPlugin = (IPlugin)object;
                mPlugin.attach(this);//传递上下文
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                mPlugin.onCreate(bundle);
            }

        }catch (Exception e){

        }
    }

    @Override
    public Resources getResources() {
        return pluginApk!=null?pluginApk.;
    }

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }

}
