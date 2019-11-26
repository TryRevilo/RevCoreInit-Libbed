package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 11/5/17.
 */

public class RevPluggableRevDrawerMenuViewLoader {

    public static View createRevPluggableRevDrawerMenuView() {
        Context mContext = RevLibGenConstantine.REV_CONTEXT;
        LinearLayout revPluggableView_LL = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_ALL();

        for (Object revPluggableViewObject : RevPluggableServices.getRevPluggableViewsObjects(new RevVarArgsData(mContext.getResources().getString(R.string.createPluggableRevDrawerMenuView)))) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            for (View view : revPluggableView.createPluggableRevDrawerMenuView()) {
                if (view != null) {
                    revPluggableView_LL.addView(view);
                    new RevPluggableServices.RevLoadRevExtPluginRunnable(revPluggableView_LL).run();
                }
            }

        }
        return revPluggableView_LL;
    }
}
