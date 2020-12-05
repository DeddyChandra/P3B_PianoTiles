package com.example.p3bpianotiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.PauseFragmentBinding;

import java.net.PasswordAuthentication;

public class PauseFragment extends Fragment implements View.OnClickListener, PauseContract.ui, SeekBar.OnSeekBarChangeListener{
    private PauseFragmentBinding binding;
    private FragmentListener listener;
    private float volume;
    private int backgroundId = 0;
    int level = 0;

    public PauseFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = PauseFragmentBinding.inflate(inflater);
        this.binding.resumeBtn.setOnClickListener(this);
        this.binding.restartBtn.setOnClickListener(this);
        this.binding.quitBtn.setOnClickListener(this);
        this.binding.seekBar.setOnSeekBarChangeListener(this);
        if(backgroundId != 0) {
            this.binding.backgroundIv.setImageResource(backgroundId);
        }
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v == this.binding.resumeBtn){
            this.listener.setLevel(level);
        }
        else if(v == this.binding.restartBtn){
            this.listener.setLevel(level);
            this.listener.setGamePlayToLoseState();
//            this.listener.changePage(2);
        }
        else if(v == this.binding.quitBtn){
            this.listener.setGamePlayToLoseState();
//            this.listener.changePage(1);
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.listener.changeVolume(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }

    public void changeVolume(int volume){
//        this.volume =(float)(1-Math.log(100-vol)/Math.log(100));
//        if(Double.isInfinite(volume)){
//            volume = 1;
//        }
//        this.mediaPlayer.setVolume(volume, volume);
    }

    public void changeBackground(int id){
        this.backgroundId = id;
//        this.binding.backgroundIv.setImageResource(id);
    }
}
