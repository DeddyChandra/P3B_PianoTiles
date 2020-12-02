package com.example.p3bpianotiles;

import android.os.Handler;
import android.os.Message;

import androidx.fragment.app.FragmentActivity;

public class TilesHandler extends Handler {
    GameplayPresenterInterface.Presenter presenter;
    private static int MSG_DRAW=0;
    public TilesHandler(GameplayPresenterInterface.Presenter presenter){
        this.presenter= presenter;
    }
    public void handleMessage(Message msg){
        if(msg.what==MSG_DRAW){
            presenter.drawRedrawTiles((Tiles)msg.obj);
        }

    }
    public void setMessage(Tiles tiles,int i){
        Message msg= new Message();
        msg.what = i;
        msg.obj= tiles;
        this.sendMessage(msg);
        //Log.d("masuk","masuk")
    }
}
