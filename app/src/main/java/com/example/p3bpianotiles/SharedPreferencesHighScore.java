package com.example.p3bpianotiles;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHighScore {
    protected SharedPreferences sharedPreferences;
    protected final static String SHARED_PREF_HS = "highscore_display";
    protected final static String KEY_HIGHSCORE = "highscore";

    public SharedPreferencesHighScore(Context context){
        this.sharedPreferences =  context.getSharedPreferences(SHARED_PREF_HS,Context.MODE_PRIVATE);
    }
    public void saveHighScore(String highscore){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(KEY_HIGHSCORE,highscore);
        editor.commit();
    }
    public String getHighScore(){
        return sharedPreferences.getString(KEY_HIGHSCORE,"");
    }
}
