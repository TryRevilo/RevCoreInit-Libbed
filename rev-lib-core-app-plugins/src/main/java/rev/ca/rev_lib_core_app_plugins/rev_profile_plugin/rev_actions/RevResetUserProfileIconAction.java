package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_actions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.REV_IMAGE_VIEW_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevResetUserProfileIconAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevResetUserProfileIconAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        String revProfileIconPath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_user_icon_path_value");

        RevEntityMetadata revEntityMetadata = new RevEntityMetadata();
        revEntityMetadata.set_revMetadataName("rev_user_icon_path_value");
        revEntityMetadata.set_metadataValue(revProfileIconPath);
        revEntityMetadata.setRevMetadataOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revEntityMetadata.set_resolveStatus(-1);

        RevPersLibRead revPersLibRead = new RevPersLibRead();
        long revMetadataId = revPersLibRead.revGetRevEntityMetadataId_By_RevMetadataName_RevEntityGUID("rev_user_icon_path_value", revEntity.get_revEntityGUID());

        if (revMetadataId == -1) {
            revMetadataId = new RevPersLibCreate().revSaveRevEntityMetadata(revEntityMetadata);
        } else {
            revMetadataId = new RevPersLibUpdate().setMetadataValue_BY_MetadataId_RevEntityGUID(revMetadataId, revProfileIconPath);
        }

        if (revMetadataId > 0) {
            Context mContext = RevLibGenConstantine.REV_CONTEXT;

            int revUserIconCurveWidth = 100;

            GradientDrawable border = new GradientDrawable();
            border.setColor(REV_IMAGE_VIEW_BASE_FUNCTIONS.REV_GET_DOMINANT_COLOR(revProfileIconPath));
            border.setCornerRadii(new float[]{0, 0, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth, revUserIconCurveWidth});
            border.setGradientType(RECTANGLE);

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);

            int profileImageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * 0.7);

            ImageView imageView = new ImageView(mContext);

            final Transformation transformation = new RoundedCornersTransformation(revUserIconCurveWidth, 2);

            Picasso.get()
                    .load(new File(revProfileIconPath))
                    .resize(profileImageSize, profileImageSize)
                    .centerCrop()
                    .transform(transformation)
                    .into(imageView);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imageView, layerDrawable);

            RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("rev_strip_profile_icon_pluggable_view", imageView);

            return revMetadataId;
        }

        return -1L;
    }
}
