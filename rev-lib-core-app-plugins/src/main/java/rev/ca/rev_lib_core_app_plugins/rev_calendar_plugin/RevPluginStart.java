package rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.PluggableServicesCalls.RevPluginRegisterCalendarTimelineObject;
import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_actions.RevPublishCalendarAction;
import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_views_overrides.RevCalendarRevCustomObjectListingView;
import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues.RevPluggableOptionsMenuItem_PUBLISH_CALENDAR;
import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues.RevSetStripRevCalendar;
import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_plugin_forms.RevCreateCalendarInputForm;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartInputForms, IRevPluginStartRevPersAction {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createRevMerryllStripMenuViewItem = new ArrayList<>();
        createRevMerryllStripMenuViewItem.add(RevSetStripRevCalendar.class);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_PUBLISH_CALENDAR.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevCalendarRevCustomObjectListingView.class);

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterCalendarTimelineObject.class);

        stringListHashMap.put("createRevMerryllStripMenuViewItem", createRevMerryllStripMenuViewItem);
        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);
        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);

        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateCalendarInputForm", RevCreateCalendarInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishCalendarAction", new RevPublishCalendarAction());

        return stringArrayListHashMap;
    }
}