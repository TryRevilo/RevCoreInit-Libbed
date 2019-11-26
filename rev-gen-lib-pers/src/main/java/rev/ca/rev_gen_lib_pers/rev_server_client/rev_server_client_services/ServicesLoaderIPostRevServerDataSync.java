package rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.I_POST_REV_SERVER_DATA_SYNC;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.IPrePostRevRevServerDataSyncCollections;
import rev.ca.rev_plugin_loader.RevPluginsObjectsRegistry;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPrePostRevPersActionCollections;

public class ServicesLoaderIPostRevServerDataSync {

    public List<I_POST_REV_SERVER_DATA_SYNC> initRevPluginsIPostRevServerDataSyncRegistry() {
        List<I_POST_REV_SERVER_DATA_SYNC> iPOSTREVSERVERDATASYNCS = new ArrayList<>();

        for (Object object : RevPluginsObjectsRegistry.getRevPluginsObjectsRegistry()) {
            if ((object instanceof IPrePostRevPersActionCollections)) {
                for (I_POST_REV_SERVER_DATA_SYNC iPOSTREVSERVERDATASYNC : ((IPrePostRevRevServerDataSyncCollections) object).I_PRE_REV_SERVER_DATA_SYNC_LIST()) {
                    if (!iPOSTREVSERVERDATASYNCS.contains(iPOSTREVSERVERDATASYNC)) {
                        iPOSTREVSERVERDATASYNCS.add(iPOSTREVSERVERDATASYNC);
                    }
                }
            }
        }

        return iPOSTREVSERVERDATASYNCS;
    }
}
