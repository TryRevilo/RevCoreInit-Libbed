package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluggableViewProperties;
import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations.RevSlideDown;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevConstantineViews;
import rev.ca.revlibviews.rev_core_layouts.RevCoreFrameLayoutLayoutParams;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/18/17.
 */

public class RevPluggableTopBarMenuItemViewLoader {

    public static View revLoadRevTopBarMenuItem(Context mContext) {
        RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        int tabSize = (int) (RevLibGenConstantine.REV_MARGIN_LARGE * .9);
        int tabToggleView = RevLibGenConstantine.REV_MARGIN_LARGE;

        final LinearLayout pluggableRevtView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        pluggableRevtView_LL.setBackgroundColor(mContext.getResources().getColor(R.color.teal_primary));
        ((LayoutParams) pluggableRevtView_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        int borderWidth = 1;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(borderWidth, mContext.getResources().getColor(R.color.teal_dark));
        gradientDrawable.setGradientType(RECTANGLE);
        gradientDrawable.setColor(ContextCompat.getColor(mContext, R.color.teal_primary));

        Drawable[] layers = {gradientDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderWidth, -borderWidth, -borderWidth, borderWidth);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(pluggableRevtView_LL, layerDrawable);

        LinearLayout.LayoutParams tab_LP = new LinearLayout.LayoutParams(tabSize, tabSize);
        tab_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .9), 0, 1, 0);
        tab_LP.gravity = Gravity.CENTER_VERTICAL;

        for (Object revPluggableViewObject : RevPluggableServices.getRevPluggableViewsObjects(new RevVarArgsData(mContext.getResources().getString(R.string.createPluggableTopBarMenuViewItem)))) {
            AbstractIRevPluggableViews revPluggableView = (AbstractIRevPluggableViews) revPluggableViewObject;

            if (revPluggableView instanceof IRevPluggableViews) {
                ArrayList<View> revViews = revPluggableView.createPluggableTopBarMenuViewItem();
                ArrayMap<View, View> revAltViews = revPluggableView.createPluggableALtTopBarMenuViewItem();

                IRevPluggableViewProperties iRevPluggableViewProperties = revPluggableView.createIRevPluggableViewProperties();

                int placementPos = iRevPluggableViewProperties.getViewPlacement();

                for (View revView : revViews) {
                    if (revView != null) {
                        if (iRevPluggableViewProperties.isRipped())
                            revView.setLayoutParams(tab_LP);

                        pluggableRevtView_LL.addView(revView, REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_GET_VIEW_PLACEMENT(pluggableRevtView_LL, placementPos));
                    }
                }

                for (int i = 0; i < revAltViews.size(); i++) {
                    if (revAltViews.keyAt(i) != null) {
                        View key = revAltViews.keyAt(i);

                        final View value = revAltViews.valueAt(i);
                        value.setId(i);

                        FrameLayout.LayoutParams centerContentView_LP = RevCoreFrameLayoutLayoutParams.getRev_MATCH_ALL_LP();
                        centerContentView_LP.setMargins(0, (int) (RevLibGenConstantine.REV_TOPBAR_HEIGHT * .65), 0, tabToggleView);
                        value.setLayoutParams(centerContentView_LP);

                        key.setLayoutParams(tab_LP);
                        pluggableRevtView_LL.addView(key, REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_GET_VIEW_PLACEMENT(pluggableRevtView_LL, placementPos));

                        key.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RevSlideDown.initSlide(value);
                            }
                        });

                        if (revPluggableView.createIRevPluggableViewProperties() != null) {
                            if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(iRevPluggableViewProperties.getViewName())) {
                                RevConstantineViews.REV_LOADED_VIEWS.put(iRevPluggableViewProperties.getViewName(), key);
                            }
                        }
                    }
                }
            }
        }

        if (pluggableRevtView_LL != null) {
            return pluggableRevtView_LL;
        }

        return null;
    }
}
