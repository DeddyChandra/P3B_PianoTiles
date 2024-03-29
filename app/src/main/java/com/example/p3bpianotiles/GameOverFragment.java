package com.example.p3bpianotiles;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.GameOverFragmentBinding;

public class GameOverFragment extends Fragment implements View.OnClickListener {
    private GameOverFragmentBinding binding;
    private int backgroundId = 0;
    private FragmentListener listener;
    public int score;
    public int level;
    SharedPreferencesHighScore sharedPreferencesHighScore;

    public GameOverFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance) {
        this.binding = GameOverFragmentBinding.inflate(inflater);
        if(backgroundId != 0) {
            this.binding.backgroundIv.setImageResource(backgroundId);
        }
        this.binding.mainMenuBtn.setOnClickListener(this);
        this.binding.restartBtn.setOnClickListener(this);
        Log.d("setscore", "gamefrag: "+score);
        this.binding.scorePointTv.setText(Integer.toString(score));
        this.setLevel();
        return this.binding.getRoot();
    }

    public void setScore(int score, int level){
        this.score = score;
        this.level = level;
    }

    public void setLevel(){
        if(level == 0){
            this.binding.levelTv.setText(R.string.Easy);
            this.binding.levelTv.setTextColor(ContextCompat.getColor(getContext(), R.color.green));

        }
        else if(level == 1){
            this.binding.levelTv.setText(R.string.Normal);
            this.binding.levelTv.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));

        }
        else if(level == 2){
            this.binding.levelTv.setText(R.string.Hard);
            this.binding.levelTv.setTextColor(ContextCompat.getColor(getContext(), R.color.red));

        }
    }

    @Override
    public void onClick(View v) {
        if(v == this.binding.mainMenuBtn){
            this.listener.changePage(1);
        }
        else if(v == this.binding.restartBtn){
            this.listener.changePage(2);
            this.listener.setLevel(level);
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
