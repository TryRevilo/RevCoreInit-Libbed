package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;
import android.view.View;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER;

/**
 * Created by rev on 10/18/17.
 */

public class RevPluggableCenterMainContentViewLoader {

    public static View revLoadCenterMainContentView(RevVarArgsData revVarArgsData) {
        Context mContext = RevLibGenConstantine.REV_CONTEXT;
        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER);
        revVarArgsData.setRevViewType(mContext.getResources().getString(R.string.createPluggableRevMainCenterCctView_LL));

        for (Object revPluggableViewObject : RevPluggableServices.getIRevPluggableViewsObjects(revVarArgsData)) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            View revPluggableView_LL;
            if (REV_SESSION_SETTINGS.isIsNotLoggedIn()) {
                revPluggableView_LL = revPluggableView.createRevPluggableLoggedOutView();
            } else {
                revPluggableView_LL = revPluggableView.createPluggableRevMainCenterCctView_LL();
            }

            if (revPluggableView_LL != null) {
                PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.addView(revPluggableView_LL);
                new RevPluggableServices.RevLoadRevExtPluginRunnable(PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER).run();
            }
        }

        return PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER;
    }
}
