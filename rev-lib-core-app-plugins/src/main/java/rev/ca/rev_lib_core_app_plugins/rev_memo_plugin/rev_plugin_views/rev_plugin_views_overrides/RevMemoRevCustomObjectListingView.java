package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_full_entity_view.RevMemoFullEntityView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 12/1/17.
 */

public class RevMemoRevCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevMemoRevCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_memo";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(final RevEntity revEntity) {
        if (revEntity == null) {
            return null;
        }

        LinearLayout itemContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) itemContainer_LL.getLayoutParams()).setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);

        TextView revEntityDesc_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.9f);

        String revEntityDesc = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_memo_message_value");
        revEntityDesc = revEntityDesc.replace("\n", " ").replace("\r", " ");

        revEntityDesc_TV.setText(REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(revEntityDesc, 100));
        revEntityDesc_TV.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        revEntityDesc_TV.setBackgroundColor(mContext.getResources().getColor(R.color.teal_100_dark));
        revEntityDesc_TV.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);

        itemContainer_LL.addView(this.getGeneralPointingUpVotes(revEntity));
        itemContainer_LL.addView(revEntityDesc_TV);

        itemContainer_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revEntity);

                RevPop.initiatePopupWindow_MINIMAL_TOP_MATCH_WIDTH(new RevMemoFullEntityView(postRVD).getRevMemoFullEntityView());
            }
        });

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_memo");
        revEntityListingViewOverrideVOM.setRevEntity(revEntity);
        revEntityListingViewOverrideVOM.setOverrideView(itemContainer_LL);

        return revEntityListingViewOverrideVOM;
    }

    @Override
    public View createRevObjectListingFooterOptionView() {
        LinearLayout revObjectListingFooterOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        RevObjectListingViewFooterTabs revObjectListingViewFooterTabs = new RevObjectListingViewFooterTabs(revVarArgsData);

        LinearLayout.LayoutParams revObjectListingFooterOptionsWrapper_LL_LP = (LinearLayout.LayoutParams) revObjectListingFooterOptionsWrapper_LL.getLayoutParams();
        revObjectListingFooterOptionsWrapper_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revLikeItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revCommentItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revMoreOptionsItem());

        return revObjectListingFooterOptionsWrapper_LL;
    }

    private View getGeneralPointingUpVotes(RevEntity revEntity) {
        /* PROFILE */

        String profileNameTtl = "Memo";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        /**  REV ENTITY NAME **/

        String revEntityName_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_memo_subject_value"), 100);

        revEntityName_S = revEntityName_S.replace("\n", " ").replace("\r", " ");

        SpannableString revEntityName_S_Span = new SpannableString(revEntityName_S);
        revEntityName_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revEntityName_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        /** END SPANNING **/

        /** TITLE **/

        // let's put both spans together with a separator and all
        CharSequence finalText = TextUtils.concat(profileNameTtlSpan, " ", revEntityName_S_Span);

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);
        Picasso.get()
                .load(R.drawable.ic_vertical_align_top_black_48dp)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        LinearLayout revEntityTopMainItems = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revEntityTopMainItems.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);

        LinearLayout revEntityTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView revEntityTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityTitle.setText(finalText);
        revEntityTitle.setGravity(Gravity.TOP);

        LinearLayout.LayoutParams revEntityTitle_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        revEntityTitle_LP.setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
        revEntityTitle.setLayoutParams(revEntityTitle_LP);

        revEntityTitle_LL.addView(imageView);
        revEntityTitle_LL.addView(revEntityTitle);

        /** TIME PUBLISHED SPAN **/

        String revEntityTimePublished_S = revEntity.get_timeCreated();

        SpannableString revEntityTimePublished_Span = new SpannableString(revEntityTimePublished_S);
        revEntityTimePublished_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .7)), 0, revEntityTimePublished_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence revEntityTimeFinalText = TextUtils.concat(revEntityTimePublished_Span);

        TextView revEntityTime = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityTime.setText(revEntityTimeFinalText);

        revEntityTopMainItems.addView(revEntityTitle_LL);
        revEntityTopMainItems.addView(revEntityTime);

        return revEntityTopMainItems;
    }
}
