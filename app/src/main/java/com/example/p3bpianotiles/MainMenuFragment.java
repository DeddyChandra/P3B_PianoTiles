package com.example.p3bpianotiles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.p3bpianotiles.databinding.MainMenuFragmentBinding;

public class MainMenuFragment extends Fragment implements View.OnClickListener, MainMenuContract.UI{
    //binding here
    private MainMenuFragmentBinding binding;
    private MainMenuPresenter presenter;
    public MainMenuFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        this.binding = MainMenuFragmentBinding.inflate(inflater);
        this.rotateVinyl();
        this.presenter = new MainMenuPresenter();
        this.setLevel(this.presenter.getLevel());
        this.binding.easy.setOnClickListener(this);
        this.binding.normal.setOnClickListener(this);
        this.binding.hard.setOnClickListener(this);
        this.binding.volumeFab.setOnClickListener(this);
        return binding.getRoot();
    }

    public void rotateVinyl(){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        this.binding.vinylIv.startAnimation(rotateAnimation);
    }


    @Override
    public void onClick(View v) {
        if(v == this.binding.easy){
            setLevel(0);
        }
        else if(v == this.binding.normal){
            setLevel(1);
        }
        else if(v == this.binding.hard){
            setLevel(2);
        }
        else if(v == this.binding.volumeFab){
            if(this.presenter.isMute()){
                Log.d("volume", "muted");
                this.presenter.setMute(false);
            }
            else{
                Log.d("volume", "unmuted");
                this.presenter.setMute(true);
            }
            mute();
        }
    }

    public void setLevel(int level){
        if(level == 0){
            this.binding.easy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_green_button));
            this.binding.normal.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.hard.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));

            this.binding.easy.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
            this.binding.normal.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.hard.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
        }
        else if(level == 1){
            this.binding.easy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.normal.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_yellow_button));
            this.binding.hard.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));

            this.binding.easy.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.normal.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
            this.binding.hard.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
        }
        else{
            this.binding.easy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.normal.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.hard.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_red_button));

            this.binding.easy.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.normal.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.hard.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }
    }

    public void mute(){
        if(this.presenter.isMute()){
            this.binding.volumeFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.music_off));
        }
        else{
            this.binding.volumeFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.music_on));
        }
    }
}
