package com.example.akash.cookieclicker;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.akash.cookieclicker.R.id.constraintLayout;

public class MainActivity extends AppCompatActivity implements OnGestureListener {

    GestureDetector gestureDetector;
    static ImageView stack;
    public static TextView counter;
    public static int swipes = 0;
    public static ConstraintLayout layout;
    static Handler handler = new Handler();
    static int fishCounter = 0;
    static int matressCounter = 0;
    static int bankCounter = 0;
    static int bitcoinCounter = 0;
    Button menu;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stack = (ImageView)findViewById(R.id.Stack) ;
        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);
        layout = (ConstraintLayout)findViewById(R.id.lay);
        counter = (TextView)findViewById(R.id.swipeCounter);
        menu = (Button)findViewById(R.id.menuAccess);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), menuActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if(motionEvent1.getY() - motionEvent2.getY() > 50){
            swipes++;
            swipeUpAnimation();
            counter.setText("$"+swipes);

            return true;
        }
        if(motionEvent1.getX() - motionEvent2.getX() > 50){
            Intent myIntent = new Intent(getApplicationContext(), menuActivity.class);
            startActivityForResult(myIntent, 0);
            return true;
        }
        else {

            return true ;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // TODO Auto-generated method stub

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

    public void swipeUpAnimation(){
        final ImageView note = new ImageView(MainActivity.this);
        note.setImageResource(R.drawable.note);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(1500, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        note.setLayoutParams(params);
        note.setScaleX(0.955f);
        note.setScaleY(0.93787f);
        note.setY(591);
        note.setX(25);
        layout.addView(note);

        final TextView plusOne = new TextView(MainActivity.this);
        plusOne.setText("+$1");
        plusOne.setX((float)Math.random()*100);
        plusOne.setY((float)(Math.random()*500)+500);
        plusOne.setTextSize(20);
        plusOne.setTextColor(Color.BLACK);
        layout.addView(plusOne);

        final ImageView note2 = new ImageView(this);
        note2.setImageResource(R.drawable.single);

        ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        note2.setLayoutParams(params2);

        float random = (float) (Math.random()*650)+10;
        note2.setX(random);


        final TranslateAnimation slideDown = new TranslateAnimation(note2.getTranslationX(), note2.getTranslationX(), -1000, 2800);
        slideDown.setDuration(5500);

        final TranslateAnimation slideUp = new TranslateAnimation(note.getTranslationX(), note.getTranslationX(), 0, -5000);
        slideUp.setDuration(700);
        final TranslateAnimation textUp = new TranslateAnimation(plusOne.getTranslationX(), plusOne.getTranslationX(), plusOne.getTranslationY(), -1000);
        textUp.setDuration(2000);
        final ScaleAnimation shrink = new ScaleAnimation(1,.5f,1,.5f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shrink.setDuration(700);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(slideUp);
        animationSet.addAnimation(shrink);
        note.startAnimation(animationSet);

        plusOne.bringToFront();
        note.bringToFront();
        counter.bringToFront();

        plusOne.startAnimation(textUp);
        textUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.removeView(plusOne);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layout.addView(note2);
                counter.bringToFront();
                note2.startAnimation(slideDown);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.removeView(note);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.removeView(note2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        int random2 = (int)(Math.random()*5)+1;

        switch (random2){
            case 1: layout.setBackgroundColor(Color.WHITE);
                break;
            case 2: layout.setBackgroundColor(Color.RED);
                break;
            case 3: layout.setBackgroundColor(Color.GREEN);
                break;
            case 4: layout.setBackgroundColor(Color.BLUE);
                break;
            case 5: layout.setBackgroundColor(Color.YELLOW);
                break;
            case 6: layout.setBackgroundColor(Color.GRAY);
                break;
        }
    }

    public static class backgroundCounter extends Thread {

        public void run(){
            boolean t = true;
            while (t){
                swipes++;
                handler.post(new Runnable() {
                    public void run() {
                        String s = String.valueOf(swipes);
                        counter.setText("$"+s);
                        menuActivity.money.setText("$"+s+"  ");
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}