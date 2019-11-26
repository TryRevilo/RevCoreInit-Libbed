package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.PluggableServicesCalls.RevPluginRegisterPicsTimelineSubtype;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_actions.RevPublishPicsAlbumAction;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus.RevDirectAudioFileSelectPluggableMenuItem;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus.RevDirectDocumentFileSelectPluggableMenuItem;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus.RevDirectPicSelectPluggableMenuItem;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus.RevDirectVideoFileSelectPluggableMenuItem;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus.RevPhotoAlbumPluggableMenuItem;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_forms.RevCreateNewObjectAlbumForm;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_views_overrides.RevPhotoAlbumCustomObjectListingView;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartInputForms, IRevPluginStartRevPersAction {
    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevPhotoAlbumPluggableMenuItem.class);
        createRevPluggableMenuItems.add(RevDirectPicSelectPluggableMenuItem.class);
        createRevPluggableMenuItems.add(RevDirectAudioFileSelectPluggableMenuItem.class);
        createRevPluggableMenuItems.add(RevDirectVideoFileSelectPluggableMenuItem.class);
        createRevPluggableMenuItems.add(RevDirectDocumentFileSelectPluggableMenuItem.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevPhotoAlbumCustomObjectListingView.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);
        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);

        return stringListHashMap;
    }

    @Override
    public Map<String, List> I_REV_CUSTOM_PLUGIN_SERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterPicsTimelineSubtype.class);

        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNewObjectAlbumForm", RevCreateNewObjectAlbumForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishPicsAlbumAction", new RevPublishPicsAlbumAction());

        return stringArrayListHashMap;
    }
}