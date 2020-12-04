package com.example.p3bpianotiles;

import static java.lang.Thread.sleep;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;
    private GameplayContract.Presenter presenter;
    private GameState gameState;

    ThreadTiles(TilesHandler handler, Tiles tiles,  GameplayContract.Presenter presenter, GameState gameState){
        thread = new Thread(this);
        this.tiles =tiles;
        tiles.resetTiles();
        this.handler=handler;
        this.presenter=presenter;
        this.gameState = gameState;
    }
    public void startingthread(){
        this.thread.start();

    }

    @Override
    public void run() {
        while(!(tiles.isPass()==true&&tiles.clicked==true)) {
            //handler.setMessage(tiles,1);
            checkLose();
            if(this.presenter.getGameState() == 2){
                break;
            }
//            if(checkClick()){
//                tiles.setClicked(true);
//            }
//            if(tiles.getClicked()==true){
//                Object arr[]= {
//                       tiles,getAy()
//                };
//                handler.setMessage(arr,1);
//
//
//            }
            else {
                Object arr[]= {
                        tiles,getAy()
                };
                handler.setMessage(arr, 0);


            }
            if(YPassThrought()==true){
                Object arr[]= {
                        true
                };
                handler.setMessage(arr,2);
            }

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



    public float getAy(){
        long prevtime = tiles.getTimestamp();
        long curtime = System.currentTimeMillis();
        tiles.setTimestamp(curtime);
        float deltatime = (curtime-prevtime)/2f;
        return deltatime;
    }

    public void checkLose(){
        this.presenter.checkLose(tiles.getY()+tiles.getHeight());
    }
}
