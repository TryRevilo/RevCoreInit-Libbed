package rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_objects_listings;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.Arrays;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 2/12/18.
 */

public class RevCommentsListingView {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevEntity revPublisherEntity;
    private long revPublisherEntityGUID;

    public RevCommentsListingView(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        revPublisherEntity = revVarArgsData.getRevEntity().get_revPublisherEntity();
    }

    public View getRevCommentsListingView(final RevEntity revEntity) {
        if (revEntity.get_revEntityMetadataList() == null || revEntity.get_revPublisherEntity() == null)
            return null;

        revPublisherEntityGUID = revPublisherEntity.get_remoteRevEntityGUID();

        int revPadding = RevLibGenConstantine.REV_MARGIN_TINY;

        LinearLayout revRevCommentsListingViewWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revRevCommentsListingViewWrapper_LL.getLayoutParams()).setMargins(0, 2, 0, 0);
        revRevCommentsListingViewWrapper_LL.setPadding(0, revPadding, revPadding, revPadding);

        if (revPublisherEntityGUID == REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid().longValue()) {
            revRevCommentsListingViewWrapper_LL.setBackgroundColor(Color.parseColor("#dff0d8"));
        }

        LinearLayout revRevCommentsListingViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revRevCommentsListingViewWrapper_LL.addView(this.getLeftyImages());

        TextView revPublisherEntity_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        revPublisherEntity_TV.setText(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPublisherEntity.get_revEntityMetadataList(), "rev_entity_full_names_value") + " ");

        TextView revPublishTime = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublishTime.setText(revEntity.get_timeCreated());

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        String revCommentValue_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_comment_value");
        textView.setText(revCommentValue_S);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.addView(revPublisherEntity_TV);
        linearLayout.addView(revPublishTime);

        revRevCommentsListingViewContainer_LL.addView(linearLayout);
        revRevCommentsListingViewContainer_LL.addView(textView);

        revRevCommentsListingViewWrapper_LL.addView(revRevCommentsListingViewContainer_LL);

        /** START REV LIKES **/

        RevVarArgsData revPassRVD = new RevVarArgsData(mContext);
        revPassRVD.setRevViewType("RevCreateLikeInputForm");
        revPassRVD.setRevEntity(revEntity);
        revPassRVD.setRevPassViews(Arrays.asList((View) revRevCommentsListingViewWrapper_LL));
        IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revPassRVD);

        if (iRevInputFormView != null) {
            View revLikeView = iRevInputFormView.createRevInputForm();

            if (revLikeView != null) {
                revRevCommentsListingViewContainer_LL.addView(revLikeView);
            }
        }

        /** END REV LIKES **/

        return revRevCommentsListingViewWrapper_LL;
    }

    private View getLeftyImages() {
        long revEntityGUID = revPublisherEntity.get_remoteRevEntityGUID();

        String revProfileIconPath = "";
        int revUserIconmageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView userIconImageView = new ImageView(mContext);
        userIconImageView.bringToFront();

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(revUserIconmageSize, revUserIconmageSize);
        imageView_LP.gravity = (Gravity.TOP | Gravity.LEFT);
        imageView_LP.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        userIconImageView.setLayoutParams(imageView_LP);

        /** TAB **/
        int imageViewBorderSize = 8;

        GradientDrawable imageViewBorderTop = new GradientDrawable();
        imageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.greyDark));
        imageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.greyDark));
        imageViewBorderTop.setGradientType(RECTANGLE);

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorderTop, imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);
        imageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);

        if (revEntityGUID == REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid()) {
            revProfileIconPath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(
                    new RevPersLibRead().revPersGetALLRevEntityMetadataByRevEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid()), "rev_user_icon_path_value");
        } else {
            revProfileIconPath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPublisherEntity.get_revEntityMetadataList(), "rev_user_icon_path_value");
        }

        File revProfilePicFile = new File(revProfileIconPath);

        if (revProfilePicFile.exists()) {
            final Transformation transformation = new RoundedCornersTransformation(0, 1);

            Picasso.get()
                    .load(revProfilePicFile)
                    .placeholder(R.drawable.ic_account_box_black_48dp)
                    .resize(revUserIconmageSize, revUserIconmageSize)
                    .centerCrop()
                    .transform(transformation)
                    .into(userIconImageView);
        } else {
            Picasso.get()
                    .load(R.drawable.ic_account_box_black_48dp)
                    .resize(revUserIconmageSize, revUserIconmageSize)
                    .centerCrop()
                    .into(userIconImageView);
        }

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(userIconImageView, imageViewLayerDrawable);

        LinearLayout leftyImagesContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        ((LinearLayout.LayoutParams) leftyImagesContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        leftyImagesContainer_LL.addView(userIconImageView);

        ImageView pointerInImageView = new ImageView(mContext);
        pointerInImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.black_OPAQUE), android.graphics.PorterDuff.Mode.MULTIPLY);
        pointerInImageView.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        int imageSize = 32;

        Picasso.get()
                .load(R.drawable.ic_subdirectory_arrow_right_black_24dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(pointerInImageView);

        LinearLayout.LayoutParams pointerInImageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pointerInImageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);
        pointerInImageView_LP.setMargins(-RevLibGenConstantine.REV_MARGIN_TINY, (int) (-RevLibGenConstantine.REV_MARGIN_TINY * .7), 0, 0);
        pointerInImageView.setLayoutParams(pointerInImageView_LP);

        leftyImagesContainer_LL.addView(pointerInImageView);

        return leftyImagesContainer_LL;
    }
}