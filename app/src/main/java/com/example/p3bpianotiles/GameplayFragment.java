package com.example.p3bpianotiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
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

    public GameplayFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstance){
        binding = GameplayFragmentBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void draw(Tiles tiles) {
        int width = iv_canvas.getWidth();
        int height = iv_canvas.getHeight();

        double widthTiles = tiles.getWidth();
        double heightTiles = tiles.getHeight();
        double x = tiles.getX();
        double y = tiles.getY();

        this.bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        this.iv_canvas.setImageBitmap(bitmap);

        this.canvas = new Canvas(this.bitmap);
        int nColorBackground = ResourcesCompat.getColor(getResources(),R.color.white,null);
        canvas.drawColor(nColorBackground);

        paint = new Paint();
        int mColorText = ResourcesCompat.getColor(getResources(),R.color.white,null);
        paint.setColor(mColorText);

        tiles = new Tiles(x,y,width,height);
        this.iv_canvas.invalidate();
    }
}
