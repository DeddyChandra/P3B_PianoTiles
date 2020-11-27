package com.example.p3bpianotiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.GameplayFragmentBinding;

public class GameplayFragment extends Fragment implements GameplayPresenterInterface.UI {
    //binding here
    private GameplayFragmentBinding binding;


    public GameplayFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        binding = GameplayFragmentBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void draw() {

    }
}
