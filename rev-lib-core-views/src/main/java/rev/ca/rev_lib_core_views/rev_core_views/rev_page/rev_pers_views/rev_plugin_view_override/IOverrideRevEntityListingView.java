package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;

/**
 * Created by rev on 1/13/18.
 */

public interface IOverrideRevEntityListingView {
    String registerRevPluggableCustomObjectListingView();
    RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(RevEntity revEntity);
}
