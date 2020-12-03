package com.example.p3bpianotiles;

import android.graphics.PointF;

public interface GameplayContract {
    interface UI{
        public void draw(Tiles tiles);
        public void delete(Tiles tiles);

    }
    interface Presenter{
        public void generateTiles(int column, int width,int height, int index);
        public void drawRedrawTiles(Object[] arr);
        public void setTouchPoint(PointF pointf);
        public PointF getTouchPoint();
        void delete(Object o[]);
        void setLevel(int level);
        void generate(Object[] arr);
        //hapus
        void setWH(int width, int height);
        void setPassTrue(Object[] objects);
        void setTimeStamp(Object[] objects);
        //hapus
    }
}
