package rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.register_timeline_object;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;

public class REV_REGISTER_TIMELINE_ENTITY_SUB_TYPE_LOADER {

    private static ArrayList REV_LOAD_PLUGGABLE_TIMELINE_ENTITY_SUBT_YPES() {
        ArrayList iRevPluggableViewsClassesList = new ArrayList();

        for (Object o : RevPluggableServices.I_REV_CUSTOM_PLUGIN_SERVICES_SERVICE_PROVIDERS("getRegisteredTimelineEntityType")) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            IRevPluginRegisterTimelineEntityType instance;

            try {
                constructor = aClass.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                instance = (IRevPluginRegisterTimelineEntityType) constructor.newInstance();
                iRevPluggableViewsClassesList.add(instance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return iRevPluggableViewsClassesList;
    }

    public static List<String> REV_GET_PLUGGABLE_TIMELINE_ENTITY_SUB_TYPES() {
        List<String> stringList = new ArrayList<>();

        for (Object o : REV_LOAD_PLUGGABLE_TIMELINE_ENTITY_SUBT_YPES()) {
            IRevPluginRegisterTimelineEntityType iRevPluginRegisterTimelineEntityType = (IRevPluginRegisterTimelineEntityType) o;

            if (iRevPluginRegisterTimelineEntityType.getRegisteredTimelineEntityType() != null) {
                String registeredTimelineEntityType = iRevPluginRegisterTimelineEntityType.getRegisteredTimelineEntityType();

                if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(registeredTimelineEntityType) && !stringList.contains(registeredTimelineEntityType))
                    stringList.add(registeredTimelineEntityType);
            }
        }

        return stringList;
    }
}
