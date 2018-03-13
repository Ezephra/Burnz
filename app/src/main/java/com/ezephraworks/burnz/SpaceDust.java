package com.ezephraworks.burnz;

import java.util.Random;

/**
 * Created by Ezephra on 13/02/2018.
 */

public class SpaceDust {
    private int x, y;
    private int speed;
    private int minX, maxX;
    private int minY, maxY;

    public SpaceDust(int screenX, int screenY){
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(10);
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        x -= playerSpeed;
        x -= speed;

        if (x < 0){
            x = maxX;
            Random ran = new Random();
            y = ran.nextInt(maxY);
            speed = ran.nextInt(10);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
