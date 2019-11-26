package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.rev_pluggable_menu_area_views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/14/17.
 */

public class CreateRevMenuAreaViewContainerPublisher {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public CreateRevMenuAreaViewContainerPublisher(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        Activity activity = (Activity) mContext;

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public View createPluggableRevMainCenterCctView_LL() {
        LinearLayout activityStreamTabs_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        if (this.kiwiActivityStreamListingTab() != null)
            activityStreamTabs_LL.addView(this.kiwiActivityStreamListingTab());

        if (this.genPublisherWrapper_LL() != null)
            activityStreamTabs_LL.addView(this.genPublisherWrapper_LL());

        activityStreamTabs_LL.addView(this.attachBagPublisherWrapper_LL());

        if (this.moreOptionsWrapper_LL() != null)
            activityStreamTabs_LL.addView(this.moreOptionsWrapper_LL());

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.addView(activityStreamTabs_LL);

        revVarArgsData.setRevViewType("RevCreateKiwiInputForm");
        IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
        linearLayout.addView(iRevInputFormView.createRevInputForm());

        return linearLayout;
    }

    public View genPublisherWrapper_LL() {
        return new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_core_gen_publisher_options_menu", revVarArgsData);
    }

    public View newBagPublisherWrapper_LL() {
        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("create_new_bag_menu_item")) {
            revVarArgsData.setRevViewType("create_new_bag_menu_item");
            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(revVarArgsData);
            return iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();
        }

        return null;
    }

    public View attachBagPublisherWrapper_LL() {
        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("attach_bags_menu_item")) {
            revVarArgsData.setRevViewType("attach_bags_menu_item");
            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(revVarArgsData);
            return iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();
        }

        return null;
    }

    public View moreOptionsWrapper_LL() {
        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_VM_MAP.containsKey("test_options_menu"))
            return new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("test_options_menu", revVarArgsData);

        return null;
    }

    public View kiwiActivityStreamListingTab() {
        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("kiwi_dormant_tab")) {
            RevVarArgsData revVarArgsData = new RevVarArgsData();
            revVarArgsData.setRevViewType("kiwi_dormant_tab");
            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(revVarArgsData);
            return iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();
        }

        return null;
    }
}
