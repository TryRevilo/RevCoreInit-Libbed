package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevFileFunctions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevMinFooterImagesScrollerWidgetView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .7);

    public RevMinFooterImagesScrollerWidgetView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public View getImagesFooterScroller(List<RevEntity> revEntityList, final LinearLayout mainImageContainer_LL) {
        final LinearLayout imagesWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        imagesWrapper_LL.setBackgroundColor(Color.parseColor("#ffffff"));

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        scrollView.setBackgroundColor(mContext.getResources().getColor(R.color.black_OPAQUE));

        for (int i = 0; i < revEntityList.size(); i++) {
            final RevEntity revFileEntity = revEntityList.get(i);

            if (revFileEntity == null || !revFileEntity.get_revEntitySubType().equals("rev_file") || revFileEntity.get_revEntityMetadataList().isEmpty())
                continue;

            String revFinalPath;
            String selectImagePath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revFileEntity.get_revEntityMetadataList(), "rev_file_name_value");
            String selectImagePathLocalPath = RevPersConstantine.revBaseUserImagesLargeDirPath + "/" + selectImagePath;
            revFinalPath = selectImagePathLocalPath;

            if (revEntityList.size() > 1) {
                imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .6);

                final ImageView picImageView = new ImageView(RevLibGenConstantine.REV_CONTEXT);
                picImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                if (i > 0)
                    ((LinearLayout.LayoutParams) picImageView.getLayoutParams()).setMargins(1, 0, 0, 0);

                if (!RevFileFunctions.REV_FILE_EXISTS(selectImagePathLocalPath)) {
                    revFinalPath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revFileEntity.get_revEntityMetadataList(), "rev_remote_file_name");

                    Picasso.get()
                            .load(revFinalPath)
                            .resize(0, imageSize)
                            .into(picImageView);
                } else {
                    File revImageFile = new File(revFinalPath);
                    Picasso.get()
                            .load(revImageFile)
                            .resize(0, imageSize)
                            .into(picImageView);
                }

                final String finalRevFinalPath = revFinalPath;
                picImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(mainImageContainer_LL, new RevTimelineMainImageView(revVarArgsData).revSetRevTimelineMainImageView(finalRevFinalPath, mainImageContainer_LL));
                    }
                });

                imagesWrapper_LL.addView(picImageView);
            }
        }

        scrollView.addView(imagesWrapper_LL);

        return scrollView;
    }
}
