package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.*;
import android.view.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying = true;

    private Player player;
    private List<Bullet> bullets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    private SurfaceHolder holder;
    private Paint paint;

    private boolean gameOver = false;


    public GameView(Context context) {
        super(context);
        player = new Player(context);
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(context, 100 + i * 200, 100));
        }

        holder = getHolder();
        paint = new Paint();

        thread = new Thread(this);
        thread.start();



        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getX() < getWidth() / 2) player.moveLeft();
                else player.moveRight();

                bullets.add(new Bullet(context, player.x + player.width / 2, player.y));
            }
            return true;
        });
   }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }


    private void update() {
        player.update();
        for (Bullet bullet : bullets) bullet.update();
        for (Enemy enemy : enemies) enemy.update();

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (bullet.isOffScreen()) {
                bulletIterator.remove();
                continue;
            }

            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (Rect.intersects(bullet.getRect(), enemy.getRect())) {
                    bulletIterator.remove();
                    enemyIterator.remove();
                    break;
                }
            }
        }
        for (Enemy enemy : enemies) {
            enemy.update();
            if (enemy.y + 100 >= getHeight()) {  // Jeśli dolna część przeciwnika dotknie dołu
                gameOver = true;
                break;
            }
        }

    }

    private void draw() {
        if (holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            player.draw(canvas, paint);
            for (Bullet bullet : bullets) bullet.draw(canvas, paint);
            for (Enemy enemy : enemies) enemy.draw(canvas, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(40f);
            canvas.drawText("Enemies left: " + enemies.size(), 50f, 50f, paint);

            holder.unlockCanvasAndPost(canvas);
        }

    }

    private void control() {
        try {
            Thread.sleep(17); // ~60fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
