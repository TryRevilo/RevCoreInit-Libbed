package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevProfileContentFilterPluggableFloatingOptionsMenuWrapper implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    private RevCoreInputFormTextView revCoreInputFormTextView;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevProfileContentFilterPluggableFloatingOptionsMenuWrapper(Context mContext) {
        this.mContext = mContext;
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_profile_floating_options_menu");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(this.revGetListingSortTab());
        revPluggableOptionsMenuVM.setMenuItemsViewType("drop_down");

        return revPluggableOptionsMenuVM;
    }

    private View revGetListingSortTab() {
        LinearLayout noticiasHeaderWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) noticiasHeaderWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) noticiasHeaderWrapper_LL.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.baseline_keyboard_arrow_down_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        TextView footerTab_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(1.2f);
        footerTab_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("moRE"));
        footerTab_TV.setGravity(Gravity.CENTER_VERTICAL);
        ((LinearLayout.LayoutParams) footerTab_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        noticiasHeaderWrapper_LL.addView(userIcon_IV);
        noticiasHeaderWrapper_LL.addView(footerTab_TV);

        return noticiasHeaderWrapper_LL;
    }
}
