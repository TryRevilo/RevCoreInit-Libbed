package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.REV_IMAGE_VIEW_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/11/17.
 */

public class RevProfileFloatingTabViewWidget extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    int revDominantColor = -R.color.teal_100_dark;

    public RevProfileFloatingTabViewWidget(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public View createRevPluggableCenterMainContentOverlayFloatingView() {
        return this.getRevStripTab_FL();
    }

    public LinearLayout getRevStripTab_FL() {
        LinearLayout revCabUserProfileTop_LL = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_WRAP_ALL();

        int profileImageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * 0.7);

        LinearLayout.LayoutParams stripTabIcon_IV_LP = new LinearLayout.LayoutParams(profileImageSize, profileImageSize);
        stripTabIcon_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;

        ImageView stripTabIcon_IV = new ImageView(mContext);

        String revProfileIconPath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(
                new RevPersLibRead().revPersGetALLRevEntityMetadataByRevEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid()), "rev_user_icon_path_value");

        File revProfilePicFile = new File(revProfileIconPath);

        int revUserIconCurveWidth = 100;
        Transformation transformation = new RoundedCornersTransformation(revUserIconCurveWidth, 2);

        if (revProfilePicFile.exists()) {
            revDominantColor = REV_IMAGE_VIEW_BASE_FUNCTIONS.REV_GET_DOMINANT_COLOR(revProfileIconPath);

            GradientDrawable border = new GradientDrawable();
            border.setColor(revDominantColor);
            border.setCornerRadii(new float[]{0, 0, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth});
            border.setGradientType(RECTANGLE);

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);

            Picasso.get()
                    .load(new File(revProfileIconPath))
                    .resize(profileImageSize, profileImageSize)
                    .centerCrop()
                    .transform(transformation)
                    .into(stripTabIcon_IV);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(stripTabIcon_IV, layerDrawable);
        } else {
            stripTabIcon_IV.setImageResource(R.drawable.ic_person_pin_black_48dp);
            stripTabIcon_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rev_default_background));
            stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
            stripTabIcon_IV.setLayoutParams(stripTabIcon_IV_LP);

            Picasso.get()
                    .load(R.drawable.ic_person_pin_black_48dp)
                    .resize(profileImageSize, profileImageSize)
                    .centerCrop()
                    .transform(transformation)
                    .into(stripTabIcon_IV);
        }

        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("rev_strip_profile_icon_pluggable_view", stripTabIcon_IV);

        TextView userPoints = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        userPoints.setText("+ 1K");
        userPoints.setTextColor(ContextCompat.getColor(mContext, R.color.revWhite));
        userPoints.setBackgroundColor(revDominantColor);

        int marginExtraSmall = RevLibGenConstantine.REV_MARGIN_TINY;
        int marginSmall = RevLibGenConstantine.REV_MARGIN_SMALL;

        userPoints.setPadding(marginSmall, marginExtraSmall, marginSmall, marginExtraSmall);
        ((LinearLayout.LayoutParams) userPoints.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{0, 0, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth});
        gradientDrawable.setColor(REV_IMAGE_VIEW_BASE_FUNCTIONS.getContrastColor(Color.WHITE));

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(userPoints, gradientDrawable);

        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("rev_strip_profile_icon_pluggable_view", stripTabIcon_IV);

        revCabUserProfileTop_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("rev_strip_profile_icon_pluggable_view"));
        revCabUserProfileTop_LL.addView(userPoints);

        return revCabUserProfileTop_LL;
    }
}
