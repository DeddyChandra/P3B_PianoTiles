package com.example.p3bpianotiles;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
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
    private MediaPlayer mediaPlayer;
    private FragmentListener fragmentListener;
    private SoundPool soundPool;
    private AudioAttributes audioAttr;
    private int sound1;
    private boolean loaded = false;
    public MainMenuFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        this.binding = MainMenuFragmentBinding.inflate(inflater);
        this.rotateVinyl();
        this.presenter = new MainMenuPresenter();
        this.setLevel(this.presenter.getLevel());

        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.LOLLIPOP) {
            this.audioAttr = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
            this.soundPool = new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttr).build();
        }
        else{
            soundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
        }
        sound1 = soundPool.load(getActivity(),R.raw.jinggle_bell_piano,1);


        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                loaded = true;
            }
        });
//        this.mediaPlayer = MediaPlayer.create(getActivity(),R.raw.jinggle_bell_piano);
//        this.mediaPlayer.start();
//        this.mediaPlayer.setLooping(true);


        this.binding.easy.setOnClickListener(this);
        this.binding.normal.setOnClickListener(this);
        this.binding.hard.setOnClickListener(this);
        this.binding.volumeFab.setOnClickListener(this);
        this.binding.startBtn.setOnClickListener(this);
        this.binding.settingFab.setOnClickListener(this);
        return binding.getRoot();
    }

    public void rotateVinyl(){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        this.binding.vinylIv.startAnimation(rotateAnimation);
    }

    public void pauseSound(){
//        this.mediaPlayer.pause();
        this.soundPool.pause(sound1);
    }

    public void resumeSound(){
//        this.mediaPlayer.start();
        this.soundPool.resume(sound1);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
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
        else if(v == this.binding.startBtn){
            this.fragmentListener.changePage(2);
        }
        else if(v == this.binding.settingFab){
            soundPool.play(sound1,1,1,0, 0, 1);
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
//            this.mediaPlayer.setVolume(0,0);
            this.soundPool.setVolume(sound1,0,0);
        }
        else{
            this.binding.volumeFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.music_on));
//            this.mediaPlayer.setVolume(1,1);
            this.soundPool.setVolume(sound1,1,1);
        }
    }


}
