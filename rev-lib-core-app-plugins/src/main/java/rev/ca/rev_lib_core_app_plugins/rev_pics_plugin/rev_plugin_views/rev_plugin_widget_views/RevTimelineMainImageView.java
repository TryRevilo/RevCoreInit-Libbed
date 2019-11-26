package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.File;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.RevUserIconViews;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;

public class RevTimelineMainImageView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    RevCoreLayoutsFrameLayouts revCoreLayoutsFrameLayouts;

    public RevTimelineMainImageView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsFrameLayouts = new RevCoreLayoutsFrameLayouts(mContext);
    }

    public View revSetRevTimelineMainImageView(final String revFinalSelectImagePath, final LinearLayout mainImageContainer_LL) {
        FrameLayout revMain_IV_FL = new FrameLayout(mContext);
        revMain_IV_FL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        View revFullImageViewTab = new RevFullPagePhotoViewTab(revVarArgsData).getRevFullPagePhotoViewTab(revFinalSelectImagePath);

        final ImageView dynamicImageView = new ImageView(mContext, null);

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

                        int revWidth = mainImageContainer_LL.getWidth();
                        dynamicImageView.setLayoutParams(new FrameLayout.LayoutParams(revWidth, revWidth));

                        if (REV_STRINGS_BASE_FUNCTIONS.REV_IS_VALID_URL(revFinalSelectImagePath)) {
                            Picasso.get()
                                    .load(revFinalSelectImagePath)
                                    .placeholder(rev.ca.revlibpersistence.R.drawable.ic_add_a_photo_black_48dp)
                                    .resize(revWidth, revWidth)
                                    .centerCrop()
                                    .into(dynamicImageView);
                        } else {
                            File revImageFile = new File(revFinalSelectImagePath);
                            if (revImageFile.exists()) {
                                Picasso.get()
                                        .load(revImageFile)
                                        .placeholder(rev.ca.revlibpersistence.R.drawable.ic_add_a_photo_black_48dp)
                                        .resize(revWidth, revWidth)
                                        .centerCrop()
                                        .into(dynamicImageView);
                            } else
                                Log.v(RevLangStrings.REV_TAG, "revImageFile >>> " + revImageFile.getPath() + " : NULL");
                        }
                    }
                });

        TranslateAnimation animation = new TranslateAnimation(100, 0, 100, 0);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener(dynamicImageView));

        dynamicImageView.startAnimation(animation);

        revMain_IV_FL.addView(dynamicImageView);
        revMain_IV_FL.addView(revFullImageViewTab);

        /** SET USER ICON **/
        revMain_IV_FL.addView(new RevUserIconViews(revVarArgsData).getRevUserIconViewSmall());

        return revMain_IV_FL;
    }

    private static class MyAnimationListener implements Animation.AnimationListener {

        ImageView imageView;

        public MyAnimationListener(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }
    }
}
