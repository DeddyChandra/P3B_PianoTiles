package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;
    private GameplayPresenterInterface.Presenter presenter;

    ThreadTiles(TilesHandler handler, Tiles tiles,  GameplayPresenterInterface.Presenter presenter){
        thread = new Thread(this);

        this.tiles =tiles;
        tiles.setTimestamp(System.currentTimeMillis());
        this.handler=handler;
        this.presenter=presenter;

    }
    public void startingthread(){
        this.thread.start();

    }

    @Override
    public void run() {

        while(tiles.getStop()==false) {
            //handler.setMessage(tiles,1);

            Object arr[]= {
                tiles,check(),getAy(),YPassThrought()
            };
            handler.setMessage(arr,0);

                try {

                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }
    }

    public boolean YPassThrought(){
        //Log.d("checks", tiles.toString());
        if(tiles.getY() >= 0 && !tiles.isPass()){

            tiles.setPass(true);
            return true;
        }
        else{

            return false;
        }
    }

    public boolean check(){
        if (presenter.getTouchPoint()!=null&&
                presenter.getTouchPoint().x <= tiles.x + tiles.getWidth() &&
                presenter.getTouchPoint().x >= tiles.x &&
                presenter. getTouchPoint().y >=tiles.y &&
                presenter.getTouchPoint().y <=tiles.getY() + tiles.getHeight())
        {
            return true;
        }else{
            return false;
        }
    }

    public float getAy(){
        long prevtime = tiles.getTimestamp();
        long curtime = System.currentTimeMillis();
        tiles.setTimestamp(curtime);
        float deltatime = (curtime-prevtime)/5.0f;
        return deltatime;
    }
}
