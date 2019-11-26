package rev.ca.rev_lib_core_views.rev_pluggable_views_impl;

import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_views.IRevPluginStart;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

/**
 * Created by rev on 12/18/17.
 */

public class RevConstantinePluggableViewsServices {

    public static ArrayList<IRevPluginStart> REV_PLUGIN_START_ARRAYLIST;

    public static Map<String, Class> REV_PLUGIN_INPUT_FORMS_MAP = null;
    public static Map<String, IRevPersAction> REV_PLUGIN_START_REV_PERS_ACTIONS_MAP;

    public static Map<String, RevEntityListingViewOverrideVOM> stringIOverrideRevEntityListingViewMap;

    // Menus
    public static Map<String, RevPluggableOptionsMenuVM> REV_PLUGGABLE_OPTIONS_MENU_VM_MAP;
    public static Map<String, List<ICreateRevPluggableMenuItem>> REV_PLUGGABLE_OPTIONS_MENU_ITEMS_MAP;
    public static Map<String, Class> REV_PLUGGABLE_MENU_ITEMS_MAP;

    // Inline Views
    public static Map<String, View> ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS;
    public static Map<String, RevPluggableInlineView> ALL_PLUGGABLE_REV_INLINE_VIEWS;
    public static Map<String, List<RevPluggableInlineView>> PLUGGABLE_REV_INLINE_VIEWS;
    public static Map<String, List<RevPluggableInlineView>> PLUGGABLE_REV_INLINE_VIEWS_BEFORE;
    public static Map<String, List<RevPluggableInlineView>> PLUGGABLE_REV_INLINE_VIEWS_AFTER;

    public static void initRevConstantinePluggableViewsServices() {
        REV_PLUGIN_START_ARRAYLIST = new ArrayList<>();
        REV_PLUGIN_START_REV_PERS_ACTIONS_MAP = new HashMap<>();

        ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS = new HashMap<>();
        ALL_PLUGGABLE_REV_INLINE_VIEWS = new HashMap<>();
        PLUGGABLE_REV_INLINE_VIEWS = new HashMap<>();
        PLUGGABLE_REV_INLINE_VIEWS_BEFORE = new HashMap<>();
        PLUGGABLE_REV_INLINE_VIEWS_AFTER = new HashMap<>();

        REV_PLUGIN_INPUT_FORMS_MAP = new HashMap<>();
        stringIOverrideRevEntityListingViewMap = new HashMap<>();

        // Menus
        REV_PLUGGABLE_OPTIONS_MENU_VM_MAP = new HashMap<>();
        REV_PLUGGABLE_OPTIONS_MENU_ITEMS_MAP = new HashMap<>();
        REV_PLUGGABLE_MENU_ITEMS_MAP = new HashMap<>();
    }

    public static Object revViewCreator(String viewName) {
        if (!REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey(viewName))
            return null;

        Constructor constructor;
        try {
            constructor = REV_PLUGGABLE_MENU_ITEMS_MAP.get(viewName).getConstructor(RevVarArgsData.class);
            return constructor.newInstance(RevLibGenConstantine.REV_CONTEXT);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(RevVarArgsData revVarArgsData) {
        String viewName = revVarArgsData.getRevViewType();

        Constructor constructor;
        try {
            constructor = REV_PLUGGABLE_MENU_ITEMS_MAP.get(viewName).getConstructor(RevVarArgsData.class);
            return constructor.newInstance(revVarArgsData);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(RevVarArgsData revVarArgsData) {
        String viewName = revVarArgsData.getRevViewType();

        if (!REV_PLUGIN_INPUT_FORMS_MAP.containsKey(viewName))
            return null;

        Constructor constructor;
        try {
            constructor = (REV_PLUGIN_INPUT_FORMS_MAP.get(viewName)).getConstructor(RevVarArgsData.class);
            return constructor.newInstance(revVarArgsData);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}