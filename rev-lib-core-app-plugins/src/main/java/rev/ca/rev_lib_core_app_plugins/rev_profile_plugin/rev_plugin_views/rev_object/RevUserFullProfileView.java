package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevDownloadRevEntityInfoWrapper;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.rev_user_full_profile_view_widgets.rev_profile_subtype_widgets.RevUserFullProfileViewWidget_SOCIAL;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.RevPageContentHeaderTabsContainer;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.RevUserProfileProfileViewHeader;
import rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations.RevLoadingGIFView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_gen_functions.I_REV_PROCESS_FINISH;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/23/17.
 */

public class RevUserFullProfileView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevEntity revEntity = null;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    public RevUserFullProfileView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        if (revVarArgsData.getRevEntity() != null) {
            revEntity = revVarArgsData.getRevEntity();
        }

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    private View revGetTopItems(RevVarArgsData revVarArgsData) {
        FrameLayout infoView_FL = new FrameLayout(mContext);
        infoView_FL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        infoView_FL.setBackgroundColor(mContext.getResources().getColor(R.color.teal_primary));

        View itemsTabsView = new RevUserProfileProfileViewHeader(revVarArgsData).getRevProfileViewHeader();

        FrameLayout.LayoutParams itemsTabsView_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemsTabsView_LP.gravity = Gravity.BOTTOM;
        itemsTabsView.setLayoutParams(itemsTabsView_LP);

        infoView_FL.addView(itemsTabsView);

        return infoView_FL;
    }

    public View revResetPageOwnerProfileContent(View revReplacementtView) {
        LinearLayout revEntityMainCenterCctView_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
        revEntityMainCenterCctView_LL.addView(this.revGetTopItems(revVarArgsData));
        revEntityMainCenterCctView_LL.addView(new RevPageContentHeaderTabsContainer(revVarArgsData).revProfileTypesTabHeaderView());

        View infoChecks_LL = new RevProfilesSetUpsCheck(revVarArgsData).getRevProfilesSetUpsCheckView();

        if (infoChecks_LL != null) {
            revEntityMainCenterCctView_LL.addView(infoChecks_LL);
        }

        revEntityMainCenterCctView_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("REV_USER_PROFILE_VIEW_CONTAINER_LL"));
        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevLoadingGIFView(mContext).getRevLoadingGIFView());

        REV_SESSION_SETTINGS.setRevCurrentPageOwnerEntity(revVarArgsData.getRevEntity());

        final FrameLayout frameLayout = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();
        LinearLayout.LayoutParams frameLayout_LL_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(frameLayout_LL_LP);

        frameLayout.addView(revReplacementtView);

        LinearLayout revViewOptions_LL = (LinearLayout) new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_profile_content_floating_options_wrapper_menu_area", revVarArgsData);
        ((FrameLayout.LayoutParams) revViewOptions_LL.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        frameLayout.addView(revViewOptions_LL);

        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", frameLayout);

        return revEntityMainCenterCctView_LL;
    }

    public View revResetPageOwnerProfileContent_NO_FLOATING_WIDGET(View revReplacementtView) {
        LinearLayout revEntityMainCenterCctView_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
        revEntityMainCenterCctView_LL.addView(this.revGetTopItems(revVarArgsData));
        revEntityMainCenterCctView_LL.addView(new RevPageContentHeaderTabsContainer(revVarArgsData).revProfileTypesTabHeaderView());

        View infoChecks_LL = new RevProfilesSetUpsCheck(revVarArgsData).getRevProfilesSetUpsCheckView();

        if (infoChecks_LL != null) {
            revEntityMainCenterCctView_LL.addView(infoChecks_LL);
        }

        revEntityMainCenterCctView_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("REV_USER_PROFILE_VIEW_CONTAINER_LL"));
        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevLoadingGIFView(mContext).getRevLoadingGIFView());

        REV_SESSION_SETTINGS.setRevCurrentPageOwnerEntity(revVarArgsData.getRevEntity());

        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", revReplacementtView);

        return revEntityMainCenterCctView_LL;
    }

    public View getUserMainCenterCctViewLL() {
        REV_SESSION_SETTINGS.setRevCurrentPageRevVarArgsData(revVarArgsData);

        LinearLayout revEntityMainCenterCctView_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
        revEntityMainCenterCctView_LL.addView(this.revGetTopItems(revVarArgsData));
        revEntityMainCenterCctView_LL.addView(new RevPageContentHeaderTabsContainer(revVarArgsData).revProfileTypesTabHeaderView());

        View infoChecks_LL = new RevProfilesSetUpsCheck(revVarArgsData).getRevProfilesSetUpsCheckView();

        if (infoChecks_LL != null) {
            revEntityMainCenterCctView_LL.addView(infoChecks_LL);
        }

        revEntityMainCenterCctView_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("REV_USER_PROFILE_VIEW_CONTAINER_LL"));
        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevLoadingGIFView(mContext).getRevLoadingGIFView());

        REV_SESSION_SETTINGS.setRevCurrentPageOwnerEntity(revVarArgsData.getRevEntity());

        final FrameLayout frameLayout = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();
        LinearLayout.LayoutParams frameLayout_LL_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(frameLayout_LL_LP);

        frameLayout.addView(new RevUserFullProfileViewWidget_SOCIAL(revVarArgsData).getRevUserFullProfileViewWidget());

        LinearLayout revViewOptions_LL = (LinearLayout) new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_profile_content_floating_options_wrapper_menu_area", revVarArgsData);
        ((FrameLayout.LayoutParams) revViewOptions_LL.getLayoutParams()).setMargins(0, 152, 0, 0);
        frameLayout.addView(revViewOptions_LL);

        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", frameLayout);

        return revEntityMainCenterCctView_LL;
    }

    public View revGetProfileTimelineItems(RevVarArgsData revDownloadVarArgsData) {
        FrameLayout frameLayout = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();
        LinearLayout.LayoutParams frameLayout_LL_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(frameLayout_LL_LP);

        frameLayout.addView(new RevUserFullProfileViewWidget_SOCIAL(revDownloadVarArgsData).getRevUserFullProfileViewWidget());

        LinearLayout revViewOptions_LL = (LinearLayout) new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_profile_content_floating_options_wrapper_menu_area", revDownloadVarArgsData);
        ((FrameLayout.LayoutParams) revViewOptions_LL.getLayoutParams()).setMargins(0, 152, 0, 0);
        frameLayout.addView(revViewOptions_LL);

        return frameLayout;
    }

    public void revResetUserMainCenterCctViewLL() {
        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevLoadingGIFView(mContext).getRevLoadingGIFView());

        new RevDownloadRevEntityInfoWrapper(mContext, revEntity.get_remoteRevEntityGUID(), new I_REV_PROCESS_FINISH() {
            @Override
            public void REV_PROCESS_FINISH(Object o) {
                RevVarArgsData revDownloadVarArgsData = (RevVarArgsData) o;
                REV_SESSION_SETTINGS.setRevCurrentPageRevVarArgsData(revDownloadVarArgsData);

                final LinearLayout revEntityMainCenterCctView_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();

                revEntityMainCenterCctView_LL.addView(revGetTopItems(revDownloadVarArgsData));
                revEntityMainCenterCctView_LL.addView(new RevPageContentHeaderTabsContainer(revDownloadVarArgsData).revProfileTypesTabHeaderView());

                View infoChecks_LL = new RevProfilesSetUpsCheck(revDownloadVarArgsData).getRevProfilesSetUpsCheckView();

                if (infoChecks_LL != null) {
                    revEntityMainCenterCctView_LL.addView(infoChecks_LL);
                }

                revEntityMainCenterCctView_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("REV_USER_PROFILE_VIEW_CONTAINER_LL"));

                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", revGetProfileTimelineItems(revDownloadVarArgsData));

                REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(revEntityMainCenterCctView_LL);
            }
        });
    }
}