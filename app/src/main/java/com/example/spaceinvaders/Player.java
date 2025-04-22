package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.*;

public class Player {
    public float x = 500, y = 2500;
    public int width = 200, height = 200;
    private float dx = 0;

    private Bitmap bitmap;

    public Player(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
    }

    public void update() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 1000) x = 1000;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, null, new Rect((int)x, (int)y, (int)x + width, (int)y + height), paint);
    }

    public void moveLeft() {
        dx = -15;
    }

    public void moveRight() {
        dx = 15;
    }
}
