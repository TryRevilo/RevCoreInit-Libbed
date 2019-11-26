package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;

public class IRevPluginRegisterTranslationItems_SP_Loader {

    public static ArrayList geRevPluginRegisterTranslationBlockObjects() {
        ArrayList revPluggableServicesList = new ArrayList();

        for (Object o : RevPluggableServices.REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS("IRevPluginRegisterTranslationItems_SPI")) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            IRevPluginRegisterTranslationItems_SPI instance;

            try {
                constructor = aClass.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                instance = (IRevPluginRegisterTranslationItems_SPI) constructor.newInstance();
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

    public static List<IRevPluginRegisterTranslationObject_SPI> revGetPluggableTranslationItems() {
        List<IRevPluginRegisterTranslationObject_SPI> iRevPluginRegisterTranslationItems_spis = new ArrayList<>();

        for (Object o : geRevPluginRegisterTranslationBlockObjects()) {
            IRevPluginRegisterTranslationItems_SPI iRevPluginRegisterTranslationItems_spi = (IRevPluginRegisterTranslationItems_SPI) o;

            List<IRevPluginRegisterTranslationObject_SPI> iRevPluginRegisterTranslationObject_spis = iRevPluginRegisterTranslationItems_spi.I_REV_PLUGIN_REGISTER_TRANSLATION_OBJECT_SPIS();

            if (iRevPluginRegisterTranslationObject_spis != null && !iRevPluginRegisterTranslationObject_spis.isEmpty()) {
                for (IRevPluginRegisterTranslationObject_SPI iRevPluginRegisterTranslationObject_spi : iRevPluginRegisterTranslationObject_spis)
                iRevPluginRegisterTranslationItems_spis.add(iRevPluginRegisterTranslationObject_spi);
            }
        }

        return iRevPluginRegisterTranslationItems_spis;
    }
}
