package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_actions.RevCreateNewRevPluginAction;
import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services.RevPersSavePluginItemObjects;
import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_plugin_views.rev_pluggable_menus.CreatePluggableTopBarMenuViewItems_PLUGINS;
import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_plugin_views.rev_pluggable_menus.RevEntityPluginsPluggableMenuItemReg;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;

/**
 * Created by rev on 10/19/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createPluggableTopBarMenuViewItem = new ArrayList<>();
        createPluggableTopBarMenuViewItem.add(CreatePluggableTopBarMenuViewItems_PLUGINS.class);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevEntityPluginsPluggableMenuItemReg.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);
        stringListHashMap.put("createPluggableTopBarMenuViewItem", createPluggableTopBarMenuViewItem);

        return stringListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNewRevPluginAction", new RevCreateNewRevPluginAction());

        return stringArrayListHashMap;
    }

    @Override
    public void revPostStartCalls() {
        // new RevPersSavePluginItemObjects().revSavePlugins();
    }
}