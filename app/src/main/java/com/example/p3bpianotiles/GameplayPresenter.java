package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameplayPresenter implements GameplayContract.Presenter {
    ArrayList<Tiles> tilesArrayList;
    TilesHandler handler;
    GameplayContract.UI presenterUI;
    GameState gameState;
    SoundPoolTiles soundPoolTiles;
    PointF point;
    public int level;
    public int index;
    public int indexfirst;
    public boolean lose;
    public int width, height;
    public boolean pause;
    public FragmentActivity fragmentActivity;

    GameplayPresenter(GameplayContract.UI presenterUI, int width, int height, FragmentActivity fragmentActivity){
        this.tilesArrayList = new ArrayList<>();
        this.presenterUI=presenterUI;
        this.width = width;
        this.height = height;
        this.index = 0;
        this.indexfirst = 0;
        this.lose = false;
        this.pause = false;
        this.gameState = new GameState(0);
        this.fragmentActivity = fragmentActivity;
        this.soundPoolTiles = new SoundPoolTiles(this);
    }

    public FragmentActivity getActivity(){
        return this.fragmentActivity;
    }

    public void generateTiles(int column,int width,int height, int index){
        //Log.d("generate:",tilesArrayList.get(index).getY()+"");
        handler= new TilesHandler(this);
        ThreadTiles thread = new ThreadTiles(handler,tilesArrayList.get(index),this,gameState,soundPoolTiles);
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
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height/4,1));
            }
        }
        else if(level == 1){
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height/4,1));
            }
        }
        else{
            for(int i = 0; i < 5; i++){
                this.tilesArrayList.add(new Tiles(generateRandomColumn(),width,height/4,1));
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

        indexfirst++;
    }
    public void increaseAy(Object[] arr){
        ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[1]);
    }

    public void checkClick(PointF p){
        if(indexfirst>=tilesArrayList.size()){
            indexfirst=0;

        }

        int idxclicked=-1;
        if(indexfirst<index) {
            for (int i = indexfirst; i < index; i++) {
                if (getTouchPoint() != null &&
                    p.x <= tilesArrayList.get(i).x + tilesArrayList.get(i).getWidth() &&
                    p.x >= tilesArrayList.get(i).x &&
                    p.y >= tilesArrayList.get(i).y &&
                    p.y <= tilesArrayList.get(i).getY() + tilesArrayList.get(i).getHeight()) {
                        idxclicked = i;
                }
            }
        }else {
            for (int i = indexfirst; i < tilesArrayList.size(); i++) {
                    if (getTouchPoint() != null &&
                            p.x <= tilesArrayList.get(i).x + tilesArrayList.get(i).getWidth() &&
                            p.x >= tilesArrayList.get(i).x &&
                            p.y >= tilesArrayList.get(i).y &&
                            p.y <= tilesArrayList.get(i).getY() + tilesArrayList.get(i).getHeight()) {
                        idxclicked = i;
                    }
            }


            for (int i = 0; i < index; i++) {
                if (getTouchPoint() != null &&
                    p.x <= tilesArrayList.get(i).x + tilesArrayList.get(i).getWidth() &&
                    p.x >= tilesArrayList.get(i).x &&
                    p.y >= tilesArrayList.get(i).y &&
                    p.y <= tilesArrayList.get(i).getY() + tilesArrayList.get(i).getHeight()) {
                        idxclicked = i;
                }
            }
        }
        if(idxclicked==-1){
            Log.d("delete","mati");
            gameState.setState(GameState.GAME_OVER);
        }else if(idxclicked==indexfirst){
            if(!tilesArrayList.get(idxclicked).isAddedScore()) {
                addScore();
                tilesArrayList.get(idxclicked).setAddedScore(true);
            }
            Log.d("delete","delete");
            tilesArrayList.get(idxclicked).setToBeDelete(true);
        }


    }

    public void generateMultipleTiles(Object[] arr){
        //Log.d("masuk","masuk");
        if(gameState.getState() == 2){
            this.presenterUI.lose();
        }
        else if(gameState.getState() == 1){
            Log.d("pause", "pause");
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

    public void setToLoseState(){
        this.lose = true;
    }

    public boolean getLoseState(){
        return this.lose;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
        if(pause){
            gameState.setState(1);
        }
        else{

        }
    }
}
