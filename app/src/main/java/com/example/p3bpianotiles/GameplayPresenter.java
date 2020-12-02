package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

public class GameplayPresenter implements GameplayPresenterInterface.Presenter {
    ArrayList<Tiles> tiles;
    TilesHandler handler;
    GameplayPresenterInterface.UI presenterUI;
    PointF point;


    GameplayPresenter(GameplayPresenterInterface.UI presenterUI){
        this.presenterUI=presenterUI;
    }
    public void generateTiles(int column,int width,int height){
        handler= new TilesHandler(this);
        ThreadTiles thread = new ThreadTiles(handler,new Tiles(column,width,height));
        thread.startingthread();
        this.presenterUI=presenterUI;

    }
    public void setTouchPoint(PointF point){
        this.point = point;
    }
    public void drawRedrawTiles(Tiles tiles){
        presenterUI.delete(tiles);

            if (getTouchPoint()!=null&&
                getTouchPoint().x <= tiles.x + tiles.getWidth() &&
                getTouchPoint().x >= tiles.x &&
                getTouchPoint().y >=tiles.y &&
                getTouchPoint().y <=tiles.getY() + tiles.getHeight())
            {
                tiles.setStop(true);
            }
            else {
            tiles.setY(tiles.getY() + getAy(tiles));
            presenterUI.draw(tiles);
            }
    }
    public PointF getTouchPoint(){
        return this.point;
    }


    public float getAy(Tiles tiles){
        long prevtime = tiles.getTimestamp();
        long curtime = System.currentTimeMillis();
        tiles.setTimestamp(curtime);
        float deltatime = (curtime-prevtime)/5.0f;



        return deltatime;
    }

}
