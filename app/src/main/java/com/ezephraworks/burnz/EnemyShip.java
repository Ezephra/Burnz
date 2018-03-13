package com.ezephraworks.burnz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;


/**
 * Created by Ezephra on 9/02/2018.
 */

public class EnemyShip {
    private Bitmap bitmap;
    private int x, y;
    private int speed = -1;
    private Rect hitbox;

    private int minX, maxX;
    private int minY, maxY;

    public EnemyShip(Context context, int screenX, int screenY){
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.spacemissiles_004);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(6) + 10;

        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();
        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed){
        x -= playerSpeed;
        x -= speed;

        if (x < minX - bitmap.getWidth()){
            Random generator = new Random();
            speed = generator.nextInt(10)+10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
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

    public Rect getHitbox() {
        return hitbox;
    }

    public void setX(int x){
        this.x = x;
    }
}
