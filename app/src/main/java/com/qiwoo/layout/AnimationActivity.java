package com.qiwoo.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    private ConstraintLayout constraintLayout;
    private ConstraintSet newConstraintSet = new ConstraintSet();
    private ConstraintSet originConstraintSet = new ConstraintSet();

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (tag) {
                TransitionManager.beginDelayedTransition(constraintLayout);
                newConstraintSet.setMargin(R.id.animation_left_eye, ConstraintSet.START, 20);
                newConstraintSet.setMargin(R.id.animation_right_eye, ConstraintSet.END, 20);
                newConstraintSet.applyTo(constraintLayout);
            } else {
                TransitionManager.beginDelayedTransition(constraintLayout);
                originConstraintSet.applyTo(constraintLayout);
            }

            tag = !tag;

            return false;
        }
    });

    private boolean tag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        constraintLayout = findViewById(R.id.main);
        originConstraintSet.clone(constraintLayout);
        newConstraintSet.clone(constraintLayout);


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(0);

            }
        }, 0, 1000);

    }
}
