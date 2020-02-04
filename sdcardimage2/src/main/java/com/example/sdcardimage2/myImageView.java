package com.example.sdcardimage2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myImageView extends View {
    String imagePath=null;
    float s;
    public myImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint= new Paint();
        if (imagePath != null) {
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            int x=(this.getWidth()-bm.getWidth())/2;//bmㅇㅔ 그림이 들어와있음
            int y=(this.getHeight()-bm.getHeight())/2;
            ColorMatrix cm=new ColorMatrix();
            if(s==1){
                cm.setSaturation(1);
            }else{
                cm.setSaturation(0);
            }
            canvas.drawBitmap(bm, x, y, null);//이미지가 도화지(캔버스) 가운데에있음
            bm.recycle(); //비트맵자료를 해제시킨다

        }
    }
}


