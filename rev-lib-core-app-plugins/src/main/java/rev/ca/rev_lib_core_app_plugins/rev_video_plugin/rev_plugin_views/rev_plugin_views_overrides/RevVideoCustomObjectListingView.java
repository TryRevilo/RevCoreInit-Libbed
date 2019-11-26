package rev.ca.rev_lib_core_app_plugins.rev_video_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.DynamicImageView;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/1/17.
 */

public class RevVideoCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevVideoCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_video";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(RevEntity revEntity) {
        RevObjectEntity revObjectEntity;
        if (revEntity == null) {
            return null;
        } else {
            revObjectEntity = revEntity.getRevObjectEntity();
        }

        LinearLayout imagesWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LayoutParams picLayoutParams = (LayoutParams) imagesWrapper_LL.getLayoutParams();
        picLayoutParams.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);

        List<RevEntityMetadata> selectImages = new RevPersLibRead().revPersGetALLRevEntityMetadataByRevEntityGUID(revEntity.get_revEntityGUID());

        final FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_dark));

        for (RevEntityMetadata revEntityMetadata : selectImages) {
            final String videoPath = revEntityMetadata.get_metadataValue();

            final File picImgFile = new File(videoPath);

            if (picImgFile.exists()) {
                if (selectImages.indexOf(revEntityMetadata) == 0) {

                    final ImageView dynamicImageView = new DynamicImageView(mContext, null);

                    Bitmap thumb = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
                    final BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);
                    final int bitMapImageWidth = thumb.getWidth();
                    final int bitMapImageHeight = thumb.getHeight();

                    dynamicImageView.setLayoutParams(new FrameLayout.LayoutParams(bitMapImageWidth, bitMapImageHeight));
                    ((FrameLayout.LayoutParams) dynamicImageView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

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

                                    dynamicImageView.setBackgroundDrawable(bitmapDrawable);
                                    dynamicImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                }
                            });

                    int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

                    Drawable joinImg_DR = mContext.getResources().getDrawable(R.drawable.baseline_play_arrow_black_48dp);
                    joinImg_DR.setBounds(0, 0, imgSize, imgSize);
                    DrawableCompat.setTint(joinImg_DR, ContextCompat.getColor(mContext, R.color.revWhite));

                    TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
                    textView.setText("Play");
                    textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
                    textView.setCompoundDrawablePadding(0);
                    textView.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);
                    textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
                    textView.setGravity(Gravity.CENTER_VERTICAL);

                    FrameLayout.LayoutParams textView_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    textView_LP.setMargins(0, 0, 0, 0);
                    textView_LP.gravity = Gravity.TOP;
                    textView.setLayoutParams(textView_LP);

                    textView.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onClick(View v) {
                            Uri videoUri = Uri.fromFile(new File(videoPath));

                            VideoView videoView = new VideoView(RevLibGenConstantine.REV_CONTEXT);
                            videoView.setVideoURI(videoUri);

                            videoView.setLayoutParams(new LinearLayout.LayoutParams(bitMapImageWidth, bitMapImageHeight));

                            MediaController controller = new MediaController(RevLibGenConstantine.REV_CONTEXT);
                            controller.setAnchorView(videoView);
                            controller.setMediaPlayer(videoView);
                            videoView.setMediaController(controller);

                            videoView.requestFocus();
                            videoView.start();

                            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    frameLayout.removeViewAt(frameLayout.getChildCount() - 1);
                                }
                            });

                            frameLayout.addView(videoView);
                        }
                    });

                    textView.setCompoundDrawables(joinImg_DR, null, null, null);

                    frameLayout.addView(dynamicImageView);
                    frameLayout.addView(textView);

                }
            }
        }

        TextView revPublisherEntity = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublisherEntity.setText(new RevPersLibRead().revPersGetRevEntityName(revEntity.get_revEntityOwnerGUID()) + " ");
        revPublisherEntity.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublisherEntity.setGravity(Gravity.CENTER_VERTICAL);

        TextView revPublishTime = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublishTime.setText(revEntity.get_timeCreated());
        revPublishTime.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublishTime.setGravity(Gravity.CENTER_VERTICAL);
        revPublishTime.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        LinearLayout publisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        publisherWrapper_LL.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        publisherWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        FrameLayout.LayoutParams publicationHeadersWrapper_LL_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        publicationHeadersWrapper_LL_LP.setMargins(0, 0, 0, 0);
        publicationHeadersWrapper_LL_LP.gravity = Gravity.BOTTOM;
        publisherWrapper_LL.setLayoutParams(publicationHeadersWrapper_LL_LP);

        publisherWrapper_LL.addView(revPublisherEntity);
        publisherWrapper_LL.addView(revPublishTime);

        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        textView.setText("+1M " + " by other people");

        if (selectImages.size() > 1)
            textView.setText("+ " + (selectImages.size() - 1) + " more videos");

        textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        textView.setCompoundDrawablePadding(0);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        publisherWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        publisherWrapper_LL.addView(textView);

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
        frameLayout.addView(imageView);

        /** END PUBLISHER ICOM **/

        frameLayout.addView(publisherWrapper_LL);

        RevVarArgsData passRVD = new RevVarArgsData(mContext);
        passRVD.setRevEntity(revEntity);

        LinearLayout revObjectListingViewFooterWrapper_LL = (LinearLayout) new RevObjectListingViewFooterTabs(passRVD).getRevObjectListingViewFooterTabs();
        LayoutParams revObjectListingViewFooterWrapper_LL_LP = (LayoutParams) revObjectListingViewFooterWrapper_LL.getLayoutParams();
        revObjectListingViewFooterWrapper_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout itemsBash_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        itemsBash_LL.addView(frameLayout);
        itemsBash_LL.addView(imagesWrapper_LL);

        itemsBash_LL.addView(revObjectListingViewFooterWrapper_LL);

        if (revEntity != null) {

            RevVarArgsData passRevRVD = new RevVarArgsData();
            passRevRVD.setRevEntity(revEntity);
            passRevRVD.setRevContainerEntityGUID(revEntity.get_revEntityGUID());

            itemsBash_LL.addView(new RevObjectListingViewFooterTabs(passRevRVD).commentFooterView());
        }

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_video_custom_activity_listing_view_override");
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
