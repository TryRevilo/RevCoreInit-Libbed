package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevPluggableOptionsKIWIMenuVMTest implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    public RevPluggableOptionsKIWIMenuVMTest(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("kiwi_test_options_menu");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(linearLayout);
        revPluggableOptionsMenuVM.setMenuItemsViewType("linear_horizontal");

        return revPluggableOptionsMenuVM;
    }
}
