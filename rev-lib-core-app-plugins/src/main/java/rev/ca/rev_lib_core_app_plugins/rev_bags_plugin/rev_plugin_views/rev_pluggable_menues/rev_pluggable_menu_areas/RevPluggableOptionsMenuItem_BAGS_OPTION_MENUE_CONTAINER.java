package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_areas;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 1/20/18.
 */

public class RevPluggableOptionsMenuItem_BAGS_OPTION_MENUE_CONTAINER implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;

    private String menuItemName = "rev_bag_options_menu";

    public RevPluggableOptionsMenuItem_BAGS_OPTION_MENUE_CONTAINER(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return Arrays.asList("rev_bag_options_menu_wrapper_area");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_bag_options_menu_holder"));

        if (new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_bag_options_menu", revVarArgsData) != null) {
            RevVarArgsData postRVD = new RevVarArgsData();
            postRVD.setmContext(revVarArgsData.getmContext());
            postRVD.setRevEntity(revVarArgsData.getRevEntity());
            revPluggableMenuItemVM.setRevPluggableMenuView(new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_bag_options_menu", postRVD));
        }

        return revPluggableMenuItemVM;
    }
}
