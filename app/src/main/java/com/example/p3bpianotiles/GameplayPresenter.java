package com.example.p3bpianotiles;

import java.util.ArrayList;

public class GameplayPresenter implements GameplayPresenterInterface.Presenter {
    ArrayList<Tiles> tiles;
    TilesHandler handler;
    public void generateTiles(GameplayPresenterInterface.UI presenterUI){
        handler= new TilesHandler(presenterUI);
        ThreadTiles thread = new ThreadTiles(handler);
        thread.startingthread();
    }
}
