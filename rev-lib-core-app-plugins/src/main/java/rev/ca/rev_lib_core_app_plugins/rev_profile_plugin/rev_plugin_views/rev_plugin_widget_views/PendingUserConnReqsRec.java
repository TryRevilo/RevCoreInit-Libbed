package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;
import java.util.Map;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_pers_gen_functions.REV_RELS;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckConnectivity;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.RevPersUploadAcceptedRels;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersEntityInfoWrapperModel;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_pers_file.RevDownloadFileFromURL;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs.RevLocalEntityInfoWrapperModel;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.RevDownloadContainerUploads;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.I_REV_PROCESS_FINISH;
import rev.ca.rev_lib_gen_functions.REV_IMAGE_VIEW_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.DynamicImageView;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class PendingUserConnReqsRec {

    private RevVarArgsData revVarArgsData;

    Context mContext;

    RevPersLibRead revPersLibRead;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    public PendingUserConnReqsRec(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        this.mContext = this.revVarArgsData.getmContext();

        revPersLibRead = new RevPersLibRead();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getPendingConnReqsRec() {
        LinearLayout pendingConnReplsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) pendingConnReplsContainer_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        int pendingConnRepls_TV_revBorderWidth = 2;

        GradientDrawable pendingConnRepls_TV_border = new GradientDrawable();
        pendingConnRepls_TV_border.setStroke(pendingConnRepls_TV_revBorderWidth, mContext.getResources().getColor(R.color.revWhite));
        pendingConnRepls_TV_border.setGradientType(RECTANGLE);

        Drawable[] pendingConnRepls_TV_layers = {pendingConnRepls_TV_border};
        LayerDrawable pendingConnRepls_TV_layerDrawable = new LayerDrawable(pendingConnRepls_TV_layers);
        pendingConnRepls_TV_layerDrawable.setLayerInset(0, -pendingConnRepls_TV_revBorderWidth, -pendingConnRepls_TV_revBorderWidth, -pendingConnRepls_TV_revBorderWidth, pendingConnRepls_TV_revBorderWidth);


        List<RevEntityRelationship> revEntityRelationshipList = revPersLibRead.revPersGetRevEntityRelsSubjects_By_RelTypeValueId_TargetGUID_ResolveStatus(
                5, REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), 2);

        int reqWrapper_revBorderWidth = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(reqWrapper_revBorderWidth, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -reqWrapper_revBorderWidth, -reqWrapper_revBorderWidth, -reqWrapper_revBorderWidth, reqWrapper_revBorderWidth);

        for (final RevEntityRelationship revEntityRelationship : revEntityRelationshipList) {
            final long revRelGUID = REV_RELS.REV_GET_ALT_REL_GUID(revEntityRelationship, REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
            final RevEntity baseRevEntity = revPersLibRead.revPersGetRevEntityByGUID(revRelGUID);

            final LinearLayout reqWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) reqWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
            reqWrapper_LL.setClickable(true);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .55);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(reqWrapper_LL, layerDrawable);
            final ImageView userIconImageView = new ImageView(mContext);
            userIconImageView.setLayoutParams(new LinearLayout.LayoutParams(imgSize, imgSize));

            int revUserIconCurveWidth = 100;
            Transformation transformation = new RoundedCornersTransformation(revUserIconCurveWidth, 2);

            File revUserIconFile = new File(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(baseRevEntity.get_revEntityMetadataList(), "rev_user_icon_path_value"));

            if (revUserIconFile.exists()) {
                Picasso.get()
                        .load(revUserIconFile)
                        .placeholder(R.drawable.ic_account_box_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .transform(transformation)
                        .into(userIconImageView);
            } else {
                Picasso.get()
                        .load(R.drawable.ic_account_box_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .transform(transformation)
                        .into(userIconImageView);
            }

            reqWrapper_LL.addView(userIconImageView);

            final LinearLayout relInfoContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
            relInfoContainer_LL.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

            TextView fullNames_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING(.9f);
            String mainTextLeadString = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(baseRevEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");
            fullNames_TV.setText(mainTextLeadString);

            relInfoContainer_LL.addView(fullNames_TV);

            final TextView revRelTimeCreated_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(0.7f);
            revRelTimeCreated_TV.setText(revEntityRelationship.get_timeCreated());
            revRelTimeCreated_TV.setPadding(0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), 0, 0);

            relInfoContainer_LL.addView(revRelTimeCreated_TV);
            reqWrapper_LL.addView(relInfoContainer_LL);

            reqWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
            reqWrapper_LL.addView(this.acceptTab(revEntityRelationship));
            reqWrapper_LL.addView(this.cancelTab(revEntityRelationship));

            reqWrapper_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RevVarArgsData revVarArgsData = new RevVarArgsData();
                    revVarArgsData.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                    revVarArgsData.setRevViewType("RevCreateInputFormRevProfileParents");

                    IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                    iRevInputFormView.createRevInputForm();
                    new RevPop().initiatePopupWindow(revEntityBaseSummuryView(revEntityRelationship));
                }
            });

            pendingConnReplsContainer_LL.addView(reqWrapper_LL);
        }

        return pendingConnReplsContainer_LL;
    }

    private View revEntityBaseSummuryView(RevEntityRelationship revEntityRelationship) {
        final long revRelGUID = REV_RELS.REV_GET_ALT_REL_GUID(revEntityRelationship, REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        final RevEntity baseRevEntity = revPersLibRead.revPersGetRevEntityByGUID(revRelGUID);

        final ScrollView scrollViewContainer_SV = new ScrollView(mContext);
        scrollViewContainer_SV.setVerticalScrollBarEnabled(false);
        scrollViewContainer_SV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((LinearLayout.LayoutParams) scrollViewContainer_SV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);

        final LinearLayout revEntityBaseSummuryViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revEntityBaseSummuryViewContainer_LL.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);

        String revIconFilePath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(baseRevEntity.get_revEntityMetadataList(), "rev_user_icon_path_value");

        final File revUserIconFile = new File(revIconFilePath);

        if (revUserIconFile.isFile()) {
            final ImageView dynamicImageView = new DynamicImageView(mContext, null);

            dynamicImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

                            Picasso.get()
                                    .load(revUserIconFile)
                                    .placeholder(rev.ca.revlibpersistence.R.drawable.ic_add_a_photo_black_48dp)
                                    .into(dynamicImageView);

                            revEntityBaseSummuryViewContainer_LL.setBackgroundColor(REV_IMAGE_VIEW_BASE_FUNCTIONS.REV_GET_DOMINANT_COLOR(revUserIconFile.getAbsolutePath()));
                        }
                    });

            revEntityBaseSummuryViewContainer_LL.addView(dynamicImageView);
        }
        revEntityBaseSummuryViewContainer_LL.addView(this.revBaseInfoView(revEntityRelationship));

        /** REV PICS **/
        RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(baseRevEntity.get_revEntityGUID());

        RevEntity revProfilePicALbumEntity = null;

        for (RevEntity revEntity : revPersEntityInfoWrapperModel.getRevEntity().get_revInfoEntity().get_revEntityChildrenList()) {
            if (revEntity.get_revEntitySubType().equals("rev_pics_album")) {
                revProfilePicALbumEntity = revEntity;
                break;
            }
        }

        if (revProfilePicALbumEntity != null) {
            List<RevEntity> revPicsChildrenList = revProfilePicALbumEntity.get_revEntityChildrenList();

            LinearLayout imagesWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

            for (int i = 0; i < revPicsChildrenList.size(); i++) {
                RevEntity revEntity = revPicsChildrenList.get(i);
                if (!revEntity.get_revEntitySubType().equals("rev_file") || revEntity.get_revEntityMetadataList().isEmpty())
                    continue;

                final String selectImagePath = RevPersConstantine.revBaseUserImagesLargeDirPath + "/" + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_file_name_value");
                final File picImgFile = new File(selectImagePath);

                if (picImgFile.exists()) {
                    int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_LARGE;

                    ImageView picImageView = new ImageView(RevLibGenConstantine.REV_CONTEXT);

                    if (i > 0) {
                        LinearLayout.LayoutParams picImageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        picImageView_LP.setMargins(1, 0, 0, 0);
                        picImageView.setLayoutParams(picImageView_LP);
                    }

                    Picasso.get()
                            .load(picImgFile)
                            .resize(0, imageSize)
                            .into(picImageView);

                    imagesWrapper_LL.addView(picImageView);
                }
            }

            final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
            scrollView.setHorizontalScrollBarEnabled(false);
            scrollView.addView(imagesWrapper_LL);

            revEntityBaseSummuryViewContainer_LL.addView(scrollView);
        }

        scrollViewContainer_SV.addView(revEntityBaseSummuryViewContainer_LL);

        return scrollViewContainer_SV;
    }

    private View revBaseInfoView(RevEntityRelationship revEntityRelationship) {
        final long revRelGUID = REV_RELS.REV_GET_ALT_REL_GUID(revEntityRelationship, REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        final RevEntity baseRevEntity = revPersLibRead.revPersGetRevEntityByGUID(revRelGUID);

        LinearLayout revBaseInfoViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revBaseInfoViewContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revBaseInfoViewContainer_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        TextView fullNames_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView_NO_PADDING(.7f);

        String mainTextLeadString = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(baseRevEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");

        if (revVarArgsData.getRevEntity() != null) {
            String mainTextLeadStringReduced = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(String.valueOf(mainTextLeadString), 35);

            SpannableString profileNameTtlSpan = new SpannableString(mainTextLeadStringReduced);
            profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .85)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
            profileNameTtlSpan.setSpan(new StyleSpan(Typeface.BOLD), 0, mainTextLeadStringReduced.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (mainTextLeadStringReduced.length() - (mainTextLeadStringReduced.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
            profileNameTtlSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, mainTextLeadStringReduced.length(), 0); // set color

            SpannableString userInfoDetails_S = REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("Oct 5," + REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(" 1985, Male Kenyan"));
            userInfoDetails_S.setSpan(new StyleSpan(Typeface.ITALIC), 0, userInfoDetails_S.length(), 0);

            fullNames_TV.setText(TextUtils.concat(profileNameTtlSpan, " ", userInfoDetails_S));

            /** END USER INFO SET **/

            SpannableString userInfoBrief_Span = REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(mainTextLeadString);
            userInfoBrief_Span.setSpan(new StyleSpan(Typeface.NORMAL), 0, userInfoBrief_Span.length(), 0);
            userInfoBrief_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.darkText)), 0, userInfoBrief_Span.length(), 0); // set color

            int borderWidth = 1;

            GradientDrawable border = new GradientDrawable();
            border.setStroke(borderWidth, mContext.getResources().getColor(rev.ca.revlibpersistence.R.color.revExtraLightGreen_OPAQUE));
            border.setGradientType(RECTANGLE);

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            layerDrawable.setLayerInset(0, -borderWidth, -borderWidth, -borderWidth, borderWidth);

            TextView userInfoBrief_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
            userInfoBrief_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.colorPrimary));
            userInfoBrief_TV.setText("viEw pRoFiLE");
            userInfoBrief_TV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_MEDIUM);
            ((LinearLayout.LayoutParams) userInfoBrief_TV.getLayoutParams()).width = LinearLayout.LayoutParams.MATCH_PARENT;

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(userInfoBrief_TV, layerDrawable);

            LinearLayout topWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) topWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

            topWrapper_LL.addView(fullNames_TV);
            topWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
            topWrapper_LL.addView(this.acceptTab(revEntityRelationship));
            topWrapper_LL.addView(this.cancelTab(revEntityRelationship));

            revBaseInfoViewContainer_LL.addView(topWrapper_LL);
            revBaseInfoViewContainer_LL.addView(userInfoBrief_TV);
        }

        return revBaseInfoViewContainer_LL;
    }

    private View cancelTab(final RevEntityRelationship revEntityRelationship) {
        final ImageView cancelReqImageView = new ImageView(mContext);
        cancelReqImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
        cancelReqImageView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(cancelReqImageView);

        Picasso.get()
                .load(R.drawable.baseline_person_add_disabled_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(cancelReqImageView);

        cancelReqImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long revEntityGUID = revEntityRelationship.get_revEntitySubjectGUID();
                Log.v(RevLangStrings.REV_TAG, "Cancelled Req from " + revPersLibRead.revPersGetRevEntityName(revEntityGUID));
            }
        });

        return cancelReqImageView;
    }

    private View acceptTab(final RevEntityRelationship revEntityRelationship) {
        String mainTextLeadStringReduced = "AccEpT";

        SpannableString profileNameTtlSpan = new SpannableString(mainTextLeadStringReduced);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (mainTextLeadStringReduced.length() - (mainTextLeadStringReduced.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, mainTextLeadStringReduced.length(), 0); // set color

        TextView textView = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.7f);
        textView.setText(profileNameTtlSpan);
        textView.setGravity(Gravity.BOTTOM);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RevPersLibUpdate().revPersUpdateRelResStatus_By_RelId(revEntityRelationship.get_revEntityRelationshipId(), 1);
                if (RevCheckConnectivity.isIsRevNetworkConnected())
                    new RevPersUploadAcceptedRels(mContext, new I_REV_PROCESS_FINISH() {
                        @Override
                        public void REV_PROCESS_FINISH(Object o) {
                            new RevDownloadContainerUploads(new RevVarArgsData(mContext), new I_REV_PROCESS_FINISH() {
                                @Override
                                public void REV_PROCESS_FINISH(Object o) {
                                    final RevPersJava revPersJava = new RevPersJava();

                                    Map<Object, Object> revreturnObjectsMap = ((Map<Object, Object>) o);
                                    List<RevEntity> revPicsAlbumList = (List<RevEntity>) revreturnObjectsMap.get("rev_pics_albums");

                                    for (final RevEntity revPicAlbumEntity : revPicsAlbumList) {
                                        for (RevEntity revFileEntity : revPicAlbumEntity.get_revEntityChildrenList()) {
                                            if (!revFileEntity.get_revEntitySubType().equals("rev_file"))
                                                continue;

                                            final Long aLong = revFileEntity.get_revEntityGUID();
                                            List<RevEntityMetadata> revEntityMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(aLong);
                                            String revFileName = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_file_name_value");
                                            String revFilePath = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_remote_file_name");

                                            Log.v(RevLangStrings.REV_TAG, "revPicAlbumEntity : " + revPicAlbumEntity.get_revEntityChildrenList().size() + " revFileName : " + revFileName + " revFilePath : " + revFilePath);

                                            new RevDownloadFileFromURL(mContext, new RevDownloadFileFromURL.IRevDownloadFileFromURL() {
                                                @Override
                                                public void revFileDownloadComplete(boolean output) {
                                                    RevEntityRelationship revInfoPicAlbumRel = new RevEntityRelationship("rev_picture_of", aLong, revPicAlbumEntity.get_revEntityGUID());
                                                    revInfoPicAlbumRel.set_revResolveStatus(0);
                                                    revInfoPicAlbumRel.set_remoteRevEntitySubjectGUID(revPersLibRead.getRemoteRevEntityGUID(aLong));
                                                    revInfoPicAlbumRel.set_remoteRevEntityTargetGUID(revPicAlbumEntity.get_remoteRevEntityGUID());
                                                    revPersJava.saveRevEntity_NO_REMOTE_SYNC(revInfoPicAlbumRel);
                                                }
                                            }).execute(REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + revFilePath, revFileName);
                                        }

                                    }
                                }
                            });
                        }
                    });
            }
        });

        return textView;
    }
}
