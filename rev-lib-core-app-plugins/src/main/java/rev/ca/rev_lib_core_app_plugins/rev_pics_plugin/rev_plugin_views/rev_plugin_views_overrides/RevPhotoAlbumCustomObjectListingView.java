package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views.RevPhotoAlbumClusterListingWidget;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/1/17.
 */

public class RevPhotoAlbumCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevPhotoAlbumCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_pics_album";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(RevEntity revEntity) {
        if (revEntity == null || revEntity.get_revPublisherEntity() == null) return null;

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_photo_album_custom_activity_listing_view_override");
        revEntityListingViewOverrideVOM.setRevEntity(revEntity);

        LinearLayout itemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        List<RevEntity> revPicsAlbumFiles = new ArrayList<>();

        for (RevEntity revPicsAlbumFileEntity : revEntity.get_revEntityChildrenList()) {
            if (revPicsAlbumFileEntity.get_revEntitySubType().equals("rev_file") && !revPicsAlbumFileEntity.get_revEntityMetadataList().isEmpty()) {
                revPicsAlbumFiles.add(revPicsAlbumFileEntity);
            }
        }

        LinearLayout revClusterAlbumView_LL = (LinearLayout) new RevPhotoAlbumClusterListingWidget(revVarArgsData).getRevPhotoAlbumClusterListingWidget(revPicsAlbumFiles);

        if (revClusterAlbumView_LL != null) {
            itemsContainer_LL.addView(revClusterAlbumView_LL);
        } else {
            TextView revNullItemsView = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView(.7f);
            revNullItemsView.setText("imAGEs uNAvAiLABLE");

            ((LinearLayout.LayoutParams) revNullItemsView.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;

            itemsContainer_LL.addView(revNullItemsView);
        }

        LinearLayout revObjectListingViewFooterWrapper_LL = (LinearLayout) new RevObjectListingViewFooterTabs(revVarArgsData).commentFooterView();
        ((LinearLayout.LayoutParams) revObjectListingViewFooterWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        itemsContainer_LL.addView(revObjectListingViewFooterWrapper_LL);

        revEntityListingViewOverrideVOM.setOverrideView(itemsContainer_LL);

        return revEntityListingViewOverrideVOM;
    }
}
