package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.*;

public class Bullet {
    private float x, y;
    private int width = 10, height = 30;
    private int speed = 20;

    public Bullet(Context context, float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= speed;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public boolean isOffScreen() {
        return y < 0;
    }

    public Rect getRect() {
        return new Rect((int)x, (int)y, (int)(x + width), (int)(y + height));
    }
}
