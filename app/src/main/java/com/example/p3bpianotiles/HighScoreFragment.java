package com.example.p3bpianotiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.HighscoreFragmentBinding;

public class HighScoreFragment extends Fragment implements View.OnClickListener, HighScoreContract.ui {
    HighscoreFragmentBinding binding;
    private FragmentListener listener;

    public HighScoreFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = HighscoreFragmentBinding.inflate(inflater);

        return this.binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v == this.binding.nextBtn){
            this.listener.changePage(5);
        }
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
