package rev.ca.rev_lib_core_app_plugins.rev_settings_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_pluggable_menus.CreatePluggableSettingsTopBarMenuViewItems;
import rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_pluggable_menus.RevEntitySettingsPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_pluggable_menus.rev_reg_menu_areas.RevMainUserSettingsPluggableMenuWrapper;
import rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_registered_inline_views.RegisterRevSettingsPluggableInlineViews;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;

/**
 * Created by rev on 10/19/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartPluginInlineViews {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createPluggableTopBarMenuViewItem = new ArrayList<>();
        createPluggableTopBarMenuViewItem.add(CreatePluggableSettingsTopBarMenuViewItems.class);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevEntitySettingsPluggableMenuItemReg.class);

        ArrayList createRevPluggableMainUserSettingsMenuAreas = new ArrayList<>();
        createRevPluggableMainUserSettingsMenuAreas.add(RevMainUserSettingsPluggableMenuWrapper.class);

        stringListHashMap.put("createPluggableTopBarMenuViewItem", createPluggableTopBarMenuViewItem);
        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);
        stringListHashMap.put("createRevPluggableOptionsMenus", createRevPluggableMainUserSettingsMenuAreas);

        return stringListHashMap;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevSettingsPluggableInlineViews.class);

        return createRevPluggableInlineView;
    }
}