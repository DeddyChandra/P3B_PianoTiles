package com.example.p3bpianotiles;

public class Tiles {
    int column;//buat columnnya
    double x;
    double y;
    double width;
    double height;

    public Tiles(int column,int height){
        this.column = column;
        this.height = height;
    }
    public Tiles(double x,double y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }
    public void setWidth(double width){
        this.width=width;
    }
    public void setHeight(double height){
        this.height=height;
    }
    public void setColumn(int column){
        this.column= column;
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getWidth(){
        return this.width;
    }
    public double getHeight(){
        return this.height;
    }
    public int getColumn(){
        return this.column;
    }


}
