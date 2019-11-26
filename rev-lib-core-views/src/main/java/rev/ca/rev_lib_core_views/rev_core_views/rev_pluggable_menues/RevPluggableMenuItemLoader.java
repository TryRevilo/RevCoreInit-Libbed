package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 11/5/17.
 */

public class RevPluggableMenuItemLoader {

    public static void createRevPluggableLoader() {
        for (Object revPluggableViewObject : RevPluggableServices.REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS("ICreateRevPluggableMenuItem")) {

            Constructor constructor;
            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = null;
            try {
                constructor = ((Class) revPluggableViewObject).getConstructor(RevVarArgsData.class);
                iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) constructor.newInstance(new RevVarArgsData(RevLibGenConstantine.REV_CONTEXT));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            String revPluggableMenuName = iCreateRevPluggableMenuItem.REV_PLUGGABLE_MENU_ITEM_VM_NAME();
            if (!RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey(revPluggableMenuName)) {
                RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.put(revPluggableMenuName, iCreateRevPluggableMenuItem.getClass());
            }
        }
    }
}
