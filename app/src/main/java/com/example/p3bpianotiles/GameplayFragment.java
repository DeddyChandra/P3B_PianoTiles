package com.example.p3bpianotiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.GameplayFragmentBinding;

import java.util.LinkedList;
import java.util.List;

public class GameplayFragment extends Fragment implements GameplayContract.UI, View.OnClickListener,View.OnTouchListener, SensorEventListener {
    //binding here
    private GameplayFragmentBinding binding;
    private GameplayContract.Presenter presenter;
    private GameplayContract.UI ui;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private ImageView iv_canvas;
    private int width;
    private int height;
    private int mColorTiles;
    private GestureDetector mDetector;
    private Paint transparentPaint;
    private int level;
    private int score;
    private FragmentListener listener;
    private boolean lose;

    private Sensor accelerometer, magnetometer;
    private SensorManager sensorManager;
    private float[] accelerometerReading, magnetometerReading;
    public float VALUE_DRIFT = 0.05f;


    public GameplayFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        binding = GameplayFragmentBinding.inflate(inflater);
        ui = this;
        this.score = 0;
        this.lose = false;
        this.mDetector = new GestureDetector(getContext(), new GameplayFragment.MyCustomGestureListener());
        this.binding.ivCanvas.post(
            new Runnable() {
                @Override
                public void run() {
                    initCanvas();
                    presenter = new GameplayPresenter(ui,width,height,getActivity());
                    presenter.setLevel(level);
                    presenter.generateTiles(0,width,height/4,0);
                    presenter.setGameState(0);
//                    presenter.generateTiles(1,width,height/4);
//                    presenter.generateTiles(2,width,height/4);
//                    presenter.generateTiles(3,width,height/4);
//                    presenter.generateTiles(width/4,100,width,height/4);
//                    presenter.generateTiles(width*2/4,400,width,height/4);
//                    presenter.generateTiles(width*3/4,500,width,height/4);
                }
            }
        );
        this.binding.pauseBtn.setOnClickListener(this);
        this.binding.scoreTv.bringToFront();
        this.binding.scoreTv.invalidate();
        this.binding.ivCanvas.setOnTouchListener(this);
        this.binding.scoreTv.setText(Integer.toString(this.score));

        this.sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.magnetometer = this.sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        this.showAllSensor();
        this.accelerometerReading = new float[3];
        this.magnetometerReading = new float[3];

        return binding.getRoot();

    }
    @Override
    public void onResume(){
        super.onResume();
        if(accelerometer != null){
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(magnetometer != null){
            sensorManager.registerListener(this,magnetometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void showAllSensor() {
        List<Sensor> sensorList = this.sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor currentSensor : sensorList){
            Log.d("sensor", currentSensor.getName());
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                this.accelerometerReading = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                this.magnetometerReading = event.values.clone();
                break;
        }
        final float[] rotationMatrix = new float[9];
        sensorManager.getRotationMatrix(rotationMatrix,null, accelerometerReading, magnetometerReading);

        final float[] orientationAngles = new float[3];
        sensorManager.getOrientation(rotationMatrix,orientationAngles);

        float azimuth = orientationAngles[0];
        float pitch = orientationAngles[1];
        float roll = orientationAngles[2];

        if(Math.abs(roll)>0) {
            presenter.checkSensor(roll);
        }


        Log.d("roll",roll+"");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void initCanvas(){
        width=binding.ivCanvas.getWidth();
        height=binding.ivCanvas.getHeight();
        this.bitmap = Bitmap.createBitmap(binding.ivCanvas.getWidth(),binding.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        binding.ivCanvas.setImageBitmap(bitmap);
        this.canvas = new Canvas(this.bitmap);

        this.paint = new Paint();


        this.transparentPaint = new Paint();
        this.transparentPaint.setColor(Color.TRANSPARENT);
        this.transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

    }

    @Override
    public void draw(Tiles tiles) {
        mColorTiles = ResourcesCompat.getColor(getResources(),tiles.getColor(),null);
        paint.setColor(mColorTiles);
        float widthTiles = tiles.getWidth();
        float heightTiles = tiles.getHeight();
        float x = tiles.getX();
        float y = tiles.getY();
        canvas.drawRect(x,y,x+widthTiles,y+heightTiles,paint);
        binding.ivCanvas.invalidate();
    }

    @Override
    public void delete(Tiles tiles) {
        this.transparentPaint = new Paint();
        this.transparentPaint.setColor(Color.TRANSPARENT);
        this.transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        //paint.setColor(Color.WHITE);
        float widthTiles = tiles.getWidth();
        float heightTiles = tiles.getHeight();
        float x = tiles.getX();
        float y = tiles.getY();
        canvas.drawRect(x,y,x+widthTiles,y+heightTiles,transparentPaint);
        binding.ivCanvas.invalidate();
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

    public void onClick(View v){
        if(v == this.binding.pauseBtn){
            this.listener.setLevel(level);
            this.listener.changePage(4);
            Log.d("click", "onClick: ");
        }
    }

    public void addScore(){
        this.score += 1;
        this.binding.scoreTv.setText(Integer.toString(this.score));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return this.mDetector.onTouchEvent(event);
    }


    private class MyCustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            if(presenter.getTouchPoint()==null){
                presenter.setTouchPoint(new PointF(e.getX(),e.getY()));
            }
            else{
                presenter.getTouchPoint().set(e.getX(),e.getY());
            }
            presenter.checkClick(presenter.getTouchPoint());
            return true;
        }

    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }

    public void lose(){
        Log.d("loses", ""+lose);
        if(!lose) {
            this.listener.setScore(score, level);
            this.lose = true;
        }
    }

    public void setLose() {
        this.lose = true;
        this.presenter.setGameState(2);
        this.presenter.setToLoseState();
    }

    public void setPause(boolean pause) {
        this.presenter.setPause(pause);
    }

    public void setUnPauseCount(String s){
        this.binding.count.setText(s);
    }
}
