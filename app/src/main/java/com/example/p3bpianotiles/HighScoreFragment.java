package com.example.p3bpianotiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.HighscoreFragmentBinding;

public class HighScoreFragment extends Fragment implements View.OnClickListener, HighScoreContract.ui {
    HighscoreFragmentBinding binding;
    private FragmentListener listener;
    private int backgroundId = 0;
    private int score;
    private int level;
    SharedPreferencesHighScore sharedPreferencesHighScore;
    public HighScoreFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = HighscoreFragmentBinding.inflate(inflater);
        if(backgroundId != 0) {
            this.binding.backgroundIv.setImageResource(backgroundId);
        }

        this.setLevel();

        this.binding.highscoreTv.setText(Integer.toString(score));
        this.binding.nextBtn.setOnClickListener(this);
        return this.binding.getRoot();
    }

    public void changeBackground(int id){
        this.backgroundId = id;
    }
    public void setLevel(){
        if(level == 0){
            this.binding.highscoreTv.setTextColor(ContextCompat.getColor(getContext(), R.color.green));

        }
        else if(level == 1){
            this.binding.highscoreTv.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));

        }
        else if(level == 2){
            this.binding.highscoreTv.setTextColor(ContextCompat.getColor(getContext(), R.color.red));

        }
    }
    public void setScore(int score, int level){
        this.score = score;
        this.level = level;
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
    public void onClick(View v) {
        if(v == this.binding.nextBtn){
            this.listener.changePage(5);
        }
    }
}
