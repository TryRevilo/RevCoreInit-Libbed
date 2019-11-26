package rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_pluggable_menus.rev_reg_menu_areas;

import android.content.Context;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevMainUserSettingsPluggableMenuWrapper implements ICreateRevPluggableOptionsMenu {

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevMainUserSettingsPluggableMenuWrapper(Context mContext) {
        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_main_user_settings_menu_wrapper");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(linearLayout);
        revPluggableOptionsMenuVM.setMenuItemsViewType("linear_horizontal");

        return revPluggableOptionsMenuVM;
    }
}
