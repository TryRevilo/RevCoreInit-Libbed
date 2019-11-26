package rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_objects_listings.RevCommentsListingView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/1/17.
 */

public class CommentsRevCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevDimens revDimens;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public CommentsRevCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revDimens = new RevDimens(mContext);

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_comment";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(RevEntity revEntity) {
        if (revEntity == null) {
            return null;
        }

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_comment");
        revEntityListingViewOverrideVOM.setRevEntity(revEntity);

        if (revEntity.get_revEntityMetadataList() == null || revEntity.get_revEntityMetadataList().isEmpty())
            return null;

        revEntityListingViewOverrideVOM.setOverrideView(new RevCommentsListingView(revVarArgsData).getRevCommentsListingView(revEntity));

        return revEntityListingViewOverrideVOM;
    }


    @Override
    public View createRevObjectListingFooterOptionView() {
        LinearLayout revObjectListingFooterOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        RevObjectListingViewFooterTabs revObjectListingViewFooterTabs = new RevObjectListingViewFooterTabs(revVarArgsData);

        LinearLayout.LayoutParams revObjectListingFooterOptionsWrapper_LL_LP = (LinearLayout.LayoutParams) revObjectListingFooterOptionsWrapper_LL.getLayoutParams();
        revObjectListingFooterOptionsWrapper_LL_LP.setMargins(0, revDimens.getRevMarginExtraSmall(), 0, 0);

        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revLikeItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revCommentItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revMoreOptionsItem());
        return revObjectListingFooterOptionsWrapper_LL;
    }
}
