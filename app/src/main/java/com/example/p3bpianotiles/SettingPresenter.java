package com.example.p3bpianotiles;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingPresenter implements SettingContract.presenter {
    private List<Background> backgroundList;
    private SettingContract.ui ui;
    int backgroundIndex;

    public SettingPresenter(SettingContract.ui ui,List<Background> backgroundList ){
        this.ui = ui;
        this.backgroundList = backgroundList;
    }

    public void setBackgroundIndex(int backgroundIndex) {
        this.backgroundIndex = backgroundIndex;
    }

    public void changeBackground(){
        Log.d("change", "changeBackgroundBefore: "+backgroundIndex);
        if(backgroundIndex+1 > backgroundList.size()){
            backgroundIndex = 0;
            this.ui.setBackgroundIndex(0);
            this.ui.changeBackground(backgroundList.get(backgroundIndex).getId());
        }
        else{
            this.ui.changeBackground(backgroundList.get(backgroundIndex).getId());
            backgroundIndex++;
        }
        Log.d("change", "changeBackground: "+backgroundIndex);

    }
}
