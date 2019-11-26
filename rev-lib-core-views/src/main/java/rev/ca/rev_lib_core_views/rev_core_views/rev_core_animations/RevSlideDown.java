package rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevHeaderView;
import rev.ca.revlibviews.rev_core_layouts.RevConstantineViews;

/**
 * Created by rev on 10/23/17.
 */

public class RevSlideDown {

    private static boolean isHidden = true;

    public static void initSlide(View myView) {
        if (isHidden) {
            doSlideDown(myView);
        } else {
            doSlideUp(myView);
        }
        isHidden = !isHidden;
    }

    public static void doSlideDown(View myView) {
        RevHeaderView.revAltTopBar(myView);

        myView.setVisibility(myView.VISIBLE);
        Animation slideDown = setLayoutAnim_slidedown();
        myView.startAnimation(slideDown);
    }

    public static void doSlideUp(View myView) {
        Animation slideUp = setLayoutAnim_slideup(myView);
        myView.startAnimation(slideUp);
    }

    public static Animation setLayoutAnim_slidedown() {
        AnimationSet set = new AnimationSet(true);

        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(800);
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                // MapContacts.this.mapviewgroup.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                Log.d("LA", "sliding down ended");

            }
        });
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.25f);

        return animation;
    }

    public static Animation setLayoutAnim_slideup(final View myView) {
        AnimationSet set = new AnimationSet(true);

        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        animation.setDuration(800);
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                myView.clearAnimation();
                RevConstantineViews.REV_TOP_BAR_FL.removeView(myView);

                Log.v("MyApp", "REMOVED");
            }
        });
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.25f);

        return animation;
    }
}
