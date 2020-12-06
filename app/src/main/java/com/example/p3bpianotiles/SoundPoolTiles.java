package com.example.p3bpianotiles;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

public class SoundPoolTiles{
    private GameplayContract.Presenter presenter;
    private SoundPool soundPool;
    private int do1, re2, mi3, fa4, so5, la6, si7, do1_octave, setengah;
    private AudioManager audioManager;
    float volume,cur,max;

    public SoundPoolTiles(GameplayContract.Presenter presenter){
        this.presenter = presenter;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(8)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        }
        this.audioManager = (AudioManager) presenter.getActivity().getSystemService(Context.AUDIO_SERVICE);
        this.cur = this.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        this.max = this.audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        this.volume = this.cur/this.max;
        this.do1 = soundPool.load(presenter.getActivity(),R.raw.do_1,1);
        this.re2 = soundPool.load(presenter.getActivity(),R.raw.re_2,1);
        this.mi3 = soundPool.load(presenter.getActivity(),R.raw.mi_3,1);
        this.fa4 = soundPool.load(presenter.getActivity(),R.raw.fa_4,1);
        this.so5 = soundPool.load(presenter.getActivity(),R.raw.so_5,1);
        this.la6 = soundPool.load(presenter.getActivity(),R.raw.la_6,1);
        this.si7 = soundPool.load(presenter.getActivity(),R.raw.si_7,1);
        this.do1_octave = soundPool.load(presenter.getActivity(),R.raw.do_1_octave,1);
        this.setengah = soundPool.load(presenter.getActivity(),R.raw.setengah,1);
    }

    public void setVolume(float volume){
        this.volume = volume;
    }

    public void play(int note){
        if(note == do1){
            note = do1;
        }
        else if(note == re2){
            note = re2;
        }
        else if(note == mi3){
            note = mi3;
        }
        else if(note == fa4){
            note = fa4;
        }
        else if(note == so5){
            note = so5;
        }
        else if(note == la6){
            note = la6;
        }
        else if(note == si7){
            note = si7;
        }
        else if(note == do1_octave){
            note = do1_octave;
        }
        else if(note == setengah){
            note = setengah;
        }
        if(soundPool!=null) {
            soundPool.play(note, volume, volume, 1, 0, 1);
        }
    }

    public void setRelease(){
        this.soundPool.release();
        if(soundPool!=null) {
            this.soundPool = null;
        }
    }
}
