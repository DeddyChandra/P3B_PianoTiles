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
        ThreadTiles thread = new ThreadTiles(handler,new Tiles(column,width,height),this);
        thread.startingthread();
        this.presenterUI=presenterUI;

    }
    public void setTouchPoint(PointF point){
        this.point = point;
    }
    public void drawRedrawTiles(Object[] arr){
        presenterUI.delete((Tiles)arr[0]);
        if((boolean)arr[1]==true) {
            ((Tiles) arr[0]).setStop(true);
        }else {
            ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[2]);
            presenterUI.draw(((Tiles) arr[0]));
        }

    }
    public PointF getTouchPoint(){
        return this.point;
    }




}
