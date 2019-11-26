package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pages;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.default_rev_entities_listing_view.RevEntityListingView;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevAllMembersListing {

    private Context mContext;

    public RevAllMembersListing(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
    }

    public View revObjectListing_LV(List<RevEntity> revEntities) {
        ScrollView revEntitiesList_SV = new ScrollView(mContext);

        FrameLayout.LayoutParams revObjectsList_SV_LP = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        revEntitiesList_SV.setLayoutParams(revObjectsList_SV_LP);

        LinearLayout itemsContainer = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_ALL();

        if (revEntities.size() < 1) return revEntitiesList_SV;

        for (int i = 0; i < revEntities.size(); i++) {
            RevEntity revEntity = revEntities.get(i);

            if (revEntity.get_remoteRevEntityGUID().longValue() == REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid().longValue())
                continue;

            RevVarArgsData rVD = new RevVarArgsData();
            rVD.setmContext(mContext);
            rVD.setRevEntity(revEntity);
            rVD.setRevEntityGUID(revEntity.get_revEntityGUID());

            itemsContainer.addView(new RevEntityListingView(rVD).createDefaultRevEntityListingView());
        }

        revEntitiesList_SV.addView(itemsContainer);

        return revEntitiesList_SV;
    }
}
