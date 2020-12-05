package com.example.p3bpianotiles;

import java.util.Random;

public class MusicFiles {
    static int width;
    static int height;
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
    public static Tiles[] LittleStar={

            new Tiles(generateRandomColumn(),width,height/4,1),
            new Tiles(generateRandomColumn(),width,height/4,1),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,6),
            new Tiles(generateRandomColumn(),width,height/4,6),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,2),
            new Tiles(generateRandomColumn(),width,height/4,2),
            new Tiles(generateRandomColumn(),width,height/4,1),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,2),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,2),
            new Tiles(generateRandomColumn(),width,height/4,1),
            new Tiles(generateRandomColumn(),width,height/4,1),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,6),
            new Tiles(generateRandomColumn(),width,height/4,6),
            new Tiles(generateRandomColumn(),width,height/4,5),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,4),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,3),
            new Tiles(generateRandomColumn(),width,height/4,2),
            new Tiles(generateRandomColumn(),width,height/4,2),
            new Tiles(generateRandomColumn(),width,height/4,1),
    };

    private static float generateRandomColumn() {
        Random rand = new Random();
        return rand.nextInt(4);

    }

}
