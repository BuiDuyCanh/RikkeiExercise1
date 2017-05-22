package com.example.canhbd.draw1exercise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RandomShapeView extends View {
    private Integer[] mBackgrounds =
            { Color.CYAN, Color.GRAY, Color.LTGRAY,
                    Color.MAGENTA, Color.YELLOW, Color.WHITE };
    private Paint[] mForegrounds =
            { makePaint(Color.BLACK), makePaint(Color.BLUE),
                    makePaint(Color.GREEN), makePaint(Color.RED) };

    private Bitmap[] mPics =
        { makeBitmap(R.drawable.emo_im_angel), 
          makeBitmap(R.drawable.emo_im_cool),
          makeBitmap(R.drawable.emo_im_crying),
          makeBitmap(R.drawable.emo_im_happy),
          makeBitmap(R.drawable.emo_im_yelling) };

    
    // This constructor called if View made programmatically and added with setContentView
    
    public RandomShapeView(Context context) {
        super(context);
    }
    
    // This constructor called if made from XML file
    
    public RandomShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Avoid allocating objects in onDraw, especially bitmaps.
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(RandomUtils.randomElement(mBackgrounds));
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int avgShapeWidth = viewWidth/5;
        for(int i=0; i<20; i++) {

            drawRandomBitmap(canvas, viewWidth, viewHeight);

        }
    }
    private Paint makePaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        return(p);
    }
    
    private Bitmap makeBitmap(int bitmapId) {
        return(BitmapFactory.decodeResource(getResources(), bitmapId));
    }

    private void drawRandomBitmap(Canvas canvas, int viewWidth, 
                                  int viewHeight) {
        float left = RandomUtils.randomFloat(viewWidth);
        float top = RandomUtils.randomFloat(viewHeight);
        Bitmap pic = RandomUtils.randomElement(mPics);
        // Last arg is the Paint: you can use null for opaque images
        canvas.drawBitmap(pic, left, top, null);
    }


}