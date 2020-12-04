package com.example.p3bpianotiles;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHighScore {
    protected SharedPreferences sharedPreferences;
    protected final static String SHARED_PREF_HS = "highscore_display";
    protected final static String EASY = "easy";
    protected final static String MED = "med";
    protected final static String HARD = "hard";
    private int score;
    private int level;
    public SharedPreferencesHighScore(Context context){
        this.sharedPreferences =  context.getSharedPreferences(SHARED_PREF_HS,Context.MODE_PRIVATE);
    }
    public void saveEasy(int highscore){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(EASY,highscore);
        editor.commit();
    }
    public int getEasy(){
        return sharedPreferences.getInt(EASY,-1);
    }
    public void saveMed(int highscore){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(MED,highscore);
        editor.commit();
    }
    public int getMed(){
        return sharedPreferences.getInt(MED,-1);
    }
    public void saveHard(int highscore){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(HARD,highscore);
        editor.commit();
    }
    public int getHard(){
        return sharedPreferences.getInt(HARD,-1);
    }

}
