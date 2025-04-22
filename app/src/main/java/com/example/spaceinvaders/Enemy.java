package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.*;

public class Enemy {
    public int x, y;
    private int width = 150, height = 150;
    private Bitmap bitmap;

    public Enemy(Context context, int x, int y) {
        this.x = x;
        this.y = y;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
    }

    public void update() {
        y += 2;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, null, new Rect(x, y, x + width, y + height), paint);
    }

    public Rect getRect() {
        return new Rect(x, y, x + width, y + height);
    }
}
