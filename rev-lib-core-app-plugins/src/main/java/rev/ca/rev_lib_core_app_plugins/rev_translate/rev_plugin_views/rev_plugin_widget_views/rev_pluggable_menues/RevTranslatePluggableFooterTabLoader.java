package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_object.RevTranslateObjectView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/21/17.
 */

public class RevTranslatePluggableFooterTabLoader extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreColoredTabs revCoreColoredTabs;

    public RevTranslatePluggableFooterTabLoader(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revCoreColoredTabs = new RevCoreColoredTabs(mContext);
    }

    @Override
    public ArrayList<View> createRevFooterTab() {
        ArrayList<View> views = new ArrayList<>();

        LinearLayout wrapper = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        wrapper.setClickable(true);

        TextView infoTab = revCoreColoredTabs.getRevColoredTab();
        infoTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        infoTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));
        infoTab.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecStringWhite(mContext.getResources().getString(R.string.translate)));

        wrapper.addView(infoTab);

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        wrapper.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());

        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(RevLibGenConstantine.REV_MARGIN_LARGE, 0, (int) (RevLibGenConstantine.REV_MARGIN_LARGE * .93), 0);

        int borderWidth = 1;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(borderWidth, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        gradientDrawable.setGradientType(RECTANGLE);

        Drawable[] layers = {gradientDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderWidth, borderWidth, -borderWidth, -borderWidth);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, layerDrawable);

        TextView textView = revCoreColoredTabs.getRevColoredTab();
        textView.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecStringWhite("Camp Ann"));

        linearLayout.addView(textView);
        wrapper.addView(linearLayout);

        wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = REV_SESSION_SETTINGS.getRevCurrentPageRevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(postRVD.getRevEntity());

                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevTranslateObjectView(postRVD).getRevTranslateObjectView());
            }
        });

        views.add(wrapper);

        return views;
    }
}
