package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_object;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service.RevPluginTranslationBlock;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.RevPluginTranslationObject;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_libs.RevTranslateGenFunctions;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevNetworkResolverViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormSpinner;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevTranslateObjectView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    private List<RevEntity> revEntityList;

    private List<long[]> revSelectValuesGUIDsList = new ArrayList<>();
    private List<String> revSelectValuesList = new ArrayList<>();

    private void revInitLangOptions() {
        for (RevEntity revEntity : revEntityList) {
            revEntity.get_revEntityMetadataList().addAll(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntity.get_revEntityGUID()));
            String revLanguageNameValue = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_language_name_value");

            revSelectValuesList.add(revLanguageNameValue);
            revSelectValuesGUIDsList.add(new long[]{revEntity.get_revEntityGUID(), revEntity.get_remoteRevEntityGUID()});
        }
    }

    public RevTranslateObjectView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        // Rev create a list of items for the spinner.
        revEntityList = Arrays.asList(new RevPersLibRead().revPersGetALLRevEntity_By_SubType("rev_entity_language"));

        ((LinearLayout) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("REV_TRANSLATION_BLOCK_CONTAINER_LL")).removeAllViews();

        this.revInitLangOptions();
    }

    public View getRevTranslateObjectView() {
        LinearLayout linearLayout_Container = new RevCoreLayoutsLinearLayout(RevLibGenConstantine.REV_CONTEXT).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout_Container.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.3), 0, 0, 0);

        String revTranslateTell_S = "AnyoNE cAN TRANLATE \nHELp BRiNG CampAnn To THE woRLD ARouND you\n\n";

        SpannableString revTranslateTell_S_Span = new SpannableString(revTranslateTell_S);
        revTranslateTell_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .8)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        revTranslateTell_S_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, revTranslateTell_S.length(), 0); // set color

        TextView revTranslateTell_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        revTranslateTell_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(revTranslateTell_S));
        revTranslateTell_TV.setGravity(Gravity.CENTER_HORIZONTAL);
        ((LinearLayout.LayoutParams) revTranslateTell_TV.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;

        /**  START REV DESC TELL **/
        String revDesc_S = "please keep your translations as short / succiNcT as possible\n\nDoesN'T NEED be particularly AccuRATE\nfEEL FREE To sLicE woRDs & pHRAsEs";

        TextView revDesc_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
        revDesc_TV.setText(revDesc_S);
        revDesc_TV.setGravity(Gravity.CENTER_HORIZONTAL);
        ((LinearLayout.LayoutParams) revDesc_TV.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
        /** END REV DESC TELL **/

        LinearLayout revCoreDescItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        int revPadding = RevLibGenConstantine.REV_MARGIN_SMALL;
        revCoreDescItemsContainer_LL.setPadding(revPadding, revPadding, revPadding, revPadding);

        revCoreDescItemsContainer_LL.addView(revTranslateTell_TV);
        revCoreDescItemsContainer_LL.addView(revDesc_TV);

        revDecView(revCoreDescItemsContainer_LL);

        LinearLayout revScrollViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revScrollViewContainer_LL.addView(this.revLangOptionsView());
        linearLayout_Container.addView(revCoreDescItemsContainer_LL);
        linearLayout_Container.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("REV_TRANSLATION_BLOCK_CONTAINER_LL"));

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.setScrollbarFadingEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);

        LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );

        scrollView.setLayoutParams(scrollView_LP);
        scrollView.addView(linearLayout_Container);

        revScrollViewContainer_LL.addView(scrollView);

        return revScrollViewContainer_LL;
    }

    private View revLangOptionsView() {
        LinearLayout revLangOptionsWrapper = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revLangOptionsWrapper.addView(this.revCreateNewLanguageOption());
        revLangOptionsWrapper.addView(this.revLangsOptions());

        return revLangOptionsWrapper;
    }

    private View revCreateNewLanguageOption() {
        LinearLayout revCreateNewLangWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) revCreateNewLangWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
        ((LinearLayout.LayoutParams) revCreateNewLangWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revPurple));

        ImageView imageView = new ImageView(mContext);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_500_dark));
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.ic_add_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        revCreateNewLangWrapper_LL.addView(imageView);

        LinearLayout.LayoutParams contentHeaderItemTab_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentHeaderItemTab_TV_LP.gravity = Gravity.CENTER_VERTICAL;
        contentHeaderItemTab_TV_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .1), 0, 0, 0);

        TextView contentHeader_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) .8);
        contentHeader_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("cREATE NEw LANGuAGE"));
        contentHeader_TV.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        contentHeader_TV.setLayoutParams(contentHeaderItemTab_TV_LP);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(contentHeader_TV);

        revCreateNewLangWrapper_LL.addView(contentHeader_TV);

        revCreateNewLangWrapper_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());

                postRVD.setRevViewType("RevCreateNewLanguageInputForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return revCreateNewLangWrapper_LL;
    }

    private View revLangsOptions() {
        LinearLayout revLangSelectWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) revLangSelectWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);
        ((LinearLayout.LayoutParams) revLangSelectWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;

        TextView revSelectLangTell_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        revSelectLangTell_TV.setText("sELEcT LANGuAGE : ");
        ((LinearLayout.LayoutParams) revSelectLangTell_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0);
        revLangSelectWrapper_LL.addView(revSelectLangTell_TV);

        revLangSelectWrapper_LL.addView(new RevCoreInputFormSpinner(mContext, revSelectValuesList.indexOf(new RevTranslateGenFunctions().revDefLoggedInLangName()), revSelectValuesList, new RevCoreInputFormSpinner.IRevCoreInputFormSpinner() {
            @Override
            public void revSpinnerOnSelectedCallback(int revSelectedPos) {
                revOnItemSelected(revSelectedPos);
            }
        }).revGetRevCoreInputFormSpinner());

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView revSettingsImageView = new ImageView(mContext);
        revSettingsImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
        revSettingsImageView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(revSettingsImageView);

        Picasso.get()
                .load(R.drawable.ic_settings_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(revSettingsImageView);

        revSettingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData revPassRVD = REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData();
                revPassRVD.setRevEntity(revPassRVD.getRevEntity());
                revPassRVD.setRevViewType("RevBagTypeChooserInputForm");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revPassRVD);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        revLangSelectWrapper_LL.addView(revSettingsImageView);

        return revLangSelectWrapper_LL;
    }


    public void revOnItemSelected(int pos) {
        if (pos >= 0) {
            long revSelectedItemEntityGUID = revSelectValuesGUIDsList.get(pos)[0];

            List<RevEntity> revEntityTranslationBlocksList = Arrays.asList(revPersLibRead.revPersGet_ALL_RevEntity_By_RevEntityContainerGUID_SubTYPE(revSelectedItemEntityGUID, "rev_entity_lang_translation_block"));

            if (revEntityTranslationBlocksList.size() < 1) {
                String revNullLangs_S = "No LANGuAGEs\n\npLEAsE cREATE A LANGuAGE ABovE";
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_TRANSLATION_BLOCK_CONTAINER_LL",
                        new RevNetworkResolverViews(mContext).getNoRevNullEntityNetworkResolverView(String.valueOf(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(revNullLangs_S))));

                return;
            }

            for (RevEntity revPluginTranslationBlockEntity : revEntityTranslationBlocksList) {
                List<RevEntityMetadata> revPluginTranslationBlockEntityMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revPluginTranslationBlockEntity.get_revEntityGUID());

                RevPluginTranslationBlock revPluginTranslationBlock = new RevPluginTranslationBlock();
                revPluginTranslationBlock.setRevPluginTranslationBlockGUID(revPluginTranslationBlockEntity.get_revEntityGUID());
                revPluginTranslationBlock.setRevTranslationBlockName(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPluginTranslationBlockEntityMetadataList, "rev_translation_block_name"));
                revPluginTranslationBlock.setRevTranslationBloackTitle(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revPluginTranslationBlockEntityMetadataList, "rev_translation_block_title"));

                List<RevEntity> revSelectedLangTranslationItemsList = Arrays.asList(revPersLibRead.revPersGet_ALL_RevEntity_By_RevEntityContainerGUID_SubTYPE(revPluginTranslationBlockEntity.get_revEntityGUID(), "rev_entity_lang_translation_item"));

                for (RevEntity revTranslationItemEntity : revSelectedLangTranslationItemsList) {
                    List<RevEntityMetadata> revEntityMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revTranslationItemEntity.get_revEntityGUID());

                    RevPluginTranslationObject revPluginTranslationObject = new RevPluginTranslationObject();
                    revPluginTranslationObject.setRevPluginTranslationObjectGUID(revTranslationItemEntity.get_revEntityGUID());

                    revPluginTranslationObject.setRevTranslationItemName(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_translation_item_name"));
                    revPluginTranslationObject.setRevTranslationItemValue(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_translation_item_value"));
                    revPluginTranslationObject.setRevTranslationItemValueDesc(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_translation_item_value_desc"));
                    revPluginTranslationObject.setRevTranslationPluginName(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_translation_plugin_name"));
                    revPluginTranslationObject.setRevTranslationPluginBlockName(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_translation_plugin_block_name"));

                    revPluginTranslationBlock.getiRevPluginRegisterTranslationObject_spis().add(revPluginTranslationObject);
                }

                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_TRANSLATION_BLOCK_CONTAINER_LL", revTranslationBlockContainer(revPluginTranslationBlock));
            }
        }
    }

    private View revTranslationBlockContainer(RevPluginTranslationBlock pluginRegisterTranslationBlock) {
        LinearLayout revTranslationBlockContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revTranslationBlockContainer_LL.getLayoutParams()).setMargins(0, 0, 0, (int) (RevLibGenConstantine.REV_MARGIN_LARGE * .52));

        LinearLayout revEditTabWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revEditTabWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, Color.WHITE);
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revEditTabWrapper_LL, layerDrawable);

        TextView revTranslationBlockTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        revTranslationBlockTtl_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(pluginRegisterTranslationBlock.getRevTranslationBloackTitle()));
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(revTranslationBlockTtl_TV);

        revEditTabWrapper_LL.addView(revTranslationBlockTtl_TV);

        TextView revEditTab_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);
        revEditTab_TV.setText("[ EDiT ]");
        ((LinearLayout.LayoutParams) revEditTab_TV.getLayoutParams()).setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * .55f), 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(revEditTab_TV);
        ((LinearLayout.LayoutParams) revEditTab_TV.getLayoutParams()).gravity = Gravity.RIGHT;

        revEditTabWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        revEditTabWrapper_LL.addView(revEditTab_TV);

        revTranslationBlockContainer_LL.addView(revEditTabWrapper_LL);

        if (pluginRegisterTranslationBlock.getRevTranslationBlockView() != null)
            revTranslationBlockContainer_LL.addView(pluginRegisterTranslationBlock.getRevTranslationBlockView());

        int revPadding = RevLibGenConstantine.REV_MARGIN_SMALL;
        revTranslationBlockContainer_LL.setPadding(revPadding, revPadding, revPadding, revPadding);

        revDecView(revTranslationBlockContainer_LL);

        for (RevPluginTranslationObject iRevPluginRegisterTranslationItems_spi : pluginRegisterTranslationBlock.getiRevPluginRegisterTranslationObject_spis()) {
            String revTranslationPluginBlockName = iRevPluginRegisterTranslationItems_spi.getRevTranslationPluginBlockName();

            if (pluginRegisterTranslationBlock.getRevTranslationBlockName().equals(revTranslationPluginBlockName)) {
                LinearLayout revTranslationValueItemContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                ((LinearLayout.LayoutParams) revTranslationValueItemContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

                TextView revTranslationItemValue_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
                revTranslationItemValue_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(iRevPluginRegisterTranslationItems_spi.getRevTranslationItemValue()));
                ((LinearLayout.LayoutParams) revTranslationItemValue_TV.getLayoutParams()).width = 0;
                ((LinearLayout.LayoutParams) revTranslationItemValue_TV.getLayoutParams()).weight = 9;
                ((LinearLayout.LayoutParams) revTranslationItemValue_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

                TextView revEditTranslationValueTab_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);
                revEditTranslationValueTab_TV.setText("[ EDiT ]");
                ((LinearLayout.LayoutParams) revEditTranslationValueTab_TV.getLayoutParams()).width = 0;
                ((LinearLayout.LayoutParams) revEditTranslationValueTab_TV.getLayoutParams()).weight = 1;
                ((LinearLayout.LayoutParams) revEditTranslationValueTab_TV.getLayoutParams()).gravity = Gravity.RIGHT;

                LinearLayout revtranslationValueWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                ((LinearLayout.LayoutParams) revtranslationValueWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

                revtranslationValueWrapper_LL.addView(revTranslationItemValue_TV);
                revtranslationValueWrapper_LL.addView(revEditTranslationValueTab_TV);

                TextView revTranslationItemValueDesc_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
                ((LinearLayout.LayoutParams) revTranslationItemValueDesc_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

                String revTranslationItemValueDesc_S = iRevPluginRegisterTranslationItems_spi.getRevTranslationItemValueDesc();

                if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revTranslationItemValueDesc_S)) {
                    revTranslationItemValueDesc_TV.setText(revTranslationItemValueDesc_S);
                } else {
                    revTranslationItemValueDesc_TV.setText("No Description");
                }

                revTranslationValueItemContainer_LL.addView(revtranslationValueWrapper_LL);
                revTranslationValueItemContainer_LL.addView(revTranslationItemValueDesc_TV);

                revTranslationBlockContainer_LL.addView(revTranslationValueItemContainer_LL);
            }
        }

        return revTranslationBlockContainer_LL;
    }

    private void revDecView(View revDecView) {
        ShapeDrawable background = new ShapeDrawable();
        background.getPaint().setColor(mContext.getResources().getColor(R.color.lime_primary));

        ShapeDrawable leftBorder = new ShapeDrawable();
        leftBorder.getPaint().setColor(mContext.getResources().getColor(R.color.lime_primary));

        ShapeDrawable border = new ShapeDrawable();
        border.getPaint().setColor(mContext.getResources().getColor(R.color.teal_dark));

        ShapeDrawable clip = new ShapeDrawable();
        clip.getPaint().setColor(mContext.getResources().getColor(R.color.rev_default_light));

        Drawable[] layers = {background, leftBorder, border, clip};
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        layerDrawable.setLayerInset(1, 0, 1, 1, 0);
        layerDrawable.setLayerInset(2, 7, 1, 1, 0);
        layerDrawable.setLayerInset(3, 7, 1, 1, 1);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revDecView, layerDrawable);
    }
}
