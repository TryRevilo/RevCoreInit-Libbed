package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms.rev_plugin_form_widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevAnnotation;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevDownloadUserFromServer;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_pers_file.RevDownloadFileFromURL;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormButton;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditTextPassword;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 12/20/17.
 */

public class RevCreateNewUserRegForm_WIDGETS {

    private Context mContext;

    private EditText fullNamesET, eMailET, password, confirmPassword;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormButton revCoreInputFormButton;

    private RevPersJava revPersJava = new RevPersJava();
    private RevPersLibRead revPersLibRead = new RevPersLibRead();
    private RevPersLibUpdate revPersLibUpdate = new RevPersLibUpdate();

    public RevCreateNewUserRegForm_WIDGETS(Context mContext) {
        this.mContext = mContext;

        revCoreInputFormButton = new RevCoreInputFormButton(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormButton = new RevCoreInputFormButton(mContext);
    }

    public LinearLayout getPageForm() {

        LinearLayout revPageContainerLL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
        revPageContainerLL.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        revPageContainerLL.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);

        revPageContainerLL.addView(this.revTell());
        revPageContainerLL.addView(this.revUserRegFormItems());
        revPageContainerLL.addView(this.revLogInFormFooter());
        return revPageContainerLL;
    }

    private View revTell() {
        String profileNameTtl = "pLAcE A NEw AccouNT";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0);

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan);

        TextView logInTextTell = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView_NO_PADDING();
        logInTextTell.setText(finalText);
        logInTextTell.setPadding(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(logInTextTell, layerDrawable);

        return logInTextTell;

    }

    public View revUserRegFormItems() {
        LinearLayout revFormItemsContainerLL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
        revFormItemsContainerLL.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);

        fullNamesET = new RevCoreInputFormEditText(mContext).getRevEditText_NO_Borders();
        fullNamesET.setHint(R.string.full_names);

        eMailET = new RevCoreInputFormEditText(mContext).getRevEditText_NO_Borders();
        eMailET.setHint(R.string.email_or_phone_number);

        password = new RevCoreInputFormEditTextPassword(mContext).getRevEditText_NO_Borders();
        password.setHint(R.string.password);

        confirmPassword = new RevCoreInputFormEditTextPassword(mContext).getRevPasswordEditTextPass_ONLY_BottomBorders();
        confirmPassword.setHint(R.string.confirm_password);

        revFormItemsContainerLL.addView(fullNamesET);
        revFormItemsContainerLL.addView(eMailET);
        revFormItemsContainerLL.addView(password);
        revFormItemsContainerLL.addView(confirmPassword);

        return revFormItemsContainerLL;

    }

    public RevEntity revObjectFormdata() {
        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_USER_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_user_entity");
        revPersEntity.set_revEntityChildableStatus(1);
        revPersEntity.set_revEntityOwnerGUID(-1l);

        String revEMail_S = eMailET.getText().toString();
        revEMail_S = revEMail_S.replaceAll("\\s+", "");

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_entity_full_names_value", fullNamesET.getText().toString()),
                new RevEntityMetadata("rev_user_entity_email_value", revEMail_S)
        ));

        return revPersEntity;
    }

    public View revLogInFormFooter() {
        LinearLayout revLogInFormFooter_LL = revCoreLayoutsLinearLayout.getHorizontalFormFooterRevLinearLayout();

        Button logInBttn = revCoreInputFormButton.getFormInputSubmitButton();
        logInBttn.setText("OK");
        logInBttn.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_dark));

        logInBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices.REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevCreateNewUserRegAction").initRevAction(revObjectFormdata());
                RevPop.getPw().dismiss();
            }
        });

        Button closeWin = revCoreInputFormButton.getFormInputSubmitButton();
        closeWin.setText(R.string.cancel);
        closeWin.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));

        closeWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String revUniqueUserId_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revObjectFormdata().get_revEntityMetadataList(), "rev_entity_full_names_value");
                new RevDownloadUserFromServer(mContext, revUniqueUserId_S, new RevDownloadUserFromServer.IRevDownloadUserFromServer() {
                    @Override
                    public void onRevDownloadUserProcessFinish(final RevEntity revEntity) {
                        RevPop.closerevPopUpWin(RevPop.REV_POPUP_WIN_LIST.size());
                    }
                });
            }
        });

        revLogInFormFooter_LL.addView(logInBttn);
        revLogInFormFooter_LL.addView(closeWin);

        return revLogInFormFooter_LL;
    }

    private void revResUresContainerGUIDs(List<Long> revUnresolvedOwnerGUIDs) {
        for (long revEntityGUID : revUnresolvedOwnerGUIDs) {
            RevEntity revEntity = revPersLibRead.revPersGetRevEntityByGUID(revEntityGUID);
            long revEntityContainerGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntity.get_revEntityContainerGUID());
            if (revEntityContainerGUID > 0) {
                int revContainerGUIDUpdateStatus = revPersLibUpdate.revPersSetContainerGUID_By_RevEntityGUID(revEntityGUID, revEntityContainerGUID);
                if (revContainerGUIDUpdateStatus == 1)
                    revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(0, revEntityGUID);
                else
                    revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(-1110, revEntityGUID);
            } else revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(-1111, revEntityGUID);
        }
    }

    private void revSetLocalVals(RevEntity revEntity) {
        long revEntityRemoteOwnerGUID = revEntity.get_revEntityOwnerGUID();
        if (revEntityRemoteOwnerGUID > 0) {
            long revEntityLocalOwnerGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntityRemoteOwnerGUID);

            if (revEntityLocalOwnerGUID > 0)
                revEntity.set_revEntityOwnerGUID(revEntityLocalOwnerGUID);
            else revEntity.set_revEntityResolveStatus(-1010);
        }

        long revEntityRemoteContainerGUID = revEntity.get_revEntityRemoteContainerGUID();
        if (revEntityRemoteContainerGUID > 0) {
            long revEntityLocalContainerGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntityRemoteContainerGUID);

            if (revEntityLocalContainerGUID > 0)
                revEntity.set_revEntityContainerGUID(revEntityLocalContainerGUID);
            else revEntity.set_revEntityResolveStatus(-1011);
        }
    }

    private void revSaveRemoteRevTimelineEntity(RevEntity tlRevEntity) {
        if (revPersLibRead.revEntityExistsByRemoteEntityGUID(tlRevEntity.get_remoteRevEntityGUID()) > 0)
            return;

        RevPersGenFunctions.REV_SET_REV_ENTITY_RESOLVED(tlRevEntity);
        RevPersGenFunctions.REV_SET_METADATA_RESOLVED(tlRevEntity);

        this.revSetLocalVals(tlRevEntity);

        if (tlRevEntity.get_revEntitySubType().equals("rev_pics_album")) {
            revSaveProfilePicsAlbum(tlRevEntity);
            return;
        } else {
            tlRevEntity.set_revEntityGUID((Long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(tlRevEntity));
            this.revSaveAnnotation(tlRevEntity);
        }

        List<RevEntity> revTimelineChildrenEntityList = tlRevEntity.get_revEntityChildrenList();

        if (revTimelineChildrenEntityList.size() > 0) {
            for (int i = 0; i < revTimelineChildrenEntityList.size(); i++) {
                this.revSaveRemoteRevTimelineEntity(revTimelineChildrenEntityList.get(i));
            }
        }
    }

    private void revSaveAnnotation(RevEntity revEntity) {
        RevPersLibCreate revPersLibCreate = new RevPersLibCreate();

        for (RevAnnotation revAnnotation : revEntity.get_revAnnotations()) {
            revAnnotation.set_revAnnotationResStatus(0);
            revAnnotation.set_revAnnotationName("rev_entity_like");

            long revEntityGUID = revEntity.get_revEntityGUID();
            long remoteRevEntityGUID = revEntity.get_remoteRevEntityGUID();

            if (revEntityGUID < 1 && remoteRevEntityGUID > 0) {
                revSaveRemoteRevTimelineEntity(revEntity);
                revEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(remoteRevEntityGUID);

                if (revEntityGUID < 1)
                    continue;
            }

            revAnnotation.set_revAnnotationEntityGUID(revEntityGUID);
            revAnnotation.set_revAnnOwnerEntityGUID(revEntity.get_revPublisherEntity().get_revEntityGUID());

            revPersLibCreate.revPersRevEntityAnnotation(revAnnotation);
        }

        List<RevEntity> revChildrenEntityList = revEntity.get_revEntityChildrenList();

        if (revChildrenEntityList.size() > 0) {
            for (int i = 0; i < revChildrenEntityList.size(); i++) {
                revSaveAnnotation(revChildrenEntityList.get(i));
            }
        }
    }

    private long revSaveProfilePicsAlbum(final RevEntity revPicsAlbum) {
        if (revPicsAlbum == null) return -1;

        final long revPicsAlbumGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revPicsAlbum);
        revPicsAlbum.set_revEntityGUID(revPicsAlbumGUID);
        revSaveAnnotation(revPicsAlbum);

        List<RevEntity> revChildrenEntityList = revPicsAlbum.get_revEntityChildrenList();

        List<Long> revFileEntityList = new ArrayList<>();

        for (RevEntity revPicFileChildEntity : revChildrenEntityList) {
            revPicFileChildEntity.set_revEntityOwnerGUID(revPicsAlbum.get_revEntityOwnerGUID());

            long revPicFileChildEntityGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revPicFileChildEntity);
            revPicFileChildEntity.set_revEntityGUID(revPicFileChildEntityGUID);
            revSaveAnnotation(revPicFileChildEntity);

            if (revPicFileChildEntity.get_revEntitySubType().equals("rev_file")) {
                int revFileResStatus = revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(7, revPicFileChildEntityGUID);
                if (revFileResStatus == 1) revFileEntityList.add(revPicFileChildEntityGUID);
            }
        }

        for (final Long aLong : revFileEntityList) {
            List<RevEntityMetadata> revEntityMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(aLong);
            String revFileName = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_file_name_value");
            String revFilePath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_remote_file_name");

            new RevDownloadFileFromURL(mContext, new RevDownloadFileFromURL.IRevDownloadFileFromURL() {
                @Override
                public void revFileDownloadComplete(boolean output) {
                    RevEntityRelationship revInfoPicAlbumRel = new RevEntityRelationship("rev_picture_of", aLong, revPicsAlbumGUID);
                    revInfoPicAlbumRel.set_revResolveStatus(0);
                    revInfoPicAlbumRel.set_remoteRevEntitySubjectGUID(revPersLibRead.getRemoteRevEntityGUID(aLong));
                    revInfoPicAlbumRel.set_remoteRevEntityTargetGUID(revPicsAlbum.get_remoteRevEntityGUID());
                    revPersJava.saveRevEntity_NO_REMOTE_SYNC(revInfoPicAlbumRel);
                }
            }).execute(REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + revFilePath, revFileName);
        }

        return revPicsAlbumGUID;
    }
}
