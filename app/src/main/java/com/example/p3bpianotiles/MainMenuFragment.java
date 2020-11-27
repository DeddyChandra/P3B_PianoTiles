package com.example.p3bpianotiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.fragment.app.Fragment;


import com.example.p3bpianotiles.databinding.MainMenuFragmentBinding;

public class MainMenuFragment extends Fragment implements View.OnClickListener {
    //binding here
    private MainMenuFragmentBinding binding;
    public MainMenuFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        this.binding = MainMenuFragmentBinding.inflate(inflater);
        this.rotateVinyl();
        return binding.getRoot();
    }

    public void rotateVinyl(){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        this.binding.vinylIv.startAnimation(rotateAnimation);
    }


    @Override
    public void onClick(View v) {

    }
}
