package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevPageContentHeaderTabsContainer {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevPageContentHeaderTabsContainer(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View revProfileTypesTabHeaderView() {
        LinearLayout revTabHeaderView_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revTabHeaderView_LL.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
        ((LinearLayout.LayoutParams) revTabHeaderView_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -2, -2, -2, 1);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revTabHeaderView_LL, layerDrawable);

        revTabHeaderView_LL.addView(this.findPeopleWidgetView());
        revTabHeaderView_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());

        revTabHeaderView_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(this.createNewSpaceTab()));

        return revTabHeaderView_LL;
    }

    private View findPeopleWidgetView() {
        LinearLayout findPeopleWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) findPeopleWrapper_LL.getLayoutParams()).weight = 100.0f;

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * .7);

        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_find_in_page_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        EditText findPeople_ET = new RevCoreInputFormEditText(mContext).getRevEditText_L_R_B_PartialBorders();
        findPeople_ET.setMaxLines(1);
        ((LinearLayout.LayoutParams) findPeople_ET.getLayoutParams()).width = LinearLayout.LayoutParams.MATCH_PARENT;
        ((LinearLayout.LayoutParams) findPeople_ET.getLayoutParams()).weight = 1.0f;
        findPeople_ET.setHint("  sEARcH . . .");
        findPeople_ET.setBackgroundColor(Color.TRANSPARENT);
        findPeople_ET.setCompoundDrawables(generalPointingAverageImg, null, null, null);

        ImageView filterSearch_IV = new ImageView(mContext);
        filterSearch_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_500_dark));
        filterSearch_IV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(filterSearch_IV);

        Picasso.get()
                .load(R.drawable.icons8_options_40)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(filterSearch_IV);

        TextView findTab_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.7f);
        findTab_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("FiND"));
        ((LinearLayout.LayoutParams) findTab_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) findTab_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0);
        findTab_TV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_subdirectory_arrow_right_black_24dp);
        listedVehsBttnImg.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.revGreen));
        findTab_TV.setCompoundDrawables(null, null, listedVehsBttnImg, null);
        findTab_TV.setGravity(Gravity.CENTER_VERTICAL);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        int borderWidth = 2;

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderWidth, -borderWidth, -borderWidth, borderWidth);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(findTab_TV, layerDrawable);

        findPeopleWrapper_LL.addView(findPeople_ET);
        findPeopleWrapper_LL.addView(filterSearch_IV);
        findPeopleWrapper_LL.addView(findTab_TV);

        return findPeopleWrapper_LL;
    }

    private View createNewSpaceTab() {
        TextView contentHeader_TV = null;

        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("create_new_bag_menu_item")) {
            RevVarArgsData passRVD = new RevVarArgsData();
            passRVD.setRevViewType("create_new_bag_menu_item");
            passRVD.setRevEntity(passRVD.getRevEntity());

            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(passRVD);
            contentHeader_TV = (TextView) iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();

            int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

            Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.baseline_playlist_add_black_48dp);
            listedVehsBttnImg.setBounds(0, 0, imgSize, imgSize);
            DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.revGreen));
            contentHeader_TV.setCompoundDrawables(listedVehsBttnImg, null, null, null);
            contentHeader_TV.setGravity(Gravity.CENTER_VERTICAL);
        }

        contentHeader_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(String.valueOf(contentHeader_TV.getText())));
        ((LinearLayout.LayoutParams) contentHeader_TV.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        return contentHeader_TV;
    }
}
