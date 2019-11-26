package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_gen_functions.REV_IMAGE_VIEW_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevFileFunctions;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevPhotoAlbumClusterListingWidget {

    private RevVarArgsData revVarArgsData;

    private Context mContext;

    private RevEntity revPageOwnerEntity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    int dominantColor = 0;

    public RevPhotoAlbumClusterListingWidget(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.revPageOwnerEntity = revVarArgsData.getRevEntity();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getRevPhotoAlbumClusterListingWidget(List<RevEntity> revEntityList) {
        if (revPageOwnerEntity == null || revPageOwnerEntity.get_revPublisherEntity() == null)
            return null;
        if (revEntityList == null || revEntityList.isEmpty()) return null;

        final LinearLayout itemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        final LinearLayout mainImageContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        boolean mainImageIsSet = false;

        final LinearLayout revEntityAreaContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        for (int i = 0; i < revEntityList.size(); i++) {
            final RevEntity revFileEntity = revEntityList.get(i);

            if (revFileEntity == null || !revFileEntity.get_revEntitySubType().equals("rev_file") || revFileEntity.get_revEntityMetadataList().isEmpty())
                continue;

            String revFinalPath;
            String selectImagePath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revFileEntity.get_revEntityMetadataList(), "rev_file_name_value");
            String selectImagePathLocalPath = RevPersConstantine.revBaseUserImagesLargeDirPath + "/" + selectImagePath;
            revFinalPath = selectImagePathLocalPath;

            if (!RevFileFunctions.REV_FILE_EXISTS(selectImagePathLocalPath)) {
                revFinalPath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revFileEntity.get_revEntityMetadataList(), "rev_remote_file_name");
            }

            if (!mainImageIsSet) {
                if (!revVarArgsData.isRemote()) {
                    mainImageContainer_LL.setBackgroundColor(REV_IMAGE_VIEW_BASE_FUNCTIONS.REV_GET_DOMINANT_COLOR(selectImagePath));
                } else {
                    new REV_IMAGE_VIEW_BASE_FUNCTIONS(new REV_IMAGE_VIEW_BASE_FUNCTIONS.IREV_IMAGE_VIEW_BASE_FUNCTIONS() {
                        @Override
                        public void processFinishGetBitmapDorminantColor(int i) {
                            if (i == -1)
                                i = rev.ca.revlibviews.R.color.rev_yellow_primary;

                            revEntityAreaContainer_LL.setBackgroundColor(i);
                        }
                    }).execute(selectImagePath);
                }
                mainImageContainer_LL.addView(new RevTimelineMainImageView(revVarArgsData).revSetRevTimelineMainImageView(revFinalPath, itemsContainer_LL));
                mainImageIsSet = true;
            }
        }

        revEntityAreaContainer_LL.addView(mainImageContainer_LL);
        revEntityAreaContainer_LL.addView(new RevMinFooterImagesScrollerWidgetView(revVarArgsData).getImagesFooterScroller(revEntityList, mainImageContainer_LL));
        revEntityAreaContainer_LL.addView(this.revGetPublisherWrapper(revEntityList.size() - 1));

        itemsContainer_LL.addView(revEntityAreaContainer_LL);

        return itemsContainer_LL;
    }

    public View revGetPublisherWrapper(int revTotImagesCount) {
        final LinearLayout publisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView revPublisherEntity = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublisherEntity.setText(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPageOwnerEntity.get_revPublisherEntity().get_revEntityMetadataList(), "rev_entity_full_names_value") + " ");
        revPublisherEntity.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublisherEntity.setGravity(Gravity.CENTER_VERTICAL);

        TextView revPublishTime = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublishTime.setText(revPageOwnerEntity.get_timeCreated());
        revPublishTime.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublishTime.setGravity(Gravity.CENTER_VERTICAL);
        revPublishTime.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        publisherWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        publisherWrapper_LL.addView(revPublisherEntity);
        publisherWrapper_LL.addView(revPublishTime);

        TextView showMoreImagesPointer_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        showMoreImagesPointer_TV.setText((revTotImagesCount + 1) + " picTuREs");

        showMoreImagesPointer_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        showMoreImagesPointer_TV.setCompoundDrawablePadding(0);
        showMoreImagesPointer_TV.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);
        showMoreImagesPointer_TV.setBackgroundColor(REV_IMAGE_VIEW_BASE_FUNCTIONS.REV_MANIPULATE_COLOR_TONE(dominantColor, 0.7f));
        showMoreImagesPointer_TV.setGravity(Gravity.CENTER_VERTICAL);

        publisherWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        publisherWrapper_LL.addView(showMoreImagesPointer_TV);

        return publisherWrapper_LL;
    }
}
