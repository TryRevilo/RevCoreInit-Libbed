package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_gen_functions.REV_IMAGE_VIEW_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevUserIconViews {

    private RevVarArgsData revVarArgsData;

    private Context mContext;

    public RevUserIconViews(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
    }

    public View getRevUserIconViewSmall() {
        String revUserIcon = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revVarArgsData.getRevEntity().get_revPublisherEntity().get_revEntityMetadataList(), "rev_user_icon_path_value");

        int revUserIconCurveWidth = 100;
        Transformation transformation = new RoundedCornersTransformation(revUserIconCurveWidth, 2);

        ImageView ownerEntityImageView = new ImageView(mContext);

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        File userIconFIle = new File(revUserIcon);

        if (userIconFIle.exists()) {
            Picasso.get()
                    .load(userIconFIle)
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .resize(imageSize, imageSize)
                    .centerCrop()
                    .transform(transformation)
                    .into(ownerEntityImageView);
        } else {
            Picasso.get()
                    .load(R.drawable.ic_account_circle_black_48dp)
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .resize(imageSize, imageSize)
                    .into(ownerEntityImageView);
        }

        int borderSize = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.revWhite));
        border.setColor(REV_IMAGE_VIEW_BASE_FUNCTIONS.REV_GET_DOMINANT_COLOR(revUserIcon));
        border.setCornerRadii(new float[]{revUserIconCurveWidth, revUserIconCurveWidth, 0, 0, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth});
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, borderSize, -borderSize, -borderSize, borderSize);

        int ownerEntityImageView_padding = 2;

        ownerEntityImageView.setPadding((ownerEntityImageView_padding), 0, 0, (ownerEntityImageView_padding));

        FrameLayout.LayoutParams ownerEntityImageView_LP = new FrameLayout.LayoutParams(imageSize + borderSize + ownerEntityImageView_padding, LinearLayout.LayoutParams.WRAP_CONTENT);
        ownerEntityImageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);

        ownerEntityImageView.setLayoutParams(ownerEntityImageView_LP);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(ownerEntityImageView, layerDrawable);

        ownerEntityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(RevLangStrings.REV_TAG, ">>> " + revVarArgsData.getRevEntity().get_revPublisherEntity().get_remoteRevEntityGUID() + " : " + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revVarArgsData.getRevEntity().get_revPublisherEntity().get_revEntityMetadataList(), "rev_entity_full_names_value"));
            }
        });

        return ownerEntityImageView;
    }
}
