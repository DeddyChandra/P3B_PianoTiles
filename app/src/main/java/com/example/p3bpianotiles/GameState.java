package com.example.p3bpianotiles;

public class GameState {
    public static final int RESUME = 1;
    public static final int GAME_OVER=2;
    private int state;


    public GameState(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
