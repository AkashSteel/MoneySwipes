package com.example.akash.cookieclicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.akash.cookieclicker.MainActivity.swipes;

/**
 * Created by Akash on 2/13/2018.
 */

public class menuActivity extends AppCompatActivity implements OnGestureListener {
    public ListView upgr;

    ArrayList<Upgrades> upgrades = new ArrayList<>();
    Button close;
    GestureDetector gestureDetector;
    static LinearLayout l;
    static TextView money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrademenu);
        upgr = (ListView)findViewById(R.id.upgrades);
        close = (Button)findViewById(R.id.close);
        gestureDetector = new GestureDetector(menuActivity.this, menuActivity.this);
        l = (LinearLayout)findViewById(R.id.upgradeLayout);
        money = (TextView)findViewById(R.id.amount);

        money.setText("$"+MainActivity.swipes+"  ");

        upgrades.add(new Upgrades("Fish Bowl","+$1/sec","Cost: $100",MainActivity.fishCounter, R.drawable.fishbowl));
        upgrades.add(new Upgrades("Matress","+$3/sec","Cost: $300",MainActivity.matressCounter, R.drawable.mattress));
        upgrades.add(new Upgrades("Bank Account","+$5/sec","Cost: $500",MainActivity.bankCounter, R.drawable.bank));
        upgrades.add(new Upgrades("Bitcoin Mining", "+10/sec", "Cost: $1000", MainActivity.bitcoinCounter, R.drawable.bitcoin));

        final CustomAdapter adapter = new CustomAdapter(this,R.layout.upgrade,upgrades);
        upgr.setAdapter(adapter);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        final ImageView up = new ImageView(menuActivity.this);
        up.setImageResource(R.drawable.single);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        up.setLayoutParams(params);
        up.setY(1591);
        up.setX(250);
        l.addView(up);
        up.bringToFront();

        upgr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = upgrades.get(i).getName();
                money.setText("$"+MainActivity.swipes+"  ");
                if(MainActivity.swipes <0){
                    swipes = 0;
                }
                switch(name){
                    case "Fish Bowl":
                        if(MainActivity.swipes>100){
                            upgradeAnimate(0);
                            Toast.makeText(menuActivity.this, "Fish Bowl Selected", Toast.LENGTH_SHORT).show();
                            new MainActivity.backgroundCounter().start();
                            MainActivity.fishCounter++;
                            upgrades.set(0,new Upgrades("Fish Bowl","+$1/sec","Cost: $100",MainActivity.fishCounter, R.drawable.fishbowl));
                            upgr.setAdapter(adapter);
                            swipes-=100;
                            money.setText("$"+MainActivity.swipes+"  ");
                        }
                        break;
                    case "Matress":
                        if (MainActivity.swipes>300) {
                            money.setText("$"+MainActivity.swipes+"  ");
                            upgradeAnimate(1);
                            Toast.makeText(menuActivity.this, "Mattress Selected", Toast.LENGTH_SHORT).show();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            MainActivity.matressCounter++;
                            upgrades.set(1, new Upgrades("Matress", "+$3/sec", "Cost: $300", MainActivity.matressCounter, R.drawable.mattress));
                            upgr.setAdapter(adapter);
                            swipes -= 300;
                            money.setText("$"+MainActivity.swipes+"  ");
                        }
                        break;
                    case "Bank Account":
                        if(MainActivity.swipes>500) {
                            money.setText("$"+MainActivity.swipes+"  ");
                            upgradeAnimate(2);
                            Toast.makeText(menuActivity.this, "Bank Selected", Toast.LENGTH_SHORT).show();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            MainActivity.bankCounter++;
                            upgrades.set(2, new Upgrades("Bank Account", "+$5/sec", "Cost: $500", MainActivity.bankCounter, R.drawable.bank));
                            upgr.setAdapter(adapter);
                            swipes -= 500;
                        }
                        break;
                    case "Bitcoin Mining":
                        if(MainActivity.swipes>1000) {
                            money.setText("$"+MainActivity.swipes+"  ");
                            upgradeAnimate(3);
                            Toast.makeText(menuActivity.this, "Bitcoin Selected", Toast.LENGTH_SHORT).show();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            new MainActivity.backgroundCounter().start();
                            MainActivity.bitcoinCounter++;
                            upgrades.set(3, new Upgrades("Bitcoin Mining", "+$10/sec", "Cost: $1000", MainActivity.bitcoinCounter, R.drawable.bitcoin));
                            upgr.setAdapter(adapter);
                            swipes -= 1000;
                            money.setText("$"+MainActivity.swipes+"  ");
                        }
                        break;
                }
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);

    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float v, float v1) {
        if(motionEvent1.getY() - motionEvent2.getY() > 50) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        else {

            return true ;
        }    }


    public void upgradeAnimate(int p){
        final ImageView up = new ImageView(menuActivity.this);
        up.setImageResource(upgrades.get(p).getPic());

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(1500, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        up.setLayoutParams(params);
        up.setY(591);
        up.setX(25);
        l.addView(up);

        final TranslateAnimation slideUp = new TranslateAnimation(up.getTranslationX(), up.getTranslationX(), -1000, 500);
        slideUp.setDuration(700);
        final TranslateAnimation slideDown = new TranslateAnimation(up.getTranslationX(), up.getTranslationX(), -500, 1000);
        slideDown.setDuration(700);

        up.startAnimation(slideUp);

        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                up.startAnimation(slideDown);
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
                l.removeView(up);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public class CustomAdapter extends ArrayAdapter<Upgrades> {
        Context context;
        List list;

        public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Upgrades> objects){
            super(context, resource, objects);
            this.context = context;
            list = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(R.layout.upgrade,null);

            TextView textView = adapterView.findViewById(R.id.Upgrade);
            TextView addition = adapterView.findViewById(R.id.number);
            TextView times = adapterView.findViewById(R.id.times);
            TextView cost = adapterView.findViewById(R.id.cost);
            ImageView pic = adapterView.findViewById(R.id.pic);
            String a = String.valueOf(upgrades.get(position).getAdditional());
            String t = String.valueOf(upgrades.get(position).getTimesSelected());

            textView.setText(upgrades.get(position).getName());
            addition.setText(a);
            times.setText(t);
            cost.setText(upgrades.get(position).getCost());
            pic.setImageResource(upgrades.get(position).getPic());
            return adapterView;
        }

    }
}
