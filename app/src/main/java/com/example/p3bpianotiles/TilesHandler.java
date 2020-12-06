package com.example.p3bpianotiles;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TilesHandler extends Handler {
    public GameplayContract.Presenter presenter;
    private static int MSG_DRAW=0;
    private static int MSG_DELTE=1;
    protected final static int ADD_SCORE=0;

    public TilesHandler(GameplayContract.Presenter presenter){
        this.presenter= presenter;
    }

    public void handleMessage(Message msg){
        if(msg.what == MSG_DRAW){
            presenter.drawRedrawTiles((Object[])msg.obj);
        }
        else if(msg.what == TilesHandler.MSG_DELTE){
            presenter.delete((Object[])msg.obj);
        }
        else if(msg.what == 2){
            Log.d("a","a");
            presenter.generate((Object[])msg.obj);
        }
        else if(msg.what == 3){
            presenter.addScore();
        }
        else if(msg.what==4){
            presenter.increaseAy((Object[])msg.obj);
        }
        else if(msg.what==5){
            presenter.setUnPauseCount((Object[])msg.obj);
        }
    }
    public void setMessage(Object[] arr, int i){
        Message msg= new Message();
        msg.what = i;
        msg.obj= arr;
        this.sendMessage(msg);
    }
}
