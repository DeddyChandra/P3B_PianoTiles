package com.example.p3bpianotiles;

import java.util.ArrayList;

public class GameplayPresenter implements GameplayPresenterInterface.Presenter {
    ArrayList<Tiles> tiles;
    TilesHandler handler;
    GameplayPresenterInterface.UI presenterUI;



    GameplayPresenter(GameplayPresenterInterface.UI presenterUI){
        this.presenterUI=presenterUI;
    }
    public void generateTiles(int x,int y,int width,int height){
        handler= new TilesHandler(this);
        ThreadTiles thread = new ThreadTiles(handler,new Tiles(1,width,height));
        thread.startingthread();
        this.presenterUI=presenterUI;

    }
    public void drawRedrawTiles(Tiles tiles){
        presenterUI.delete(tiles);
        tiles.setY(tiles.getY()+1);
        presenterUI.draw(tiles);
    }

}
