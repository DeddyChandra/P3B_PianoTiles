package com.example.p3bpianotiles;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.p3bpianotiles.databinding.SettingFragmentBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SettingFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, SettingContract.ui{
    private SettingFragmentBinding binding;
    private FragmentListener listener;
    private SettingPresenter presenter;
    private List<Background> backgroundList;
    private int backgroundId = 0;

    public SettingFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = SettingFragmentBinding.inflate(inflater);
        this.binding.doneBtn.setOnClickListener(this);
        this.backgroundList = new ArrayList<>(Arrays.asList(BackgroundFiles.backgrounds));
        this.presenter = new SettingPresenter(this,backgroundList);
        this.presenter.setBackgroundIndex(backgroundId);
        this.binding.seekBar.setOnSeekBarChangeListener(this);
        this.binding.changeBgBtn.setOnClickListener(this);
        this.binding.resetBtn.setOnClickListener(this);
        this.presenter.changeBackground();

        return this.binding.getRoot();
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

    @Override
    public void onClick(View v){
        if(v == this.binding.doneBtn){
            Toast.makeText(this.getContext(),"Setting Changed",Toast.LENGTH_SHORT).show();
            this.listener.changePage(1);
        }
        else if(v == this.binding.changeBgBtn){
            this.backgroundId++;
            this.presenter.setBackgroundIndex(backgroundId);
            this.presenter.changeBackground();
            Toast.makeText(this.getContext(),"Background Changed",Toast.LENGTH_SHORT).show();
        }
        else if(v == this.binding.resetBtn){
            setDefault();
            Toast.makeText(this.getContext(),"Setting Changed to Default",Toast.LENGTH_SHORT).show();
            this.listener.changePage(1);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.listener.changeVolume(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
//        Toast.makeText(getContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        Toast.makeText(getContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeBackground(int id){
        this.binding.backgroundIv.setImageResource(id);
        this.listener.changeBackground(id);
    }

    public void setDefault(){
        this.changeBackground(R.drawable.background);
        this.listener.setDefault();
        this.listener.changeBackground(R.drawable.background);
        this.binding.seekBar.setProgress(100);
    }

    public void setBackgroundIndex(int i){
        this.backgroundId = 0;
    }
}
