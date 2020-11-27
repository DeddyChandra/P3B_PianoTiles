package com.example.p3bpianotiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.p3bpianotiles.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    MainMenuFragment mainMenuFragment;
    GameplayFragment gameplayFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        mainMenuFragment=new MainMenuFragment();
        gameplayFragment=new GameplayFragment();
    }
    //testMike
    @Override
    public void changePage(int page){
        if(page==0){

        }


    }
}