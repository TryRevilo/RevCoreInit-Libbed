package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.default_rev_entities_listing_view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevNetworkResolverViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevViewOverrides;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 11/30/17.
 */

public class RevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevEntity revEntity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevEntityListingView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public View createDefaultRevEntityListingView() {
        if (RevViewOverrides.overrideItemPOMMap().containsKey(revEntity.get_revEntitySubType())) {
            LinearLayout itemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

            LinearLayout.LayoutParams itemsContainer_LL_LP = (LinearLayout.LayoutParams) itemsContainer_LL.getLayoutParams();
            itemsContainer_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

            LinearLayout revObjectListingViewCustomViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout();
            LinearLayout.LayoutParams revObjectListingViewCustomViewContainer_LL_LP = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );

            revVarArgsData.setRevViewType("createRevPluggableCustomObjectListingView");
            IOverrideRevEntityListingView iRevPluggableViews = RevViewOverrides.REV_GET_IOverrideRevEntityListingView(revVarArgsData);
            RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = iRevPluggableViews.create_OVERRIDE_ITEM(revEntity);

            if (revEntityListingViewOverrideVOM == null) {
                itemsContainer_LL.addView(new RevNetworkResolverViews(mContext).getNoRevNullEntityNetworkResolverView());
                return itemsContainer_LL;
            }

            View overrideView = revEntityListingViewOverrideVOM.getOverrideView();

            if (overrideView == null) return null;

            if (revEntityListingViewOverrideVOM.getRevVarArgsData().getRevVarArgsDataMetadataStrings().containsKey("isDecorated")) {
                return revEntityListingViewOverrideVOM.getOverrideView();
            }

            revObjectListingViewCustomViewContainer_LL.setBackgroundDrawable(mContext.getResources().getDrawable(rev.ca.revlibviews.R.drawable.rev_object_listing_container_border));
            revObjectListingViewCustomViewContainer_LL.setLayoutParams(revObjectListingViewCustomViewContainer_LL_LP);

            revObjectListingViewCustomViewContainer_LL.addView(overrideView);

            itemsContainer_LL.addView(revObjectListingViewCustomViewContainer_LL);

            return itemsContainer_LL;
        }

        return null;
    }
}