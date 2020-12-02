package com.example.p3bpianotiles;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.p3bpianotiles.databinding.SettingFragmentBinding;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SettingFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private SettingFragmentBinding binding;
    private FragmentListener fragmentListener;
    private SettingPresenter presenter;

    public SettingFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = SettingFragmentBinding.inflate(inflater);
        this.binding.doneBtn.setOnClickListener(this);
        this.presenter = new SettingPresenter();
        this.binding.seekBar.setOnSeekBarChangeListener(this);

        return this.binding.getRoot();
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
    public void onClick(View v){
        if(v == this.binding.doneBtn){
            Toast.makeText(this.getContext(),"Setting Changed",Toast.LENGTH_SHORT).show();
            this.fragmentListener.changePage(1);
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.fragmentListener.changeVolume(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
//        Toast.makeText(getContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        Toast.makeText(getContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
    }



}
