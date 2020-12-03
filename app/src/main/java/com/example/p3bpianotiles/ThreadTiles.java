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
        tiles.resetTiles();
        this.handler=handler;
        this.presenter=presenter;
    }
    public void startingthread(){
        this.thread.start();

    }

    @Override
    public void run() {

        while(true) {
            //handler.setMessage(tiles,1);
            if(checkClick()){
                tiles.setClicked(true);
            }
            if(tiles.getClicked()==true){
                Object arr[]= {
                       tiles,getAy()
                };
                handler.setMessage(arr,1);
                try {

                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else {
                Object arr[]= {
                        tiles,checkClick(),getAy(),YPassThrought()
                };
                handler.setMessage(arr, 0);
                try {

                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if(YPassThrought()==true){
                Object arr[]= {
                        true
                };
                handler.setMessage(arr,2);
            }
            if(tiles.isPass()==true&&tiles.clicked==true){

                break;
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

    public boolean checkClick(){
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
        float deltatime = (curtime-prevtime)/2f;
        return deltatime;
    }
}
