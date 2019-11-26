package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.PluggableServicesCalls.RevPluginRegisterKiwiTimelineSubtype;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_actions.RevPublishKiwiAction;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues.RevGenPluggableOptionsMenuVMPublisher;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues.RevKiwiPluggableMenuItem_DORMANT_TAB;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues.RevKiwiPluggableOptionsMenuVMFooterFormPublisher;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues.RevPluggableOptionsKIWIMenuVMTest;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms.RevCreateKiwiInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_views_overrides.KiwiRevCustomObjectListingView;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction, IRevPluginStartInputForms {
    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(KiwiRevCustomObjectListingView.class);

        ArrayList createRevPluggableOptionsMenus = new ArrayList<>();
        createRevPluggableOptionsMenus.add(RevPluggableOptionsKIWIMenuVMTest.class);
        createRevPluggableOptionsMenus.add(RevKiwiPluggableOptionsMenuVMFooterFormPublisher.class);
        createRevPluggableOptionsMenus.add(RevGenPluggableOptionsMenuVMPublisher.class);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevKiwiPluggableMenuItem_DORMANT_TAB.class);

        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);
        stringListHashMap.put("createRevPluggableOptionsMenus", createRevPluggableOptionsMenus);
        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);

        return stringListHashMap;
    }

    @Override
    public Map<String, List> I_REV_CUSTOM_PLUGIN_SERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterKiwiTimelineSubtype.class);

        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateKiwiInputForm", RevCreateKiwiInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishKiwiAction", new RevPublishKiwiAction());

        return stringArrayListHashMap;
    }
}