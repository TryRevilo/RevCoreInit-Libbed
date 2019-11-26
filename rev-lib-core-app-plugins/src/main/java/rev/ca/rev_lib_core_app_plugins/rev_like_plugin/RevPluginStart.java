package rev.ca.rev_lib_core_app_plugins.rev_like_plugin;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_like_plugin.rev_actions.RevPublishLikeAction;
import rev.ca.rev_lib_core_app_plugins.rev_like_plugin.rev_plugin_views.rev_plugin_forms.RevCreateLikeInputForm;
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
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateLikeInputForm", RevCreateLikeInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishLikeAction", new RevPublishLikeAction());

        return stringArrayListHashMap;
    }
}