package rev.ca.rev_lib_core_app_plugins.rev_members_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_members_plugin.PluggableServicesCalls.RevPluginRegisterMemberTimelineObject;
import rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pluggable_menues.RevMembersSetStrip;
import rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pluggable_menues.menu_item_reg.RevConnectWithMemberPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pluggable_menues.menu_item_reg.RevCreateMemberProfileConnectPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_plugin_views_overrides.RevMemberRevCustomObjectListingView;
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

        ArrayList createRevMerryllStripMenuViewItem = new ArrayList<>();
        createRevMerryllStripMenuViewItem.add(RevMembersSetStrip.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevMemberRevCustomObjectListingView.class);

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterMemberTimelineObject.class);

        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);
        stringListHashMap.put("createRevMerryllStripMenuViewItem", createRevMerryllStripMenuViewItem);
        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevCreateMemberProfileConnectPluggableMenuItemReg.class);
        createRevPluggableMenuItems.add(RevConnectWithMemberPluggableMenuItemReg.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);

        return stringListHashMap;
    }
}