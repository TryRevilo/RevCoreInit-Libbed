package rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by rev on 10/20/17.
 */

public class RevSlideUpDown {

    public static class RevTopDownSlide {
        public static void slideDown(View view) {
            Activity mActivity = (Activity) view.getContext();

            DisplayMetrics dm = new DisplayMetrics();
            mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

            int height = dm.heightPixels;

            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    0,                 // fromYDelta
                    height); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);

            view.setVisibility(View.INVISIBLE);
        }

        public static void slideUp(View view) {
            Activity mActivity = (Activity) view.getContext();

            DisplayMetrics dm = new DisplayMetrics();
            mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

            int height = dm.heightPixels;

            view.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    height,  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }

        public static void slideUp(View view, int heightPlace) {
            Activity mActivity = (Activity) view.getContext();

            DisplayMetrics dm = new DisplayMetrics();
            mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

            int height = dm.heightPixels;

            view.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    height,  // fromYDelta
                    (float) (height * .5));                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }
    }

    public static void slideHide(View view) {
        Activity mActivity = (Activity) view.getContext();

        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;

        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                height);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
}
