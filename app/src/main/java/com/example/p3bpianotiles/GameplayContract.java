package com.example.p3bpianotiles;

import android.graphics.PointF;

public interface GameplayContract {
    interface UI{
        void draw(Tiles tiles);
        void delete(Tiles tiles);
        void addScore();
        void lose();

    }
    interface Presenter{
        void generateTiles(int column, int width,int height, int index);
        void drawRedrawTiles(Object[] arr);
        void setTouchPoint(PointF pointf);
        PointF getTouchPoint();
        void delete(Object o[]);
        void setLevel(int level);
        void generate(Object[] arr);
        void addScore();
        void checkLose(float lowerY);
        void setGameState(int i);
        int getGameState();
        void checkClick(PointF pointf);
        void increaseAy(Object[] arr);
        boolean getLoseState();
        void setToLoseState();
        boolean isPause();
        void setPause(boolean pause);
    }
}
