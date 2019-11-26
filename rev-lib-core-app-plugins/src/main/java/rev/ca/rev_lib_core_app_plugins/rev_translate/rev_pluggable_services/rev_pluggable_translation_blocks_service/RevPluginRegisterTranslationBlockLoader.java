package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;

public class RevPluginRegisterTranslationBlockLoader {

    public static ArrayList geRevPluginRegisterTranslationBlockObjects() {
        ArrayList iRevPluggableViewsClassesList = new ArrayList();

        for (Object o : RevPluggableServices.REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS("IRevPluginRegisterTranslationBlockList")) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            IRevPluginRegisterTranslationBlock instance;

            try {
                constructor = aClass.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                instance = (IRevPluginRegisterTranslationBlock) constructor.newInstance();
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

    public static List<IRevPluginRegisterTranslationBlock> revGetPluggableTranslationBlocks() {
        List<IRevPluginRegisterTranslationBlock> revPluginRegisterTranslationBlocks = new ArrayList<>();

        for (Object IRevPluginRegisterTranslationBlock : geRevPluginRegisterTranslationBlockObjects()) {
            IRevPluginRegisterTranslationBlock revTranslationBlock = (IRevPluginRegisterTranslationBlock) IRevPluginRegisterTranslationBlock;

            String revTranslationBlockName = revTranslationBlock.getRevTranslationBlockName();

            if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revTranslationBlockName) && !revPluginRegisterTranslationBlocks.contains(revTranslationBlockName))
                revPluginRegisterTranslationBlocks.add(revTranslationBlock);
        }

        return revPluginRegisterTranslationBlocks;
    }
}
