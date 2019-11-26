package rev.ca.rev_lib_core_app_plugins.rev_appointment_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_appointment_plugin.PluggableServicesCalls.RevPluginRegisterAppointmentTimelineObject;
import rev.ca.rev_lib_core_app_plugins.rev_appointment_plugin.rev_actions.RevPublishAppointmentAction;
import rev.ca.rev_lib_core_app_plugins.rev_appointment_plugin.rev_plugin_views.rev_plugin_views_overrides.RevAppointmentRevCustomObjectListingView;
import rev.ca.rev_lib_core_app_plugins.rev_appointment_plugin.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues.RevPluggableOptionsMenuItem_PUBLISH_APPOINTMENT;
import rev.ca.rev_lib_core_app_plugins.rev_appointment_plugin.rev_plugin_views.rev_plugin_widget_views.rev_plugin_forms.RevCreateAppointmentInputForm;
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

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_PUBLISH_APPOINTMENT.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevAppointmentRevCustomObjectListingView.class);

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterAppointmentTimelineObject.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);
        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);

        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateAppointmentInputForm", RevCreateAppointmentInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishAppointmentAction", new RevPublishAppointmentAction());

        return stringArrayListHashMap;
    }
}