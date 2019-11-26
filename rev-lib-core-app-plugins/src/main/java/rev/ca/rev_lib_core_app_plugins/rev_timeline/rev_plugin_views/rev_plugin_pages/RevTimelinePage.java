package rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_plugin_views.rev_plugin_pages;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers.RevPersRevEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers.RevPersRevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.default_rev_entities_listing_view.RevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_objects_classes_reg.ICreatePluggableDataClass;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 11/27/17.
 */

public class RevTimelinePage extends AbstractIRevPluggableViews implements ICreatePluggableDataClass {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private LinearLayout pluggableRevMainCenterCctView_LL;

    public RevTimelinePage(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();

        pluggableRevMainCenterCctView_LL = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_ALL();
    }

    @Override
    public View createPluggableRevMainCenterCctView_LL() {
        LinearLayout.LayoutParams pluggableRevMainCenterCctView_LL_LP = (LinearLayout.LayoutParams) pluggableRevMainCenterCctView_LL.getLayoutParams();
        pluggableRevMainCenterCctView_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        pluggableRevMainCenterCctView_LL.addView(revObjectListing_LV());
        return pluggableRevMainCenterCctView_LL;
    }

    public View revObjectListing_LV() {
        ScrollView revEntitiesList_SV = new ScrollView(mContext);

        LinearLayout.LayoutParams revObjectsList_SV_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        revEntitiesList_SV.setLayoutParams(revObjectsList_SV_LP);

        List<Long> revTLGuids = new RevPersRevEntity(mContext).revRead("rev_timeline");

        LinearLayout itemsContainer = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_ALL();

        for (long revTLGuid : revTLGuids) {
            RevObjectEntity tlObjectEntity = new RevPersRevObjectEntity(mContext).revReadRevObject_BY_GUID(revTLGuid);
            String revEntityType = new RevPersRevEntity(mContext).revReadEntityType(tlObjectEntity.get_revEntityOwnerGUID());

            if (!revEntityType.equals(FeedReaderContract.FeedEntry_REV_USER_ENTITY.TABLE_NAME)) {
                RevVarArgsData rVD = new RevVarArgsData();
                rVD.setmContext(mContext);
                rVD.setRevEntityGUID(tlObjectEntity.get_revEntityOwnerGUID());

                itemsContainer.addView(new RevEntityListingView(rVD).createDefaultRevEntityListingView());
            }
        }

        revEntitiesList_SV.addView(itemsContainer);

        return revEntitiesList_SV;
    }

    public View revBriefTimelieneListing_LV() {
        ((LinearLayout) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_brief_timeline_listings_container")).removeAllViews();

        FrameLayout frameLayout = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.setScrollbarFadingEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);

        for (RevEntity tlRevEntity : revVarArgsData.getRevPersEntityInfoWrapperModel().getRevTimelineEntities()) {
            RevVarArgsData passRevRVD = new RevVarArgsData();
            passRevRVD.setmContext(revVarArgsData.getmContext());
            passRevRVD.setRevEntity(tlRevEntity);

            View view = new RevEntityListingView(passRevRVD).createDefaultRevEntityListingView();

            if (view != null)
                ((LinearLayout) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_brief_timeline_listings_container")).addView(view);
        }

        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_REMOVE_INLINE_VIEW_PARENT("rev_new_brief_timeline_listings_container");
        scrollView.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_brief_timeline_listings_container"));

        frameLayout.addView(scrollView);

        return frameLayout;
    }

    @Override
    public Class createPluggableDataClass() {
        return this.getClass();
    }
}