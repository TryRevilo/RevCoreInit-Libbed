package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_plugin_views.rev_listings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services.RevPluginItemObject;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevInstalledPluginsListingView {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevInstalledPluginsListingView(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getRevInstalledPluginsListingView() {
        LinearLayout revInstalledPluginsListingViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revInstalledPluginsListingViewContainer_LL.addView(this.getRevPageTitleView());

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.setScrollbarFadingEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);

        LinearLayout scrollViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        RevPersLibRead revPersLibRead = new RevPersLibRead();

        List<RevEntity> revEntityList = Arrays.asList(revPersLibRead.revPersGetALLRevEntity_By_SubType("rev_plugin"));

        for (RevEntity revEntity : revEntityList) {
            revEntity.get_revEntityMetadataList().addAll(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntity.get_revEntityGUID()));

            RevPluginItemObject revPluginItemObject = new RevPluginItemObject().revResolveRevEntity_To_RevPluginItemObject(revEntity);
            revPluginItemObject.setRevPluginId(revPluginItemObject.getRevPluginId());
            revPluginItemObject.setRevPluginName(revPluginItemObject.getRevPluginName());
            revPluginItemObject.setRevPluginDescription(revPluginItemObject.getRevPluginDescription());
            revPluginItemObject.setRevPluginDependencies(revPluginItemObject.getRevPluginDescription());
            revPluginItemObject.setRevPluginDependencies(revPluginItemObject.getRevPluginDependencies());
            revPluginItemObject.setRevPluginPublisher(revPluginItemObject.getRevPluginPublisher());

            scrollViewContainer_LL.addView(this.revPluginListItemView(revPluginItemObject));
        }

        scrollView.addView(scrollViewContainer_LL);

        revInstalledPluginsListingViewContainer_LL.addView(scrollView);

        return revInstalledPluginsListingViewContainer_LL;
    }

    private View getRevPageTitleView() {
        LinearLayout revInstalledPluginsTitleWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        this.revDecView(revInstalledPluginsTitleWrapper_LL, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

        Picasso.get()
                .load(R.drawable.icons8_module_40)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        TextView revInstalledplugins = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(1.4f);
        revInstalledplugins.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("iNsTALLED"));
        revInstalledplugins.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        ((LinearLayout.LayoutParams) revInstalledplugins.getLayoutParams()).gravity = Gravity.BOTTOM;

        TextView revOnlinePlugins = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(1.4f);
        revOnlinePlugins.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(" moRE . . ."));
        revOnlinePlugins.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        ((LinearLayout.LayoutParams) revOnlinePlugins.getLayoutParams()).gravity = Gravity.BOTTOM;

        revInstalledPluginsTitleWrapper_LL.addView(revInstalledplugins);
        revInstalledPluginsTitleWrapper_LL.addView(revOnlinePlugins);

        return revInstalledPluginsTitleWrapper_LL;
    }

    private View revPluginListItemView(RevPluginItemObject revPluginItemObject) {
        LinearLayout revPluginListItemContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revPluginListItemContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);

        this.revDecView(revPluginListItemContainer_LL, mContext.getResources().getColor(R.color.lime_primary));

        LinearLayout revPluginListItemViewTop_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView revPluginName_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(1.4f);
        revPluginName_TV.setText(revPluginItemObject.getRevPluginName());

        revPluginListItemViewTop_LL.addView(revPluginName_TV);
        revPluginListItemContainer_LL.addView(revPluginListItemViewTop_LL);

        /** START REV DESC **/

        LinearLayout revPluginListItemViewMid_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        String revPluginDescription = revPluginItemObject.getRevPluginDescription();

        if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revPluginDescription)) {
            TextView revPluginDesc_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
            revPluginDesc_TV.setText(revPluginDescription);

            revPluginListItemViewMid_LL.addView(revPluginDesc_TV);
        }
        revPluginListItemContainer_LL.addView(revPluginListItemViewMid_LL);

        /** END REV DESC **/

        /** START REV FOOTER**/

        LinearLayout revPluginListItemViewFooter_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revPluginListItemViewFooter_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        TextView revPluginInstallDate_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_ITALICS();
        revPluginInstallDate_TV.setText("iNsTALLED Jan 22, 2019 22:20");

        revPluginListItemViewFooter_LL.addView(revPluginInstallDate_TV);

        revPluginListItemViewFooter_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        revPluginListItemViewFooter_LL.addView(revPluginStatus());

        revPluginListItemContainer_LL.addView(revPluginListItemViewFooter_LL);

        /** END REV FOOTER **/

        revPluginListItemContainer_LL.addView(this.revPublication(revPluginItemObject));

        return revPluginListItemContainer_LL;
    }

    private View revPluginStatus() {
        LinearLayout revPluginStatusWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        TextView revPluginDisable_TV = revCoreInputFormTextView.getRevExtraSmallNormalBoldTextView_NO_PADDING_LINK(.9f);
        revPluginDisable_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("[ sTop ]"));
        ((LinearLayout.LayoutParams) revPluginDisable_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        TextView revPluginDelete_TV = revCoreInputFormTextView.getRevExtraSmallNormalBoldTextView_NO_PADDING_LINK(.9f);
        revPluginDelete_TV.setText("[ DELETE ]");
        revPluginDelete_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revColorAccentRed));
        ((LinearLayout.LayoutParams) revPluginDelete_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        revPluginStatusWrapper_LL.addView(revPluginDisable_TV);
        revPluginStatusWrapper_LL.addView(revPluginDelete_TV);

        return revPluginStatusWrapper_LL;
    }

    private View revPublication(RevPluginItemObject revPluginItemObject) {
        LinearLayout revPublicationWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) revPublicationWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 1);

        TextView revPluginPublisher_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.9f);
        revPluginPublisher_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(revPluginItemObject.getRevPluginPublisher()));
        ((LinearLayout.LayoutParams) revPluginPublisher_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        TextView revPluginInstallsCount_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.7f);
        revPluginInstallsCount_TV.setText("1.7B installs");
        ((LinearLayout.LayoutParams) revPluginInstallsCount_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        revPublicationWrapper_LL.addView(revPluginPublisher_TV);
        revPublicationWrapper_LL.addView(revPluginInstallsCount_TV);

        return revPublicationWrapper_LL;
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
