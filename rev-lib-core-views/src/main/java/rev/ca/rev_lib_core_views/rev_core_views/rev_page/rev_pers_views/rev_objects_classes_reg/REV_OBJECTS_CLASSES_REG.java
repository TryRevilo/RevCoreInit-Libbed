package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_objects_classes_reg;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 2/3/18.
 */

public class REV_OBJECTS_CLASSES_REG {

    public static Map<String, Class> REV_PLUGGABLE_DATA_CLASSES_REG;

    public static void createRevPluggableLoader() {
        REV_PLUGGABLE_DATA_CLASSES_REG = new HashMap<>();

        RevVarArgsData revVarArgsData = new RevVarArgsData();
        revVarArgsData.setRevViewType("ICreatePluggableDataClass");
        for (Object revPluggableViewObject : RevPluggableServices.getICreatePluggableDataClassObjects("ICreatePluggableDataClass")) {
            ICreatePluggableDataClass iCreatePluggableDataClass = (ICreatePluggableDataClass) revPluggableViewObject;

            if (!REV_PLUGGABLE_DATA_CLASSES_REG.containsKey(iCreatePluggableDataClass.createPluggableDataClass().getName())) {
                REV_PLUGGABLE_DATA_CLASSES_REG.put(iCreatePluggableDataClass.createPluggableDataClass().getName(), iCreatePluggableDataClass.createPluggableDataClass());
            }
        }
    }
}
