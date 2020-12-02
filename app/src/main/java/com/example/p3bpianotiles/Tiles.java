package com.example.p3bpianotiles;

public class Tiles {
    int column;//buat columnnya
    float x;
    float y;
    float width;
    float height;
    boolean stop;
    int note;
    long timestamp;
//kecepatan = jarak / waktu
    //System.currentTimeMillis();
    //System.currentTimeMillis();

    public Tiles(float x,float y,float width,float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.timestamp = System.currentTimeMillis();
    }
    public long getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(long timestamp){
        this.timestamp=timestamp;
    }

    public Tiles(int column,float width,float length){
        this.column = column;
        if(column==0){
           setX(0);
           setY(-length);
           setWidth(width/4);
           setHeight(length);
        }
        if(column==1){
            setX(width/4);
            setY(-length);
            setWidth(width/4);
            setHeight(length);
        }
        if(column==2){
            setX(2*width/4);
            setY(-length);
            setWidth(width/4);
            setHeight(length);
        }
        if(column==3){
            setX(3*width/4);
            setY(-length);
            setWidth(width/4);
            setHeight(length);
        }
        this.timestamp = System.currentTimeMillis();
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
    public void setStop(boolean stop){
        this.stop=stop;
    }
    public boolean getStop(){
        return this.stop;
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
