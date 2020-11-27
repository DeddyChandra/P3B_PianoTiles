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
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private GestureDetector detector;
    private ImageView iv_canvas;
    private int width;
    private int height;
    public GameplayFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        binding = GameplayFragmentBinding.inflate(inflater);
        this.binding.ivCanvas.post(
                new Runnable() {
                    @Override
                    public void run() {
                        initCanvas();
                        draw(new Tiles(0,0,width/4,height/4));
                        draw(new Tiles(width/4,height/4,width/4,height/4));
                        draw(new Tiles(3*width/4,3*height/4,width/4,height/4));
                        draw(new Tiles(2*width/4,2*height/4,width/4,height/4));
                    }
                }
        );




        return binding.getRoot();

    }
    public void initCanvas(){
        this.width=binding.ivCanvas.getWidth();
        this.height=binding.ivCanvas.getHeight();
        this.bitmap = Bitmap.createBitmap(binding.ivCanvas.getWidth(),binding.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        binding.ivCanvas.setImageBitmap(bitmap);
        this.canvas = new Canvas(this.bitmap);
        int nColorBackground = ResourcesCompat.getColor(getResources(),R.color.white,null);
        canvas.drawColor(nColorBackground);
        paint = new Paint();
        int mColorText = ResourcesCompat.getColor(getResources(),R.color.black,null);
        paint.setColor(mColorText);
    }


    @Override
    public void draw(Tiles tiles) {
        float widthTiles = tiles.getWidth();
        float heightTiles = tiles.getHeight();
        float x = tiles.getX();
        float y = tiles.getY();
        canvas.drawRect(x,y,x+widthTiles,y+heightTiles,paint);
        binding.ivCanvas.invalidate();
    }
}
