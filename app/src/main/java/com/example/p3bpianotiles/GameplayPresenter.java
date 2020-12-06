package com.example.p3bpianotiles;

import android.graphics.PointF;
import android.os.Handler;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameplayPresenter implements GameplayContract.Presenter {
    public ArrayList<Tiles> tilesArrayList;
    public TilesHandler handler;
    public GameplayContract.UI presenterUI;
    public GameState gameState;
    public SoundPoolTiles soundPoolTiles;
    public PointF point;
    public int level;
    public int index;
    public int indexfirst;
    public boolean lose;
    public int width, height;
    public boolean pause;
    public FragmentActivity fragmentActivity;
    public MusicFiles mf;
    private float volume;

    GameplayPresenter(GameplayContract.UI presenterUI, int width, int height, FragmentActivity fragmentActivity){
        this.tilesArrayList = new ArrayList<>();
        this.presenterUI=presenterUI;
        this.width = width;
        this.height = height;
        this.index = 0;
        this.indexfirst = 0;
        this.lose = false;
        this.gameState = new GameState(0);
        this.fragmentActivity = fragmentActivity;
        this.soundPoolTiles = new SoundPoolTiles(this);
        this.mf = new MusicFiles(width, height);
    }

    public FragmentActivity getActivity(){
        return this.fragmentActivity;
    }

    public void generateTiles(int column,int width,int height, int index){
        this.handler= new TilesHandler(this);
        ThreadTiles thread = new ThreadTiles(handler,tilesArrayList.get(index),this,gameState,soundPoolTiles);
        thread.startingthread();
    }

    public void setTouchPoint(PointF point){
        this.point = point;
    }

    public float getHeight() {
        return height;
    }

    public void setLevel(int level) {
        this.level = level;
        this.setArrayTiles();
    }

    public void setArrayTiles(){
        if(level == 0){
            gameState.setSpeed(0.5f);
            this.tilesArrayList = mf.getKartini();
        }
        else if(level == 1){
            gameState.setSpeed(0.6f);
            this.tilesArrayList = mf.getLittleStart();
        }
        else{
            gameState.setSpeed(0.8f);
            this.tilesArrayList = mf.getMinuet();

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

    }
    public void increaseAy(Object[] arr){
        ((Tiles) arr[0]).setY(((Tiles) arr[0]).getY() + (float) arr[1]);
    }

    public void checkClick(PointF p){
        if(indexfirst>=tilesArrayList.size()){
            indexfirst=0;
        }

        int idxclicked=-1;
        if(indexfirst<=index) {
            for (int i = indexfirst; i <= index; i++) {
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
            for (int i = 0; i <= index; i++) {
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

            gameOver(p);
        }else if(idxclicked==indexfirst&&tilesArrayList.get(idxclicked).getColor()==R.color.black){
            if(!tilesArrayList.get(idxclicked).isAddedScore()) {
                addScore();
                tilesArrayList.get(idxclicked).setAddedScore(true);
            }
            tilesArrayList.get(idxclicked).setToBeDelete(true);
            indexfirst++;
        }
    }

    public void checkSensor(float roll){
        if(indexfirst>=tilesArrayList.size()){
            indexfirst=0;
        }
        if(tilesArrayList.get(indexfirst).getColor()==R.color.yellow){
            if(roll<-0.3){
                if(!tilesArrayList.get(indexfirst).isAddedScore()) {
                    addScore();
                    tilesArrayList.get(indexfirst).setAddedScore(true);
                }
                tilesArrayList.get(indexfirst).setToBeDelete(true);
                indexfirst++;
            }

        }else if(tilesArrayList.get(indexfirst).getColor()==R.color.green){
            if(roll>0.3) {
                if (!tilesArrayList.get(indexfirst).isAddedScore()) {
                    addScore();
                    tilesArrayList.get(indexfirst).setAddedScore(true);
                }
                tilesArrayList.get(indexfirst).setToBeDelete(true);
                indexfirst++;
            }
        }
    }

    public void generateMultipleTiles(Object[] arr){
        if(gameState.getState() == 2){
            this.presenterUI.lose();
        }
        else if(gameState.getState() == 1){
//            Log.d("pause", "pause");
        }
        else {
            if ((boolean) arr[0]) {
                generateTiles(generateRandomColumn(), width, height/4, index);
            }
            if (index >= this.tilesArrayList.size() - 1) {
                index = -1;
            }
        }
    }

    @Override
    public PointF getTouchPoint(){
        return this.point;
    }

    public void generate(Object[] arr){
        index++;
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
        if(lowerY >= height){
            gameOver();
            lose = true;

        }
        else{
            lose = false;
        }
    }

    private void gameOver(){
        gameState.setState(GameState.GAME_OVER);
        soundPoolTiles.play(9);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        this.releaseSoundPool();
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
            gameState.setState(0);
        }
    }

    public void setUnPauseCount(Object[] s){
        this.presenterUI.setUnPauseCount(s[0].toString());
    }

    public void changeVolume(int vol){
        this.volume =(float)(1-Math.log(100-vol)/Math.log(100));
        if(Double.isInfinite(volume)){
            volume = 1;
        }
        this.soundPoolTiles.setVolume(volume);
    }

    public void gameOver(final PointF touchPoint){

        if(point.x<width/4) {
            presenterUI.draw(0, touchPoint.y, width / 4, height / 4);
        }else if(point.x<2*width/4){
            presenterUI.draw(width/4, touchPoint.y, width / 4, height / 4);
        }
        else if(point.x<3*width/4){
            presenterUI.draw(2*width/4, touchPoint.y, width / 4, height / 4);
        }
        else{
            presenterUI.draw(3*width/4, touchPoint.y, width / 4, height / 4);
        }
        this.gameOver();

    }

    public void releaseSoundPool(){
        this.soundPoolTiles.setRelease();
    }
}
