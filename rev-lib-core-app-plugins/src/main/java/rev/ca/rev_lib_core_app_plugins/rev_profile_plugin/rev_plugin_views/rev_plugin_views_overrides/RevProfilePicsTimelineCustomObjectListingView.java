package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.DynamicImageView;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/1/17.
 */

public class RevProfilePicsTimelineCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    public RevProfilePicsTimelineCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_profile_pics_album";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(RevEntity revEntity) {
        RevObjectEntity revObjectEntity;
        if (revEntity == null) {
            return null;
        } else {
            revObjectEntity = revEntity.getRevObjectEntity();
        }

        final FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_dark));

        /** TAB **/

        Drawable mainTagViewOptions_DR = mContext.getResources().getDrawable(R.drawable.baseline_zoom_out_map_black_48dp);
        mainTagViewOptions_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(mainTagViewOptions_DR, ContextCompat.getColor(mContext, R.color.revWhite));

        TextView mainTagViewOptions_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        mainTagViewOptions_TV.setText("Rev VIEW");
        mainTagViewOptions_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        mainTagViewOptions_TV.setCompoundDrawablePadding(0);
        mainTagViewOptions_TV.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);
        mainTagViewOptions_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        mainTagViewOptions_TV.setGravity(Gravity.CENTER_VERTICAL);

        mainTagViewOptions_TV.setCompoundDrawables(mainTagViewOptions_DR, null, null, null);

        FrameLayout.LayoutParams textView_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(0, 0, 0, 0);
        textView_LP.gravity = Gravity.TOP;
        mainTagViewOptions_TV.setLayoutParams(textView_LP);

        /** END TAB **/

        /** PUBLISHER ICOM **/

        String imgPath = "/storage/emulated/0/Music/audio_mc/Linkin Park AAC 320/Albums/2000 - Hybrid Theory/cover.jpg";
        ImageView imageView = new ImageView(mContext);

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .7);

        FrameLayout.LayoutParams imageView_LP = new FrameLayout.LayoutParams(imageSize, LayoutParams.WRAP_CONTENT);
        imageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);

        Picasso.get()
                .load(new File(imgPath))
                .placeholder(R.drawable.ic_account_circle_black_48dp)
                .resize(imageSize, 0)
                .into(imageView);

        imageView.setLayoutParams(imageView_LP);

        /** END PUBLISHER ICOM **/

        LinearLayout imagesWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        final LinearLayout mainImageWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LayoutParams mainImageWrapper_LL_LP = (LayoutParams) mainImageWrapper_LL.getLayoutParams();
        mainImageWrapper_LL_LP.setMargins(0, 0, 0, 1);

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        List<RevEntityMetadata> selectImages = new RevPersLibRead().revPersGetALLRevEntityMetadataByRevEntityGUID(revEntity.get_revEntityGUID());

        for (RevEntityMetadata revEntityMetadata : selectImages) {
            String selectImagePath = revEntityMetadata.get_metadataValue();

            final File picImgFile = new File(selectImagePath);

            if (picImgFile.exists()) {
                if (selectImages.indexOf(revEntityMetadata) == 0) {
                    final ImageView dynamicImageView = new DynamicImageView(mContext, null);

                    dynamicImageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    dynamicImageView.getViewTreeObserver()
                            .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                // Wait until layout to call Picasso
                                @Override
                                public void onGlobalLayout() {
                                    // Ensure we call this only once
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        dynamicImageView.getViewTreeObserver()
                                                .removeOnGlobalLayoutListener(this);
                                    }

                                    Picasso.get()
                                            .load(picImgFile)
                                            .placeholder(rev.ca.revlibpersistence.R.drawable.ic_add_a_photo_black_48dp)
                                            .into(dynamicImageView);
                                }
                            });

                    frameLayout.addView(dynamicImageView);
                } else {
                    if (selectImages.size() > 1) {
                        imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .6);

                        ImageView picImageView = new ImageView(RevLibGenConstantine.REV_CONTEXT);

                        Picasso.get()
                                .load(picImgFile)
                                .resize(0, imageSize)
                                .into(picImageView);

                        imagesWrapper_LL.addView(picImageView);
                    }
                }
            }
        }

        scrollView.addView(imagesWrapper_LL);

        RevVarArgsData passFooterRVD = new RevVarArgsData(mContext);
        passFooterRVD.setRevEntity(revEntity);

        LinearLayout revObjectListingViewFooterWrapper_LL = (LinearLayout) new RevObjectListingViewFooterTabs(passFooterRVD).getRevObjectListingViewFooterTabs();
        LayoutParams revObjectListingViewFooterWrapper_LL_LP = (LayoutParams) revObjectListingViewFooterWrapper_LL.getLayoutParams();
        revObjectListingViewFooterWrapper_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        /** PUBLISHER WRAPPER **/

        TextView revPublisherEntity = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublisherEntity.setText("Oliver Muchai ");
        revPublisherEntity.setGravity(Gravity.CENTER_VERTICAL);

        TextView revPublishTime = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublishTime.setText(revEntity.get_timeCreated());
        revPublishTime.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublishTime.setGravity(Gravity.CENTER_VERTICAL);
        revPublishTime.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        LinearLayout publisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        publisherWrapper_LL.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        publisherWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        publisherWrapper_LL.addView(revPublisherEntity);
        publisherWrapper_LL.addView(revPublishTime);

        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        textView.setText("+" + (selectImages.size() - 1) + " Edited profile  pictures");

        textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        textView.setCompoundDrawablePadding(0);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        publisherWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        publisherWrapper_LL.addView(textView);

        /** END PUBLISHER WRAPPER **/

        LinearLayout footerContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        FrameLayout.LayoutParams publicationHeadersWrapper_LL_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        publicationHeadersWrapper_LL_LP.setMargins(0, 0, 0, 0);
        publicationHeadersWrapper_LL_LP.gravity = Gravity.BOTTOM;
        footerContainer_LL.setLayoutParams(publicationHeadersWrapper_LL_LP);

        footerContainer_LL.addView(scrollView);
        footerContainer_LL.addView(publisherWrapper_LL);

        LinearLayout itemsBash_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        frameLayout.addView(mainImageWrapper_LL);
        frameLayout.addView(mainTagViewOptions_TV);
        frameLayout.addView(footerContainer_LL);
        frameLayout.addView(imageView);

        itemsBash_LL.addView(frameLayout);

        itemsBash_LL.addView(revObjectListingViewFooterWrapper_LL);

        if (revVarArgsData.getRevEntityGUID() != null) {

            RevVarArgsData passRevRVD = new RevVarArgsData();
            passRevRVD.setmContext(revVarArgsData.getmContext());
            passRevRVD.setRevEntity(revEntity);
            passRevRVD.setRevContainerEntityGUID(revEntity.get_revEntityGUID());

            itemsBash_LL.addView(new RevObjectListingViewFooterTabs(passRevRVD).commentFooterView());
        }

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_profile_pics_album_custom_activity_listing_view_override");
        revEntityListingViewOverrideVOM.setRevEntity(revObjectEntity);
        revEntityListingViewOverrideVOM.setOverrideView(itemsBash_LL);

        return revEntityListingViewOverrideVOM;
    }

    @Override
    public View createRevObjectListingFooterOptionView() {
        LinearLayout revObjectListingFooterOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        RevObjectListingViewFooterTabs revObjectListingViewFooterTabs = new RevObjectListingViewFooterTabs(revVarArgsData);

        LayoutParams revObjectListingFooterOptionsWrapper_LL_LP = (LayoutParams) revObjectListingFooterOptionsWrapper_LL.getLayoutParams();
        revObjectListingFooterOptionsWrapper_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revLikeItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revCommentItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revMoreOptionsItem());
        return revObjectListingFooterOptionsWrapper_LL;
    }
}
