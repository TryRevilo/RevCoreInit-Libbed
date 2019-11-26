package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_areas;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;

/**
 * Created by rev on 1/19/18.
 */

public class RevUserPluggableOptionsMenuContainer implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    public RevUserPluggableOptionsMenuContainer(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView moreOptions_IV = new ImageView(mContext);
        moreOptions_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
        moreOptions_IV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(moreOptions_IV);

        Picasso.get()
                .load(R.drawable.ic_toc_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(moreOptions_IV);

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_user_options_menu");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(moreOptions_IV);
        revPluggableOptionsMenuVM.setMenuItemsViewType("drop_down");

        return revPluggableOptionsMenuVM;
    }
}
