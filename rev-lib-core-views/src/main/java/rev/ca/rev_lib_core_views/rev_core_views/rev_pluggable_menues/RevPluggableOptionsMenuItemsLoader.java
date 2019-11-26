package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import java.util.ArrayList;
import java.util.Iterator;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 11/5/17.
 */

public class RevPluggableOptionsMenuItemsLoader {

    public static void createRevPluggableLoader() {
        for (final ICreateRevPluggableOptionsMenu createRevPluggableOptionsMenu : RevPluggableServices.getICreateRevPluggableOptionsMenuObjects("createRevPluggableOptionsMenus")) {
            final RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = createRevPluggableOptionsMenu.revCreateRevPluggableOptionsMenue();

            String revPluggableOptionsMenueName = revPluggableOptionsMenuVM.getRevPluggableOptionsMenueName();
            final Iterator iterator = RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.keySet().iterator();

            while (iterator.hasNext()) {
                Object revPluggableMenuItemName = iterator.next();
                RevVarArgsData revVarArgsData = new RevVarArgsData();
                revVarArgsData.setRevViewType(String.valueOf(revPluggableMenuItemName));
                final ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(revVarArgsData);

                if (iCreateRevPluggableMenuItem == null) continue;

                if (revPluggableOptionsMenueName.equals(iCreateRevPluggableMenuItem.REV_PLUGGABLE_MENU_CONTAINER_NAME())) {
                    if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_ITEMS_MAP.containsKey(revPluggableOptionsMenueName)) {
                        RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_ITEMS_MAP.get(revPluggableOptionsMenueName).add(iCreateRevPluggableMenuItem);
                    } else {
                        RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_ITEMS_MAP.put(revPluggableOptionsMenueName,
                                new ArrayList() {{
                                    add(iCreateRevPluggableMenuItem);
                                }});
                    }
                }
            }

            RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_VM_MAP.put(
                    revPluggableOptionsMenueName, revPluggableOptionsMenuVM);
        }
    }
}
