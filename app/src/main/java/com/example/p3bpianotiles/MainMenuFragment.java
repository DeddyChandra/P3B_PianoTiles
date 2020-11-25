package com.example.p3bpianotiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.example.p3bpianotiles.databinding.MainMenuFragmentBinding;

public class MainMenuFragment extends Fragment {
    //binding here
    private MainMenuFragmentBinding binding;
    public MainMenuFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        binding = MainMenuFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }
}
