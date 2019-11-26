package rev.ca.rev_lib_core_views;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.util.Arrays;

public class RevViewsTapDance {

    public interface IRevViewsTapDance {
        void revCaller(int revTapDanceCount);
    }

    public RevViewsTapDance(final Integer[] revTapDanceCount, final View revDancingView, final IRevViewsTapDance iRevViewsTapDance) {
        revDancingView.setOnTouchListener(new View.OnTouchListener() {
            Handler handler = new Handler();

            int numberOfTaps = 0;
            long lastTapTimeMs = 0;
            long touchDownMs = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchDownMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);

                        if ((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()) {
                            // It was not a tap

                            numberOfTaps = 0;
                            lastTapTimeMs = 0;
                            break;
                        }

                        if (numberOfTaps > 0
                                && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                            numberOfTaps += 1;
                        } else {
                            numberOfTaps = 1;
                        }

                        lastTapTimeMs = System.currentTimeMillis();

                        if (Arrays.asList(revTapDanceCount).contains(numberOfTaps)) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Compare TAP DANCES count
                                    iRevViewsTapDance.revCaller(numberOfTaps);
                                }
                            }, ViewConfiguration.getDoubleTapTimeout());
                        }
                }

                return true;
            }
        });
    }
}
