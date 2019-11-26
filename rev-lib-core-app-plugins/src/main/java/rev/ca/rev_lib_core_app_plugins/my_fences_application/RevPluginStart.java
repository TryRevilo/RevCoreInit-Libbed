package rev.ca.rev_lib_core_app_plugins.my_fences_application;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_actions.RevCreateNewUserRegAction;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_actions.RevCreateUserLoginAction;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_actions.rev_post_actions.RevCreateNewUserPostActions;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms.RevCreateNewUserRegForm;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms.RevCreateUserLoginForm;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.RevFencesLoggedIn;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.RevLoggedOutFullscreen;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_pluggable_menues.CreatePluggableTopBarMenuViewItems;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPostRevPersAction;
import rev.ca.revlibpersistence.rev_persistence.rev_services.IRevConstantine;
import rev.ca.revlibpersistence.rev_persistence.rev_services.RevPersServicesRevConstantine;

/**
 * Created by rev on 10/31/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartRevPersAction, IRevPluginStartInputForms, IRevConstantine {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createPluggableTopBarMenuViewItem = new ArrayList<>();
        createPluggableTopBarMenuViewItem.add(CreatePluggableTopBarMenuViewItems.class);

        ArrayList createPluggableRevMainCenterCctView_LL = new ArrayList<>();
        createPluggableRevMainCenterCctView_LL.add(RevLoggedOutFullscreen.class);
        createPluggableRevMainCenterCctView_LL.add(RevFencesLoggedIn.class);

        stringListHashMap.put("createPluggableTopBarMenuViewItem", createPluggableTopBarMenuViewItem);
        stringListHashMap.put("createPluggableRevMainCenterCctView_LL", createPluggableRevMainCenterCctView_LL);

        return stringListHashMap;
    }

    @Override
    public List<IPostRevPersAction> I_POST_REV_PERS_ACTION_LIST() {
        List<IPostRevPersAction> iPostRevPersActionList = new ArrayList<>();
        iPostRevPersActionList.add(new RevCreateNewUserPostActions());

        return iPostRevPersActionList;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNewUserRegForm", RevCreateNewUserRegForm.class);
        stringArrayListHashMap.put("RevCreateUserLoginForm", RevCreateUserLoginForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNewUserRegAction", new RevCreateNewUserRegAction());
        stringArrayListHashMap.put("RevCreateUserLoginAction", new RevCreateUserLoginAction());

        return stringArrayListHashMap;
    }

    @Override
    public ArrayList<RevPersServicesRevConstantine> createRevConstantine() {
        ArrayList<RevPersServicesRevConstantine> revPersServicesRevConstantineArrayList = new ArrayList<>();

        RevPersServicesRevConstantine revPersServicesRevConstantine = new RevPersServicesRevConstantine();
        revPersServicesRevConstantine.setRevConstantineName("name");

        TextView textView = new TextView(mContext);
        textView.setText("TEXT");
        revPersServicesRevConstantine.setRevConstantineObject(textView);

        revPersServicesRevConstantineArrayList.add(revPersServicesRevConstantine);
        return revPersServicesRevConstantineArrayList;
    }
}
