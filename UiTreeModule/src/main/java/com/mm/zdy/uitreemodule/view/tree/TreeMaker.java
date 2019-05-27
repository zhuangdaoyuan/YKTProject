package com.mm.zdy.uitreemodule.view.tree;

import java.util.List;

public class TreeMaker {

    private static float r;
    private static float c;
    private static int p;
    private static FallingBloom[] sRecycleBlooms = new FallingBloom[8];

    public static void init(int canvasHeight, float crownRadiusFactor) {
        r = canvasHeight * crownRadiusFactor;
        c = r * 1.35f;
    }

    public static Branch getBranches() {
        //id,parentId,bezier control points(3 points,int 6 columns),max radius,length
        int[][] data = new int[][]{
                {0, -1, 217, 490, 252, 60, 182, 10, 30, 100},
                {1, 0, 222, 310, 137, 226, 22, 210, 13, 100},
                {2, 1, 132, 245, 116, 240, 76, 285, 2, 100},
                {3, 0, 232, 255, 282, 166, 362, 155, 12, 100},
                {4, 3, 260, 210, 330, 219, 343, 210, 3, 80},
                {5, 0, 212, 91, 219, 58, 226, 27, 3, 40},
                {6, 0, 228, 207, 95, 57, 10, 54, 9, 80},
                {7, 6, 109, 96, 65, 63, 53, 15, 2, 40},
                {8, 6, 180, 155, 117, 126, 77, 140, 4, 60},
                {9, 0, 228, 167, 290, 62, 360, 31, 6, 100},
                {10, 9, 272, 103, 327, 87, 330, 80, 2, 80},
        };
        int n = data.length;
        Branch[] branches = new Branch[n];
        for (int i = 0; i < n; i++) {
            branches[i] = new Branch(data[i]);
            int parent = data[i][1];
            if (parent != -1) {
                branches[parent].addChild(branches[i]);
            }
        }
        return branches[0];
    }

    public static void recycleBloom(FallingBloom fallingBloom) {
        if (p < sRecycleBlooms.length) {
            while (true) {
                float x = CommonUtil.random(-c, c);
                float y = CommonUtil.random(-c, c);
                if (inHeart(x, y, r)) {
                    fallingBloom.reset(x, -y);
                    break;
                }
            }
            sRecycleBlooms[p++] = fallingBloom;
        }
    }



    public static void fillFallingBlooms(List<FallingBloom> blooms, int num) {
        int n = 0;
        while(n < num && p > 0){
            blooms.add(sRecycleBlooms[--p]);
            n++;
        }
        while(n < num){
            float x = CommonUtil.random(-c, c);
            float y = CommonUtil.random(-c, c);
            if (inHeart(x, y, r)) {
                blooms.add(new FallingBloom(new Point(x, -y)));
                n++;
            }
        }
    }

    public static void fillBlooms(List<Bloom> blooms, int num) {
        int n = 0;
        while (n < num) {
            float x = CommonUtil.random(-c, c);
            float y = CommonUtil.random(-c, c);
            if (inHeart(x, y, r)) {
                blooms.add(new Bloom(new Point(x, -y)));
                n++;
            }
        }
    }


    public static boolean inHeart(float px, float py, float r) {
        float x = px / r;
        float y = py / r;
        float sx = x * x;
        float sy = y * y;
        float a = sx + sy - 1;
        return a * a * a - sx * sy * y < 0;
    }

}
