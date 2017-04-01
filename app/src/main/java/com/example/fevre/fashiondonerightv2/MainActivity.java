package com.example.fevre.fashiondonerightv2;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final ArrayList<SmartFab> smartFabs = new ArrayList<>();
        final int screenWidth = size.x; // int screenWidth = display.getWidth(); on API < 13
        final int screenHeight = size.y;
        final SmartFab hatButton = createFab(Math.round(0.39F * screenWidth), Math.round(0.1F * screenHeight), R.id.fabHat, screenWidth,screenHeight);
        final SmartFab shirtButton = createFab(Math.round(0.39F * screenWidth), Math.round(0.35F * screenHeight), R.id.fabShirt, screenWidth,screenHeight);
        final SmartFab jacketButton = createFab(Math.round(0.39F * screenWidth), Math.round(0.25F * screenHeight), R.id.fabJacket, screenWidth,screenHeight);
        final SmartFab pantsButton = createFab(Math.round(0.45F * screenWidth), Math.round(0.5F * screenHeight), R.id.fabPants, screenWidth,screenHeight);
        final SmartFab shoesButton = createFab(Math.round(0.28F * screenWidth), Math.round(0.6F * screenHeight), R.id.fabShoes, screenWidth,screenHeight);
        smartFabs.add(hatButton);
        smartFabs.add(shirtButton);
        smartFabs.add(jacketButton);
        smartFabs.add(pantsButton);
        smartFabs.add(shoesButton);

        addClickHandlers(smartFabs, screenWidth,R.drawable.ic_hat ,hatButton);
        addClickHandlers(smartFabs, screenWidth,R.drawable.ic_shirt ,shirtButton);
        addClickHandlers(smartFabs, screenWidth,R.drawable.ic_jacket ,jacketButton);
        addClickHandlers(smartFabs, screenWidth,R.drawable.ic_pants ,pantsButton);
        addClickHandlers(smartFabs, screenWidth,R.drawable.ic_shoes ,shoesButton);

        /**
        for (SmartFab smartFab : smartFabs) {
            smartFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!(smartFabs.get(0).getX() == screenWidth-(smartFabs.get(0).getWidth()))){
                        for (SmartFab smartFab : smartFabs) {
                            smartFab.startAnimation(smartFab.getAnimationAlpha());
                        }
                    } else {
                        for (SmartFab smartFab : smartFabs) {
                            smartFab.startAnimation(smartFab.getAnimationBeta());
                        }
                    }
                }
            });
        }
        **/
        

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
    }

    private void addClickHandlers(final ArrayList<SmartFab> smartFabs, final int screenWidth, final int iconNumber, final SmartFab button) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!(button.getX() == screenWidth-(button.getWidth()))){
                        button.setImageResource(iconNumber);
                        for (SmartFab smartFab : smartFabs) {
                            smartFab.startAnimation(smartFab.getAnimationAlpha());
                        }
                    } else {
                        button.setImageResource(iconNumber);
                        // change icon

                        // Open dialog


                        /**
                        // Moveback code
                        for (SmartFab smartFab : smartFabs) {
                            smartFab.startAnimation(smartFab.getAnimationBeta());
                        }
                         **/
                    }
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private SmartFab createFab(int x,int y, int id, final int screenWidth, final int screenHeight){
        final SmartFab fab= (SmartFab) findViewById(id);
        fab.setX(x);
        fab.setY(y);
        fab.setxStdPos(x);
        fab.setyStdPos(y);
        fab.setxMovPos(screenWidth-(fab.getX()+fab.getWidth()));
        fab.setyMovPos(0);

        // setup animation
        final Animation animation = new TranslateAnimation(0,screenWidth-(fab.getX()+fab.getDrawable().getIntrinsicWidth()+4),0,0);
        // set Animation for 5 sec
        animation.setDuration(1000);
        //for button stops in the new position.
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.clearAnimation();
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                fab.setLayoutParams(layoutParams);
                fab.setX(screenWidth-(fab.getWidth()));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fab.setAnimationAlpha(animation);



        // close animation
        final Animation closeAnimation = new TranslateAnimation(0,-(screenWidth-(fab.getX()+fab.getDrawable().getIntrinsicWidth())),0,0);
        // set Animation for 5 sec
        closeAnimation.setDuration(1000);
        //for button stops in the new position.
        closeAnimation.setFillAfter(true);
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.clearAnimation();
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                fab.setLayoutParams(layoutParams);
                fab.setX(fab.getxStdPos());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fab.setAnimationBeta(closeAnimation);

        return fab;
    }
}
