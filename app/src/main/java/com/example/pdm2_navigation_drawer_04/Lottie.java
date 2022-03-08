package com.example.pdm2_navigation_drawer_04;

import android.animation.Animator;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class Lottie {

    private LottieAnimationView lottieAnimationView;

    public Lottie(LottieAnimationView lav){
        lottieAnimationView = lav;
    }

    public void animar(int rawId) {

        lottieAnimationView.setAnimation(rawId);
        lottieAnimationView.setRepeatCount(1);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                lottieAnimationView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
