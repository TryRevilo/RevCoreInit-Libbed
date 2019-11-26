package rev.ca.rev_lib_core_app_plugins.rev_mail_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_mail_plugin.rev_plugin_views.rev_pluggable_menus.RevSetStripRevMail;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;

/**
 * Created by rev on 11/5/17.
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
        createRevMerryllStripMenuViewItem.add(RevSetStripRevMail.class);

        stringListHashMap.put("createRevMerryllStripMenuViewItem", createRevMerryllStripMenuViewItem);

        return stringListHashMap;
    }
}
