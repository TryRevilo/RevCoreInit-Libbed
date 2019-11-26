package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.io.File;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_gen_functions.RevFileFunctions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.DynamicImageView;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevFullPagePhotoViewTab {

    private RevVarArgsData revVarArgsData;

    private Context mContext;

    int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    public RevFullPagePhotoViewTab(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
    }

    public View getRevFullPagePhotoViewTab(final String revFinalSelectImagePath) {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setClickable(true);

        linearLayout.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
        linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        int revUserIconCurveWidth = 100;

        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        border.setCornerRadii(new float[]{0, 0, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth});
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        ImageView ownerEntityImageView = new ImageView(mContext);
        ownerEntityImageView.setColorFilter(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));

        Picasso.get()
                .load(R.drawable.baseline_zoom_out_map_black_48dp)
                .placeholder(R.drawable.ic_account_circle_black_48dp)
                .resize(imgSize, imgSize)
                .into(ownerEntityImageView);

        linearLayout.addView(ownerEntityImageView);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, layerDrawable);

        FrameLayout.LayoutParams textView_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = Gravity.TOP;
        linearLayout.setLayoutParams(textView_LP);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RevFullPagePhotoViewTab(revVarArgsData).revEntityImageViewFullView(revFinalSelectImagePath);
            }
        });

        return linearLayout;
    }

    public void revEntityImageViewFullView(final String imgURL) {
        ScrollView scrollViewContainer_SV = new ScrollView(mContext);
        scrollViewContainer_SV.setVerticalScrollBarEnabled(false);
        scrollViewContainer_SV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollViewContainer_SV.setPadding(0, 0, 0, 0);

        ImageView dynamicImageView = new DynamicImageView(mContext, null);
        dynamicImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (RevFileFunctions.REV_IS_VALID_URL(imgURL)) {
            Picasso.get()
                    .load(imgURL)
                    .placeholder(rev.ca.revlibpersistence.R.drawable.ic_add_a_photo_black_48dp)
                    .into(dynamicImageView);
        } else {
            Picasso.get()
                    .load(new File(imgURL))
                    .placeholder(rev.ca.revlibpersistence.R.drawable.ic_add_a_photo_black_48dp)
                    .into(dynamicImageView);
        }


        scrollViewContainer_SV.addView(dynamicImageView);

        new RevPop().initiatePopupWindow_ClearBackground(scrollViewContainer_SV);
    }
}
