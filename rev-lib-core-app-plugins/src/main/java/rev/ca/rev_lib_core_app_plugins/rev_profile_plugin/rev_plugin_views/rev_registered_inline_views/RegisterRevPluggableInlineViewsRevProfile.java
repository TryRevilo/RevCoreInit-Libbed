package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_registered_inline_views;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevRegisterPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RegisterRevPluggableInlineViewsRevProfile implements IRevRegisterPluggableInlineView {

    @Override
    public List<RevPluggableInlineView> createPluggableRevInlineView() {
        List<RevPluggableInlineView> revPluggableInlineViews = new ArrayList<>();

        revPluggableInlineViews.add(this.revNewBriefTimelineListingsContainer());

        return revPluggableInlineViews;
    }

    public RevPluggableInlineView revNewBriefTimelineListingsContainer() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(RevLibGenConstantine.REV_CONTEXT).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();
        revPluggableInlineView.setInlineView(linearLayout);
        revPluggableInlineView.setViewName("REV_USER_PROFILE_VIEW_CONTAINER_LL");

        return revPluggableInlineView;
    }
}
