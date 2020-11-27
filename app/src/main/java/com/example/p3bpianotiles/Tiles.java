package com.example.p3bpianotiles;

public class Tiles {
    int column;//buat columnnya
    float x;
    float y;
    float width;
    float height;

    public Tiles(int column,int height){
        this.column = column;
        this.height = height;
    }
    public Tiles(float x,float y,float width,float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    public void setWidth(float width){
        this.width=width;
    }
    public void setHeight(float height){
        this.height=height;
    }
    public void setColumn(int column){
        this.column= column;
    }

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public float getWidth(){
        return this.width;
    }
    public float getHeight(){
        return this.height;
    }
    public int getColumn(){
        return this.column;
    }


}
