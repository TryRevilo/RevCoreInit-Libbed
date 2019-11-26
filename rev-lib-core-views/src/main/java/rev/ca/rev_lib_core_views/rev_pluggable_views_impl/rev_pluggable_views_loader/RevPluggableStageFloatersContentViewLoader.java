package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.REV_BASE_CONTENT_CONTAINER_FL;

/**
 * Created by rev on 10/18/17.
 */

public class RevPluggableStageFloatersContentViewLoader {

    public static void revLoadRevPluggableStageFloatersContentView(RevVarArgsData revVarArgsData) {
        revVarArgsData.setRevViewType("createRevPluggableCenterMainContentOverlayFloatingView");

        for (Object revPluggableViewObject : RevPluggableServices.getIRevPluggableViewsObjects(revVarArgsData)) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            REV_BASE_CONTENT_CONTAINER_FL.addView(revPluggableView.createPluggableRevMainCenterCctView_LL());
        }
    }
}