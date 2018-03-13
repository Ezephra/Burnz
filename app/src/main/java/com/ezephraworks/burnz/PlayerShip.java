package com.ezephraworks.burnz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Ezephra on 2/02/2018.
 */

public class PlayerShip {
    private Bitmap bitmap;
    private int x,y;
    private int minY, maxY;
    private int speed = 0;
    private boolean boosting;
    private Rect hitbox;

    private final int GRAVITY = -12;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    public PlayerShip(Context context, int screenX, int screenY){
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.space_ship);
        this.x = 50;
        this.y = 50;
        this.speed = 1;
        this.minY = 0;
        this.maxY = screenY - getBitmap().getHeight();
        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(){
        //BOOSTING???
        if (boosting){
            speed += 2;
        } else {
            speed -= 5;
        }
        //Constrain speed
        if (speed > MAX_SPEED){
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED){
            speed = MIN_SPEED;
        }
        //Ship MOVEMENT
        y -= speed + GRAVITY;
        //NOT OFFSCREEN
        if (y < minY){
            y = minY;
        }
        if (y > maxY){
            y = maxY;
        }

        hitbox.left = x;
        hitbox.top = y;
        hitbox.right = x + bitmap.getWidth();
        hitbox.bottom = y + bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setBoosting(){
        this.boosting = true;
    }

    public void stopBoosting(){
        boosting = false;
    }

    public Rect getHitbox() {
        return hitbox;
    }
}
