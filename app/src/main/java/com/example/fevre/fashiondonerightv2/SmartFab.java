package com.example.fevre.fashiondonerightv2;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.animation.Animation;

/**
 * Created by Fevre on 01/04/17.
 */

public class SmartFab extends FloatingActionButton {
    private int xStdPos;
    private int yStdPos;
    private float xMovPos;
    private float yMovPos;
    private Animation animationAlpha;
    private Animation animationBeta;


    public SmartFab(Context context) {
        super(context);
    }

    public SmartFab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SmartFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public SmartFab(Context context, int xStdPos, int yStdPos,int xMovPos,int yMovPos) {
        super(context);
        setPos(xStdPos,yStdPos,xMovPos,yMovPos);
    }

    public SmartFab(Context context, AttributeSet attrs, int xStdPos, int yStdPos,int xMovPos,int yMovPos) {
        super(context, attrs);
        setPos(xStdPos,yStdPos,xMovPos,yMovPos);
    }

    public SmartFab(Context context, AttributeSet attrs, int defStyleAttr, int xStdPos, int yStdPos,int xMovPos,int yMovPos) {
        super(context, attrs, defStyleAttr);
        setPos(xStdPos,yStdPos,xMovPos,yMovPos);
    }

    private void setPos(int xStdPos, int yStdPos,float xMovPos,float yMovPos) {
        this.xStdPos = xStdPos;
        this.xMovPos = xMovPos;
        this.yStdPos = yStdPos;
        this.yMovPos = yMovPos;
    }

    public int getxStdPos() {
        return xStdPos;
    }

    public void setxStdPos(int xStdPos) {
        this.xStdPos = xStdPos;
    }

    public int getyStdPos() {
        return yStdPos;
    }

    public void setyStdPos(int yStdPos) {
        this.yStdPos = yStdPos;
    }

    public float getxMovPos() {
        return xMovPos;
    }

    public void setxMovPos(float xMovPos) {
        this.xMovPos = xMovPos;
    }

    public float getyMovPos() {
        return yMovPos;
    }

    public void setyMovPos(float yMovPos) {
        this.yMovPos = yMovPos;
    }

    public Animation getAnimationAlpha() {
        return animationAlpha;
    }

    public void setAnimationAlpha(Animation animation) {
        this.animationAlpha = animation;
    }

    public Animation getAnimationBeta() {
        return animationBeta;
    }

    public void setAnimationBeta(Animation animationBeta) {
        this.animationBeta = animationBeta;
    }
}
