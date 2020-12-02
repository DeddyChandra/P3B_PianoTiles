package com.example.p3bpianotiles;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingPresenter implements SettingContract.presenter {
    private List<Background> backgroundList;
    private SettingContract.ui ui;
    int backgroundIndex = 0;

    public SettingPresenter(SettingContract.ui ui,List<Background> backgroundList){
        this.ui = ui;
        this.backgroundList = backgroundList;
    }

    public void changeBackground(){
        Log.d("change", "changeBackground: ");
        if(backgroundIndex+1 >= backgroundList.size()){
            backgroundIndex = 0;
        }
        else{
            backgroundIndex++;
        }
        this.ui.changeBackground(backgroundList.get(backgroundIndex).getId());
    }
}
