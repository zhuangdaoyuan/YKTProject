package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Iterator;
import java.util.LinkedList;

public class Tree {
    //1绘制树干（贝塞尔曲线）
    //2绘制花瓣（心形曲线）
    //3整体平移
    //4花瓣掉落
    private enum Step {
        BRANCHES_GROWING,//树干长大
        BLOOMS_GROWING,//树叶长大
        MOVING_SNAPSHOT,//移动快照
        BLOOPS_FALLING,//树叶掉落
    }

    private Step step = Step.BRANCHES_GROWING;

    //绘制树干
    private float branchesDx;
    private float branchesDy;
    private LinkedList<Branch> growingBranches = new LinkedList<>();

    //scale Factory
    private static final float CROWN_RADIUS_FACTORY = 0.35f;
    private static final float STAND_FACTORY = (CROWN_RADIUS_FACTORY / 0.28f);
    private static final float BRANCHES_FACTORY = 1.3f * STAND_FACTORY;
    private final float resolutionFactor;//屏幕像素缩放因子

    //Snapshot
    private final Snapshot treeSnapshot;
    private Paint snapshotPaint = new Paint();

    private float snapshotDx;
    private float xOffset = 200f;
    private float maxOffset;


    public Tree(int canvasWidth, int canvasHeight) {
        //数据初始化
        resolutionFactor = canvasHeight / 1080f;
//        TreeMaker.init(canvasHeight, CROWN_RADIUS_FACTORY);

        //snapshot
        float snapshotWidth = 816f * STAND_FACTORY * resolutionFactor;
        treeSnapshot = new Snapshot(Bitmap.createBitmap(Math.round(snapshotWidth), canvasHeight, Bitmap.Config.ARGB_8888));
        //Branches
        float branchesWidth = 375f * BRANCHES_FACTORY * resolutionFactor;
        float branchesHeight = 490f * BRANCHES_FACTORY * resolutionFactor;
        branchesDx = (canvasWidth-branchesWidth) / 2f;
        branchesDy = canvasHeight - branchesHeight;
        growingBranches.add(TreeMaker.getBranches());

    }

    public void draw(Canvas canvas) {
        canvas.drawColor(0xFFFFFF);
        canvas.save();
        canvas.translate(snapshotDx + xOffset, 0);
        switch (step) {
            case BRANCHES_GROWING:
                drawBranches();
                drawSnapshot(canvas);
                break;
            case BLOOMS_GROWING:
                break;
            case BLOOPS_FALLING:
                break;
            case MOVING_SNAPSHOT:
                break;
        }
        canvas.restore();
    }

    private void drawBranches() {
        if (!growingBranches.isEmpty()) {
            LinkedList<Branch> tempBranches = null;
            treeSnapshot.canvas.save();
            treeSnapshot.canvas.translate(branchesDx, branchesDy);

            Iterator<Branch> iterator = growingBranches.iterator();
            while (iterator.hasNext()) {
                Branch branch = iterator.next();
                if (!branch.grow(treeSnapshot.canvas, BRANCHES_FACTORY * resolutionFactor)) {
                    iterator.remove();
                    if (branch.childList != null) {
                        if (tempBranches == null) {
                            tempBranches = branch.childList;
                        } else {
                            tempBranches.addAll(branch.childList);
                        }
                    }
                }
            }
            treeSnapshot.canvas.restore();
            if (tempBranches != null) {
                growingBranches.addAll(tempBranches);
            }
        }
        if (growingBranches.isEmpty()) {
            //绘制树干完成
            step = Step.BLOOMS_GROWING;
        }

    }

    private void drawSnapshot(Canvas canvas) {
        canvas.drawBitmap(treeSnapshot.bitmap, 0, 0, snapshotPaint);
    }
}
