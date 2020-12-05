package com.example.p3bpianotiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

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
    private Fragment fcurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.gameplayFragment = new GameplayFragment();
        this.settingFragment = new SettingFragment();
        this.pauseFragment = new PauseFragment();
        this.gameOverFragment = new GameOverFragment();
        this.highScoreFragment = new HighScoreFragment();
        this.sharedPreferencesHighScore = new SharedPreferencesHighScore(this);
        this.fragmentManager = this.getSupportFragmentManager();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                fcurrent = fragmentManager.findFragmentById(R.id.fragment_container);
            }
        });
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
        if(fcurrent == mainMenuFragment || fcurrent == settingFragment) {
            this.mainMenuFragment.resumeSound();
        }
    }

    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page == 1){
            this.mainMenuFragment = new MainMenuFragment();
            if(fcurrent!=null) {
                ft.hide(fcurrent);
            }
            if(this.mainMenuFragment.isAdded()){
                ft.show(this.mainMenuFragment);
                fcurrent=this.mainMenuFragment;
            }
            else{
                ft.add(R.id.fragment_container,this.mainMenuFragment);
                fcurrent=this.mainMenuFragment;
            }
        } else if(page == 2){
            this.gameplayFragment = new GameplayFragment();
            if(fcurrent!=null) {
                ft.hide(fcurrent);
            }
            if(this.gameplayFragment.isAdded()){
                ft.show(this.gameplayFragment);
                fcurrent=this.gameplayFragment;
            }
            else{
                ft.add(R.id.fragment_container,this.gameplayFragment);
                fcurrent=this.gameplayFragment;
            }
        }
        else if(page == 3){
            if(fcurrent!=null) {
                ft.hide(fcurrent);
            }
            if(this.settingFragment.isAdded()){
                ft.show(this.settingFragment);
                fcurrent=this.settingFragment;
            }
            else{
                ft.add(R.id.fragment_container,this.settingFragment);
                fcurrent=this.settingFragment;
            }
        }
        else if(page == 4){
            this.pauseFragment = new PauseFragment();
            this.pauseFragment.setLevel(this.gameplayFragment.getLevel());
            if(fcurrent!=null) {
                ft.hide(fcurrent);
            }
            if(this.pauseFragment.isAdded()){
                ft.show(this.pauseFragment);
                fcurrent=this.pauseFragment;
            }
            else{
                ft.add(R.id.fragment_container,this.pauseFragment);
                fcurrent=this.pauseFragment;
            }
        }
        else if(page == 5){
            if(fcurrent!=null) {
                ft.hide(fcurrent);
            }
            if(this.gameOverFragment.isAdded()){
                ft.show(this.gameOverFragment);
                fcurrent=this.gameOverFragment;
            }
            else{
                ft.add(R.id.fragment_container,this.gameOverFragment);
                fcurrent=this.gameOverFragment;
            }
        }
        else if(page == 6){
            if(fcurrent!=null) {
                ft.hide(fcurrent);
            }
            if(this.highScoreFragment.isAdded()){
                ft.show(this.highScoreFragment);
                fcurrent=this.highScoreFragment;
            }
            else{
                ft.add(R.id.fragment_container,this.highScoreFragment);
                fcurrent=this.highScoreFragment;
            }
        }
        else if(page == 7){
            ft.hide(this.fcurrent);
            Log.d("page", "changePage: resume");
            ft.show(this.gameplayFragment);
            fcurrent=this.gameplayFragment;
            this.gameplayFragment.setPause(false);
        }
        ft.commit();
    }

    @Override
    public void changeVolume(int vol){
        this.mainMenuFragment.changeVolume(vol);
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
        this.pauseFragment.setLevel(level);
        Log.d("levell", "setLevel: "+level);
    }

    @Override
    public void setGamePlayToLoseState(){
        this.gameplayFragment.setLose();
    }

    @Override
    public void setScore(int score, int level){
        this.gameOverFragment = new GameOverFragment();
        this.highScoreFragment = new HighScoreFragment();
        if(level == 0){
            this.gameOverFragment.setScore(score,level);
            if(score>sharedPreferencesHighScore.getEasy()){
                this.sharedPreferencesHighScore.saveEasy(score);
                this.highScoreFragment.setScore(score,level);
                this.changePage(6);
            }
            else{
                this.changePage(5);
            }
        }
        else if(level == 1){
            this.gameOverFragment.setScore(score,level);
            if(score>sharedPreferencesHighScore.getMed()){
                this.sharedPreferencesHighScore.saveMed(score);
                this.highScoreFragment.setScore(score,level);
                this.changePage(6);
            }
            else{
                this.changePage(5);
            }
        }
        else if(level == 2){
            this.gameOverFragment.setScore(score,level);
            if(score>sharedPreferencesHighScore.getHard()){
                this.sharedPreferencesHighScore.saveHard(score);
                this.highScoreFragment.setScore(score,level);
                this.changePage(6);
            }
            else{
                this.changePage(5);
            }
        }
    }

    public void setPause(boolean pause){
        this.gameplayFragment.setPause(pause);
    }

    public void resume(){
        Log.d("page", "changePage: resume");
        this.changePage(7);
    }

}