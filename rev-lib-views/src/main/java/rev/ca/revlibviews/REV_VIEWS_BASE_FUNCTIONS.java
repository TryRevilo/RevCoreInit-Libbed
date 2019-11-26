package rev.ca.revlibviews;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

/**
 * Created by rev on 3/21/18.
 */

public class REV_VIEWS_BASE_FUNCTIONS {

    public static View REV_SPACER() {
        View space = new View(RevLibGenConstantine.REV_CONTEXT);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 0.1f);
        space.setLayoutParams(spaceLP);

        return space;
    }

    public static void CENTER_VIEW_VERTICALLY(View view) {
        if (view.getLayoutParams() == null) {
            LinearLayout.LayoutParams addPeopleToProfile_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addPeopleToProfile_TV_LP.gravity = Gravity.CENTER_VERTICAL;
            view.setLayoutParams(addPeopleToProfile_TV_LP);
        } else {
            ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        }
    }

    public static double V_T_OBSERVER_WIDTH(final View viewContainerWrapper) {
        final double[] width = new double[1];
        ViewTreeObserver observer = viewContainerWrapper.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                width[0] = viewContainerWrapper.getWidth();
                viewContainerWrapper.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });

        return width[0];
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void REV_SAFE_SET_BACKGROUND(View parentView, Drawable drawable) {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            parentView.setBackground(drawable);
        } else {
            parentView.setBackgroundDrawable(drawable);
        }
    }

    public static void revRemoveParentView(View view) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
    }

    public static View revRemovedParentView(View view) {
        revRemoveParentView(view);
        return view;
    }

}
