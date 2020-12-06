package com.example.p3bpianotiles;

import java.util.ArrayList;
import java.util.Random;

public class MusicFiles {
    public int width;
    public int height;
    public ArrayList<Tiles> littleStar;
    public ArrayList<Tiles> minuet;

    public MusicFiles(int width, int height){
        this.littleStar = new ArrayList<>();
        this.minuet= new ArrayList<>();
        this.width = width;
        this.height = height;
        this.addLittleStar();
        this.addMinuet();
    }


    public ArrayList<Tiles> getLittleStart(){
        return littleStar;
    }
    public ArrayList<Tiles> getMinuet(){
        return minuet;
    }

    public static Music[] music ={
            new Music (R.raw.jingle_bells, "Jingle Bell"),
            new Music (R.raw.silent_night, "Silent Night"),
            new Music (R.raw.last_christmas, "Last Christmas"),
            new Music (R.raw.xia_yu_tian, "下雨天"),
            new Music (R.raw.hui_bu_hui, "会不会"),
            new Music (R.raw.tian_wai_lai_wu, "天外来物"),
            new Music (R.raw.hou_lai_yu_jian_ta, "后来遇见他"),
            new Music (R.raw.xiao_zhang, "嚣张"),
            new Music (R.raw.yu_wo_wu_guan, "与我无关"),
            new Music (R.raw.fei_niao_he_chan, "飞鸟和蝉")
//            new Music (R.raw.do_1, "do"),
//            new Music (R.raw.re_2, "re"),
//            new Music (R.raw.mi_3, "mi"),
//            new Music (R.raw.fa_4, "fa"),
//            new Music (R.raw.so_5, "so"),
//            new Music (R.raw.la_6, "la"),
//            new Music (R.raw.si_7, "si"),
//            new Music (R.raw.do_1_octave, "do_octave")
//            new Music (R.raw.cutted5s, "cutted"),
//            new Music (R.raw.cutted5s, "cutted")
    };

    public void addLittleStar(){
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,1));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,1));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,6));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,6));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5,R.color.yellow));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,2));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,2));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,1,R.color.yellow));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,2,R.color.yellow));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,2,R.color.green));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,1));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,1));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,6));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,6));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,5));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,4));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,3));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,2));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,2));
            littleStar.add(new Tiles(generateRandomColumn(),width,height/4,1,R.color.green));
    };
    public void addMinuet(){
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,5));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,2));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,3));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,4));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,5));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,6));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,4));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,5));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,6));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,7));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,8));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,4));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,5));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,4));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,3));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,2));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,3));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,4));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,3));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,2));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,2));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,3));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,2));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,2));
            minuet.add(new Tiles(generateRandomColumn(),width,height/4,1));
    }


    public int generateRandomColumn() {
        Random rand = new Random();
        return rand.nextInt(4);

    }

}
