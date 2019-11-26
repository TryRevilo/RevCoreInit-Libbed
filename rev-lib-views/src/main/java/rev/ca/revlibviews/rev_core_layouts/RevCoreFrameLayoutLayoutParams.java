package rev.ca.revlibviews.rev_core_layouts;

import android.widget.FrameLayout;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

/**
 * Created by rev on 10/28/17.
 */

public class RevCoreFrameLayoutLayoutParams {

    public static FrameLayout.LayoutParams getRev_MATCH_ALL_LP() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return layoutParams;
    }

    public static FrameLayout.LayoutParams getRev_MATCH_WRAP_LP() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return layoutParams;
    }

    public static FrameLayout.LayoutParams getRev_WRAP_ALL_LP() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        return layoutParams;
    }

    public static FrameLayout.LayoutParams getRev_WRAP_CONTENT_MATCH_PARENT_LP() {
        FrameLayout.LayoutParams revLogInFormItemsInputs_LP = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return revLogInFormItemsInputs_LP;
    }

    public static FrameLayout.LayoutParams getRev_MATCH_PARENT_WRAP_CONTENT_FL_LP() {
        FrameLayout.LayoutParams revLogInFormItemsInputs_LP = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        return revLogInFormItemsInputs_LP;
    }

    public static FrameLayout.LayoutParams getRev_centerContentView_LL_LP() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                (int) (RevLibGenConstantine.REV_DIMENS.getPageWidth() * .8), FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, RevLibGenConstantine.REV_DIMENS.getRevSize5() * 3, RevLibGenConstantine.REV_FOOTER_HEIGHT);

        return layoutParams;
    }
}
