package rev.ca.rev_lib_core_views;

import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;

import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER;
import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.REV_BASE_CONTENT_CONTAINER_FL;

/**
 * Created by rev on 11/4/17.
 */

public class REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS {

    public static GradientDrawable REV_GET_ALL_BORDERS_CURVED() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10f); // Set corner radius
        gradientDrawable.setColor(0xFFFFFFFF); //white background
        // gradientDrawable.setColor(ContextCompat.getColor(mContext, R.color.revColorAccentLight));
        gradientDrawable.setStroke(1, 0xFF000000); //black border with full opacity

        return gradientDrawable;
    }

    public static void REV_REMOVE_PARENTVIEW(View revView) {
        if (revView.getParent() != null)
            ((ViewGroup) revView.getParent()).removeView(revView);
    }

    public static void REV_CLEAR_CONTENT_VIEW(ViewGroup viewGroup) {
        if (viewGroup != null && viewGroup.getChildCount() > 0) {
            viewGroup.removeAllViews();
            viewGroup.invalidate();
        }
    }

    public static void REV_RESET_BASE_CONTENT_CONTAINER_BY_PARENT_VIEW_ID(int parentViewID, View replacementView) {
        if (replacementView != null) {
            if (parentViewID == R.id.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER_ID) {
                REV_REMOVE_PARENTVIEW(replacementView);

                if (REV_BASE_CONTENT_CONTAINER_FL.findViewById(R.id.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER_ID) == null) {
                    REV_BASE_CONTENT_CONTAINER_FL.addView(replacementView);
                    return;
                }

                REV_CLEAR_CONTENT_VIEW(PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER);
                PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.addView(replacementView);
            }
        }
    }

    public static void REV_RESET_CONTENT_VIEW(ViewGroup revContainerView, View revReplacementView) {
        if (revContainerView == null) return;

        if (revContainerView.getChildCount() > 0)
            REV_CLEAR_CONTENT_VIEW(revContainerView);


        if (revReplacementView == null) {
            RevCoreInputFormTextView revCoreInputFormTextView = new RevCoreInputFormTextView(RevLibGenConstantine.REV_CONTEXT);
            TextView nullViewReturn = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
            nullViewReturn.setText("ERROR ~ return rev_null_view");

            revContainerView.addView(nullViewReturn);
            return;
        }

        REV_REMOVE_PARENTVIEW(revReplacementView);
        REV_CLEAR_CONTENT_VIEW(revContainerView);
        revContainerView.addView(revReplacementView);
    }

    public static void REV_REMOVE_INLINE_VIEW_PARENT(String revViewName) {
        View revInlineView = RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW(revViewName);
        if (revInlineView != null) {
            if (revInlineView.getParent() != null) {
                ((ViewGroup) revInlineView.getParent()).removeView(revInlineView);
            }
        }
    }

    public static int REV_GET_VIEW_PLACEMENT(ViewGroup viewGroup, int pos) {
        if (pos > 0) {
            pos = ((viewGroup.getChildCount() + pos) - pos) - 1;
        } else if (pos < 0) {
            pos = (viewGroup.getChildCount() + pos) - 1;
        }

        return pos;
    }

    public static Rect REV_GET_VIEW_LOCATION(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe) {
            // Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }
}
