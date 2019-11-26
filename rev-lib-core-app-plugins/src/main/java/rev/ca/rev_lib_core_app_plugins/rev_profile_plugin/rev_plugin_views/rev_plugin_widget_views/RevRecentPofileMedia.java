package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views.RevFullPagePhotoViewTab;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevFileFunctions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevRecentPofileMedia {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevEntity revEntity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    String pageOwnerName;

    List<RevEntity> revImages = new ArrayList<>();
    List<Long> addedGUIDs = new ArrayList<>();

    public RevRecentPofileMedia(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        pageOwnerName = "Unset Names";

        pageOwnerName = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");
    }

    public View getRevRecentPofileMedia() {
        LinearLayout revItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revItemsContainer_LL.addView(this.revBriefStudyTopicsView());

        return revItemsContainer_LL;
    }

    private View revBriefStudyTopicsView() {
        LinearLayout revContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));

        LinearLayout revHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revHeaderWrapper_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .6), RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        headerIcon_IV_LP.gravity = (Gravity.BOTTOM);
        headerIcon_IV_LP.setMargins(0, 0, 0, (int) (-RevLibGenConstantine.REV_MARGIN_TINY * .4));
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);

        Picasso.get()
                .load(R.drawable.ic_publish_black_48dp)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(headerIcon_IV);

        TextView headerTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        headerTtl_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("upLoADs"));
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).gravity = Gravity.BOTTOM;
        ((LinearLayout.LayoutParams) headerTtl_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        revHeaderWrapper_LL.addView(headerIcon_IV);
        revHeaderWrapper_LL.addView(headerTtl_TV);

        revContainer_LL.addView(revHeaderWrapper_LL);
        revContainer_LL.addView(this.revDrawItems());

        return revContainer_LL;
    }

    private List<RevEntity> revGetAllFiles(List<RevEntity> revTimelineEntities) {
        for (RevEntity revEntity : revTimelineEntities) {
            if (revEntity == null) continue;

            if (revEntity.get_revEntitySubType().equals("rev_file") && !addedGUIDs.contains(revEntity.get_remoteRevEntityGUID())) {
                revImages.add(revEntity);
                addedGUIDs.add(revEntity.get_remoteRevEntityGUID());
            }

            if (revEntity.get_revEntityChildrenList().size() > 0)
                revGetAllFiles(revEntity.get_revEntityChildrenList());
        }

        return revImages;
    }

    private View revDrawItems() {
        final LinearLayout itemsImages_SV_Wrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int borderWidth = 1;
        final int revRadius = 100;

        final Transformation transformation = new RoundedCornersTransformation(revRadius, 0);

        List<RevEntity> revTimelineEntities = revGetAllFiles(revVarArgsData.getRevPersEntityInfoWrapperModel().getRevTimelineEntities());
        Collections.shuffle(revTimelineEntities);

        for (int i = 0; i < revTimelineEntities.size(); i++) {
            RevEntity tlRevEntity = revTimelineEntities.get(i);

            if (tlRevEntity == null || tlRevEntity.getRevObjectEntity() == null || tlRevEntity.get_revEntityMetadataList().size() < 1)
                continue;

            final LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            imgView_LL.setClickable(true);

            String imgPath = tlRevEntity.get_revEntityMetadataList().get(0).get_metadataValue();

            if (!RevFileFunctions.REV_FILE_EXISTS(imgPath)) {
                imgPath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + imgPath;
            } else {
                imgPath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + tlRevEntity.get_revEntityMetadataList().get(0).get_metadataValue();
            }

            final String revFinalSelectImagePath = imgPath;

            final int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.5);

            final LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView_LP.gravity = Gravity.CENTER_VERTICAL;

            if (i != 0)
                imageView_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .4), 0, 0, 0);

            GradientDrawable border = new GradientDrawable();
            border.setStroke(borderWidth, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_100_dark));
            border.setGradientType(RECTANGLE);
            border.setCornerRadius((float) (revRadius));

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            layerDrawable.setLayerInset(0, borderWidth, borderWidth, borderWidth, borderWidth);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imgView_LL, layerDrawable);
            int padding = RevLibGenConstantine.REV_MARGIN_TINY;
            imgView_LL.setPadding(padding, padding, padding, padding);
            imgView_LL.setLayoutParams(imageView_LP);

            final ImageView itemImageView = new ImageView(mContext);
            itemImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, imgSize));
            Picasso.get()
                    .load(revFinalSelectImagePath)
                    .placeholder(R.drawable.ic_add_a_photo_black_48dp)
                    .error(R.drawable.ic_add_a_photo_black_48dp)
                    .resize(0, imgSize)
                    .transform(transformation)
                    .into(itemImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            imgView_LL.addView(itemImageView);
                            imgView_LL.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new RevFullPagePhotoViewTab(revVarArgsData).revEntityImageViewFullView(revFinalSelectImagePath);
                                }
                            });

                            itemsImages_SV_Wrapper_LL.addView(imgView_LL);
                        }

                        @Override
                        public void onError(Exception e) {
                        }
                    });

        }

        HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        scrollView.addView(itemsImages_SV_Wrapper_LL);

        return scrollView;
    }
}
