package rev.ca.rev_lib_core_views.rev_pluggable_views_impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_objects_classes_reg.ICreatePluggableDataClass;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_objects_classes_reg.REV_OBJECTS_CLASSES_REG;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemLoader;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuItemsLoader;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_plugin_loader.RevPluginLoader;
import rev.ca.rev_plugin_loader.RevPluginsObjectsRegistry;

/**
 * Created by rev on 11/2/17.
 */

public class RevPluggableServices {

    public static void initIRevPluggableServices() {
        RevConstantinePluggableViewsServices.initRevConstantinePluggableViewsServices();

        for (Object object : RevPluginsObjectsRegistry.getRevPluginsObjectsRegistry()) {
            if ((object instanceof IRevPluginStart)) {
                RevConstantinePluggableViewsServices.REV_PLUGIN_START_ARRAYLIST.add((IRevPluginStart) object);
            }

            if ((object instanceof IRevPluginStartInputForms)) {
                Map<String, Class> stringArrayListHashMap = ((IRevPluginStartInputForms) object).getIRevPluggableInputFormsServicesViews();
                Iterator iterator = stringArrayListHashMap.keySet().iterator();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    Class value = stringArrayListHashMap.get(key);
                    RevConstantinePluggableViewsServices.REV_PLUGIN_INPUT_FORMS_MAP.put(String.valueOf(key), value);
                }
            }

            if ((object instanceof IRevPluginStartPluginInlineViews)) {
                List<Class> classList = ((IRevPluginStartPluginInlineViews) object).getIRevPluggableInlineViewsServices();
                RevPluggableViewImpl.initRevPluggableInlineViewsMap(classList);
            }

            if ((object instanceof IRevPluginStartRevPersAction)) {
                RevConstantinePluggableViewsServices.REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.putAll(((IRevPluginStartRevPersAction) object).getIRevPersActionServices());
            }

            ((IRevPluginStart) object).revPostStartCalls();
        }

        RevPluggableMenuItemLoader.createRevPluggableLoader();
        RevPluggableOptionsMenuItemsLoader.createRevPluggableLoader();

        REV_OBJECTS_CLASSES_REG.createRevPluggableLoader();
    }

    public static ArrayList REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS(String implName) {
        ArrayList iRevPluggableServiceClassesList = new ArrayList<>();

        for (IRevPluginStart iRevPluginStart : RevConstantinePluggableViewsServices.REV_PLUGIN_START_ARRAYLIST) {
            Map<String, List> iRevPluggableServices = iRevPluginStart.I_REV_PLUGGABLE_VIEW_SSERVICES();
            Iterator iterator = iRevPluggableServices.keySet().iterator();

            while (iterator.hasNext()) {
                Object key = iterator.next();
                if (key.equals(implName)) {
                    iRevPluggableServiceClassesList.addAll(iRevPluggableServices.get(key));
                }
            }
        }

        return iRevPluggableServiceClassesList;
    }

    public static ArrayList I_REV_CUSTOM_PLUGIN_SERVICES_SERVICE_PROVIDERS(String implName) {
        ArrayList iRevPluggableServiceClassesList = new ArrayList<>();

        for (IRevPluginStart iRevPluginStart : RevConstantinePluggableViewsServices.REV_PLUGIN_START_ARRAYLIST) {
            Map<String, List> iRevPluggableServices = iRevPluginStart.I_REV_CUSTOM_PLUGIN_SERVICES();
            Iterator iterator = iRevPluggableServices.keySet().iterator();

            while (iterator.hasNext()) {
                Object key = iterator.next();
                if (key.equals(implName)) {
                    iRevPluggableServiceClassesList.addAll(iRevPluggableServices.get(key));
                }
            }
        }

        return iRevPluggableServiceClassesList;
    }

    public static ArrayList getRevPluggableViewsObjects(RevVarArgsData revVarArgsData) {
        ArrayList iRevPluggableViewsClassesList = new ArrayList();

        for (Object o : REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS(revVarArgsData.getRevViewType())) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            IRevPluggableViews instance;

            try {
                constructor = aClass.getConstructor(RevVarArgsData.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                instance = (IRevPluggableViews) constructor.newInstance(revVarArgsData);
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

    public static ArrayList getICreatePluggableDataClassObjects(String implView) {
        ArrayList iRevPluggableViewsClassesList = new ArrayList();

        for (Object o : REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS(implView)) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            try {
                constructor = aClass.getConstructor(RevVarArgsData.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            ICreatePluggableDataClass instance =
                    null;
            try {
                instance = (ICreatePluggableDataClass) constructor.newInstance(new RevVarArgsData());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            iRevPluggableViewsClassesList.add(instance);
        }

        return iRevPluggableViewsClassesList;
    }

    public static ArrayList getIRevPluggableViewsObjects(RevVarArgsData revVarArgsData) {
        ArrayList iRevPluggableViewsClassesList = new ArrayList();

        for (Object o : REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS(revVarArgsData.getRevViewType())) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            try {
                constructor = aClass.getConstructor(RevVarArgsData.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            IRevPluggableViews instance =
                    null;
            try {
                instance = (IRevPluggableViews) constructor.newInstance(revVarArgsData);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            iRevPluggableViewsClassesList.add(instance);
        }

        return iRevPluggableViewsClassesList;
    }

    public static ArrayList<ICreateRevPluggableOptionsMenu> getICreateRevPluggableOptionsMenuObjects(String implView) {
        ArrayList<ICreateRevPluggableOptionsMenu> iRevPluggableViewsClassesList = new ArrayList<>();

        for (Object o : REV_GET_PLUGGABLE_VIEWS_SERVICE_PROVIDERS(implView)) {
            Class aClass = (Class) o;
            Constructor constructor = null;
            try {
                constructor = aClass.getConstructor(Context.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            ICreateRevPluggableOptionsMenu instance =
                    null;
            try {
                instance = (ICreateRevPluggableOptionsMenu) constructor.newInstance(RevLibGenConstantine.REV_CONTEXT);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            iRevPluggableViewsClassesList.add(instance);
        }

        return iRevPluggableViewsClassesList;
    }

    public static class RevLoadRevExtPluginRunnable implements Runnable {
        private View view;

        public RevLoadRevExtPluginRunnable(View _view) {
            this.view = _view;
        }

        public void run() {
            RevPluginLoader revPluginLoader = new RevPluginLoader(RevLibGenConstantine.REV_CONTEXT);
            revPluginLoader.revLoadView((ViewGroup) view);
        }
    }
}
