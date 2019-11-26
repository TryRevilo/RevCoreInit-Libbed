package rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_pluggable_services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;

public class RegisterNoticiasEntityTypeLoader {

    public static ArrayList getRevPluggableNoticiasEntityTypeObjects() {
        ArrayList iRevPluggableViewsClassesList = new ArrayList();

        for (Object o : RevPluggableServices.REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS("getRegisteredNoticiasEntityType")) {
            Class aClass = (Class) o;
            IRevPluginRegisterNoticiasEntityType instance;

            try {
                Constructor constructor = aClass.getConstructor();
                instance = (IRevPluginRegisterNoticiasEntityType) constructor.newInstance();
                iRevPluggableViewsClassesList.add(instance);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return iRevPluggableViewsClassesList;
    }

    public static List<String> getRevPluggableNoticiasEntityTypes() {
        List<String> stringList = new ArrayList<>();

        for (Object o : getRevPluggableNoticiasEntityTypeObjects()) {
            IRevPluginRegisterNoticiasEntityType iRevPluginRegisterNoticiasEntityType = (IRevPluginRegisterNoticiasEntityType) o;

            if (iRevPluginRegisterNoticiasEntityType.getRegisteredNoticiasEntityType() != null) {
                String registeredNoticiasEntityType = iRevPluginRegisterNoticiasEntityType.getRegisteredNoticiasEntityType();

                if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(registeredNoticiasEntityType) && !stringList.contains(registeredNoticiasEntityType))
                    stringList.add(registeredNoticiasEntityType);
            }
        }

        return stringList;
    }
}
