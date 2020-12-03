package com.example.p3bpianotiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.GameOverFragmentBinding;

public class GameOverFragment extends Fragment implements View.OnClickListener, GameOverContract.ui {
    private GameOverFragmentBinding binding;
    public GameOverFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = GameOverFragmentBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View v) {

    }
}
