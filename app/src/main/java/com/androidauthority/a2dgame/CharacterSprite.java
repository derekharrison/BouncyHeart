package com.androidauthority.a2dgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by rushd on 7/5/2017.
 */

public class CharacterSprite {


    private Bitmap image;
    private int x, y;
    private int x_o, y_o;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private int maxSpeed = 500;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public CharacterSprite (Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
        x_o = 100;
        y_o = 100;
    }


    public void draw(Canvas canvas) {
       canvas.drawBitmap(image, x, y, null);


    }
    public void update(float loc_x, float loc_y, int action) {

        x_o = x;
        y_o = y;
        if( loc_x != x && action == 0) {
            x = (int) loc_x;
            xVelocity = x - x_o;
        }
        else {
            x += xVelocity;
            if ((x > screenWidth - image.getWidth()) || (x < 0)) {
                xVelocity = xVelocity * -1;
            }

        }

        if( loc_y != y && action == 0) {
            y = (int) loc_y;
            yVelocity = y - y_o;
        }
        else {
            y += yVelocity;
            if ((y > screenHeight - image.getHeight()) || (y < 0)) {
                yVelocity = yVelocity * -1;
            }
        }

        if(yVelocity*yVelocity + xVelocity*xVelocity > maxSpeed*maxSpeed) {
            int xVelocityMax = maxSpeed / (int) sqrt(1 + (yVelocity/xVelocity)*(yVelocity/xVelocity));
            int yVelocityMax = (int) abs(yVelocity / xVelocity) * xVelocityMax;
            if(xVelocity < 0) {
                xVelocity = -xVelocityMax;
            }
            else {
                xVelocity = xVelocityMax;
            }
            if(yVelocity < 0) {
                yVelocity = -yVelocityMax;
            }
            else {
                yVelocity = yVelocityMax;
            }
        }

    }


}


