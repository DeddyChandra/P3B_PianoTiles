package com.example.p3bpianotiles;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingPresenter implements SettingContract.presenter {
    private List<Background> backgroundList;
    private SettingContract.ui ui;
    int backgroundIndex;
    int backgroundId;

    public SettingPresenter(SettingContract.ui ui,List<Background> backgroundList ){
        this.ui = ui;
        this.backgroundList = backgroundList;
    }

    public void setBackgroundIndex(int backgroundIndex) {
        this.backgroundIndex = backgroundIndex;
    }

    public void changeBackground(){
        if(backgroundIndex+1 > backgroundList.size()){
            backgroundIndex = 0;
            this.ui.setBackgroundIndex(0);
            this.backgroundId = backgroundList.get(backgroundIndex).getId();
            this.ui.changeBackground(backgroundId);
        }
        else{
            this.backgroundId = backgroundList.get(backgroundIndex).getId();
            this.ui.changeBackground(backgroundId);
            backgroundIndex++;
        }
    }

    public int getBackgroundId() {
        return backgroundId;
    }
}
