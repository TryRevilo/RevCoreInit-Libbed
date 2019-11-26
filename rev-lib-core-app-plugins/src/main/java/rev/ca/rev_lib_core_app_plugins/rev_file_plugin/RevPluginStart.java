package rev.ca.rev_lib_core_app_plugins.rev_file_plugin;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_actions.RevPublishFileAction;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;

/**
 * Created by rev on 1/30/19.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishFileAction", new RevPublishFileAction());

        return stringArrayListHashMap;
    }
}
