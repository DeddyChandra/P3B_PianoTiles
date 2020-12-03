package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameplayPresenter implements GameplayPresenterInterface.Presenter {
    ArrayList<Tiles> tilesArrayList;
    TilesHandler handler;
    GameplayPresenterInterface.UI presenterUI;
    GameState gameState;
    PointF point;
    public int level;
    public int index;
    //hapus
    public int width, height;

    public void setWH(int width, int height){
        this.width = width;
        this.height = height;
    }

    //hapus

    GameplayPresenter(GameplayPresenterInterface.UI presenterUI){
        tilesArrayList = new ArrayList<>();
        this.presenterUI=presenterUI;
        index = 0;
    }
    public void generateTiles(int column,int width,int height, int index){
        //Log.d("generate:",tilesArrayList.get(index).getY()+"");
        handler= new TilesHandler(this);

        ThreadTiles thread = new ThreadTiles(handler,tilesArrayList.get(index),this);
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
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height));
            }
        }
        else if(level == 1){
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height));
            }
        }
        else{
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height));
            }
        }
    }

    public void drawRedrawTiles(Object[] arr){
        presenterUI.delete((Tiles)arr[0]);
        ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[2]);
        presenterUI.draw(((Tiles) arr[0]));
    }
    public void delete(Object[] arr){
        presenterUI.delete((Tiles)arr[0]);
    }

    public void generateMultipleTiles(Object[] arr){
        if((boolean)arr[3]){
            index++;
            Log.d("index", "generateMultipleTiles:"+index);
            generateTiles(generateRandomColumn(), width, height, index);
        }
        if(index >= this.tilesArrayList.size()-1){
            Log.d("index", "reset:"+index);
            index = -1;
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
}
