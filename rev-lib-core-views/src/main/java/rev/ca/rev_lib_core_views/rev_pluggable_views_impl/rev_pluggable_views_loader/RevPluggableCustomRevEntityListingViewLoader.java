package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevViewOverrides;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 11/30/17.
 */

public class RevPluggableCustomRevEntityListingViewLoader {

    public static void createRevPluggableCustomObjectListingView() {
        Context mContext = RevLibGenConstantine.REV_CONTEXT;

        for (Object revPluggableView : RevPluggableServices.getRevPluggableViewsObjects(new RevVarArgsData(mContext.getResources().getString(R.string.createRevPluggableCustomObjectListingView)))) {
            IOverrideRevEntityListingView iOverrideRevEntityListingView = (IOverrideRevEntityListingView) revPluggableView;

            if (iOverrideRevEntityListingView.registerRevPluggableCustomObjectListingView() != null) {
                RevViewOverrides.addOverrideItem(iOverrideRevEntityListingView);
            }
        }
    }
}
