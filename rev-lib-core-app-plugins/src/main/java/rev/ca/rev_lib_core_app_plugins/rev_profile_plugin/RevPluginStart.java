package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.PluggableServicesCalls.RevPluginRegisterProfilePicsTimelineObject;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_actions.RevPublishProfileSocialCircle;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_actions.RevPublishRevEntityInfoAction;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_actions.RevResetUserProfileIconAction;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.RevSetRevProfileStrip;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevPluggableOptionsMenuItem_USER_OPTION_MENUE_CONTAINER;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevProfileContentFilterPluggableFloatingOptionsMenuWrapper;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevProfileContentFilterPluggableFloatingOptionsPublisherMenuContainer;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevProfileContentPluggableFloatingOptionsMenuWrapper;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevProfileContentPluggableFloatingOptionsPopUpAreaMenuWrapper;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevUserPluggableOptionsMenuAreaWrapper;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas.RevUserPluggableOptionsMenuContainer;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item.CreatePluggableTopBarMenuViewItemsHome;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item.CreatePluggableTopBarMenuViewItemsLibrary;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item.RevPluggableOptionsMenuItem_LIST_PROFILE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item.RevUserProfileInfoSettingsMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.RevCreateNewObjectRevProfilePicsForm;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.RevCreateRevEditInfoFormObject;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.RevProfileTypeChooserInputForm_ACADEMIC;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.RevProfileTypeChooserInputForm_FAMILY;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_profile_types.rev_academic_profile.RevCreateNewClassProfileInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_profile_types.rev_family_profile.RevCreateInputFormRevProfileParents;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_profile_types.rev_social_profile.RevCreateInputFormRevProfileSocialCircle;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_views_overrides.RevProfilePicsTimelineCustomObjectListingView;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_registered_inline_views.RegisterRevPluggableInlineViews;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_registered_inline_views.RegisterRevPluggableInlineViewsRevProfile;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;

/**
 * Created by rev on 10/11/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartPluginInlineViews, IRevPluginStartInputForms, IRevPluginStartRevPersAction {

    Context mContext;

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createPluggableTopBarMenuViewItem = new ArrayList<>();
        createPluggableTopBarMenuViewItem.add(CreatePluggableTopBarMenuViewItemsLibrary.class);
        createPluggableTopBarMenuViewItem.add(CreatePluggableTopBarMenuViewItemsHome.class);

        ArrayList createRevMerryllStripMenuViewItem = new ArrayList<>();
        createRevMerryllStripMenuViewItem.add(RevSetRevProfileStrip.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevProfilePicsTimelineCustomObjectListingView.class);

        List iRevPluginStartRegisterTimelineObjectList = new ArrayList();
        iRevPluginStartRegisterTimelineObjectList.add(RevPluginRegisterProfilePicsTimelineObject.class);

        ArrayList createRevPluggableOptionsMenus = new ArrayList<>();
        createRevPluggableOptionsMenus.add(RevUserPluggableOptionsMenuAreaWrapper.class);
        createRevPluggableOptionsMenus.add(RevUserPluggableOptionsMenuContainer.class);
        createRevPluggableOptionsMenus.add(RevProfileContentFilterPluggableFloatingOptionsMenuWrapper.class);
        createRevPluggableOptionsMenus.add(RevProfileContentPluggableFloatingOptionsMenuWrapper.class);
        createRevPluggableOptionsMenus.add(RevProfileContentFilterPluggableFloatingOptionsPublisherMenuContainer.class);

        stringListHashMap.put("createPluggableTopBarMenuViewItem", createPluggableTopBarMenuViewItem);
        stringListHashMap.put("createRevMerryllStripMenuViewItem", createRevMerryllStripMenuViewItem);
        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);
        stringListHashMap.put("createRevPluggableOptionsMenus", createRevPluggableOptionsMenus);

        stringListHashMap.put("getRegisteredTimelineEntityType", iRevPluginStartRegisterTimelineObjectList);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_USER_OPTION_MENUE_CONTAINER.class);
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_LIST_PROFILE_CONTENT.class);
        createRevPluggableMenuItems.add(RevUserProfileInfoSettingsMenuItemReg.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevProfileTypeChooserInputForm_ACADEMIC", RevProfileTypeChooserInputForm_ACADEMIC.class);
        stringArrayListHashMap.put("RevProfileTypeChooserInputForm_FAMILY", RevProfileTypeChooserInputForm_FAMILY.class);

        stringArrayListHashMap.put("RevCreateRevEditInfoFormObject", RevCreateRevEditInfoFormObject.class);

        stringArrayListHashMap.put("RevCreateNewObjectRevProfilePicsForm", RevCreateNewObjectRevProfilePicsForm.class);
        stringArrayListHashMap.put("RevCreateNewClassProfileInputForm", RevCreateNewClassProfileInputForm.class);
        stringArrayListHashMap.put("RevCreateInputFormRevProfileParents", RevCreateInputFormRevProfileParents.class);
        stringArrayListHashMap.put("RevCreateInputFormRevProfileSocialCircle", RevCreateInputFormRevProfileSocialCircle.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevPublishProfileSocialCircleAction", new RevPublishProfileSocialCircle());
        stringArrayListHashMap.put("RevPublishRevEntityInfoAction", new RevPublishRevEntityInfoAction());
        stringArrayListHashMap.put("RevResetUserProfileIconAction", new RevResetUserProfileIconAction());

        return stringArrayListHashMap;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViews.class);
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViewsRevProfile.class);

        return createRevPluggableInlineView;
    }
}