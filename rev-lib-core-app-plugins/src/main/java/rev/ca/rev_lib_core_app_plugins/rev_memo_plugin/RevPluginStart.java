package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_actions.RevPublishMemoAction;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_pluggable_services_calls.RevPluginRegisterMemoNoticiasObject;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_pluggable_services_calls.RevPluginRegisterMemoTimelineObject;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues.RevCreateNewMemoTitleBarPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues.RevPluggableOptionsMenuItem_LIST_PROFILE_MEMO;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues.RevPluggableOptionsMenuItem_PUBLISH_MEMO;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_forms.RevCreateMemoInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views.RegisterRevPluggableInlineViewsRevMemos;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views_overrides.RevMemoRevCustomObjectListingView;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartInputForms, IRevPluginStartRevPersAction, IRevPluginStartPluginInlineViews {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_PUBLISH_MEMO.class);
        createRevPluggableMenuItems.add(RevCreateNewMemoTitleBarPluggableMenuItemReg.class);
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_LIST_PROFILE_MEMO.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevMemoRevCustomObjectListingView.class);

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterMemoTimelineObject.class);

        List iRevPluginStartRegisterNoticiasObjectList = new ArrayList();
        iRevPluginStartRegisterNoticiasObjectList.add(RevPluginRegisterMemoNoticiasObject.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);
        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);

        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        stringListHashMap.put("getRegisteredNoticiasEntityType", iRevPluginStartRegisterNoticiasObjectList);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateMemoInputForm", RevCreateMemoInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishMemoAction", new RevPublishMemoAction());

        return stringArrayListHashMap;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViewsRevMemos.class);

        return createRevPluggableInlineView;
    }
}