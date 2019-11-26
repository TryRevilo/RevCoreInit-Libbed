package rev.ca.rev_lib_core_app_plugins.rev_cab_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_cab_plugin.rev_plugin_views.rev_plugin_widget_views.RevFooterMenueTogglerTabs;
import rev.ca.rev_lib_core_app_plugins.rev_cab_plugin.rev_plugin_views.rev_plugin_widget_views.RevNotifications;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createRevPluggableCenterMainContentOverlayFloatingView = new ArrayList<>();
        // createRevPluggableCenterMainContentOverlayFloatingView.add(RevCabAddNewFloatingTabViewWidget.class);

        ArrayList createRevNotificationView = new ArrayList<>();
        createRevNotificationView.add(RevNotifications.class);

        ArrayList createRevPluggableFooterMenueTogglerTab = new ArrayList<>();
        createRevPluggableFooterMenueTogglerTab.add(RevFooterMenueTogglerTabs.class);

        stringListHashMap.put("createPluggableRevMainCenterCctView_LL", createRevPluggableCenterMainContentOverlayFloatingView);
        stringListHashMap.put("createRevNotificationView", createRevNotificationView);
        stringListHashMap.put("createRevPluggableFooterMenueTogglerTab", createRevPluggableFooterMenueTogglerTab);

        return stringListHashMap;
    }
}
