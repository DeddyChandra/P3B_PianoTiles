package com.example.p3bpianotiles;

import android.util.Log;

import static java.lang.Thread.sleep;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;
    protected boolean stopped = false;

    ThreadTiles(TilesHandler handler,Tiles tiles){
        thread = new Thread(this);
        this.tiles =tiles  ;
        this.handler=handler;
    }
    public void startingthread(){
        this.thread.start();
        //this.thread.join();
    }
    public void stopThread(){
        Log.d("TAG", "stopThread: ");
        this.stopped = false;
    }
    @Override
    public void run() {
        this.stopped = true;
        while(true) {
            //handler.setMessage(tiles,1);


            handler.setMessage(tiles,0);
            if(stopped){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
