package rev.ca.revlibviews.rev_core_layouts;

import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;

/**
 * Created by rev on 12/11/17.
 */

public class RevConstantineViews {

    public static FrameLayout REV_BASE_CONTENT_CONTAINER_FL;
    public static FrameLayout REV_TOP_BAR_FL;

    public static LinearLayout PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL;
    public static LinearLayout PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP;
    public static LinearLayout PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP_BAR_LEFT;
    public static LinearLayout PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER;
    public static LinearLayout PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_BOTTOM;

    public static LinearLayout REV_PHOTOGRID_VIEW_LL;

    public static Map<Object, Object> REV_LOADED_VIEWS = new HashMap<>();

    public static void initRevConstantineViews() {
        RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(RevLibGenConstantine.REV_CONTEXT);

        REV_BASE_CONTENT_CONTAINER_FL = new RevCoreLayoutsFrameLayouts(RevLibGenConstantine.REV_CONTEXT).getRevCoreLayoutsFrameLayout_MATCH_ALL();

        REV_TOP_BAR_FL = new RevCoreLayoutsFrameLayouts(RevLibGenConstantine.REV_CONTEXT).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();
        REV_TOP_BAR_FL.setBackgroundColor(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_primary));

        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL = revCoreLayoutsLinearLayout.getRevCoreLayouts_CENTER_CONTENT_VIEW_LL();
        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP_BAR_LEFT = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER = revCoreLayoutsLinearLayout.getRevCoreLayouts_CENTER_CONTENT_VIEW_LL();
        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.setId(R.id.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER_ID);
        ((FrameLayout.LayoutParams) PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.getLayoutParams()).setMargins(0, (int) (RevLibGenConstantine.REV_MARGIN_LARGE * .9), 0, (int) (RevLibGenConstantine.REV_MARGIN_LARGE * .77));

        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_BOTTOM = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        REV_PHOTOGRID_VIEW_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
    }
}
