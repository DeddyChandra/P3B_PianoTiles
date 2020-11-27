package com.example.p3bpianotiles;

public class MainMenuPresenter implements MainMenuContract.presenter{
    //0 = easy, 1 = normal, 2 = hard
    int level;
    boolean mute;

    public MainMenuPresenter(){
        this.level = 1;
        this.mute = false;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }
}
