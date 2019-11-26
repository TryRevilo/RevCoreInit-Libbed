package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views.RevPhotoAlbumClusterListingWidget;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 12/1/17.
 */

public class KiwiRevCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevEntity revEntity;
    private RevEntity revEntityPublisher;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    public KiwiRevCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_kiwi";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(RevEntity revEntity) {
        this.revEntity = revEntity;
        this.revEntityPublisher = revEntity.get_revPublisherEntity();

        if (revEntity == null || this.revEntityPublisher == null) {
            return null;
        }

        LinearLayout itemWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout itemContainerLeft_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        ((LinearLayout.LayoutParams) itemContainerLeft_LL.getLayoutParams()).width = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
        ((LinearLayout.LayoutParams) itemContainerLeft_LL.getLayoutParams()).height = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
        ((LinearLayout.LayoutParams) itemContainerLeft_LL.getLayoutParams()).gravity = Gravity.TOP;
        ((LinearLayout.LayoutParams) itemContainerLeft_LL.getLayoutParams()).weight = 0;

        LinearLayout itemContainerRight_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        ((LinearLayout.LayoutParams) itemContainerRight_LL.getLayoutParams()).weight = 1;
        ((LinearLayout.LayoutParams) itemContainerRight_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        ImageView revUserIcon_IV = new ImageView(mContext);

        File revUserIconFile = new File(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityPublisher.get_revEntityMetadataList(), "rev_user_icon_path_value"));

        Picasso.get()
                .load(revUserIconFile)
                .placeholder(R.drawable.ic_account_circle_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(revUserIcon_IV);

        itemContainerLeft_LL.addView(revUserIcon_IV);

        TextView revEntityDesc_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();

        String revKiwi_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_kiwi_value");

        if (REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revKiwi_S)) {
            return null;
        }

        SpannableString revKiwi_S_Span = new SpannableString(revKiwi_S);
        revKiwi_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        revKiwi_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 1, 2, SPAN_INCLUSIVE_INCLUSIVE);
        revKiwi_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_SMALL), 2, (revKiwi_S.length() - (revKiwi_S.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
        revKiwi_S_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, revKiwi_S.length(), 0);

        revEntityDesc_TV.setText(revKiwi_S_Span);

        LinearLayout revKiwiQuoteWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        ImageView revQuote_IV = new ImageView(mContext);

        LinearLayout.LayoutParams revQuote_IV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        revQuote_IV_LP.gravity = (Gravity.TOP);

        Picasso.get()
                .load(R.drawable.icons8_quote_left_48)
                .placeholder(R.drawable.ic_account_circle_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(revQuote_IV);

        revUserIcon_IV.setLayoutParams(revQuote_IV_LP);

        revKiwiQuoteWrapper_LL.addView(revQuote_IV);
        revKiwiQuoteWrapper_LL.addView(revEntityDesc_TV);

        LinearLayout revObjectListingViewFooterWrapper_LL = (LinearLayout) new RevObjectListingViewFooterTabs(revVarArgsData).getRevObjectListingViewFooterTabs();
        LinearLayout.LayoutParams revObjectListingViewFooterWrapper_LL_LP = (LinearLayout.LayoutParams) revObjectListingViewFooterWrapper_LL.getLayoutParams();
        revObjectListingViewFooterWrapper_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        itemContainerRight_LL.addView(this.revPublisherEntityView(revEntity));
        itemContainerRight_LL.addView(revKiwiQuoteWrapper_LL);

        /** START REV LIKES **/

        RevVarArgsData revPassRVD = new RevVarArgsData(mContext);
        revPassRVD.setRevViewType("RevCreateLikeInputForm");
        revPassRVD.setRevEntity(revEntity);
        revPassRVD.setRevPassViews(Arrays.asList((View) itemContainerRight_LL));
        IRevInputFormView likeIRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revPassRVD);

        if (likeIRevInputFormView != null) {
            View revLikeView = likeIRevInputFormView.createRevInputForm();

            if (revLikeView != null) {
                itemContainerRight_LL.addView(revLikeView);
            }
        }

        /** END REV LIKES **/

        itemWrapper_LL.addView(itemContainerLeft_LL);
        itemWrapper_LL.addView(itemContainerRight_LL);

        LinearLayout objectListingContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        objectListingContainer.addView(itemWrapper_LL);

        if (revEntity.get_revEntityChildrenList().size() > 0) {
            RevEntity revPicsAlbum;

            for (RevEntity revPicsAlbumEntity : revEntity.get_revEntityChildrenList()) {
                if (revPicsAlbumEntity.get_revEntitySubType().equals("rev_pics_album")) {
                    revPicsAlbum = revPicsAlbumEntity;

                    for (long revEntitySubjectGUID : revPersLibRead.revPersGetALLRelSubjectGUIDs_By_TargetGUID(revPicsAlbum.get_revEntityGUID())) {
                        revPicsAlbum.get_revEntityChildrenList().add(revPersLibRead.revPersGetRevEntityByGUID(revEntitySubjectGUID));
                    }

                    LinearLayout revKiwiImages_LL = (LinearLayout) new RevPhotoAlbumClusterListingWidget(revVarArgsData).getRevPhotoAlbumClusterListingWidget(revPicsAlbum.get_revEntityChildrenList());

                    if (revKiwiImages_LL != null) {
                        ((LinearLayout.LayoutParams) revKiwiImages_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
                        objectListingContainer.addView(revKiwiImages_LL);
                    }
                    break;
                }
            }
        }

        if (RevConstantinePluggableViewsServices.REV_PLUGIN_INPUT_FORMS_MAP.containsKey("RevCreateRevCommentsInputForm")) {
            RevVarArgsData postRVD = new RevVarArgsData();
            postRVD.setmContext(revVarArgsData.getmContext());
            postRVD.setRevEntity(revVarArgsData.getRevEntity());
            postRVD.setRevViewType("RevCreateRevCommentsInputForm");

            for (RevEntity revCommentEntity : revVarArgsData.getRevEntity().get_revEntityChildrenList()) {
                if (revCommentEntity == null || !revCommentEntity.get_revEntitySubType().equals("rev_comment") || revCommentEntity.get_revPublisherEntity() == null)
                    continue;
            }

            IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
            View revCommentFooterView = iRevInputFormView.createRevInputForm();
            ((LinearLayout.LayoutParams) revCommentFooterView.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
            objectListingContainer.addView(revCommentFooterView);
        }

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_kiwi");
        revEntityListingViewOverrideVOM.setRevEntity(revEntity);
        revEntityListingViewOverrideVOM.setOverrideView(objectListingContainer);

        return revEntityListingViewOverrideVOM;
    }

    private View revPublisherEntityView(RevEntity revEntity) {
        LinearLayout publicationHeadersWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) publicationHeadersWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        TextView revPublisherNames_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        revPublisherNames_TV.setText(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityPublisher.get_revEntityMetadataList(), "rev_entity_full_names_value") + " ");

        TextView revPublicationTime_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
        revPublicationTime_TV.setText(revEntity.get_timeCreated());

        publicationHeadersWrapper_LL.addView(revPublisherNames_TV);
        publicationHeadersWrapper_LL.addView(revPublicationTime_TV);

        return publicationHeadersWrapper_LL;
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
}
