package com.example.p3bpianotiles;

public interface GameplayPresenterInterface {
    interface UI{
        public void draw(Tiles tiles);
        public void delete(Tiles tiles);
    }
    interface Presenter{
        public void generateTiles(GameplayPresenterInterface.UI presenterUI);
        public void drawRedrawTiles(Tiles tiles);
    }
}
