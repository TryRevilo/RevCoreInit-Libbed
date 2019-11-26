package rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.IRevFileCrawler;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevPictureFileCrawlerTabs {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private IRevFileCrawler iRevFileCrawler;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    TextView totalImagesCount_TV;

    List<String> selectedFiles = new ArrayList<>();

    int selectedCount = 0;

    int smallMargin = RevLibGenConstantine.REV_MARGIN_SMALL;
    int imgSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
    int imgSizeMedium = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

    int marginTiny = RevLibGenConstantine.REV_MARGIN_TINY;

    public RevPictureFileCrawlerTabs(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View cancelTab() {
        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(rev.ca.revlibpersistence.R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 1, 1, -2, 1);

        TextView dataSource_TV_Tab = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        dataSource_TV_Tab.setGravity(Gravity.CENTER_VERTICAL);
        dataSource_TV_Tab.setPadding(smallMargin, 1, smallMargin * 2, 1);
        dataSource_TV_Tab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.white_OPAQUE));
        dataSource_TV_Tab.setText("CANCEL");

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(dataSource_TV_Tab, layerDrawable);

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(dataSource_TV_Tab);
        ((LinearLayout.LayoutParams) dataSource_TV_Tab.getLayoutParams()).setMargins(0, 0, -smallMargin, 0);

        Drawable dataSource_TV_Tab_DR = mContext.getResources().getDrawable(rev.ca.revlibpersistence.R.drawable.ic_cancel_black_48dp);
        dataSource_TV_Tab_DR.setBounds(0, 0, imgSizeSmall, imgSizeSmall);
        DrawableCompat.setTint(dataSource_TV_Tab_DR, ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        dataSource_TV_Tab.setCompoundDrawablePadding(0);
        dataSource_TV_Tab.setCompoundDrawables(dataSource_TV_Tab_DR, null, null, null);

        dataSource_TV_Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
            }
        });

        return dataSource_TV_Tab;
    }

    public View okNextTab() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_primary_extra_light)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius((float) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4));
        gd.setStroke(1, ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        Drawable[] layers = {gd};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 1, 1, -2, 1);

        TextView dataSource_TV_Tab = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        dataSource_TV_Tab.setGravity(Gravity.CENTER_VERTICAL);
        dataSource_TV_Tab.setPadding(smallMargin, 1, smallMargin, 1);
        dataSource_TV_Tab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.white_OPAQUE));
        dataSource_TV_Tab.setText("NEXT");

        dataSource_TV_Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRevFileCrawler.revCallBack(selectedFiles);
                RevPop.getPw().dismiss();
            }
        });

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(dataSource_TV_Tab, layerDrawable);

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(dataSource_TV_Tab);

        imgSizeSmall = (int) (imgSizeSmall * 1.2);

        Drawable dataSource_TV_Tab_DR = mContext.getResources().getDrawable(rev.ca.revlibpersistence.R.drawable.ic_trending_flat_black_48dp);
        dataSource_TV_Tab_DR.setBounds(0, 0, imgSizeSmall, imgSizeSmall);
        DrawableCompat.setTint(dataSource_TV_Tab_DR, ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        dataSource_TV_Tab.setCompoundDrawablePadding(0);
        dataSource_TV_Tab.setCompoundDrawables(null, null, dataSource_TV_Tab_DR, null);

        return dataSource_TV_Tab;
    }

    public View allImagesTab() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_primary_extra_light)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius((float) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4));
        gd.setStroke((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        TextView dataSource_TV_Tab = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        dataSource_TV_Tab.setGravity(Gravity.CENTER_HORIZONTAL);
        dataSource_TV_Tab.setPadding(smallMargin, smallMargin, smallMargin, smallMargin);
        dataSource_TV_Tab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.white_OPAQUE));
        dataSource_TV_Tab.setText("All");

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(dataSource_TV_Tab, gd);

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(dataSource_TV_Tab);

        imgSizeSmall = (int) (imgSizeSmall * 1.2);

        Drawable dataSource_TV_Tab_DR = mContext.getResources().getDrawable(rev.ca.revlibpersistence.R.drawable.ic_all_inclusive_black_48dp);
        dataSource_TV_Tab_DR.setBounds(0, 0, imgSizeSmall, imgSizeSmall);
        DrawableCompat.setTint(dataSource_TV_Tab_DR, ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        dataSource_TV_Tab.setCompoundDrawablePadding(0);
        dataSource_TV_Tab.setCompoundDrawables(null, null, null, dataSource_TV_Tab_DR);

        return dataSource_TV_Tab;
    }

    public View galleryImagesTab() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_primary_extra_light)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius((float) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4));
        gd.setStroke((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        TextView dataSource_TV_Tab = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        dataSource_TV_Tab.setGravity(Gravity.CENTER_HORIZONTAL);
        dataSource_TV_Tab.setPadding(smallMargin, smallMargin, smallMargin, smallMargin);
        dataSource_TV_Tab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.white_OPAQUE));
        dataSource_TV_Tab.setText("Gallery");

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(dataSource_TV_Tab, gd);

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(dataSource_TV_Tab);

        imgSizeSmall = (int) (imgSizeSmall * 1.2);

        Drawable dataSource_TV_Tab_DR = mContext.getResources().getDrawable(rev.ca.revlibpersistence.R.drawable.ic_collections_black_48dp);
        dataSource_TV_Tab_DR.setBounds(0, 0, imgSizeSmall, imgSizeSmall);
        DrawableCompat.setTint(dataSource_TV_Tab_DR, ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        dataSource_TV_Tab.setCompoundDrawablePadding(0);
        dataSource_TV_Tab.setCompoundDrawables(null, null, null, dataSource_TV_Tab_DR);

        return dataSource_TV_Tab;
    }

    public View cameramagesTab() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_primary_extra_light)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius((float) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4));
        gd.setStroke((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        TextView dataSource_TV_Tab = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        dataSource_TV_Tab.setGravity(Gravity.CENTER_HORIZONTAL);
        dataSource_TV_Tab.setPadding(smallMargin, smallMargin, smallMargin, smallMargin);
        dataSource_TV_Tab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.white_OPAQUE));
        dataSource_TV_Tab.setText("Camera");

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(dataSource_TV_Tab, gd);

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(dataSource_TV_Tab);

        Drawable dataSource_TV_Tab_DR = mContext.getResources().getDrawable(rev.ca.revlibpersistence.R.drawable.ic_photo_camera_black_48dp);
        dataSource_TV_Tab_DR.setBounds(0, 0, imgSizeSmall, imgSizeSmall);
        DrawableCompat.setTint(dataSource_TV_Tab_DR, ContextCompat.getColor(mContext, rev.ca.revlibpersistence.R.color.deep_purple_dark));

        dataSource_TV_Tab.setCompoundDrawablePadding(0);
        dataSource_TV_Tab.setCompoundDrawables(null, null, null, dataSource_TV_Tab_DR);

        return dataSource_TV_Tab;
    }
}
