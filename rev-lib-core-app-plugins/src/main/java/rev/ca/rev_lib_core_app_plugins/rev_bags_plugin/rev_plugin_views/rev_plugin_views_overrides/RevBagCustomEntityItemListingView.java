package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views.RevMinFooterImagesScrollerWidgetView;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
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

public class RevBagCustomEntityItemListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private List<RevEntity> revPicAlbumFiles = new ArrayList<>();

    public RevBagCustomEntityItemListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_bag";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(final RevEntity revEntity) {
        LinearLayout itemContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        itemContainer_LL.setClickable(true);
        ((LinearLayout.LayoutParams) itemContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);

        View revItemListViewWrapper_LL = this.getGeneralPointingUpVotes(revEntity);

        if (revItemListViewWrapper_LL == null) return null;

        itemContainer_LL.addView(revItemListViewWrapper_LL);

        itemContainer_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = new RevVarArgsData(mContext);
                postRVD.setRevEntity(revEntity);
                new RevUserFullProfileView(postRVD).revResetUserMainCenterCctViewLL();
            }
        });

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_user_entity");
        revEntityListingViewOverrideVOM.setRevEntity(revEntity);
        revEntityListingViewOverrideVOM.setOverrideView(itemContainer_LL);

        RevVarArgsData argsData = new RevVarArgsData();

        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("isDecorated", "false");

        argsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

        revEntityListingViewOverrideVOM.setRevVarArgsData(argsData);

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
        LinearLayout revEntityTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LinearLayout revEntityTopMainItems = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        RevEntity revEntityInfo = revEntity.get_revInfoEntity();

        if (revEntityInfo != null) {

            /** REV PICS **/
            for (RevEntity revPicsAlbumEntity : revEntityInfo.get_revEntityChildrenList()) {
                if (revPicsAlbumEntity != null && revPicsAlbumEntity.get_revEntitySubType().equals("rev_pics_album")) {
                    revPicAlbumFiles = revPicsAlbumEntity.get_revEntityChildrenList();
                    break;
                }
            }

            /**  REV ENTITY NAME **/

            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

            String revSpaceCompanyName_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityInfo.get_revEntityMetadataList(), "rev_grp_entity_company_name");
            SpannableString revSpaceCompanyName_Span = null;

            if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revSpaceCompanyName_S)) {
                revSpaceCompanyName_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(revSpaceCompanyName_S, 85);

                revSpaceCompanyName_Span = new SpannableString(revSpaceCompanyName_S);
                revSpaceCompanyName_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);

                revSpaceCompanyName_Span.setSpan(boldSpan, 1, revSpaceCompanyName_S.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                revSpaceCompanyName_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .9)), 2, revSpaceCompanyName_S.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
                revSpaceCompanyName_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, revSpaceCompanyName_S.length(), 0); // set color
            }

            /** END .CO NAME **/

            String revSpaceAreaDptDesc_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");
            SpannableString revSpaceAreaDptDesc_Span = null;

            if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revSpaceAreaDptDesc_S)) {
                revSpaceAreaDptDesc_Span = new SpannableString(revSpaceAreaDptDesc_S);

                revSpaceAreaDptDesc_Span.setSpan(boldSpan, 1, revSpaceAreaDptDesc_S.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                revSpaceAreaDptDesc_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revSpaceAreaDptDesc_S.length(), SPAN_INCLUSIVE_INCLUSIVE);
                revSpaceAreaDptDesc_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, revSpaceAreaDptDesc_S.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
            }

            /**  END  REV ENTITY NAME **/

            /** END SPANNING **/

            if (revSpaceCompanyName_Span == null || revSpaceAreaDptDesc_Span == null) return null;

            // let's put both spans together with a separator and all
            CharSequence finalText = TextUtils.concat(revSpaceCompanyName_Span, "\n ", revSpaceAreaDptDesc_Span);

            String imgPath = null;

            if (!revPicAlbumFiles.isEmpty()) {
                imgPath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPicAlbumFiles.get(0).get_revEntityMetadataList(), "rev_remote_file_name");
            }

            ImageView imageView = new ImageView(mContext);

            int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .7);

            Transformation transformation = new RoundedCornersTransformation(100, 1);

            if (imgPath != null) {
                Picasso.get()
                        .load(imgPath)
                        .placeholder(R.drawable.ic_account_circle_black_48dp)
                        .resize(imageSize, 0)
                        .centerCrop()
                        .transform(transformation)
                        .into(imageView);
            } else {
                Picasso.get()
                        .load(R.drawable.ic_account_circle_black_48dp)
                        .placeholder(R.drawable.ic_account_circle_black_48dp)
                        .resize(imageSize, 0)
                        .centerCrop()
                        .transform(transformation)
                        .into(imageView);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
            layoutParams.gravity = (Gravity.TOP);
            imageView.setLayoutParams(layoutParams);

            TextView revEntityTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
            revEntityTitle.setGravity(Gravity.TOP);
            revEntityTitle.setText(finalText);
            revEntityTitle.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

            LinearLayout.LayoutParams revEntityTitle_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            revEntityTitle_LP.setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
            revEntityTitle.setLayoutParams(revEntityTitle_LP);

            revEntityTitle_LL.addView(imageView);

            revEntityTopMainItems.addView(revEntityTitle);

            if (!revPicAlbumFiles.isEmpty()) {
                RevVarArgsData revPassRVD = new RevVarArgsData(mContext);
                revPassRVD.setRevEntity(revEntity);
                LinearLayout mainImageContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                revEntityTopMainItems.addView(mainImageContainer_LL);
                View revProfileImagesWrapper_LL = new RevMinFooterImagesScrollerWidgetView(revPassRVD).getImagesFooterScroller(revPicAlbumFiles, mainImageContainer_LL);
                revProfileImagesWrapper_LL.setBackgroundColor(mContext.getResources().getColor(R.color.colorTransparent));
                revEntityTopMainItems.addView(revProfileImagesWrapper_LL);
            }
        }

        revEntityTitle_LL.addView(revEntityTopMainItems);

        return revEntityTitle_LL;
    }
}
