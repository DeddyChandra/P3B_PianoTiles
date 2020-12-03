package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameplayPresenter implements GameplayContract.Presenter {
    ArrayList<Tiles> tilesArrayList;
    TilesHandler handler;
    GameplayContract.UI presenterUI;
    GameState gameState;
    PointF point;
    public int level;
    public int index;
    public boolean lose;
    //hapus
    public int width, height;
    //hapus

    GameplayPresenter(GameplayContract.UI presenterUI, int width, int height){
        tilesArrayList = new ArrayList<>();
        this.presenterUI=presenterUI;
        this.width = width;
        this.height = height;
        index = 0;
        lose = false;
        this.gameState = new GameState(0);
    }
    public void generateTiles(int column,int width,int height, int index){
        //Log.d("generate:",tilesArrayList.get(index).getY()+"");
        handler= new TilesHandler(this);
        ThreadTiles thread = new ThreadTiles(handler,tilesArrayList.get(index),this,gameState);
        thread.startingthread();
    }

    public void setTouchPoint(PointF point){
        this.point = point;
    }

    public void setLevel(int level) {
        this.level = level;
        this.setArrayTiles();
    }

    public void setArrayTiles(){
        if(level == 0){
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height/4));
            }
        }
        else if(level == 1){
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height/4));
            }
        }
        else{
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height/4));
            }
        }
    }

    public void drawRedrawTiles(Object[] arr){
        if(gameState.getState() == 2){
            this.presenterUI.lose();
        }
        else {
            presenterUI.delete((Tiles) arr[0]);
            ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[1]);
            presenterUI.draw(((Tiles) arr[0]));
        }
    }
    public void delete(Object[] arr){
        presenterUI.delete((Tiles)arr[0]);
        ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[1]);
    }

    public void generateMultipleTiles(Object[] arr){
        //Log.d("masuk","masuk");
        if(gameState.getState() == 2){
            this.presenterUI.lose();
        }
        else {
            if ((boolean) arr[0]) {
                index++;
                Log.d("index", "generateMultipleTiles:" + index);
                generateTiles(generateRandomColumn(), width, height/4, index);
            }
            if (index >= this.tilesArrayList.size() - 1) {
                Log.d("index", "reset:" + index);
                index = -1;
            }
        }
    }

    @Override
    public PointF getTouchPoint(){
        return this.point;
    }

    public void generate(Object[] arr){
//        generateTiles(generateRandomColumn(), width, height, index);
        generateMultipleTiles(arr);
    }

    public int generateRandomColumn(){
        Random rand = new Random();
        return rand.nextInt(4);
    }

    public void addScore(){
        this.presenterUI.addScore();
    }

    public void checkLose(float lowerY){
        Log.d("height", "checkLose: "+lowerY);
        Log.d("height", "checkLose: "+height);
        if(lowerY >= height){
            lose = true;
            this.gameState.setState(2);
        }
        else{
            lose = false;
        }
    }

    public void setGameState(int i){
        this.gameState.setState(i);
    }

    public int getGameState(){
        return this.gameState.getState();
    }
}
