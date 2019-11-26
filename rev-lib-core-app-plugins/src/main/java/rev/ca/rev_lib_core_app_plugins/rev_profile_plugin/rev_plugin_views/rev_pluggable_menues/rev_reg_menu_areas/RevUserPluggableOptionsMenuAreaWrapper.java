package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas;

import android.content.Context;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevUserPluggableOptionsMenuAreaWrapper implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    public RevUserPluggableOptionsMenuAreaWrapper(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_user_options_menu_wrapper_area");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL());
        revPluggableOptionsMenuVM.setMenuItemsViewType("linear_horizontal");

        return revPluggableOptionsMenuVM;
    }
}
