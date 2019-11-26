package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_object;

import android.content.Context;
import android.widget.LinearLayout;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_widget_views.RevObjectInfoDetailsWidget;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevObjectListingOptionsTogglerMenu;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/23/17.
 */

public class RevBagsRevObjectFullProfileView {

    private Context mContext;
    private RevEntity revEntity;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevBagsRevObjectFullProfileView(Context mContext, RevEntity revEntity) {
        this.mContext = mContext;
        this.revEntity = new RevPersLibRead().revPersGetRevEntityByGUID(revEntity.get_revEntityGUID());

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public LinearLayout getRevEntityMainCenterCctViewLL() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        linearLayout.addView(RevObjectListingOptionsTogglerMenu.getRevObjectListingOptionsTogglerMenu());
        linearLayout.addView(new RevObjectInfoDetailsWidget(mContext, revEntity).getRevEntityDetailsWidget());

        return linearLayout;
    }
}
