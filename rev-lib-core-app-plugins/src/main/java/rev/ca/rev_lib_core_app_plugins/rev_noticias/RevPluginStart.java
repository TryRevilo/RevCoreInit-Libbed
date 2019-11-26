package rev.ca.rev_lib_core_app_plugins.rev_noticias;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_actions.RevCreateNoticiasEntityAction;
import rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_actions.rev_pre_actions.RevNoticiasPostActions;
import rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_plugin_views.rev_pluggable_menues.CreatePluggableTopBarMenuViewItemsNoticias;
import rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_plugin_views.rev_plugin_widget_views.RegisterRevPluggableInlineViewsRevNoticias;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPostRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPrePostRevPersActionCollections;

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction, IPrePostRevPersActionCollections, IRevPluginStartPluginInlineViews {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public List<IPostRevPersAction> I_POST_REV_PERS_ACTION_LIST() {
        List<IPostRevPersAction> iPostRevPersActionList = new ArrayList<>();
        iPostRevPersActionList.add(new RevNoticiasPostActions());
        iPostRevPersActionList.add(new CreatePluggableTopBarMenuViewItemsNoticias(new RevVarArgsData(RevLibGenConstantine.REV_CONTEXT)));

        return iPostRevPersActionList;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNoticiasEntityAction", new RevCreateNoticiasEntityAction());

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createPluggableTopBarMenuViewItem = new ArrayList<>();
        createPluggableTopBarMenuViewItem.add(CreatePluggableTopBarMenuViewItemsNoticias.class);

        stringListHashMap.put("createPluggableTopBarMenuViewItem", createPluggableTopBarMenuViewItem);

        return stringListHashMap;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViewsRevNoticias.class);

        return createRevPluggableInlineView;

    }
}