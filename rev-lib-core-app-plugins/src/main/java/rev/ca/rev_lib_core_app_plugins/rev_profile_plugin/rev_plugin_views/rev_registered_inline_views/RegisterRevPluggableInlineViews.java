package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_registered_inline_views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevRegisterPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

public class RegisterRevPluggableInlineViews implements IRevRegisterPluggableInlineView {

    private static Context mContext = RevLibGenConstantine.REV_CONTEXT;

    @Override
    public List<RevPluggableInlineView> createPluggableRevInlineView() {
        List<RevPluggableInlineView> revPluggableInlineViews = new ArrayList<>();

        RevCoreInputFormTextView revCoreInputFormTextView = new RevCoreInputFormTextView(RevLibGenConstantine.REV_CONTEXT);

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
        textView.setText("opTioNs");

        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL * 2, 0, 0, 0);

        textView.setLayoutParams(layoutParams);

        revPluggableInlineView.setInlineView(textView);
        revPluggableInlineView.setViewName("options_view");
        revPluggableInlineView.setTargetContainerViewName("test_view");

        revPluggableInlineViews.add(revPluggableInlineView);
        revPluggableInlineViews.add(this.getRevStripProfileIconPluggableView());
        revPluggableInlineViews.add(this.revPageUsernameProfileViewTopBarTabs());
        revPluggableInlineViews.add(this.revUserProfileViewPageHeaderTabs());
        revPluggableInlineViews.add(this.revUserProfileViewOptionsTabs());
        revPluggableInlineViews.add(this.userProfileCenterCctView_LL());
        revPluggableInlineViews.add(this.revEntityMainCenterCctView_LL());

        return revPluggableInlineViews;
    }

    public RevPluggableInlineView getRevStripProfileIconPluggableView() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(RevLibGenConstantine.REV_CONTEXT).getHorizontalRevLinearLayout_WRAPPED_ALL();

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(linearLayout);
        revPluggableInlineView.setViewName("rev_strip_profile_icon_pluggable_view");

        return revPluggableInlineView;
    }

    public RevPluggableInlineView revPageUsernameProfileViewTopBarTabs() {
        LinearLayout fullNamesViewWrapper_LL = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(fullNamesViewWrapper_LL);
        revPluggableInlineView.setViewName("rev_page_user_name_top_bar_tabs_wrapper");

        return revPluggableInlineView;
    }

    public RevPluggableInlineView revUserProfileViewPageHeaderTabs() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout();
        LinearLayout.LayoutParams vView_LL_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        vView_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
        linearLayout.setLayoutParams(vView_LL_LP);

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(linearLayout);
        revPluggableInlineView.setViewName("rev_user_profile_view_page_header_tabs_wrapper");

        return revPluggableInlineView;
    }

    public RevPluggableInlineView revUserProfileViewOptionsTabs() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout();
        LinearLayout.LayoutParams vView_LL_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        vView_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
        linearLayout.setLayoutParams(vView_LL_LP);

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(linearLayout);
        revPluggableInlineView.setViewName("rev_user_profile_view_options_tabs_wrapper");

        return revPluggableInlineView;
    }

    public RevPluggableInlineView revEntityMainCenterCctView_LL() {
        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_ALL());
        revPluggableInlineView.setViewName("rev_entity_main_center_cct_view_LL");

        return revPluggableInlineView;
    }

    public RevPluggableInlineView userProfileCenterCctView_LL() {
        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT());
        revPluggableInlineView.setViewName("user_profile_center_cct_view_LL");

        return revPluggableInlineView;
    }
}
