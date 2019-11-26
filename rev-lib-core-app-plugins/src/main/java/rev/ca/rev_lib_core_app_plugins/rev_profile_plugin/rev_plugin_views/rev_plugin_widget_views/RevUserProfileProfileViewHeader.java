package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.rev_page_widgets.RevPageTopBarPageOwnerNameWidget;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/24/17.
 */

public class RevUserProfileProfileViewHeader {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevUserProfileProfileViewHeader(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public View getRevProfileViewHeader() {
        new RevPageTopBarPageOwnerNameWidget(revVarArgsData).revReloadPageTopBarPageOwnerNameWidget();
        LinearLayout vViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        vViewContainer_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(this.revUserProfileViewOptionsTabs()));

        return vViewContainer_LL;
    }

    private LinearLayout revUserProfileViewOptionsTabs() {
        LinearLayout linearLayoutWrapper_LL = new RevCoreLayoutsLinearLayout(mContext).get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LinearLayout leftTabs_Wrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        leftTabs_Wrapper_LL.addView(this.revTimelineTab());
        leftTabs_Wrapper_LL.addView(this.revProfileTab());
        leftTabs_Wrapper_LL.addView(this.revInfoTab());

        linearLayoutWrapper_LL.addView(leftTabs_Wrapper_LL);
        linearLayoutWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        linearLayoutWrapper_LL.addView(this.familyTab());

        return linearLayoutWrapper_LL;
    }

    private View revTimelineTab() {
        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * .8);
        Drawable revTimeline_TV_DR = mContext.getResources().getDrawable(R.drawable.ic_first_page_black_48dp);
        revTimeline_TV_DR.setBounds(0, 0, (int) (imgSize * .8), (int) (imgSize * .8));
        DrawableCompat.setTint(revTimeline_TV_DR, ContextCompat.getColor(mContext, R.color.revWhite));

        TextView revTimeline_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        revTimeline_TV.setText("bAck");
        revTimeline_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_100_dark));
        revTimeline_TV.setCompoundDrawables(revTimeline_TV_DR, null, null, null);

        this.setTabProperties(revTimeline_TV);

        int paddingH = RevLibGenConstantine.REV_MARGIN_SMALL;
        int paddingV = (int) (RevLibGenConstantine.REV_MARGIN_TINY * 1.48f);

        revTimeline_TV.setPadding(paddingH, paddingV, paddingH, paddingV);

        revTimeline_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(revVarArgsData).getUserMainCenterCctViewLL());
            }
        });

        return revTimeline_TV;
    }

    private void setTabProperties(TextView textView) {
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));

        int paddingH = RevLibGenConstantine.REV_MARGIN_SMALL;
        int paddingV = (int) (RevLibGenConstantine.REV_MARGIN_TINY * 1.5f);

        textView.setPadding(paddingH, paddingV, paddingH, paddingV);
        textView.setCompoundDrawablePadding(1);
        textView.setGravity(Gravity.CENTER_VERTICAL);
    }

    private View revProfileTab() {
        TextView profile_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        profile_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));
        profile_TV.setText(R.string.user_profile);

        this.setTabProperties(profile_TV);

        profile_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData revPassRVD = REV_SESSION_SETTINGS.getRevCurrentPageRevVarArgsData();
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevUserFullProfileView(revPassRVD).revGetProfileTimelineItems(revPassRVD));
            }
        });

        return profile_TV;
    }

    private View revInfoTab() {
        TextView profileIcon_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        profileIcon_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        profileIcon_TV.setText(R.string.entity_info);

        this.setTabProperties(profileIcon_TV);

        profileIcon_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevInfoDetailsWidget(revVarArgsData).getRevDetailsWidget());
            }
        });

        return profileIcon_TV;
    }

    private View familyTab() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LayoutParams) linearLayout.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        Drawable revTimeline_TV_DR = mContext.getResources().getDrawable(R.drawable.icons8_genealogy_48);
        revTimeline_TV_DR.setBounds(0, 0, (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * 2.1), RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        DrawableCompat.setTint(revTimeline_TV_DR, ContextCompat.getColor(mContext, R.color.teal_200_dark));

        TextView revTimeline_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(1.7f);
        revTimeline_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("FAmiLy"));
        revTimeline_TV.setCompoundDrawables(revTimeline_TV_DR, null, null, null);
        revTimeline_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));
        revTimeline_TV.setGravity(Gravity.CENTER_VERTICAL);
        revTimeline_TV.setPadding(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.5), 0);

        linearLayout.addView(revTimeline_TV);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData revVarArgsData = new RevVarArgsData();
                revVarArgsData.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                revVarArgsData.setRevViewType("RevCreateInputFormRevProfileParents");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(iRevInputFormView.createRevInputForm());
            }
        });

        return linearLayout;
    }
}
