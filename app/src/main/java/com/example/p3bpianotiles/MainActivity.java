package com.example.p3bpianotiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.p3bpianotiles.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener{
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private MainMenuFragment mainMenuFragment;
    private GameplayFragment gameplayFragment;
    private SettingFragment settingFragment;
    private PauseFragment pauseFragment;
    private GameOverFragment gameOverFragment;
    private HighScoreFragment highScoreFragment;
    private SharedPreferencesHighScore sharedPreferencesHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        mainMenuFragment = new MainMenuFragment();
        gameplayFragment = new GameplayFragment();
        settingFragment = new SettingFragment();
        pauseFragment = new PauseFragment();
        gameOverFragment = new GameOverFragment();
        highScoreFragment = new HighScoreFragment();
        sharedPreferencesHighScore = new SharedPreferencesHighScore(this);
        fragmentManager = this.getSupportFragmentManager();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        changePage(1);
    }

    @Override
    protected void onPause(){
        super.onPause();
        this.mainMenuFragment.pauseSound();
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.mainMenuFragment.resumeSound();
    }

    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page == 1){
            ft.replace(R.id.fragment_container, this.mainMenuFragment).addToBackStack(null);
        } else if(page == 2){
            ft.replace(R.id.fragment_container, this.gameplayFragment).addToBackStack(null);
        }
        else if(page == 3){
            ft.replace(R.id.fragment_container,this.settingFragment).addToBackStack(null);
        }
        else if(page == 4){
            ft.replace(R.id.fragment_container,this.pauseFragment).addToBackStack(null);
        }
        else if(page == 5){
            ft.replace(R.id.fragment_container,this.gameOverFragment).addToBackStack(null);
        }
        else if(page == 6){
            ft.replace(R.id.fragment_container,this.highScoreFragment).addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    public void changeVolume(int vol){
        this.mainMenuFragment.changeVolume(vol);
        this.pauseFragment.changeVolume(vol);
    }

    @Override
    public void changeBackground(int id){
        this.mainMenuFragment.changeBackground(id);
        this.pauseFragment.changeBackground(id);
        this.gameOverFragment.changeBackground(id);
        this.highScoreFragment.changeBackground(id);
    }

    @Override
    public void setDefault(){
        this.mainMenuFragment.setDefault();
    }

    @Override
    public void setLevel(int level){
        this.gameplayFragment.setLevel(level);
    }

    @Override
    public void setScore(int score, int level){
        if(level == 0){
            if(score>sharedPreferencesHighScore.getEasy()){
                this.sharedPreferencesHighScore.saveEasy(score);
                this.highScoreFragment.setScore(score,level);
                this.changePage(6);
            }
            else{
                this.gameOverFragment.setScore(score,level);
                this.changePage(5);
            }
        }
        else if(level == 1){
            if(score>sharedPreferencesHighScore.getMed()){
                this.sharedPreferencesHighScore.saveMed(score);
                this.highScoreFragment.setScore(score,level);
                this.changePage(6);
            }
            else{
                this.gameOverFragment.setScore(score,level);
                this.changePage(5);
            }
        }
        else if(level == 2){
            if(score>sharedPreferencesHighScore.getHard()){
                this.sharedPreferencesHighScore.saveHard(score);
                this.highScoreFragment.setScore(score,level);
                this.changePage(6);
            }
            else{
                this.gameOverFragment.setScore(score,level);
                this.changePage(5);
            }
        }
    }
}