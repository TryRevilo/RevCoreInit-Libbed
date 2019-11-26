package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import java.util.List;

/**
 * Created by rev on 1/19/18.
 */

public interface ICreateRevPluggableMenuItem {

    String REV_PLUGGABLE_MENU_ITEM_VM_NAME();

    List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME();

    RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM();
}
