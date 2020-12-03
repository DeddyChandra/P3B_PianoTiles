package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameplayPresenter implements GameplayPresenterInterface.Presenter {
    ArrayList<Tiles> tilesArrayList;
    TilesHandler handler;
    GameplayPresenterInterface.UI presenterUI;
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
            for(int i = 0; i < 20; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height));
            }
        }
        else if(level == 1){

        }
        else{

        }
    }

    public void drawRedrawTiles(Object[] arr){
        presenterUI.delete((Tiles)arr[0]);
        if((boolean)arr[1]==true) {
            ((Tiles) arr[0]).setStop(true);
        }else {
            ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[2]);
            presenterUI.draw(((Tiles) arr[0]));
        }
    }

    public void generateMultipleTiles(Object[] arr){
        if(index >= this.tilesArrayList.size()){
            index = 0;
        }
        if((boolean)arr[3]){
            index++;
            Log.d("index", "generateMultipleTiles:"+index);
            generateTiles(generateRandomColumn(), width, height, index);
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
