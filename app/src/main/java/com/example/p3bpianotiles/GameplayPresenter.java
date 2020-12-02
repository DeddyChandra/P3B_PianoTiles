package com.example.p3bpianotiles;

import java.util.ArrayList;

public class GameplayPresenter implements GameplayPresenterInterface.Presenter {
    ArrayList<Tiles> tiles;
    TilesHandler handler;
    GameplayPresenterInterface.UI presenterUI;
    public void generateTiles(GameplayPresenterInterface.UI presenterUI){
        handler= new TilesHandler(this);
        ThreadTiles thread = new ThreadTiles(handler);
        thread.startingthread();
        this.presenterUI=presenterUI;

    }
    public void drawRedrawTiles(Tiles tiles){
        presenterUI.delete(tiles);
        tiles.setY(tiles.getY()+1);
        presenterUI.draw(tiles);


    }
}
