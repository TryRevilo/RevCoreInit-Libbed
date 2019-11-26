package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevProfileContentPluggableFloatingOptionsMenuWrapper implements ICreateRevPluggableOptionsMenu {

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevProfileContentPluggableFloatingOptionsMenuWrapper(Context mContext) {
        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        LinearLayout revPageOptionsWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        FrameLayout.LayoutParams linearLayout_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_LP.gravity = Gravity.TOP | Gravity.RIGHT;
        revPageOptionsWrapper_LL.setLayoutParams(linearLayout_LP);

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_profile_content_floating_options_wrapper_menu_area");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(revPageOptionsWrapper_LL);
        revPluggableOptionsMenuVM.setMenuItemsViewType("linear_horizontal");

        return revPluggableOptionsMenuVM;
    }
}
