package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.IRevFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.RevPictureFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_plugin_views_overrides.RevGenericSelectedItemsView;
import rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_widget_views.RevFullPagePhotoViewTab;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevNetworkResolverViews;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 10/24/17.
 */

public class RevInfoDetailsWidget implements IRevFileCrawler {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevEntity revPageOwnerEntity;
    private RevEntity revInfoEntity;

    private String revPageOwnerFullNames;

    List<String> selectedItems = new ArrayList<>();
    LinearLayout selectedItems_LL;

    int revProfileImagesCount = 0;

    public RevInfoDetailsWidget(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revPageOwnerEntity = this.revVarArgsData.getRevEntity();
        this.revInfoEntity = this.revPageOwnerEntity.get_revInfoEntity();

        this.revPageOwnerFullNames = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPageOwnerEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        selectedItems_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
    }

    private void setTabProperties(TextView textView) {
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));

        int paddingH = RevLibGenConstantine.REV_MARGIN_SMALL;
        int paddingV = RevLibGenConstantine.REV_MARGIN_TINY;

        textView.setPadding(paddingH, paddingV, paddingH, paddingV);
        textView.setCompoundDrawablePadding(1);
        textView.setGravity(Gravity.CENTER_VERTICAL);
    }

    private View revViewProfileTab() {
        TextView connectTab_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        connectTab_TV.setText("viEw pRoFiLE");
        connectTab_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));

        this.setTabProperties(connectTab_TV);

        return connectTab_TV;
    }

    private View revSendMessageTab() {
        TextView connectTab_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        connectTab_TV.setText("sEND mEssAGE");
        connectTab_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.colorPrimary));

        this.setTabProperties(connectTab_TV);

        return connectTab_TV;
    }

    private View revConnectTab() {
        TextView connectTab_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        connectTab_TV.setText("coNNEcT");
        connectTab_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.lime_primary));

        this.setTabProperties(connectTab_TV);

        return connectTab_TV;
    }

    public View revEditInfoTab() {
        TextView revEditInfoTab_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING(.9f);
        revEditInfoTab_TV.setText("EDiT iNFo");
        revEditInfoTab_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        ((LinearLayout.LayoutParams) revEditInfoTab_TV.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
        ((LinearLayout.LayoutParams) revEditInfoTab_TV.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);

        this.setTabProperties(revEditInfoTab_TV);

        revEditInfoTab_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData passRVD = new RevVarArgsData(mContext);
                passRVD.setRevViewType("RevCreateRevEditInfoFormObject");
                passRVD.setRevEntity(revPageOwnerEntity);

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);
                iRevInputFormView.createRevInputForm();

                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return revEditInfoTab_TV;
    }

    public LinearLayout getRevDetailsWidget() {
        LinearLayout linearLayoutContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayoutContainer_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.3), RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        if (revPageOwnerEntity != null) {
            linearLayoutContainer_LL.addView(this.selectProfilePicsTab());

            LinearLayout revEntityOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) revEntityOptionsWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

            revEntityOptionsWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
            revEntityOptionsWrapper_LL.addView(this.revConnectTab());
            revEntityOptionsWrapper_LL.addView(this.revViewProfileTab());
            revEntityOptionsWrapper_LL.addView(this.revSendMessageTab());
            revEntityOptionsWrapper_LL.addView(this.revEditInfoTab());

            linearLayoutContainer_LL.addView(revEntityOptionsWrapper_LL);

            if (revInfoEntity != null && (revInfoEntity.get_revEntityGUID().longValue() > 0 || revInfoEntity.get_remoteRevEntityGUID() > 0)) {
                if (revInfoEntity.get_revEntityGUID() > 0) {
                    linearLayoutContainer_LL.addView(this.revEntityProfileIcons());
                } else {
                    linearLayoutContainer_LL.addView(this.remoteImages());
                }

                LinearLayout leftInfoContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
                leftInfoContainer_LL.addView(this.getRevEntityDesc());
                leftInfoContainer_LL.addView(this.getUserHeight());
                leftInfoContainer_LL.addView(this.getUserRace());

                LinearLayout revInfoMidWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

                revInfoMidWrapper_LL.addView(leftInfoContainer_LL);
                revInfoMidWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());

                linearLayoutContainer_LL.addView(revInfoMidWrapper_LL);
            } else if (revPageOwnerEntity.get_revEntityGUID().longValue() == REV_SESSION_SETTINGS.getRevLoggedInEntityGuid().longValue()) {
                linearLayoutContainer_LL.addView(this.revUnsetInfo());
                linearLayoutContainer_LL.addView(this.revEditInfoTab());
            }
        } else {
            linearLayoutContainer_LL.addView(new RevNetworkResolverViews(mContext).getNoRevNullEntityNetworkResolverView());
        }

        return linearLayoutContainer_LL;
    }

    public LinearLayout selectProfilePicsTab() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int textViewBorderSize = 1;

        GradientDrawable textViewBorder = new GradientDrawable();
        textViewBorder.setStroke(textViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_primary));
        textViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.colorTransparent));
        textViewBorder.setGradientType(RECTANGLE);

        Drawable[] textViewLayers = {textViewBorder};
        LayerDrawable textViewLayerDrawable = new LayerDrawable(textViewLayers);
        textViewLayerDrawable.setLayerInset(0, -textViewBorderSize, -textViewBorderSize, -textViewBorderSize, textViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, textViewLayerDrawable);

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.baseline_nature_people_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = (Gravity.BOTTOM);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, (int) (-RevLibGenConstantine.REV_MARGIN_TINY * .2));
        imageView.setLayoutParams(layoutParams);

        /** TEXT VIEW  **/

        String mainTextLeadStringReduced = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(String.valueOf(revPageOwnerFullNames), 55);

        SpannableString profileNameTtlSpan = new SpannableString(mainTextLeadStringReduced);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (mainTextLeadStringReduced.length() - (mainTextLeadStringReduced.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, mainTextLeadStringReduced.length(), 0); // set color

        TextView textView = revCoreInputFormTextView.getRevSmallBoldTextView();
        textView.setText(profileNameTtlSpan);
        textView.setGravity(Gravity.BOTTOM);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = (Gravity.BOTTOM);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, 0, 0, (int) (-RevLibGenConstantine.REV_MARGIN_TINY * .5));

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        return linearLayout;
    }

    private View remoteImages() {
        LinearLayout revNullEntitiesContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revNullEntitiesContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revNullEntitiesContainer_LL.setPadding(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        if (revInfoEntity == null) {
            String revErrMsg = "THis iNFo HAs NoT yET BEEN sET";
            revNullEntitiesContainer_LL.addView(new RevNetworkResolverViews(mContext).getNoRevNullEntityNetworkResolverView(revErrMsg));

            return revNullEntitiesContainer_LL;
        }

        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
        linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));

        RevEntity revProfilePicALbumEntity = new RevEntity();
        List<Long> revAddedGUIDS = new ArrayList<>();

        for (RevEntity revEntity : revInfoEntity.get_revEntityChildrenList()) {
            if (revEntity.get_revEntitySubType().equals("rev_pics_album")) {
                revProfilePicALbumEntity = revEntity;
                break;
            }

            if (revEntity.get_revEntitySubType().equals("rev_file") && !revAddedGUIDS.contains(revEntity.get_remoteRevEntityGUID())) {
                revAddedGUIDS.add(revEntity.get_remoteRevEntityGUID());
                revProfilePicALbumEntity.get_revEntityChildrenList().add(revEntity);
            }
        }

        List<RevEntity> revPicsChildrenList = revProfilePicALbumEntity.get_revEntityChildrenList();

        for (int i = 0; i < revPicsChildrenList.size(); i++) {
            RevEntity revEntity = revPicsChildrenList.get(i);
            if (!revEntity.get_revEntitySubType().equals("rev_file") || revEntity.get_revEntityMetadataList().isEmpty())
                continue;

            final ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams iV_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            iV_LP.gravity = Gravity.CENTER_VERTICAL;

            if (i != revPicsChildrenList.size() - 1) iV_LP.setMargins(0, 0, 1, 0);

            imageView.setLayoutParams(iV_LP);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.7);

            final String revFinalSelectImagePath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_remote_file_name");

            Picasso.get()
                    .load(revFinalSelectImagePath)
                    .placeholder(R.drawable.icons8_picture_16)
                    .resize(0, imgSize)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new RevFullPagePhotoViewTab(revVarArgsData).revEntityImageViewFullView(revFinalSelectImagePath);
                                }
                            });

                            linearLayout.addView(imageView);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
        }

        HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.addView(linearLayout);

        revNullEntitiesContainer_LL.addView(scrollView);

        return revNullEntitiesContainer_LL;
    }

    private View revEntityProfileIcons() {
        List<RevEntity> revEntityInfoChildren = revInfoEntity.get_revEntityChildrenList();

        RevEntity revProfilePicALbumEntity = null;

        for (RevEntity revEntity : revEntityInfoChildren) {
            if (revEntity.get_revEntitySubType().equals("rev_pics_album")) {
                revProfilePicALbumEntity = revEntity;
                break;
            }
        }

        LinearLayout revNullEntitiesContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revNullEntitiesContainer_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        revNullEntitiesContainer_LL.setPadding(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        String imagesCount_S;

        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);
        linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));

        if (revProfilePicALbumEntity == null) {
            imagesCount_S = "you Do NoT HAvE ANy pRoFiLE picTuREs sET up FoR THis proFiLE";

            TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.9f);
            textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(imagesCount_S));
            textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));

            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.3), 0, 0, 0);
            revNullEntitiesContainer_LL.addView(textView);
        } else {
            List<RevEntity> revPicsAlbumChildren = revProfilePicALbumEntity.get_revEntityChildrenList();

            for (RevEntity revFileEntity : revPicsAlbumChildren) {
                final String revFilePath = RevPersConstantine.revBaseUserImagesLargeDirPath + "/" + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revFileEntity.get_revEntityMetadataList(), "rev_file_name_value");
                final File picImgFile = new File(revFilePath);

                final ImageView imageView = new ImageView(mContext);

                final int imageViewBorderSize = 8;
                final int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.7);

                if (picImgFile.exists()) {
                    Picasso.get()
                            .load(new File(revFilePath))
                            .resize(imgSize, imgSize)
                            .centerCrop()
                            .into(imageView, new Callback() {
                                @Override
                                public void onSuccess() {
                                    revProfileImagesCount++;

                                    GradientDrawable imageViewBorderTop = new GradientDrawable();
                                    imageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.rev_red));
                                    imageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.teal_300_dark));
                                    imageViewBorderTop.setGradientType(RECTANGLE);

                                    GradientDrawable imageViewBorder = new GradientDrawable();
                                    imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
                                    imageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.teal_dark));
                                    imageViewBorder.setGradientType(RECTANGLE);

                                    Drawable[] imageViewLayers = {imageViewBorderTop, imageViewBorder};
                                    LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
                                    imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);
                                    imageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

                                    REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imageView, imageViewLayerDrawable);

                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                                    imageView.setLayoutParams(layoutParams);

                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            revVarArgsData.setmContext(RevLibGenConstantine.REV_CONTEXT);
                                            new RevFullPagePhotoViewTab(revVarArgsData).revEntityImageViewFullView(revFilePath);
                                        }
                                    });

                                    imageView.setOnLongClickListener(new View.OnLongClickListener() {
                                        @Override
                                        public boolean onLongClick(View v) {
                                            REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_RESET_METADATA_VALUE(revPageOwnerEntity, "rev_user_icon_path_value", revFilePath);
                                            RevConstantinePluggableViewsServices.REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevResetUserProfileIconAction").initRevAction(revPageOwnerEntity);
                                            return true;
                                        }
                                    });

                                    linearLayout.addView(imageView);
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                }
            }
        }

        HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.addView(linearLayout);

        revNullEntitiesContainer_LL.addView(scrollView);

        revNullEntitiesContainer_LL.addView(this.genPublisherWrapper_LL());
        revNullEntitiesContainer_LL.addView(this.selectedItems_LL);

        return revNullEntitiesContainer_LL;
    }

    public LinearLayout genPublisherWrapper_LL() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int textViewBorderSize = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .4);

        GradientDrawable textViewBorder = new GradientDrawable();
        textViewBorder.setStroke(textViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        textViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.colorTransparent));
        textViewBorder.setGradientType(RECTANGLE);

        Drawable[] textViewLayers = {textViewBorder};
        LayerDrawable textViewLayerDrawable = new LayerDrawable(textViewLayers);
        textViewLayerDrawable.setLayerInset(0, -textViewBorderSize, -textViewBorderSize, -textViewBorderSize, textViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, textViewLayerDrawable);

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.ic_publish_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = (Gravity.BOTTOM);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, (int) (-textViewBorderSize * .1));
        imageView.setLayoutParams(layoutParams);

        /** TEXT VIEW  **/

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);
        textView.setText("ADD pRoFiLE picTuREs");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        textView.setGravity(Gravity.BOTTOM);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_LARGE, 0);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (selectedItems != null) {
                    Iterator iterator = revVarArgsData.getRevVarArgsDataMetadataStrings().keySet().iterator();
                    while (iterator.hasNext()) {
                        Object key = iterator.next();

                        if (!selectedItems.contains(key))
                            iterator.remove();
                    }
                }

                RevPictureFileCrawler revPictureFileCrawler = new RevPictureFileCrawler(revVarArgsData, new IRevFileCrawler() {
                    @Override
                    public void revCallBack(List<String> selectedItems) {
                        RevInfoDetailsWidget.this.selectedItems = selectedItems;
                        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(selectedItems_LL);

                        if (selectedItems.size() > 0)
                            selectedItems_LL.addView(new RevGenericSelectedItemsView(revVarArgsData).revSelectedItemsView(selectedItems));
                    }
                });

                RevPop.initiatePopupWindow_MATCH_WIDTH(revPictureFileCrawler.createRevFileCrawler());
            }
        });

        return linearLayout;
    }

    public View revUnsetInfo() {
        String profileNameTtl = "you HAveN'T sET up youR iNFo. cLick BELow To pRocEED";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        String revEntityName_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(revPageOwnerFullNames), 32);

        revEntityName_S = revEntityName_S.replace("\n", " ").replace("\r", " ");

        SpannableString revEntityName_S_Span = new SpannableString(revEntityName_S);
        revEntityName_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revEntityName_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        /** END SPANNING **/

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan, " ", revEntityName_S_Span);

        TextView unsetTell_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        unsetTell_TV.setGravity(Gravity.CENTER_HORIZONTAL);
        ((LinearLayout.LayoutParams) unsetTell_TV.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
        unsetTell_TV.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        unsetTell_TV.setText(finalText);

        return unsetTell_TV;
    }

    private View getRevEntityDesc() {
        String profileNameTtl = "ABouT ME ";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        String revEntityName_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(revPageOwnerFullNames, 100);
        revEntityName_S = revEntityName_S.replace("\n", " ").replace("\r", " ");

        SpannableString revEntityName_S_Span = new SpannableString(revEntityName_S);
        revEntityName_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revEntityName_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        /** END SPANNING **/

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan, " ", revEntityName_S_Span);

        TextView tVCct = revCoreInputFormTextView.getRevSmallNormalTextView();
        tVCct.setText(finalText);

        return tVCct;
    }

    private View getUserHeight() {
        String profileNameTtl = "HEiGHT";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .7)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        String revInfoHeight_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revInfoEntity.get_revEntityMetadataList(), "rev_info_height_value");
        revInfoHeight_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(revInfoHeight_S, 100);
        revInfoHeight_S = revInfoHeight_S.replace("\n", " ").replace("\r", " ");

        SpannableString revEntityName_S_Span = new SpannableString(revInfoHeight_S);
        revEntityName_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revInfoHeight_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        /** END SPANNING **/

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan, " ", revEntityName_S_Span);

        TextView tVCct = revCoreInputFormTextView.getRevSmallNormalTextView();
        tVCct.setText(finalText);

        return tVCct;
    }

    private View getUserRace() {
        String profileNameTtl = "RAcE \\ ETHNiciTy";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .7)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        String revEntityRace_Ethnicity_S = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revInfoEntity.get_revEntityMetadataList(), "rev_info_race_value");
        revEntityRace_Ethnicity_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(revEntityRace_Ethnicity_S, 100);


        revEntityRace_Ethnicity_S = revEntityRace_Ethnicity_S.replace("\n", " ").replace("\r", " ");

        SpannableString revEntityName_S_Span = new SpannableString(revEntityRace_Ethnicity_S);
        revEntityName_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revEntityRace_Ethnicity_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        /** END SPANNING **/

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan, " ", revEntityName_S_Span);

        TextView tVTell = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        tVTell.setText(R.string.user_race_title);

        TextView tVCct = revCoreInputFormTextView.getRevSmallNormalTextView();
        tVCct.setText(finalText);

        return tVCct;
    }

    @Override
    public void revCallBack(List<String> selectedItems) {
        this.selectedItems = selectedItems;

        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(selectedItems_LL);

        if (selectedItems.size() > 0)
            selectedItems_LL.addView(this.revDrawChosenItems(selectedItems));
    }

    private View revDrawChosenItems(List<String> selectedItems) {

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        for (int i = 0; i < selectedItems.size(); i++) {
            final String imgPath = selectedItems.get(i);

            if (new File(imgPath).exists()) {
                ImageView imageView = new ImageView(mContext);
                REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

                Picasso.get()
                        .load(new File(imgPath))
                        .resize(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM)
                        .centerCrop()
                        .into(imageView);

                linearLayout.addView(imageView);
            }
        }

        scrollView.addView(linearLayout);

        return scrollView;
    }
}
