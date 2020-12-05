package com.example.p3bpianotiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

public class GameplayFragment extends Fragment implements GameplayContract.UI, View.OnClickListener,View.OnTouchListener {
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
    private ThreadTiles threadTiles;
    private TilesHandler tilesHandler;
    private LinkedList<ThreadTiles> threadList;
    private int level;
    private int score;
    private FragmentListener listener;
    private boolean lose;


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
        return binding.getRoot();

    }

    public void initCanvas(){
        width=binding.ivCanvas.getWidth();
        height=binding.ivCanvas.getHeight();
        this.bitmap = Bitmap.createBitmap(binding.ivCanvas.getWidth(),binding.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        binding.ivCanvas.setImageBitmap(bitmap);
        this.canvas = new Canvas(this.bitmap);

        this.paint = new Paint();
        mColorTiles = ResourcesCompat.getColor(getResources(),R.color.black,null);

        this.transparentPaint = new Paint();
        this.transparentPaint.setColor(Color.TRANSPARENT);
        this.transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

    }

    @Override
    public void draw(Tiles tiles) {
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

    boolean pause = true;

    public void onClick(View v){
        if(v == this.binding.pauseBtn){
            this.listener.changePage(4);
//            if(pause == true){
//                this.listener.setPause(true);
//                this.pause = false;
//            }
//            else{
//                this.listener.setPause(false);
//                this.pause = true;
//            }
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

    public void setPause(boolean pause){
        this.presenter.setPause(pause);
    }
}
