package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;

/**
 * Created by rev on 11/30/17.
 */

public class RevViewOverrides {

    private static Map<String, IOverrideRevEntityListingView> stringIRevPluggableViewsHashMap = new HashMap<>();

    public static void addOverrideItem(IOverrideRevEntityListingView iOverrideRevEntityListingView) {
        stringIRevPluggableViewsHashMap.put(iOverrideRevEntityListingView.registerRevPluggableCustomObjectListingView(), iOverrideRevEntityListingView);
    }

    public static Map<String, IOverrideRevEntityListingView> overrideItemPOMMap() {
        return stringIRevPluggableViewsHashMap;
    }

    public static IOverrideRevEntityListingView REV_GET_IOverrideRevEntityListingView(RevVarArgsData revVarArgsData) {
        IOverrideRevEntityListingView revRetIOverrideRevEntityListingView = null;

        for (Object revPluggableView : RevPluggableServices.getRevPluggableViewsObjects(revVarArgsData)) {
            IOverrideRevEntityListingView iOverrideRevEntityListingView = (IOverrideRevEntityListingView) revPluggableView;

            if (iOverrideRevEntityListingView.registerRevPluggableCustomObjectListingView() != null) {
                if (iOverrideRevEntityListingView.registerRevPluggableCustomObjectListingView().equals(revVarArgsData.getRevEntity().get_revEntitySubType())) {
                    revRetIOverrideRevEntityListingView = iOverrideRevEntityListingView;
                    break;
                }
            }
        }

        return revRetIOverrideRevEntityListingView;
    }
}
