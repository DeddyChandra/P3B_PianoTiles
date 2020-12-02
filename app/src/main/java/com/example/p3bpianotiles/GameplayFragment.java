package com.example.p3bpianotiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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

public class GameplayFragment extends Fragment implements GameplayPresenterInterface.UI {
    //binding here
    private GameplayFragmentBinding binding;
    private GameplayPresenterInterface.Presenter presenter;
    private GameplayPresenterInterface.UI ui;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private GestureDetector detector;
    private ImageView iv_canvas;
    private int width;
    private int height;
    private int mColorBackground;
    private int mColorTiles;
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
                    presenter = new GameplayPresenter();
                    presenter.generateTiles(ui);
                }
            }
        );


        return binding.getRoot();

    }
    public void initCanvas(){

        width=binding.ivCanvas.getWidth();
        height=binding.ivCanvas.getHeight();
        this.bitmap = Bitmap.createBitmap(binding.ivCanvas.getWidth(),binding.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        binding.ivCanvas.setImageBitmap(bitmap);
        this.canvas = new Canvas(this.bitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(),R.color.white,null);
        canvas.drawColor(mColorBackground);
        paint = new Paint();
        mColorTiles = ResourcesCompat.getColor(getResources(),R.color.black,null);

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
        paint.setColor(mColorBackground);
        float widthTiles = tiles.getWidth();
        float heightTiles = tiles.getHeight();
        float x = tiles.getX();
        float y = tiles.getY();
        canvas.drawRect(x,y,x+widthTiles,y+heightTiles,paint);
        binding.ivCanvas.invalidate();
    }
}
