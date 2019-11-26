package rev.ca.rev_lib_core_app_plugins.rev_search_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_search_plugin.rev_plugin_views.rev_pluggable_menus.RevSearchRevEntityPluggableMenuItemReg;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;

/**
 * Created by rev on 10/21/17.
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

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevSearchRevEntityPluggableMenuItemReg.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);

        return stringListHashMap;
    }
}
