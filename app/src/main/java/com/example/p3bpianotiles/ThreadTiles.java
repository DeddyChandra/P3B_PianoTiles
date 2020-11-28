package com.example.p3bpianotiles;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;
    ThreadTiles(TilesHandler handler){
        thread = new Thread(this);
        tiles = new Tiles(10,10,1000,1000);
        this.handler=handler;
    }
    public void startingthread(){
        this.thread.start();
        //this.thread.join();
    }
    @Override
    public void run() {
        while(true) {
            handler.setMessage(tiles,0);
        }
    }
}
