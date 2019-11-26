package rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_input_form_widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_objects_listings.RevCommentsListingView;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateRevCommentInputForm_WIDGETS {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;


    private String revCommentValue;

    public RevCreateRevCommentInputForm_WIDGETS(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
        this.revVarArgsData = revVarArgsData;
        this.revVarArgsData.setmContext(mContext);

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public View getRevInputForm() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout commentFooterViewWrapper_LL = new RevCoreLayoutsLinearLayout(mContext).get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        commentFooterViewWrapper_LL.addView(this.commentFooterView());
        commentFooterViewWrapper_LL.addView(this.initKiwi_ET());

        linearLayout.addView(commentFooterViewWrapper_LL);

        List<Long> revAddedCommentsLocalGUIDs = new ArrayList<>();
        List<Long> revAddedCommentsRemoteGUIDs = new ArrayList<>();

        for (RevEntity revCommentEntity : revVarArgsData.getRevEntity().get_revEntityChildrenList()) {
            if (revCommentEntity == null || !revCommentEntity.get_revEntitySubType().equals("rev_comment"))
                continue;

            if (revAddedCommentsLocalGUIDs.contains(revCommentEntity.get_revEntityGUID().longValue()) || revAddedCommentsRemoteGUIDs.contains(revCommentEntity.get_remoteRevEntityGUID().longValue()))
                continue;

            String revEntityType = revCommentEntity.get_revEntityType();

            if (!revEntityType.equals(FeedReaderContract.FeedEntry_REV_USER_ENTITY.TABLE_NAME)) {
                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revCommentEntity);

                View revCommentView = new RevCommentsListingView(postRVD).getRevCommentsListingView(revCommentEntity);

                if (revCommentView != null) {
                    linearLayout.addView(revCommentView);

                    if (revCommentEntity.get_revEntityGUID() > 0) {
                        revAddedCommentsLocalGUIDs.add(revCommentEntity.get_revEntityGUID().longValue());
                    } else {
                        revAddedCommentsRemoteGUIDs.add(revCommentEntity.get_remoteRevEntityGUID().longValue());
                    }
                }
            }
        }

        return linearLayout;
    }

    private EditText initKiwi_ET() {
        final EditText revComment_ET = new RevCoreInputFormEditText(mContext).getRevEditText_FOOTER_COMMENT();
        revComment_ET.setHint("my commENT");
        revComment_ET.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);

        revComment_ET.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);

        int padding = RevLibGenConstantine.REV_MARGIN_TINY;
        revComment_ET.setPadding(padding, padding, padding, padding);

        LinearLayout.LayoutParams comment_ET_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        comment_ET_LP.setMargins(0, 0, 0, padding);
        revComment_ET.setLayoutParams(comment_ET_LP);

        revComment_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() > 0 && s.charAt(s.length() - 1) == '\n') {
                    revCommentValue = String.valueOf(revComment_ET.getText());

                    if (!revCommentValue.matches("")) {
                        RevConstantinePluggableViewsServices
                                .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishCommentAction").initRevAction(revObjectFormdata());
                    }

                    // Unregister self before setText
                    revComment_ET.removeTextChangedListener(this);
                    revComment_ET.setText("");

                    // Re-register self after setText
                    revComment_ET.addTextChangedListener(this);
                }
            }
        });

        return revComment_ET;
    }

    public RevEntity revObjectFormdata() {
        RevPersLibRead revPersLibRead = new RevPersLibRead();
        RevPersJava revPersJava = new RevPersJava();

        RevEntity revContainerEntity = revVarArgsData.getRevEntity();

        RevEntity revEntityPublisher = revContainerEntity.get_revPublisherEntity();
        revEntityPublisher.set_revEntityResolveStatus(0);
        long revEntityPublisherGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntityPublisher.get_remoteRevEntityGUID());

        if (revEntityPublisherGUID < 1) {
            revEntityPublisherGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntityPublisher);
        }

        long revContainerLocalGUID = revContainerEntity.get_revEntityGUID();
        long revContainerEntityRemoteGUID = revContainerEntity.get_remoteRevEntityGUID();

        if (revContainerLocalGUID < 0 && revContainerEntityRemoteGUID < 0) {
            return null;
        } else if (revContainerEntityRemoteGUID > 0) {
            revContainerLocalGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revContainerEntityRemoteGUID);
        }

        if (revContainerLocalGUID < 1) {
            revContainerEntity.set_revEntityOwnerGUID(revEntityPublisherGUID);
            revContainerEntity.set_revEntityResolveStatus(0);
            revContainerLocalGUID = (long) new RevPersJava().saveRevEntity_NO_REMOTE_SYNC(revContainerEntity);
        }

        if (revContainerLocalGUID < 0 && revContainerEntityRemoteGUID < 0) return null;

        RevEntity revPersEntity = new RevEntity();

        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_comment");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revContainerLocalGUID);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_comment_value", revCommentValue)
        ));

        return revPersEntity;
    }

    public View commentFooterView() {
        ImageView imageView = new ImageView(RevLibGenConstantine.REV_CONTEXT);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);
        imageView.setLayoutParams(imageView_LP);

        final Transformation transformation = new RoundedCornersTransformation(0, 1);

        String imgPath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(
                new RevPersLibRead().revPersGetALLRevEntityMetadataByRevEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid()), "rev_user_icon_path_value");

        if (new File(imgPath).exists()) {
            Picasso.get()
                    .load(new File(imgPath))
                    .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                    .centerCrop()
                    .transform(transformation)
                    .into(imageView);
        } else {
            Picasso.get()
                    .load(rev.ca.rev_lib_core_views.R.drawable.ic_account_box_black_48dp)
                    .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                    .centerCrop()
                    .transform(transformation)
                    .into(imageView);
        }

        /** TAB **/
        int imageViewBorderSize = 1;

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.greyDark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.greyDark));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imageView, imageViewLayerDrawable);

        return imageView;
    }
}
