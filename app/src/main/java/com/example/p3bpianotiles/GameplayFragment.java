package com.example.p3bpianotiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.p3bpianotiles.databinding.GameplayFragmentBinding;

import java.util.LinkedList;

public class GameplayFragment extends Fragment implements GameplayPresenterInterface.UI, View.OnClickListener {
    //binding here
    private GameplayFragmentBinding binding;
    private GameplayPresenterInterface.Presenter presenter;
    private GameplayPresenterInterface.UI ui;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private ImageView iv_canvas;
    private int width;
    private int height;
    private int mColorTiles;
    private Paint transparentPaint;
    private ThreadTiles threadTiles;
    private TilesHandler tilesHandler;
    private LinkedList<ThreadTiles> threadList;

    private int score = 0;


    public GameplayFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        binding = GameplayFragmentBinding.inflate(inflater);
        ui = this;


        this.binding.ivCanvas.post(
            new Runnable() {
                @Override
                public void run() {
                    initCanvas();
                    presenter = new GameplayPresenter(ui);
                    presenter.generateTiles(0,0,width,height/4);
                    presenter.generateTiles(width/4,100,width,height/4);
                    presenter.generateTiles(width*2/4,400,width,height/4);
                    presenter.generateTiles(width*3/4,500,width,height/4);
                }
            }
        );


        this.binding.tv.bringToFront();
        this.binding.tv.invalidate();
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


    public void onClick(View v){
        if(v == this.binding.pauseBtn){

        }
    }

    public void addScore(){
        this.score += 1;
        this.binding.tv.setText(Integer.toString(this.score));
    }

}
