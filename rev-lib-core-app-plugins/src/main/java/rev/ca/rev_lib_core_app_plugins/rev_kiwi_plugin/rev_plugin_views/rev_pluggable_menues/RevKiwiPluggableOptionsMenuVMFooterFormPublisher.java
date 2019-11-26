package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevKiwiPluggableOptionsMenuVMFooterFormPublisher implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevKiwiPluggableOptionsMenuVMFooterFormPublisher(Context mContext) {
        this.mContext = mContext;

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        int margin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

        LinearLayout kiwiOptionsWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int padding = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .2);
        kiwiOptionsWrapper_LL.setPadding(0, padding, RevLibGenConstantine.REV_MARGIN_SMALL, padding);

        LinearLayout.LayoutParams kiwiOptionsWrapper_LL_LP = (LinearLayout.LayoutParams) kiwiOptionsWrapper_LL.getLayoutParams();
        kiwiOptionsWrapper_LL_LP.setMargins(margin, 0, 0, 0);
        kiwiOptionsWrapper_LL_LP.gravity = Gravity.CENTER_VERTICAL;
        kiwiOptionsWrapper_LL.setLayoutParams(kiwiOptionsWrapper_LL_LP);

        GradientDrawable gd = new GradientDrawable();
        // gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(25);
        gd.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(kiwiOptionsWrapper_LL, gd);

        LinearLayout.LayoutParams searchTab_IV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchTab_IV_LP.setMargins(margin, 0, 0, 0);
        searchTab_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        ImageView documentTab_IV = new ImageView(mContext);
        documentTab_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revPurple));
        documentTab_IV.setLayoutParams(searchTab_IV_LP);

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_direct_select_menu_item");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(kiwiOptionsWrapper_LL);
        revPluggableOptionsMenuVM.setMenuItemsViewType("linear_horizontal");

        return revPluggableOptionsMenuVM;
    }
}
