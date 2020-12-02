package com.example.p3bpianotiles;

import android.graphics.PointF;

public interface GameplayPresenterInterface {
    interface UI{
        public void draw(Tiles tiles);
        public void delete(Tiles tiles);

    }
    interface Presenter{
        public void generateTiles(int x,int y,int width,int height);
        public void drawRedrawTiles(Tiles tiles);
        public void setTouchPoint(PointF pointf);
        public PointF getTouchPoint();
    }
}
