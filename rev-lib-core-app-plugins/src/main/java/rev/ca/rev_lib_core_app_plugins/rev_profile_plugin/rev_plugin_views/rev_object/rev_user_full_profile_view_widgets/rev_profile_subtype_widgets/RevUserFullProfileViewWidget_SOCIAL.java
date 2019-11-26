package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.rev_user_full_profile_view_widgets.rev_profile_subtype_widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersEntityInfoWrapperModel;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_views_overrides.RevCustomListingViewRecommendedBagsListingsView;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms.RevPublisher;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs.RevLocalEntityInfoWrapperModel;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.rev_user_full_profile_view_widgets.CustomScrollView;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.PendingUserConnReqsRec;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.RevRecentPofileMedia;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.rev_page_widgets.rev_publications_widget_views.RevQ_AND_A_View;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_plugin_views.rev_plugin_pages.RevTimelinePage;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.rev_pluggable_menu_area_views.CreateRevMenuAreaViewContainerPublisher;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.RevRelatedPeopleIconsListing;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_lib_sqlite.RevNativeLibWrapperSQLite3;
import rev.ca.rev_plugin_loader.RevPluginLoader;
import rev.ca.rev_v8.RevNativeLibWrapper;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 2/18/18.
 */

public class RevUserFullProfileViewWidget_SOCIAL {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel;

    long loggedInRemoteRevEntityGUID = REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid().longValue();

    RevEntity revPageOwnerEntity;
    Long revPageOwnerEntityGUID;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    LinearLayout.LayoutParams centerVertical_LP;
    LinearLayout.LayoutParams insetMarginFirst_LP;

    LinearLayout mainImageViewWrapper_LL;

    int tinyMargin = RevLibGenConstantine.REV_MARGIN_TINY;
    int mediumMargin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

    int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    String pageOwnerName;

    public RevUserFullProfileViewWidget_SOCIAL(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revPersEntityInfoWrapperModel = revVarArgsData.getRevPersEntityInfoWrapperModel();

        revPageOwnerEntity = revVarArgsData.getRevEntity();
        revPageOwnerEntityGUID = revPageOwnerEntity.get_revEntityGUID();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        centerVertical_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerVertical_LP.gravity = Gravity.CENTER_VERTICAL;

        insetMarginFirst_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        insetMarginFirst_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.1), 0, 0, 0);

        mainImageViewWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        pageOwnerName = REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPageOwnerEntity.get_revEntityMetadataList(), "rev_entity_full_names_value"));
    }

    public View getRevUserFullProfileViewWidget() {
        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        final LinearLayout restContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        restContainer_LL.setId(new Integer(1));

        restContainer_LL.addView(this.peopleYouMayKnowWidget());
        restContainer_LL.addView(this.defaultFriendsSocialCircleWidget());

        FrameLayout revBriefTL_FL = (FrameLayout) new RevTimelinePage(this.revVarArgsData).revBriefTimelieneListing_LV();
        LinearLayout.LayoutParams revBriefTL_FL_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        revBriefTL_FL_LP.setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);
        revBriefTL_FL.setLayoutParams(revBriefTL_FL_LP);

        restContainer_LL.addView(revBriefTL_FL);
        restContainer_LL.addView(this.addedSocialCircles());
        restContainer_LL.addView(this.suggestedSpaces());

        linearLayout.addView(this.postOnMyProfile());
        linearLayout.addView(this.revNoticiasView());
        linearLayout.addView(this.revBriefStudyTopicsView());
        linearLayout.addView(this.findPeopleWidgetView());
        linearLayout.addView(this.getSpaceOptionsView());
        linearLayout.addView(new RevRecentPofileMedia(revVarArgsData).getRevRecentPofileMedia());
        linearLayout.addView(restContainer_LL);

        final ScrollView mainScrollView = new CustomScrollView(mContext);
        mainScrollView.setVerticalScrollBarEnabled(false);
        mainScrollView.addView(linearLayout);

        mainScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = mainScrollView.getScrollY(); // For ScrollView
                int scrollX = mainScrollView.getScrollX(); // For HorizontalScrollView
                // DO SOMETHING WITH THE SCROLL COORDINATES

                if (isViewVisible(restContainer_LL)) {
                    // linearLayout.removeView(linearLayout.findViewById(new Integer(1)));
                }
            }
        });

        mainScrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return mainScrollView;
    }

    private boolean isViewVisible(View view) {
        Rect myViewRect = new Rect();
        view.getGlobalVisibleRect(myViewRect);
        float viewPosY = myViewRect.top;

        if (viewPosY > 555.0) {
            return true;
        } else {
            return false;
        }
    }

    private View revNoticiasView() {
        LinearLayout revContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revContainer_LL.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout revHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revHeaderWrapper_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .6), 0, 0, 0);

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revPurple));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);
        ((LinearLayout.LayoutParams) headerIcon_IV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        Picasso.get()
                .load(R.drawable.baseline_priority_high_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(headerIcon_IV);

        TextView headerTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        headerTtl_TV.setText("NoTiFicATioNs");
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        revHeaderWrapper_LL.addView(headerIcon_IV);
        revHeaderWrapper_LL.addView(headerTtl_TV);

        LinearLayout revObjectsListingsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revObjectsListingsContainer_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.2), 0, 0, 0);

        TextView revNullEntities_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);

        String revNullEntitiesTell_S;

        if (revPageOwnerEntity.get_revEntityGUID().equals(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid())) {
            revNullEntitiesTell_S = "0 uNREAD NoTiFicATioNs";
        } else if (revPageOwnerEntity.get_revEntitySubType().equals("rev_bag")) {
            revNullEntitiesTell_S = "0 uNREAD NoTiFicATioNs " + REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(pageOwnerName, 12) + " spAcE";
        } else {
            revNullEntitiesTell_S = "0 uNREAD NoTiFicATioNs " + REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(pageOwnerName) + "'s NETwoRk";
        }

        revNullEntities_TV.setText(revNullEntitiesTell_S);
        revNullEntities_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        ((LinearLayout.LayoutParams) revNullEntities_TV.getLayoutParams()).setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        revObjectsListingsContainer_LL.addView(revNullEntities_TV);

        LinearLayout unreadItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        LinearLayout unreadItemsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) unreadItemsContainer_LL.getLayoutParams()).gravity = Gravity.TOP;

        unreadItemsContainer_LL.addView(this.unreadMemosNoticiasView());
        unreadItemsContainer_LL.addView(this.unreadAnnouncementsNoticiasView());

        ImageView viewNoticiasDetails_IV = new ImageView(mContext);

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
        viewNoticiasDetails_IV.setLayoutParams(new LinearLayout.LayoutParams(revImageSizeSmall, revImageSizeSmall));

        Picasso.get()
                .load(R.drawable.icons8_compare_git_40)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(viewNoticiasDetails_IV);

        TextView noticiasItemMore_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);
        noticiasItemMore_TV.setText("READ moRE");
        ((LinearLayout.LayoutParams) noticiasItemMore_TV.getLayoutParams()).gravity = Gravity.BOTTOM;
        noticiasItemMore_TV.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        LinearLayout noticiasItemMoreWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) noticiasItemMoreWrapper_LL.getLayoutParams()).setMargins(0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), 0, 0);
        noticiasItemMoreWrapper_LL.addView(viewNoticiasDetails_IV);
        noticiasItemMoreWrapper_LL.addView(noticiasItemMore_TV);

        unreadItemsWrapper_LL.addView(unreadItemsContainer_LL);
        unreadItemsWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        unreadItemsWrapper_LL.addView(noticiasItemMoreWrapper_LL);

        revObjectsListingsContainer_LL.addView(unreadItemsWrapper_LL);

        if (revVarArgsData.getRevEntity().get_remoteRevEntityGUID().longValue() == loggedInRemoteRevEntityGUID)
            revObjectsListingsContainer_LL.addView(new PendingUserConnReqsRec(revVarArgsData).getPendingConnReqsRec());

        revContainer_LL.addView(revHeaderWrapper_LL);
        revContainer_LL.addView(revObjectsListingsContainer_LL);

        return revContainer_LL;
    }

    private View unreadMemosNoticiasView() {
        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("12 uNREAD mEmos " + REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("7 spAcEs.........")));

        return textView;
    }

    private View unreadAnnouncementsNoticiasView() {
        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("7 NEw ANNouNcEmENTs " + REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("2 spAcEs.........")));

        return textView;
    }

    private View revBriefStudyTopicsView() {
        LinearLayout revContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revContainer_LL.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout revObjectsListingsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revObjectsListingsContainer_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.2), 0, 0, 0);

        LinearLayout revPublicationsOptionsTabsWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) revPublicationsOptionsTabsWrapper_LL.getLayoutParams()).setMargins(
                (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.6) + RevLibGenConstantine.REV_IMAGE_SIZE_TINY, 0, 0, 0);

        revPublicationsOptionsTabsWrapper_LL.addView(this.revGet_Q_A_IV());
        revPublicationsOptionsTabsWrapper_LL.addView(this.revGetAllPublicationsView());
        revPublicationsOptionsTabsWrapper_LL.addView(this.revGetBestPracticeView());
        revPublicationsOptionsTabsWrapper_LL.addView(this.revGetMoreReads());

        FrameLayout frameLayout = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();

        frameLayout.addView(new RevQ_AND_A_View(new RevVarArgsData(mContext)).revGet_Q_A_View());
        frameLayout.addView(revPublicationsOptionsTabsWrapper_LL);

        revObjectsListingsContainer_LL.addView(frameLayout);

        revContainer_LL.addView(revObjectsListingsContainer_LL);

        return revContainer_LL;
    }

    private View revGet_Q_A_IV() {
        int rev_Q_A_IV_size = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .7);

        LinearLayout.LayoutParams rev_Q_A_IV_LP = new LinearLayout.LayoutParams(rev_Q_A_IV_size, rev_Q_A_IV_size);
        rev_Q_A_IV_LP.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.7), 0);
        rev_Q_A_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        ImageView rev_Q_A_IV = new ImageView(mContext);
        rev_Q_A_IV.setLayoutParams(rev_Q_A_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_faq_48)
                .resize(rev_Q_A_IV_size, rev_Q_A_IV_size)
                .centerCrop()
                .into(rev_Q_A_IV);

        rev_Q_A_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** SQL_ITE V8 **/

                RevNativeLibWrapper revNativeLibWrapper = new RevNativeLibWrapper();
                revNativeLibWrapper.initV8();
                Log.v(RevLangStrings.REV_TAG, revNativeLibWrapper.stringFromV8() + "\nSQLite v : " + new RevNativeLibWrapperSQLite3().revLibVersion());
            }
        });

        return rev_Q_A_IV;
    }

    private View revGetAllPublicationsView() {
        int revBestPractice_IV_size = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .7);

        LinearLayout.LayoutParams revBestPractice_IV_LP = new LinearLayout.LayoutParams(revBestPractice_IV_size, revBestPractice_IV_size);
        revBestPractice_IV_LP.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.7), 0);
        revBestPractice_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        ImageView revBestPractice_IV = new ImageView(mContext);
        revBestPractice_IV.setLayoutParams(revBestPractice_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_book_shelf_96)
                .resize(revBestPractice_IV_size, revBestPractice_IV_size)
                .centerCrop()
                .into(revBestPractice_IV);

        return revBestPractice_IV;
    }

    private View revGetBestPracticeView() {
        int revBestPractice_IV_size = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * 1.1);

        LinearLayout.LayoutParams revBestPractice_IV_LP = new LinearLayout.LayoutParams(revBestPractice_IV_size, revBestPractice_IV_size);
        revBestPractice_IV_LP.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.7), 0);
        revBestPractice_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        ImageView revBestPractice_IV = new ImageView(mContext);
        revBestPractice_IV.setLayoutParams(revBestPractice_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_customer_insight_48)
                .resize(revBestPractice_IV_size, revBestPractice_IV_size)
                .centerCrop()
                .into(revBestPractice_IV);

        return revBestPractice_IV;
    }

    private View revGetMoreReads() {
        int rev_Q_A_IV_size = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        LinearLayout.LayoutParams rev_Q_A_IV_LP = new LinearLayout.LayoutParams(rev_Q_A_IV_size, rev_Q_A_IV_size);
        rev_Q_A_IV_LP.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.7), 0);
        rev_Q_A_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        ImageView rev_Q_A_IV = new ImageView(mContext);
        rev_Q_A_IV.setLayoutParams(rev_Q_A_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_merge_48)
                .resize(rev_Q_A_IV_size, rev_Q_A_IV_size)
                .centerCrop()
                .rotate(90)
                .into(rev_Q_A_IV);

        return rev_Q_A_IV;
    }

    private View unreadStudyTopicsView() {
        String revNullEntitiesTell_S;

        if (revPageOwnerEntity.get_revEntityGUID().equals(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid())) {
            revNullEntitiesTell_S = "0 uNREAD sTuDy Topics";
        } else if (revPageOwnerEntity.get_revEntitySubType().equals("rev_bag")) {
            revNullEntitiesTell_S = "17 uNREAD sTuDy Topics FRom " + REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(pageOwnerName, 12) + " spAcE";
        } else {
            revNullEntitiesTell_S = "4 uNREAD sTuDy Topics FRom " + REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(pageOwnerName) + "'s NETwoRk";
        }

        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.BOTTOM;
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(revNullEntitiesTell_S));

        return textView;
    }

    private View postOnMyProfile() {
        String totConnCountTell_S = "posT oN my pRofilE . . . .";

        SpannableString totConnectionsCount_S_Span = new SpannableString(totConnCountTell_S);
        totConnectionsCount_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .8)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        totConnectionsCount_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (totConnCountTell_S.length() - (totConnCountTell_S.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
        totConnectionsCount_S_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, totConnCountTell_S.length(), 0);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView kiwiInput_ET = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView_NO_PADDING();
        kiwiInput_ET.setHint(totConnectionsCount_S_Span);
        kiwiInput_ET.setGravity(Gravity.CENTER_VERTICAL);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(kiwiInput_ET);

        kiwiInput_ET.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                RevPop.initiatePopupWindow_MATCH_WIDTH(new RevPublisher(revVarArgsData).getRevPublisher());

                Iterator iterator = revVarArgsData.getRevVarArgsDataMetadataStrings().keySet().iterator();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    Object value = revVarArgsData.getRevVarArgsDataMetadataStrings().get(key);

                    if (value.equals("imagePath"))
                        iterator.remove();
                }
            }
        });

        final LinearLayout publisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) publisherWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL * 3, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        publisherWrapper_LL.addView(kiwiInput_ET);

        publisherWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());

        final View view = new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_direct_select_menu_item", revVarArgsData);

        publisherWrapper_LL.addView(view);

        linearLayout.addView(publisherWrapper_LL);

        RevPluginLoader revPluginLoader = new RevPluginLoader(RevLibGenConstantine.REV_CONTEXT);
        revPluginLoader.revLoadView(linearLayout);

        return linearLayout;
    }

    private View defaultFriendsSocialCircleWidget() {
        LinearLayout revContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revContainer_LL.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);
        ((LinearLayout.LayoutParams) revContainer_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        LinearLayout revHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revHeaderWrapper_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .6), 0, 0, 0);

        ImageView headerIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV_LP.setMargins(0, 11, -17, 0);
        headerIcon_IV_LP.gravity = Gravity.CENTER_VERTICAL;
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);

        Picasso.get()
                .load(R.drawable.ic_subdirectory_arrow_right_black_24dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .rotate(90)
                .into(headerIcon_IV);

        String revConnections_S = "my coNNEcTioNs";

        if (revPageOwnerEntity.get_revEntityType().equals("rev_group_entity"))
            revConnections_S = "mEmBERs";

        TextView headerTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        headerTtl_TV.setText(revConnections_S);
        headerTtl_TV.setGravity(Gravity.CENTER_VERTICAL);
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        revHeaderWrapper_LL.addView(headerIcon_IV);

        ImageView pointerMoreImageView = new ImageView(mContext);

        LinearLayout.LayoutParams pointerMoreImageView_LP = new LinearLayout.LayoutParams(imageSize, RevLibGenConstantine.REV_IMAGE_SIZE_TINY);
        pointerMoreImageView_LP.gravity = Gravity.CENTER_VERTICAL;
        pointerMoreImageView.setLayoutParams(pointerMoreImageView_LP);

        Picasso.get()
                .load(R.drawable.ic_trending_flat_black_48dp)
                .resize(imageSize, RevLibGenConstantine.REV_IMAGE_SIZE_TINY)
                .centerCrop()
                .into(pointerMoreImageView);

        pointerMoreImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        TextView peopleYouMayKnowCount = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
        peopleYouMayKnowCount.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);

        String totConnCountTell_S = "viEw ALL " + revVarArgsData.getRevPersEntityInfoWrapperModel().getRevEntityConnections().size() + " coNNecTioNs";

        peopleYouMayKnowCount.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(totConnCountTell_S));
        peopleYouMayKnowCount.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        ((LinearLayout.LayoutParams) peopleYouMayKnowCount.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        revHeaderWrapper_LL.addView(headerTtl_TV);
        revHeaderWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        revHeaderWrapper_LL.addView(peopleYouMayKnowCount);
        revHeaderWrapper_LL.addView(pointerMoreImageView);

        LinearLayout revObjectsListingsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revObjectsListingsContainer_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.2), RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        revObjectsListingsContainer_LL.addView(this.revConnectionRels(revVarArgsData.getRevPersEntityInfoWrapperModel().getRevEntityConnections()));

        revContainer_LL.addView(revHeaderWrapper_LL);
        revContainer_LL.addView(revObjectsListingsContainer_LL);

        return revContainer_LL;
    }

    private View revConnectionRels(final List<RevEntity> revConns) {
        LinearLayout friendsWidgetContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout friendsIconsWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) friendsIconsWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < revConns.size(); i++) {
            final RevEntity revConn = revConns.get(i);

            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .95);

            ImageView revUserIcon_IV = new ImageView(mContext);
            revUserIcon_IV.setLayoutParams(new LinearLayout.LayoutParams(imgSize, imgSize));

            LinearLayout.LayoutParams imageView_LP = (LinearLayout.LayoutParams) imgView_LL.getLayoutParams();

            if (i > 0) imageView_LP.setMargins(1, 0, 0, 0);
            imageView_LP.gravity = Gravity.CENTER_VERTICAL;
            imgView_LL.setLayoutParams(imageView_LP);

            int revUserIconCurveWidth = 100;
            Transformation transformation = new RoundedCornersTransformation(revUserIconCurveWidth, 2);

            File revUserIconFile = new File(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revConn.get_revEntityMetadataList(), "rev_user_icon_path_value"));

            if (revUserIconFile.exists()) {
                Picasso.get()
                        .load(revUserIconFile)
                        .placeholder(R.drawable.ic_account_circle_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .transform(transformation)
                        .into(revUserIcon_IV);
            } else {
                Picasso.get()
                        .load(R.drawable.ic_account_box_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .transform(transformation)
                        .into(revUserIcon_IV);

            }

            revUserIcon_IV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long revConnLocalEntityGUID = revConn.get_revEntityGUID();
                    long revConnRemoteEntityGUID = revConn.get_remoteRevEntityGUID();

                    if (revConnLocalEntityGUID < 1 && revConnRemoteEntityGUID > 0) {
                        revConnLocalEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revConnRemoteEntityGUID);
                    }

                    if (revConnLocalEntityGUID > 0) {
                        RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(revConnLocalEntityGUID);
                        RevVarArgsData passRVD = new RevVarArgsData(mContext);
                        passRVD.setRevPersEntityInfoWrapperModel(revPersEntityInfoWrapperModel);
                        passRVD.setRevEntity(revPersEntityInfoWrapperModel.getRevEntity());

                        REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(passRVD).getUserMainCenterCctViewLL());
                    }
                }
            });

            imgView_LL.addView(revUserIcon_IV);
            friendsIconsWrapper_LL.addView(imgView_LL);
        }

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        scrollView.addView(friendsIconsWrapper_LL);

        friendsWidgetContainer_LL.addView(scrollView);

        if (revConns.size() > 0) {
            // friendsWidgetContainer_LL.addView(this.currItemConstDetailsViewSummury(revEntityGUIDs.get(0)));
            // friendsWidgetContainer_LL.addView(this.recentRevEntityPics(revEntityGUIDs.get(0)));
        } else {
            String timelineEntryStatus;

            if (revPageOwnerEntity.get_remoteRevEntityGUID().longValue() == loggedInRemoteRevEntityGUID) {
                timelineEntryStatus = "No AcTiviTiEs posTED yET. pLEAsE puBLisH somETHiNG.";
            } else {
                timelineEntryStatus = "No AcTiviTiEs posTED yET FRom " + pageOwnerName;
            }

            TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.7f);
            textView.setText(timelineEntryStatus);

            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL * 2, 0, 0, 0);
            textView.setPadding((int) (mediumMargin * 1.5), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
            textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));

            friendsWidgetContainer_LL.addView(textView);
        }

        return friendsWidgetContainer_LL;
    }

    private View addedSocialCircles() {
        LinearLayout revContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revContainer_LL.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout revHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revHeaderWrapper_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .6), 0, 0, 0);

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_500_dark));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV_LP.gravity = Gravity.CENTER_VERTICAL;
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);

        Picasso.get()
                .load(R.drawable.ic_group_work)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(headerIcon_IV);

        TextView headerTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        if (revPageOwnerEntity.get_remoteRevEntityGUID().longValue() != loggedInRemoteRevEntityGUID) {
            String txtTell;

            if (revPageOwnerEntity.get_revEntityType().equals("rev_user_entity")) {
                txtTell = REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(pageOwnerName, 12)) + " HAs JoiNED";
            } else {
                txtTell = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(pageOwnerName, 12) + " is coNNEcTED To";
            }

            headerTtl_TV.setText("spAcEs " + txtTell);
        } else {
            headerTtl_TV.setText("spAcEs i HAvE JoiNED");
        }

        revHeaderWrapper_LL.addView(headerIcon_IV);
        revHeaderWrapper_LL.addView(headerTtl_TV);

        revContainer_LL.addView(revHeaderWrapper_LL);

        LinearLayout revObjectsListingsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revObjectsListingsContainer_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .6), 0, 0, 0);

        List<RevEntity> revEntitySubscriptionsList = revPersEntityInfoWrapperModel.getRevEntitySubscriptionsList();

        if (revEntitySubscriptionsList == null || revEntitySubscriptionsList.size() < 1) {
            TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);

            String txtTell;

            if (revPageOwnerEntity.get_revEntityType().equals("rev_user_entity")) {
                if (revPageOwnerEntity.get_remoteRevEntityGUID().equals(loggedInRemoteRevEntityGUID)) {
                    txtTell = "i HAvE NoT yET JoiNED ANY spAcEs.";
                } else {
                    txtTell = REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(pageOwnerName, 12)) + " HAs NoT yET JoiNED ANy spAcES.";
                }
            } else {
                txtTell = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(pageOwnerName, 12) + " HAs NoT yET BEEN coNNEcTED To ANy oTHER spAcEs.";
            }

            textView.setText(txtTell);
            textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));

            textView.setPadding((int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.2), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);

            revObjectsListingsContainer_LL.addView(textView);
        } else {
            revObjectsListingsContainer_LL.addView(this.revJoinedSpaces(revEntitySubscriptionsList));
        }

        revContainer_LL.addView(revObjectsListingsContainer_LL);

        return revContainer_LL;
    }

    private View revJoinedSpaces(List<RevEntity> revMySpaces) {
        LinearLayout revJoinedSpacesContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        textView.setText("No uNcoNFiRmED suBscRipTioNs.");

        textView.setPadding((int) (imageSize + RevLibGenConstantine.REV_MARGIN_TINY), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        revJoinedSpacesContainer_LL.addView(textView);

        int i = 0;

        for (final RevEntity revGrpEntityItem : revMySpaces) {
            if (revGrpEntityItem.get_remoteRevEntityGUID() < 1) {
                i++;
                textView.setText(i + " . . . uNcoNFiRmED spAcE suBscRipTioNs.");
            }

            if (revGrpEntityItem == null) continue;

            revGrpEntityItem.get_revEntityMetadataList().addAll(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revGrpEntityItem.get_revEntityGUID()));

            LinearLayout socialProfileWrapper = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) socialProfileWrapper.getLayoutParams()).setMargins(0, tinyMargin, 0, 0);

            socialProfileWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(revGrpEntityItem.get_revEntityGUID());
                    RevVarArgsData passRVD = new RevVarArgsData(mContext);
                    passRVD.setRevPersEntityInfoWrapperModel(revPersEntityInfoWrapperModel);
                    passRVD.setRevEntity(revPersEntityInfoWrapperModel.getRevEntity());

                    REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(passRVD).getUserMainCenterCctViewLL());
                }
            });

            GradientDrawable border = new GradientDrawable();
            border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
            border.setGradientType(RECTANGLE);

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            layerDrawable.setLayerInset(0, -2, -2, -2, 1);

            String groupName = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revGrpEntityItem.get_revEntityMetadataList(), "rev_entity_full_names_value");
            groupName = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(groupName, 55);

            if (groupName == null || groupName.isEmpty())
                groupName = "UNSET";

            CharSequence revGrpNameFinalText;

            if (revGrpEntityItem.get_revEntityGUID().longValue() == REV_SESSION_SETTINGS.getRevLoggedInEntityGuid().longValue()) {
                String revMyself_S = "mysELF ( ";
                String revMyselfTail_S = " )";

                SpannableString revMyself_S_Span = new SpannableString(revMyself_S);
                SpannableString revMyselfTail_S_Span = new SpannableString(revMyselfTail_S);
                revMyself_S_Span.setSpan(new StyleSpan(Typeface.BOLD), 0, revMyself_S.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                revMyselfTail_S_Span.setSpan(new StyleSpan(Typeface.BOLD), 0, revMyselfTail_S.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                revMyself_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_SMALL * .8)), 0, revMyself_S.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                revMyselfTail_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_SMALL * .8)), 0, revMyselfTail_S.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                revGrpNameFinalText = TextUtils.concat(revMyself_S_Span, groupName, revMyselfTail_S_Span);
            } else {
                revGrpNameFinalText = groupName;
            }

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(socialProfileWrapper, layerDrawable);
            TextView socialProfileName_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
            socialProfileName_TV.setGravity(Gravity.CENTER_VERTICAL);
            socialProfileName_TV.setText(revGrpNameFinalText);

            REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(socialProfileName_TV);

            Drawable socialProfileListingIcon_DR = mContext.getResources().getDrawable(R.drawable.ic_group_black_48dp);
            socialProfileListingIcon_DR.setBounds(0, 0, imageSize, imageSize);
            DrawableCompat.setTint(socialProfileListingIcon_DR, ContextCompat.getColor(mContext, R.color.gray_text));

            socialProfileName_TV.setCompoundDrawablePadding((RevLibGenConstantine.REV_MARGIN_TINY));
            socialProfileName_TV.setCompoundDrawables(socialProfileListingIcon_DR, null, null, null);

            /* Go to */

            String s_TotalCount = "0";

            SpannableString s_totalCount_Span = new SpannableString(s_TotalCount);
            s_totalCount_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, s_TotalCount.length(), SPAN_INCLUSIVE_INCLUSIVE);
            s_totalCount_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, s_TotalCount.length(), 0); // set color
            s_totalCount_Span.setSpan(new StyleSpan(Typeface.BOLD), 0, s_totalCount_Span.length(), 0);

            /* txt */
            String s_membersTell = "people";

            SpannableString s_membersTell_Span = new SpannableString(s_membersTell);
            s_membersTell_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 0, s_membersTell.length(), SPAN_INCLUSIVE_INCLUSIVE);

            // let's put both spans together with a separator and all
            CharSequence finalText = TextUtils.concat(s_TotalCount, " ", s_membersTell_Span);

            TextView goToTab_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
            goToTab_TV.setGravity(Gravity.CENTER_VERTICAL);
            goToTab_TV.setText(finalText);

            REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(goToTab_TV);

            Drawable goTo_DR = mContext.getResources().getDrawable(R.drawable.ic_trending_flat_black_48dp);
            goTo_DR.setBounds(0, 0, imageSize, imageSize);
            DrawableCompat.setTint(goTo_DR, ContextCompat.getColor(mContext, R.color.teal_dark));

            goToTab_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));
            goToTab_TV.setCompoundDrawables(null, null, goTo_DR, null);

            socialProfileWrapper.addView(socialProfileName_TV);
            socialProfileWrapper.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
            socialProfileWrapper.addView(goToTab_TV);
            revJoinedSpacesContainer_LL.addView(socialProfileWrapper);
        }

        return revJoinedSpacesContainer_LL;
    }

    private View peopleYouMayKnowWidget() {
        LinearLayout peopleYouMayKnowWidgetContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) peopleYouMayKnowWidgetContainer_LL.getLayoutParams()).setMargins((int) (mediumMargin * 1.1), RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        LinearLayout peopleYouMayKnowTtlWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        peopleYouMayKnowTtlWrapper_LL.setPadding(0, 0, 0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .1));

        TextView peopleYouMayKnowTtl_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        peopleYouMayKnowTtl_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("1, 550+ pEopLE i mAy kNow"));
        peopleYouMayKnowTtl_TV.setLayoutParams(centerVertical_LP);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Drawable more_DR = mContext.getResources().getDrawable(rev.ca.rev_lib_core_views.R.drawable.ic_trending_flat_black_48dp);
        more_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(more_DR, ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.teal_dark));

        TextView peopleYouMayKnowCount = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        peopleYouMayKnowCount.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("+55 . . . ."));
        peopleYouMayKnowCount.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.colorPrimary));
        peopleYouMayKnowCount.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        peopleYouMayKnowCount.setCompoundDrawables(null, null, more_DR, null);

        peopleYouMayKnowTtlWrapper_LL.addView(peopleYouMayKnowTtl_TV);
        peopleYouMayKnowTtlWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        peopleYouMayKnowTtlWrapper_LL.addView(peopleYouMayKnowCount);

        peopleYouMayKnowWidgetContainer_LL.addView(peopleYouMayKnowTtlWrapper_LL);

        LinearLayout formerClassmatesWrapper_LL = (LinearLayout) new RevRelatedPeopleIconsListing(mContext).relatedPeopleIconPics();
        peopleYouMayKnowWidgetContainer_LL.addView(formerClassmatesWrapper_LL);

        return peopleYouMayKnowWidgetContainer_LL;
    }

    private View findPeopleWidgetView() {

        // https://stackoverflow.com/questions/20120725/layerdrawable-programmatically

        ShapeDrawable background = new ShapeDrawable();
        background.getPaint().setColor(mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));

        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(mContext.getResources().getColor(R.color.rev_default_light));

        ShapeDrawable clip = new ShapeDrawable();
        clip.getPaint().setColor(mContext.getResources().getColor(R.color.rev_default_light));

        Drawable[] layers = {background, border, clip};
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        layerDrawable.setLayerInset(1, 1, 0, 1, 1);
        layerDrawable.setLayerInset(2, 0, 0, 0, 10);

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * .7);

        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_find_in_page_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        String connTellTxt = "    FiND pEopLe . . . . .";
        EditText findPeopleInput_ET = new RevCoreInputFormEditText(mContext).getRevEditText_NO_BORDERS_NO_PADDING();
        findPeopleInput_ET.setMaxLines(1);
        findPeopleInput_ET.setPadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), 0, RevLibGenConstantine.REV_MARGIN_LARGE, 1);

        findPeopleInput_ET.setHint(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(connTellTxt));
        findPeopleInput_ET.setCompoundDrawables(generalPointingAverageImg, null, null, null);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(findPeopleInput_ET, layerDrawable);

        findPeopleInput_ET.setLayoutParams(insetMarginFirst_LP);
        ((LinearLayout.LayoutParams) findPeopleInput_ET.getLayoutParams()).
                setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.2), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_TINY);

        return findPeopleInput_ET;
    }

    private View getSpaceOptionsView() {
        LinearLayout BAGOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        ((LinearLayout.LayoutParams) BAGOptionsWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) BAGOptionsWrapper_LL.getLayoutParams()).setMargins(
                RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        BAGOptionsWrapper_LL.setPadding(
                (int) (mediumMargin * 1.1), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);

        BAGOptionsWrapper_LL.setBackgroundColor(mContext.getResources().getColor(R.color.teal_light));

        CreateRevMenuAreaViewContainerPublisher createRevMenuAreaViewContainerPublisher = new CreateRevMenuAreaViewContainerPublisher(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(new RevVarArgsData()));

        if (createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL() != null) {
            int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

            String createNewSpaceTell_S = "sHARE picTuREs, viDEos, impoRTANT DATEs oR EvENTs wiTH FRiENDs/ FAmiLy/ coLLEAGues viA spAcEs";

            SpannableString createNewSpaceTell_S_Span = new SpannableString(createNewSpaceTell_S);
            createNewSpaceTell_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .8)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
            createNewSpaceTell_S_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, createNewSpaceTell_S.length(), 0); // set color

            TextView createNewBag_TV = (TextView) createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL();
            createNewBag_TV.setText(createNewSpaceTell_S_Span);
            createNewBag_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));

            Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.baseline_playlist_add_black_48dp);
            listedVehsBttnImg.setBounds(0, 0, imgSize, imgSize);
            DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.revGreen));
            createNewBag_TV.setCompoundDrawables(listedVehsBttnImg, null, null, null);
            createNewBag_TV.setGravity(Gravity.CENTER_VERTICAL);

            createNewBag_TV.setCompoundDrawablePadding(RevLibGenConstantine.REV_MARGIN_TINY);

            BAGOptionsWrapper_LL.addView(createNewBag_TV);
        }

        return BAGOptionsWrapper_LL;
    }

    private View suggestedSpaces() {
        LinearLayout revContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revContainer_LL.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);
        ((LinearLayout.LayoutParams) revContainer_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout revHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revHeaderWrapper_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .6), 0, 0, 0);

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_500_dark));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);
        ((LinearLayout.LayoutParams) headerIcon_IV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        Picasso.get()
                .load(R.drawable.ic_filter_list_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(headerIcon_IV);

        TextView headerTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        headerTtl_TV.setText("REccommENDED spAcEs");
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        revHeaderWrapper_LL.addView(headerIcon_IV);
        revHeaderWrapper_LL.addView(headerTtl_TV);

        LinearLayout revObjectsListingsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revObjectsListingsContainer_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .8), 0, 0, 0);

        revObjectsListingsContainer_LL.addView(this.suggestedBagsListingItems());

        revContainer_LL.addView(revHeaderWrapper_LL);
        revContainer_LL.addView(revObjectsListingsContainer_LL);

        return revContainer_LL;
    }

    private View suggestedBagsListingItems() {
        LinearLayout suggestedBagsListingItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        List<RevEntity> revEntities = Arrays.asList(new RevPersLibRead().revPersGetALLRevEntityTYPE("rev_group_entity"));

        if (revEntities.size() < 1) {
            TextView noSuggestSpaces = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.7f);
            noSuggestSpaces.setText("THERE ARE No suGGEsTioNs so FAR....");

            noSuggestSpaces.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            noSuggestSpaces.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
            noSuggestSpaces.setPadding((int) (mediumMargin * 1.5), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
            noSuggestSpaces.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));

            suggestedBagsListingItemsContainer_LL.addView(noSuggestSpaces);
        } else {
            for (int i = 0; i < revEntities.size(); i++) {
                RevVarArgsData passRVD = new RevVarArgsData(mContext);
                passRVD.setRevEntity(revEntities.get(i));
                suggestedBagsListingItemsContainer_LL.addView(new RevCustomListingViewRecommendedBagsListingsView(passRVD).getRevCustomListingViewRecommendedBagsListingsView());
            }
        }

        return suggestedBagsListingItemsContainer_LL;
    }
}
