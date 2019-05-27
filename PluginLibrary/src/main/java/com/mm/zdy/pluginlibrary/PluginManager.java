package com.mm.zdy.pluginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static final PluginManager instanc= new PluginManager();
    private PluginApk mPluginApk;

    private PluginManager(){

    }
    public static PluginManager getInstanc(){
        return instanc;
    }

    private PluginApk mPluginApk;
    private Context mCntext;
    public void init(Context context){
        this.mCntext = context;
    }
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mCntext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        if(packageInfo==null){
            return;
        }
        DexClassLoader classLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResources(apkPath);
            mPluginApk= new PluginApk(classLoader,resources,packageInfo,assetManager);
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = mCntext.getAssets();
        }
    }

    private Resources createResources(AssetManager assetManager) {
        Resources resources = mCntext.getResources();
        return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mCntext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file,)
    }

    private DexClassLoader createDexLoader(String path){

    }

    public PluginApk getPluginApk() {
    }
}
