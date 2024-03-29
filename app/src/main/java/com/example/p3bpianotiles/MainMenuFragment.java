package com.example.p3bpianotiles;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.p3bpianotiles.databinding.MainMenuFragmentBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainMenuFragment extends Fragment implements View.OnClickListener, MainMenuContract.UI, View.OnTouchListener, MediaPlayer.OnCompletionListener{
    //binding here
    private MainMenuFragmentBinding binding;
    private MainMenuPresenter presenter;
    private MediaPlayer mediaPlayer;
    private FragmentListener fragmentListener;
    private List<Music> musicList;

    private GestureDetector mDetector;
    private boolean musicStarted = false;
    private int nowPlaying;
    private float volume = 1;
    private int backgroundId = 0;
    public boolean mute = false;
    public MainMenuFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        this.binding = MainMenuFragmentBinding.inflate(inflater);
        this.rotateVinyl();
        this.presenter = new MainMenuPresenter();
        this.setLevel(this.presenter.getLevel());
        this.mDetector = new GestureDetector(getContext(), new MyCustomGestureListener());
        this.musicList = new ArrayList<>(Arrays.asList(MusicFiles.music));
        startRandomMusic();
        this.mediaPlayer.start();
        this.binding.easy.setOnClickListener(this);
        this.binding.normal.setOnClickListener(this);
        this.binding.hard.setOnClickListener(this);
        this.binding.volumeFab.setOnClickListener(this);
        this.binding.startBtn.setOnClickListener(this);
        this.binding.settingFab.setOnClickListener(this);
        this.binding.vinylIv.setOnTouchListener(this);
        if(mute){
            this.presenter.setMute(true);
            this.mute();
        }
        this.mediaPlayer.setOnCompletionListener(this);
        if(backgroundId != 0) {
            this.binding.backgroundIv.setImageResource(backgroundId);
        }
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

    public boolean isMute() {
        return mute;
    }

    public void pauseSound(){
        this.mediaPlayer.pause();
    }

    public void resumeSound(){
        this.mediaPlayer.start();
        this.mediaPlayer.setOnCompletionListener(this);
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
                this.presenter.setMute(false);
                mute = false;
            }
            else{
                this.presenter.setMute(true);
                mute = true;
            }
            this.mediaPlayer.setOnCompletionListener(this);
            mute();
        }
        else if(v == this.binding.startBtn){
            this.mediaPlayer.pause();
            Toast.makeText(this.getContext(),"Click Black, Roll left Yellow, Roll right green",Toast.LENGTH_SHORT).show();
            this.fragmentListener.changePage(2);
            this.fragmentListener.setLevel(this.presenter.getLevel());
        }
        else if(v == this.binding.settingFab){
            this.fragmentListener.changePage(3);
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
            this.presenter.setLevel(0);
        }
        else if(level == 1){
            this.binding.easy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.normal.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_yellow_button));
            this.binding.hard.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));

            this.binding.easy.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.normal.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
            this.binding.hard.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.presenter.setLevel(1);
        }
        else{
            this.binding.easy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.normal.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_gray_button));
            this.binding.hard.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.round_red_button));

            this.binding.easy.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.normal.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            this.binding.hard.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            this.presenter.setLevel(2);
        }
    }

    public void mute(){
        if(this.presenter.isMute()){
            this.binding.volumeFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.music_off));
            this.mediaPlayer.setVolume(0,0);
        }
        else{
            this.binding.volumeFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.music_on));
            this.mediaPlayer.setVolume(volume,volume);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.mediaPlayer.setOnCompletionListener(this);
        return this.mDetector.onTouchEvent(event);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(nowPlaying+1 > musicList.size()-1){
            nowPlaying = 0;
        }
        else{
            nowPlaying++;
        }
        mediaPlayer = MediaPlayer.create(getActivity(),musicList.get(nowPlaying).getId());
        binding.songNameTv.setText(musicList.get(nowPlaying).getName());
        if(presenter.isMute()){
            this.mediaPlayer.setVolume(0,0);
        }
        else {
            mediaPlayer.setVolume(volume, volume);
        }
        this.mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.start();
    }

    private class MyCustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            mediaPlayer.stop();
            if(nowPlaying+1 > musicList.size()-1){
                nowPlaying = 0;
            }
            else{
                nowPlaying++;
            }
            mediaPlayer = MediaPlayer.create(getActivity(),musicList.get(nowPlaying).getId());
            binding.songNameTv.setText(musicList.get(nowPlaying).getName());
            mediaPlayer.start();
            if(presenter.isMute()) {
                mediaPlayer.setVolume(0, 0);
            }
            else{
                mediaPlayer.setVolume(volume,volume);
            }
            return true;
        }
    }

    public void startRandomMusic(){
        if(musicStarted){
            binding.songNameTv.setText(musicList.get(nowPlaying).getName());
        }
        else {
            int max = musicList.size();
            Random random = new Random();
            this.nowPlaying = random.nextInt(max);
            this.mediaPlayer = MediaPlayer.create(getActivity(), musicList.get(nowPlaying).getId());
            this.binding.songNameTv.setText(musicList.get(nowPlaying).getName());
            this.mediaPlayer.start();
            if(presenter.isMute()){
                this.mediaPlayer.setVolume(0,0);
            }
            else {
                this.mediaPlayer.setVolume(volume, volume);
            }
            this.musicStarted = true;
        }
        this.mediaPlayer.setOnCompletionListener(this);
    }

    public void changeVolume(int vol){
        this.volume =(float)(1-Math.log(100-vol)/Math.log(100));
        if(Double.isInfinite(volume)){
            volume = 1;
        }
        this.mediaPlayer.setVolume(volume, volume);
    }

    public void changeBackground(int id){
        this.backgroundId = id;
        this.binding.backgroundIv.setImageResource(id);
    }

    public void setDefault(){
        this.presenter.setMute(false);
        this.mute = false;
        this.mute();
        this.mediaPlayer.setVolume(100,100);
    }

    public void play(){
        this.mute();
        this.mediaPlayer.start();
    }
}
