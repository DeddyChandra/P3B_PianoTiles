package com.example.p3bpianotiles;

import android.graphics.PointF;

import androidx.fragment.app.FragmentActivity;

public interface GameplayContract {
    interface UI{
        void draw(Tiles tiles);
        void draw(float x, float y, float width, float height);
        void delete(Tiles tiles);
        void addScore();
        void lose();
        void setUnPauseCount(String s);

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
        FragmentActivity getActivity();
        void setUnPauseCount(Object[] s);
        void checkSensor(float roll);
        void changeVolume(int vol);
        float getHeight();

    }
}
