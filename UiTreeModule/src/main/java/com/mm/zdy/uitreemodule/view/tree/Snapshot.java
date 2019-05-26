package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

public class Snapshot {
    //快照
    Canvas canvas;
    Bitmap bitmap;

    Snapshot(@NotNull Bitmap bitmap) {
        this.bitmap = bitmap;
        this.canvas = new Canvas(bitmap);
    }
}
