package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/13/17.
 */

public class RevEntityListingOptionsTogglerMenuViewLoader {

    public static View createRevEntityListingOptionsTogglerMenuView() {
        Context mContext = RevLibGenConstantine.REV_CONTEXT;
        LinearLayout revPluggableView_LL = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();

        for (Object revPluggableViewObject : RevPluggableServices.getRevPluggableViewsObjects(new RevVarArgsData(mContext.getResources().getString(R.string.createRevObjectListingOptionsTogglerMenuView)))) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            if (revPluggableView.createRevEntityListingOptionsTogglerMenuView() != null) {
                View view = revPluggableView.createRevEntityListingOptionsTogglerMenuView();
                if (view.getLayoutParams() == null) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
                    view.setLayoutParams(layoutParams);
                } else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
                }

                revPluggableView_LL.addView(view);
                new RevPluggableServices.RevLoadRevExtPluginRunnable(revPluggableView_LL).run();
            }
        }
        return revPluggableView_LL;
    }
}
