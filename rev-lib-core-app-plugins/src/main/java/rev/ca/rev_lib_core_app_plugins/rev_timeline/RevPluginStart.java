package rev.ca.rev_lib_core_app_plugins.rev_timeline;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPostRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPrePostRevPersActionCollections;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.I_POST_REV_SERVER_DATA_SYNC;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_actions.RevPublishRevTimelineEntityAction;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_actions.rev_pre_actions.RevTimelinePostActions;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.RevTimelineAbsPOST_REV_SERVER_DATA_SYNC;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_plugin_views.rev_plugin_pages.RevTimelinePage;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_plugin_views.rev_plugin_widget_views.RegisterRevPluggableInlineViewsRevTimeline;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLangStrings;

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction, IPrePostRevPersActionCollections, IRevPluginStartPluginInlineViews {

    private Context mContext;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    public RevPluginStart(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringArrayListHashMap = new HashMap<>();

        List createPluggable_REV_OBJECTS_CLASSES_REG_ArrayList = new ArrayList();
        createPluggable_REV_OBJECTS_CLASSES_REG_ArrayList.add(RevTimelinePage.class);

        List createPluggableRevMainCenterCctView_LL = new ArrayList();
        createPluggableRevMainCenterCctView_LL.add(RevTimelinePage.class);

        stringArrayListHashMap.put("ICreatePluggableDataClass", createPluggable_REV_OBJECTS_CLASSES_REG_ArrayList);
        stringArrayListHashMap.put("createPluggableRevMainCenterCctView_LL", createPluggableRevMainCenterCctView_LL);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishRevTimelineEntityAction", new RevPublishRevTimelineEntityAction());

        return stringArrayListHashMap;
    }

    @Override
    public List<IPostRevPersAction> I_POST_REV_PERS_ACTION_LIST() {
        List<IPostRevPersAction> iPostRevPersActionList = new ArrayList<>();
        iPostRevPersActionList.add(new RevTimelinePostActions());

        return iPostRevPersActionList;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViewsRevTimeline.class);

        return createRevPluggableInlineView;
    }


    @Override
    public List<I_POST_REV_SERVER_DATA_SYNC> I_PRE_REV_SERVER_DATA_SYNC_LIST() {
        List<I_POST_REV_SERVER_DATA_SYNC> iPOSTREVSERVERDATASYNCArrayList = new ArrayList<>();

        iPOSTREVSERVERDATASYNCArrayList.add(new RevTimelineAbsPOST_REV_SERVER_DATA_SYNC());

        return iPOSTREVSERVERDATASYNCArrayList;
    }

    @Override
    public void revPostStartCalls() {
        super.revPostStartCalls();

        if (!REV_SESSION_SETTINGS.isIsNotLoggedIn()) {
            long revLoggedInEntityTimelineGUID = revPersLibRead.revEntitySubtypeExists_BY_CONTAINER_GUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_timeline");
            if (revLoggedInEntityTimelineGUID < 0) {
                revLoggedInEntityTimelineGUID = (Long) RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishRevTimelineEntityAction").initRevAction(revPersLibRead.revPersGetRevEntityByGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid()));
            }
            Log.v(RevLangStrings.REV_TAG, "revLoggedInEntityTimelineGUID : " + revLoggedInEntityTimelineGUID);
        }
    }
}