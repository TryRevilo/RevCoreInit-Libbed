package rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_pluggable_menus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_libs.RevTranslateGenFunctions;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluggableViewProperties;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormSpinner;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/23/17.
 */

public class CreatePluggableSettingsTopBarMenuViewItems extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    private int imageSize;

    public CreatePluggableSettingsTopBarMenuViewItems(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);

        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        this.revInitLangOptions();
    }

    @Override
    public IRevPluggableViewProperties createIRevPluggableViewProperties() {
        IRevPluggableViewProperties iRevPluggableViewProperties = new IRevPluggableViewProperties(mContext);
        iRevPluggableViewProperties.setViewPlacement(3);
        return iRevPluggableViewProperties;
    }

    @Override
    public ArrayList<View> createPluggableTopBarMenuViewItem() {
        ArrayList<View> views = new ArrayList<>();
        views.add(this.revSettingsTab());

        return views;
    }

    private View revSettingsTab() {
        Button tab = new Button(mContext);
        tab.setBackgroundColor(Color.TRANSPARENT);

        Drawable profileBttnImg = mContext.getResources().getDrawable(R.drawable.ic_settings_black_48dp);
        profileBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(profileBttnImg, ContextCompat.getColor(mContext, R.color.teal_500_dark));
        tab.setCompoundDrawables(profileBttnImg, null, null, null);

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout revSettingsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                revSettingsContainer_LL.addView(revGenSettingsMenueItemsWrapper());
                revSettingsContainer_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("rev_settings_item_view_container_pluggable_scroll_view"));

                REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData()).revResetPageOwnerProfileContent_NO_FLOATING_WIDGET(revSettingsContainer_LL));
            }
        });

        return tab;
    }

    private View revDefaultTSettingsTab() {
        RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("rev_settings_item_view_container_pluggable_scroll_view", this.revDefaultSettingsView());

        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING(.8f);
        textView.setText("DEFAuLT sETTiNGs");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("rev_settings_item_view_container_pluggable_scroll_view", revDefaultSettingsView());
            }
        });

        return textView;
    }

    private List<RevEntity> revEntityList;

    private List<long[]> revSelectValuesGUIDsList = new ArrayList<>();
    private List<String> revSelectValuesList = new ArrayList<>();

    private void revInitLangOptions() {
        for (RevEntity revEntity : Arrays.asList(new RevPersLibRead().revPersGetALLRevEntity_By_SubType("rev_entity_language"))) {
            revEntity.get_revEntityMetadataList().addAll(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntity.get_revEntityGUID()));
            String revLanguageNameValue = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_language_name_value");

            revSelectValuesList.add(revLanguageNameValue);
            revSelectValuesGUIDsList.add(new long[]{revEntity.get_revEntityGUID(), revEntity.get_remoteRevEntityGUID()});
        }
    }

    private View revDefaultSettingsView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);

        revDecView(linearLayout, mContext.getResources().getColor(R.color.lime_primary));

        TextView revLanguageTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(1.4f);
        revLanguageTtl_TV.setText("LANGuAGE");

        linearLayout.addView(revLanguageTtl_TV);

        /** START REV DESC **/

        LinearLayout revPluginListItemViewMid_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        String revPluginDescription = "my preferred account Language settings";

        if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revPluginDescription)) {
            TextView revPluginDesc_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
            revPluginDesc_TV.setText(revPluginDescription);

            revPluginListItemViewMid_LL.addView(revPluginDesc_TV);
        }

        linearLayout.addView(revPluginListItemViewMid_LL);

        final RevTranslateGenFunctions revTranslateGenFunctions = new RevTranslateGenFunctions();

        if (revTranslateGenFunctions != null) {
            linearLayout.addView(new RevCoreInputFormSpinner(mContext, revSelectValuesList.indexOf(revTranslateGenFunctions.revDefLoggedInLangName()), revSelectValuesList, new RevCoreInputFormSpinner.IRevCoreInputFormSpinner() {
                @Override
                public void revSpinnerOnSelectedCallback(int revSelectedPos) {
                    if (revSelectedPos >= 0) {
                        long revSelecetedLangEntityGUID = revPersLibRead.revGetRevEntityMetadataOwnerGUID_By_MetadataName_MetadataValue("rev_language_name_value", revSelectValuesList.get(revSelectedPos));
                        long revDefaultLoggedinUserLanguageGUID = revPersLibRead.revGetRevEntityMetadataId_By_RevMetadataName_RevEntityGUID("rev_default_loggedin_user_language_guid_value", revTranslateGenFunctions.revDefLoggedInLangSettingsGUID());

                        new RevPersLibUpdate().setMetadataValue_BY_MetadataId_RevEntityGUID(revDefaultLoggedinUserLanguageGUID, String.valueOf(revSelecetedLangEntityGUID));
                        Toast.makeText(mContext, "OnItemSelectedListener : " + revSelectValuesList.get(revSelectedPos), Toast.LENGTH_SHORT).show();
                    }
                }
            }).revGetRevCoreInputFormSpinner());
        }

        /** END REV DESC **/

        return linearLayout;
    }

    private View revGenSettingsMenueItemsWrapper() {
        RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        LinearLayout revPageOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revPageOptionsWrapper_LL.getLayoutParams()).setMargins(0, -2, 0, 0);

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView_LP.gravity = Gravity.CENTER_VERTICAL;
        scrollView.setLayoutParams(scrollView_LP);

        int imgSize = (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);

        ImageView leftScroll_IV = new ImageView(mContext);
        leftScroll_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revExtraLightGreen_OPAQUE));
        leftScroll_IV.setPadding(0, 0, 1, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(leftScroll_IV);

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_chevron_left_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(leftScroll_IV);

        ImageView rightScroll_IV = new ImageView(mContext);
        rightScroll_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revExtraLightGreen_OPAQUE));
        rightScroll_IV.setPadding(1, 0, 0, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(rightScroll_IV);

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_chevron_right_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(rightScroll_IV);

        LinearLayout revMainUserPluggableMenuesWrapper_LL = (LinearLayout) new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_main_user_settings_menu_wrapper", REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData());

        revMainUserPluggableMenuesWrapper_LL.addView(this.revDefaultTSettingsTab(), 0);

        int revPadding = RevLibGenConstantine.REV_MARGIN_SMALL;

        for (int i = 0; i < revMainUserPluggableMenuesWrapper_LL.getChildCount(); i++) {
            View view = revMainUserPluggableMenuesWrapper_LL.getChildAt(i);
            view.setPadding(revPadding, RevLibGenConstantine.REV_MARGIN_TINY, revPadding, RevLibGenConstantine.REV_MARGIN_TINY);

            this.revSetBackground(view);
        }

        if (revMainUserPluggableMenuesWrapper_LL != null)
            scrollView.addView(revMainUserPluggableMenuesWrapper_LL);

        revPageOptionsWrapper_LL.addView(leftScroll_IV);
        revPageOptionsWrapper_LL.addView(scrollView);
        revPageOptionsWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        revPageOptionsWrapper_LL.addView(rightScroll_IV);

        return revPageOptionsWrapper_LL;
    }

    private void revSetBackground(View revSubjectView) {
        GradientDrawable searchTab_IV_LL_border = new GradientDrawable();
        searchTab_IV_LL_border.setColor(generateRandomColor());
        searchTab_IV_LL_border.setStroke(1, generateRandomColor());
        searchTab_IV_LL_border.setCornerRadii(new float[]{0, 0, 100, 100, 0, 0, 100, 100});
        searchTab_IV_LL_border.setGradientType(RECTANGLE);

        Drawable[] searchTab_IV_LL_layers = {searchTab_IV_LL_border};
        LayerDrawable searchTab_IV_LL_ayerDrawable = new LayerDrawable(searchTab_IV_LL_layers);
        searchTab_IV_LL_ayerDrawable.setLayerInset(0, 1, 1, 1, 1);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revSubjectView, searchTab_IV_LL_ayerDrawable);
    }

    final Random mRandom = new Random(System.currentTimeMillis());

    public int generateRandomColor() {
        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

    private void revDecView(View revDecView, int revColor) {
        ShapeDrawable background = new ShapeDrawable();
        background.getPaint().setColor(revColor);

        ShapeDrawable clip = new ShapeDrawable();
        clip.getPaint().setColor(mContext.getResources().getColor(R.color.rev_default_light));

        Drawable[] layers = {background, clip};
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        layerDrawable.setLayerInset(1, 0, 0, 0, 1);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revDecView, layerDrawable);
    }
}
