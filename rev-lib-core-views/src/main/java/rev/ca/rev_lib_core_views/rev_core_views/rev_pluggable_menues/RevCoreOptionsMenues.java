package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevDropDownPopupMenue;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/20/18.
 */

public class RevCoreOptionsMenues {

    private Context mContext;

    private RevDimens revDimens;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevCoreOptionsMenues(Context mContext) {
        this.mContext = mContext;

        revDimens = new RevDimens(mContext);

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public void initRevCoreGenPublisherOptionsMenu() {
        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(R.drawable.ic_publish_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(revDimens.getRevImageSizeMedium(), revDimens.getRevImageSizeMedium());
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Publish");

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.BOTTOM);
        textView_LP.setMargins((int) (-revDimens.getRevMarginExtraSmall() * 1.4), 0, 0, (int) (revDimens.getRevMarginExtraSmall() * .5));
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        final RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_core_gen_publisher_options_menu");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(linearLayout);

        RevConstantinePluggableViewsServices.REV_PLUGGABLE_OPTIONS_MENU_VM_MAP.put(
                revPluggableOptionsMenuVM.getRevPluggableOptionsMenueName(), revPluggableOptionsMenuVM);

        final LinearLayout revMenusContainer = new RevCoreLayoutsLinearLayout(RevLibGenConstantine.REV_CONTEXT).getVerticalRevLinearLayout_WRAP_ALL();
        int revMargin = RevLibGenConstantine.REV_MARGIN_TINY;
        revMenusContainer.setPadding((int) (revMargin * 2.7), 0, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        GradientDrawable gradientDrawable = REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_GET_ALL_BORDERS_CURVED();
        if (Build.VERSION.SDK_INT >= 16)
            revMenusContainer.setBackground(gradientDrawable);
        else revMenusContainer.setBackgroundDrawable(gradientDrawable);

        final Iterator iterator = RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            List<RevPluggableMenuItemVM> revPluggableMenuItemVMList /**= RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.get(key) **/ = null;

            for (RevPluggableMenuItemVM menuItemVM : revPluggableMenuItemVMList) {
                for (String menuItemName : menuItemVM.getRevPluggableMenuName()) {
                    if (revPluggableOptionsMenuVM.getRevPluggableOptionsMenueName().equals(menuItemName)) {
                        menuItemVM.setRevEntityGUID(revPluggableOptionsMenuVM.getRevEntityGUID());
                        View menuItem = REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(menuItemVM.getRevPluggableMenuView());

                        LinearLayout.LayoutParams menuIayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        menuIayoutParams.setMargins(0, (int) (revMargin * 2.5), RevLibGenConstantine.REV_MARGIN_MEDIUM, 0);
                        menuItem.setLayoutParams(menuIayoutParams);
                        revMenusContainer.addView(menuItem);
                    }
                }
            }

            revPluggableOptionsMenuVM.getRevPluggableOptionsMenueView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new RevDropDownPopupMenue().showStatusPopup((Activity) RevLibGenConstantine.REV_CONTEXT, revPluggableOptionsMenuVM.getRevPluggableOptionsMenueView(), revMenusContainer);
                }
            });
        }
    }
}
