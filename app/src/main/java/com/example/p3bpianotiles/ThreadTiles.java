package com.example.p3bpianotiles;

import android.util.Log;

import static java.lang.Thread.sleep;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;


    ThreadTiles(TilesHandler handler,Tiles tiles){
        thread = new Thread(this);
        this.tiles =tiles  ;
        this.handler=handler;

    }
    public void startingthread(){
        this.thread.start();

    }

    @Override
    public void run() {

        while(tiles.getStop()==false) {
            //handler.setMessage(tiles,1);


            handler.setMessage(tiles,0);

                try {

                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }
    }
}
