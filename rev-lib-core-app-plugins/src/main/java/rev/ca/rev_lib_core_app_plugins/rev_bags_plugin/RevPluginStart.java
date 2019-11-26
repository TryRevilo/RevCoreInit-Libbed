package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.PluggableServicesCalls.RevBAGsPluginRegisterTranslationBlock;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.PluggableServicesCalls.RevPluginRegisterRevPluginItemSpace_SP;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.PluggableServicesCalls.RevPluginRegisterTranslationItems_SP;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_actions.RevBagTypeChooserInputFormAction;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_actions.RevPublishBagAction;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items.RevAttachBagPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_areas.RevBAGPluggableOptionsMenuAreaWrapper;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_areas.RevBAGPluggableOptionsMenuContainer;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items.RevCreateNewBagPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items.RevJoinBagPluggableMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items.RevObjectListingOptionsTogglerMenu;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_areas.RevPluggableOptionsMenuItem_BAGS_OPTION_MENUE_CONTAINER;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_areas.RevPluggableOptionsMenuVMTest;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_strip.RevSetStripRevBags;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_forms.RevBagTypeChooserInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_bag_types_forms.RevCreateNewWorkBAGInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_views_overrides.RevBagCustomEntityItemListingView;
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

        ArrayList createRevEntityListingOptionsTogglerMenuView = new ArrayList<>();
        createRevEntityListingOptionsTogglerMenuView.add(RevObjectListingOptionsTogglerMenu.class);

        ArrayList createRevPluggableCustomObjectListingView = new ArrayList<>();
        createRevPluggableCustomObjectListingView.add(RevBagCustomEntityItemListingView.class);

        ArrayList createRevMerryllStripMenuViewItem = new ArrayList<>();
        createRevMerryllStripMenuViewItem.add(RevSetStripRevBags.class);

        ArrayList createRevPluggableOptionsMenus = new ArrayList<>();
        createRevPluggableOptionsMenus.add(RevPluggableOptionsMenuVMTest.class);
        createRevPluggableOptionsMenus.add(RevBAGPluggableOptionsMenuContainer.class);
        createRevPluggableOptionsMenus.add(RevBAGPluggableOptionsMenuAreaWrapper.class);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevJoinBagPluggableMenuItemReg.class);
        createRevPluggableMenuItems.add(RevCreateNewBagPluggableMenuItemReg.class);
        createRevPluggableMenuItems.add(RevAttachBagPluggableMenuItemReg.class);
        createRevPluggableMenuItems.add(RevPluggableOptionsMenuItem_BAGS_OPTION_MENUE_CONTAINER.class);

        List IRevPluginRegisterTranslationBlockList = new ArrayList();
        IRevPluginRegisterTranslationBlockList.add(RevBAGsPluginRegisterTranslationBlock.class);
        stringListHashMap.put("IRevPluginRegisterTranslationBlockList", IRevPluginRegisterTranslationBlockList);

        List IRevPluginRegisterTranslationItems_SP_List = new ArrayList();
        IRevPluginRegisterTranslationItems_SP_List.add(RevPluginRegisterTranslationItems_SP.class);
        stringListHashMap.put("IRevPluginRegisterTranslationItems_SPI", IRevPluginRegisterTranslationItems_SP_List);

        List IRevPluginRegisterRevPluginItem_SPIList = new ArrayList();
        IRevPluginRegisterRevPluginItem_SPIList.add(RevPluginRegisterRevPluginItemSpace_SP.class);
        stringListHashMap.put("IRevPluginRegisterRevPluginItem_SPI", IRevPluginRegisterRevPluginItem_SPIList);

        stringListHashMap.put("createRevEntityListingOptionsTogglerMenuView", createRevEntityListingOptionsTogglerMenuView);
        stringListHashMap.put("createRevPluggableCustomObjectListingView", createRevPluggableCustomObjectListingView);
        stringListHashMap.put("createRevMerryllStripMenuViewItem", createRevMerryllStripMenuViewItem);
        stringListHashMap.put("createRevPluggableOptionsMenus", createRevPluggableOptionsMenus);
        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevBagTypeChooserInputForm", RevBagTypeChooserInputForm.class);
        stringArrayListHashMap.put("RevCreateNewWorkBAGInputForm", RevCreateNewWorkBAGInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevBagTypeChooserInputFormAction", new RevBagTypeChooserInputFormAction());
        stringArrayListHashMap.put("RevPublishBagAction", new RevPublishBagAction());

        return stringArrayListHashMap;
    }
}