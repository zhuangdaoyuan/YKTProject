package com.mm.zdy.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;

public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    //宿主上下文传入插件内
    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);
}
