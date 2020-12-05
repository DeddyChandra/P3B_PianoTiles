package com.example.p3bpianotiles;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import static java.lang.Thread.sleep;

public class ThreadTiles implements Runnable {
    private Thread thread;
    private TilesHandler handler;
    private Tiles tiles;
    private GameplayContract.Presenter presenter;
    private GameState gameState;
    private boolean isgenerated;
    private SoundPoolTiles soundPoolTiles;
    private boolean pause;


    ThreadTiles(TilesHandler handler, Tiles tiles,  GameplayContract.Presenter presenter, GameState gameState,SoundPoolTiles soundPoolTiles ){
        thread = new Thread(this);
        this.tiles =tiles;
        tiles.resetTiles();
        this.handler=handler;
        this.presenter=presenter;
        this.gameState = gameState;
        this.isgenerated=false;
        this.pause = false;
        this.soundPoolTiles=soundPoolTiles;
    }
    public void startingthread(){
        this.thread.start();
    }

    @Override
    public void run() {
        while(tiles.getToBeDelete()==false && !presenter.getLoseState()) {
            checkLose();
            while(presenter.isPause()){
                pause = true;
            }
            if(pause){
                try {
                    Thread.sleep(3000);
                    long curtime = System.currentTimeMillis();
                    tiles.setTimestamp(curtime);
                }
                catch (Exception e){

                }
                pause = false;
            }
            if(this.presenter.getGameState() == 2){
                break;
            }
            Object arr[]= {
                    tiles,getAy()
            };
            handler.setMessage(arr, 0);
            if(YPassThrought()==true&&isgenerated==false) {
                Object arry[] = {
                        true
                };
                handler.setMessage(arry, 2);
                isgenerated=true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        soundPoolTiles.play(tiles.getNote());
        Object arr[]= {
                tiles,getAy()
        };
        handler.setMessage(arr,1);
        boolean masuk=false;
        while(tiles.getY()<=0 && !presenter.isPause()){
            handler.setMessage(arr,4);
        }
        if(isgenerated==false){
            Object arry[]= {
                    true
            };
            handler.setMessage(arry,2);
            isgenerated=true;
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

    public synchronized float getAy(){
        long prevtime = tiles.getTimestamp();
        long curtime = System.currentTimeMillis();
        tiles.setTimestamp(curtime);
        float deltatime = (curtime-prevtime)/1f;
        return deltatime;
    }

    public void checkLose(){
        this.presenter.checkLose(tiles.getY()+tiles.getHeight());
    }

}
