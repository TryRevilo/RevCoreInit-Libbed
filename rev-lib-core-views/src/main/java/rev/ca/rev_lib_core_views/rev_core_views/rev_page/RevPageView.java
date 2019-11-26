package rev.ca.rev_lib_core_views.rev_core_views.rev_page;

import android.content.Context;
import android.widget.FrameLayout;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableCenterMainContentViewLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableCustomRevEntityListingViewLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableRevMerryllStripLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableStageFloatersContentViewLoader;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.REV_BASE_CONTENT_CONTAINER_FL;
import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.REV_TOP_BAR_FL;

/**
 * Created by rev on 10/9/17.
 */

public class RevPageView {

    public static FrameLayout getRevPageView() {
        Context mContext = RevLibGenConstantine.REV_CONTEXT;

        RevPluggableServices.initIRevPluggableServices();

        RevPluggableCustomRevEntityListingViewLoader.createRevPluggableCustomObjectListingView();

        RevVarArgsData revVarArgsData = new RevVarArgsData();
        revVarArgsData.setmContext(mContext);
        revVarArgsData.setRevEntityGUID(RevPersConstantine.REV_LOGGED_IN_ENTITY_GUID);

        REV_BASE_CONTENT_CONTAINER_FL.addView(RevPluggableCenterMainContentViewLoader.revLoadCenterMainContentView(revVarArgsData));

        if (!REV_SESSION_SETTINGS.isIsNotLoggedIn()) {
            REV_BASE_CONTENT_CONTAINER_FL.addView(RevPluggableRevMerryllStripLoader.revLoadRevMerryllStripTabs());
            REV_BASE_CONTENT_CONTAINER_FL.addView(new RevFooterView(mContext).getRevFooterViewLL());
            REV_TOP_BAR_FL.addView(new RevHeaderView(mContext).getRevHeaderViewLL());
            REV_BASE_CONTENT_CONTAINER_FL.addView(REV_TOP_BAR_FL);

            RevPluggableStageFloatersContentViewLoader.revLoadRevPluggableStageFloatersContentView(revVarArgsData);
        }

        return REV_BASE_CONTENT_CONTAINER_FL;
    }
}
