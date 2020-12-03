package com.example.p3bpianotiles;

import android.os.Handler;
import android.os.Message;

import androidx.fragment.app.FragmentActivity;

public class TilesHandler extends Handler {
    GameplayPresenterInterface.Presenter presenter;
    protected GameplayFragment gameplayFragment;

    private static int MSG_DRAW=0;
    protected final static int ADD_SCORE=0;

    public TilesHandler(GameplayPresenterInterface.Presenter presenter){
        this.presenter= presenter;
    }
    public void handleMessage(Message msg){
        if(msg.what==MSG_DRAW){
            presenter.drawRedrawTiles((Object[])msg.obj);
            presenter.generate((Object[])msg.obj);
        }
        else if(msg.what == TilesHandler.ADD_SCORE) {
            this.gameplayFragment.addScore();
        }
    }
    public void setMessage(Object[] arr, int i){
        Message msg= new Message();
        msg.what = i;
        msg.obj= arr;
        this.sendMessage(msg);
        //Log.d("masuk","masuk")
    }
    public void addScore(){
        Message msg = new Message();
        msg.what = ADD_SCORE;
        this.sendMessage(msg);
    }
}
