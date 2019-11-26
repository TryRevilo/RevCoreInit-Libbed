package rev.ca.rev_lib_core_app_plugins.rev_cab_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.rev_pluggable_menu_area_views.CreateRevMenuAreaViewContainerPublisher;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/11/17.
 */

public class RevCabAddNewFloatingTabViewWidget extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevCabAddNewFloatingTabViewWidget(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        Activity activity = (Activity) mContext;

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public View createPluggableRevMainCenterCctView_LL() {
        LinearLayout activityStreamTabs_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) activityStreamTabs_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.2), 0, 0, 0);

        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_VM_MAP.containsKey("rev_core_gen_publisher_options_menu")) {
            RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_VM_MAP.get("rev_core_gen_publisher_options_menu").setRevEntityGUID(
                    RevPersGenFunctions.GET_REV_OWNER_ENTITY_GUID(revVarArgsData)
            );
        }

        if (new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_core_gen_publisher_options_menu", revVarArgsData) != null)
            activityStreamTabs_LL.addView(new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_core_gen_publisher_options_menu", revVarArgsData));

        CreateRevMenuAreaViewContainerPublisher createRevMenuAreaViewContainerPublisher = new CreateRevMenuAreaViewContainerPublisher(revVarArgsData);

        if (createRevMenuAreaViewContainerPublisher.kiwiActivityStreamListingTab() != null) {
            LinearLayout kiwiActivityStreamListingTabWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            LinearLayout.LayoutParams kiwiActivityStreamListingTabWrapper_LL_LP = (LinearLayout.LayoutParams) kiwiActivityStreamListingTabWrapper_LL.getLayoutParams();
            kiwiActivityStreamListingTabWrapper_LL_LP.setMargins(RevLibGenConstantine.REV_MARGIN_TINY * 2, 0, 0, 0);
            kiwiActivityStreamListingTabWrapper_LL_LP.gravity = (Gravity.TOP);

            kiwiActivityStreamListingTabWrapper_LL.addView(createRevMenuAreaViewContainerPublisher.kiwiActivityStreamListingTab());
            activityStreamTabs_LL.addView(kiwiActivityStreamListingTabWrapper_LL);
        }

        if (createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL() != null)
            activityStreamTabs_LL.addView(createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL());

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        // linearLayout.addView(RevObjectListingOptionsTogglerMenu.getRevObjectListingOptionsTogglerMenu());
        linearLayout.addView(activityStreamTabs_LL);

        revVarArgsData.setRevViewType("RevCreateKiwiInputForm");
        IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
        linearLayout.addView(iRevInputFormView.createRevInputForm());

        return linearLayout;
    }
}
