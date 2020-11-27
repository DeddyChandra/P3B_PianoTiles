package com.example.p3bpianotiles;

public class MainMenuPresenter implements MainMenuContract.presenter{
    //0 = easy, 1 = normal, 2 = hard
    int level;

    public MainMenuPresenter(){
        this.level = 1;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }
}
