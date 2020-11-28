package com.example.p3bpianotiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.p3bpianotiles.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener{
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    MainMenuFragment mainMenuFragment;
    GameplayFragment gameplayFragment;
    private List<Music> musicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        mainMenuFragment=new MainMenuFragment();
        gameplayFragment=new GameplayFragment();
        fragmentManager = this.getSupportFragmentManager();
        this.musicList = new ArrayList<>(Arrays.asList(MusicFiles.music));
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

    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page == 1){
            ft.replace(R.id.fragment_container, this.mainMenuFragment).addToBackStack(null);
        } else if(page == 2){
            ft.replace(R.id.fragment_container, this.gameplayFragment).addToBackStack(null);
        }
        ft.commit();
    }
}