package com.example.p3bpianotiles;
import java.util.Random;
public class Tiles {
    public int column;
    public float x;
    public float y;
    public float width;
    public float height;
    public boolean clicked;
    public int note;
    public long timestamp;
    public boolean pass;
    public boolean addedScore;
    public boolean  toBeDelete;
    public int color;
    public Random rand;

    public long getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(long timestamp){
        this.timestamp=timestamp;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void resetTiles(){
        this.timestamp=System.currentTimeMillis();
        this.setColumn(rand.nextInt(4));
        this.setAddedScore(false);
        this.setY(-height);
        this.pass = false;
        this.clicked = false;
        this.toBeDelete=false;
    }

    public Tiles(int column,float width,float length,int note){
        this.note = note;
        this.column = column;
        this.pass = false;
        this.rand = new Random();
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
        setColor(R.color.black);
    }

    public Tiles(int column,float width,float length,int note ,int color){
        this.note = note;
        this.column = column;
        this.pass = false;
        this.rand = new Random();
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
        setColor(color);
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

    public void setToBeDelete(boolean toBeDelete) {
        this.toBeDelete = toBeDelete;
    }

    public boolean getToBeDelete(){
        return toBeDelete;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isAddedScore() {
        return addedScore;
    }

    public void setAddedScore(boolean addedScore) {
        this.addedScore = addedScore;
    }

    public int getNote() {
        return note;
    }
}
