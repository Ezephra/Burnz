package com.ezephraworks.burnz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Space;

import java.util.ArrayList;

/**
 * Created by Ezephra on 31/01/2018.
 */

public class BView extends SurfaceView implements Runnable {
    public BView(Context context, int x, int y) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
        player = new PlayerShip(context, x, y);
        enemy1 = new EnemyShip(context, x, y);
        enemy2 = new EnemyShip(context, x, y);
        enemy3 = new EnemyShip(context, x, y);
        player.update();
        int numSpecs = 40;
        for (int i = 0; i < numSpecs; i++) {
            SpaceDust spec = new SpaceDust(x, y);
            dustList.add(spec);
        }
    }

    volatile boolean playing;
    Thread gameThread = null;

    private PlayerShip player;
    private EnemyShip enemy1;
    private EnemyShip enemy2;
    private EnemyShip enemy3;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;
    public ArrayList<SpaceDust> dustList = new ArrayList<SpaceDust>();


    @Override
    public void run() {

        while (playing){
            update();
            draw();
            control();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e){

        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        if (Rect.intersects(player.getHitbox(), enemy1.getHitbox())){
            enemy1.setX(-100);
        }
        if (Rect.intersects(player.getHitbox(), enemy2.getHitbox())){
            enemy2.setX(-100);
        }
        if (Rect.intersects(player.getHitbox(), enemy3.getHitbox())){
            enemy3.setX(-100);
        }
        player.update();

        enemy1.update(player.getSpeed());
        enemy2.update(player.getSpeed());
        enemy3.update(player.getSpeed());

        for (SpaceDust sd : dustList){
            sd.update(player.getSpeed());
        }
    }

    private void draw() {

        if (ourHolder.getSurface().isValid()){
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,0,0,0));
            paint.setColor(Color.argb(255, 255, 255, 255));
            for (SpaceDust sd : dustList){
                canvas.drawPoint(sd.getX(),sd.getY(),paint);
            }
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint
            );

            canvas.drawBitmap(
                    enemy1.getBitmap(),
                    enemy1.getX(),
                    enemy1.getY(),
                    paint
            );

            canvas.drawBitmap(
                    enemy2.getBitmap(),
                    enemy2.getX(),
                    enemy2.getY(),
                    paint
            );

            canvas.drawBitmap(
                    enemy3.getBitmap(),
                    enemy3.getX(),
                    enemy3.getY(),
                    paint
            );
            //Collision Debugging
            /* paint.setColor(Color.argb(255, 255, 255, 255));
            canvas.drawRect(
                    player.getHitbox().left,
                    player.getHitbox().top,
                    player.getHitbox().right,
                    player.getHitbox().bottom,
                    paint);
            canvas.drawRect(
                    enemy1.getHitbox().left,
                    enemy1.getHitbox().top,
                    enemy1.getHitbox().right,
                    enemy1.getHitbox().bottom,
                    paint);
            canvas.drawRect(
                    enemy2.getHitbox().left,
                    enemy2.getHitbox().top,
                    enemy2.getHitbox().right,
                    enemy2.getHitbox().bottom,
                    paint);
            canvas.drawRect(
                    enemy3.getHitbox().left,
                    enemy3.getHitbox().top,
                    enemy3.getHitbox().right,
                    enemy3.getHitbox().bottom,
                    paint);
                    */
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e){

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & event.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }
        return true;
    }
}
