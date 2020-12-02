package com.example.p3bpianotiles;

import static java.lang.Thread.sleep;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;
    ThreadTiles(TilesHandler handler){
        thread = new Thread(this);
        tiles = new Tiles(10,10,100,100);
        this.handler=handler;
    }
    public void startingthread(){
        this.thread.start();
        //this.thread.join();
    }
    @Override
    public void run() {
        while(true) {
            //handler.setMessage(tiles,1);


            handler.setMessage(tiles,0);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
