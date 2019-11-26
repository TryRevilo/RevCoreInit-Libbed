package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_areas;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevPluggableOptionsMenuVMTest implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    public RevPluggableOptionsMenuVMTest(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        LinearLayout.LayoutParams linearLayout_LP = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linearLayout_LP.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(R.drawable.ic_list_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        int imageSizeMedium = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageSizeMedium, imageSizeMedium);
        layoutParams.gravity = (Gravity.BOTTOM);
        imageView.setLayoutParams(layoutParams);

        linearLayout.addView(imageView);

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("test_options_menu");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(linearLayout);
        revPluggableOptionsMenuVM.setMenuItemsViewType("drop_down");

        return revPluggableOptionsMenuVM;
    }
}
