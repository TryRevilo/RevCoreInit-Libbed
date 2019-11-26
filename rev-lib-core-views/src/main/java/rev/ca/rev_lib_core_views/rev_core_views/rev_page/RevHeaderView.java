package rev.ca.rev_lib_core_views.rev_core_views.rev_page;

import android.content.Context;
import android.view.Gravity;
import android.view.InflateException;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableTopBarMenuItemViewLoader;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreFrameLayoutLayoutParams;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.REV_TOP_BAR_FL;

/**
 * Created by rev on 10/8/17.
 */

public class RevHeaderView {

    private static Context mContext;

    private static LinearLayout topBarContainer_LL;
    private FrameLayout revViewMainWrapperOuter_FL;

    int marginMid;

    public RevHeaderView(Context mContext) {
        this.mContext = mContext;

        topBarContainer_LL = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revViewMainWrapperOuter_FL = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_WRAP_ALL_LL_LP();

        revViewMainWrapperOuter_FL.addView(revPluggableHeaderOptionsLL());

        // revViewMainWrapperOuter_FL.addView(RevObjectListingOptionsTogglerMenu.getRevObjectListingOptionsTogglerMenu());

        topBarContainer_LL.addView(revViewMainWrapperOuter_FL);
        marginMid = new RevDimens(mContext).getRevMarginMedium();
    }

    public LinearLayout getRevHeaderViewLL() {
        return topBarContainer_LL;
    }

    public static void revAltTopBar(View contentView) {
        REV_VIEWS_BASE_FUNCTIONS.revRemoveParentView(contentView);
        try {
            REV_TOP_BAR_FL.addView(contentView);
            REV_TOP_BAR_FL.getLayoutParams().height = FrameLayout.LayoutParams.WRAP_CONTENT;
            REV_TOP_BAR_FL.requestLayout();
        } catch (InflateException e) {

        }
    }

    public LinearLayout revPluggableHeaderOptionsLL() {
        LinearLayout contentLL = new LinearLayout(mContext);

        FrameLayout.LayoutParams contentLL_LP = RevCoreFrameLayoutLayoutParams.getRev_WRAP_ALL_LP();
        contentLL_LP.setMargins(marginMid, marginMid, marginMid, 0);
        contentLL.setLayoutParams(contentLL_LP);
        contentLL_LP.gravity = Gravity.CENTER_VERTICAL;

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        contentLL.addView(RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.get("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP_BAR_LEFT"));
        contentLL.addView(space);
        contentLL.addView(RevPluggableTopBarMenuItemViewLoader.revLoadRevTopBarMenuItem(mContext));

        return contentLL;
    }
}
