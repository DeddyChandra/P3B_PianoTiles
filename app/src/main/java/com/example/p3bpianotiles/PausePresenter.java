package com.example.p3bpianotiles;

public class PausePresenter implements PauseContract.presenter{
    private PauseContract.ui ui;

    public PausePresenter(PauseContract.ui ui){
        this.ui = ui;
    }
}
