package rev.ca.rev_lib_core_views;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.I_POST_REV_SERVER_DATA_SYNC;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.IPrePostRevRevServerDataSyncCollections;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPostRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPrePostRevPersActionCollections;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPreRevPersAction;

/**
 * Created by rev on 10/24/17.
 */

public abstract class AbstractIRevPluginStart implements IRevPluginStart, IPrePostRevPersActionCollections, IPrePostRevRevServerDataSyncCollections {

    private final Context mContext;

    public AbstractIRevPluginStart(Context mContext) {
        this.mContext = mContext;
    }

    protected Context getmContext() {
        return this.mContext;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();
        return stringListHashMap;
    }

    @Override
    public Map<String, List> I_REV_CUSTOM_PLUGIN_SERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();
        return stringListHashMap;
    }

    @Override
    public List<IPreRevPersAction> I_PRE_REV_PERS_ACTION_LIST() {
        List<IPreRevPersAction> iPreRevPersActionList = new ArrayList();
        return iPreRevPersActionList;
    }

    @Override
    public List<IPostRevPersAction> I_POST_REV_PERS_ACTION_LIST() {
        List<IPostRevPersAction> iPostRevPersActionList = new ArrayList<>();
        return iPostRevPersActionList;
    }

    @Override
    public List<I_POST_REV_SERVER_DATA_SYNC> I_PRE_REV_SERVER_DATA_SYNC_LIST() {
        List<I_POST_REV_SERVER_DATA_SYNC> iPOSTREVSERVERDATASYNCArrayList = new ArrayList<>();
        return iPOSTREVSERVERDATASYNCArrayList;
    }

    @Override
    public void revPostStartCalls() {}
}
