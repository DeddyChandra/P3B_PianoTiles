package com.example.p3bpianotiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.GameOverFragmentBinding;

public class GameOverFragment extends Fragment implements View.OnClickListener, GameOverContract.ui {
    private GameOverFragmentBinding binding;
    private int backgroundId = 0;
    private FragmentListener listener;

    public GameOverFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = GameOverFragmentBinding.inflate(inflater);
        if(backgroundId != 0) {
            this.binding.backgroundIv.setImageResource(backgroundId);
        }
        this.binding.mainMenuBtn.setOnClickListener(this);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v == this.binding.mainMenuBtn){
            this.listener.changePage(1);
        }
    }

    public void changeBackground(int id){
        this.backgroundId = id;
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
}
