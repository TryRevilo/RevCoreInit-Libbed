package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;

public class IRevPluginRegisterRevPluginItem_SP_Loader {

    private static ArrayList geRevPluginRegisterTranslationBlockObjects() {
        ArrayList revPluggableServicesList = new ArrayList();

        for (Object o : RevPluggableServices.REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS("IRevPluginRegisterRevPluginItem_SPI")) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            IRevPluginRegisterRevPluginItem_SPI instance;

            try {
                constructor = aClass.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                instance = (IRevPluginRegisterRevPluginItem_SPI) constructor.newInstance();
                revPluggableServicesList.add(instance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return revPluggableServicesList;
    }

    public static List<IRevPluginRegisterRevPluginItem_SPI> revGetPluggableItems() {
        List<IRevPluginRegisterRevPluginItem_SPI> iRevPluginRegisterRevPluginItem_spis = new ArrayList<>();

        for (Object o : geRevPluginRegisterTranslationBlockObjects()) {
            iRevPluginRegisterRevPluginItem_spis.add((IRevPluginRegisterRevPluginItem_SPI) o);
        }

        return iRevPluginRegisterRevPluginItem_spis;
    }
}
