package rev.ca.rev_lib_core_app_plugins.rev_comments_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_actions.RevPublishCommentAction;
import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_pluggable_views.RegisterRevPluggableInlineViewsRevCommentsFooterArea;
import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_forms.RevCreateRevCommentsInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_views_overrides.CommentsRevCustomObjectListingView;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction, IRevPluginStartInputForms, IRevPluginStartPluginInlineViews {
    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(CommentsRevCustomObjectListingView.class);

        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateRevCommentsInputForm", RevCreateRevCommentsInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishCommentAction", new RevPublishCommentAction());

        return stringArrayListHashMap;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViewsRevCommentsFooterArea.class);

        return createRevPluggableInlineView;
    }
}