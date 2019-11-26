package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.TextView;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;

/**
 * Created by rev on 12/13/17.
 */

public class RevObjectListingOptionsTogglerMenu extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevObjectListingOptionsTogglerMenu(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public View createRevEntityListingOptionsTogglerMenuView() {
        return this.revObjectListingOptionsTogglerMenuView();
    }

    private View revObjectListingOptionsTogglerMenuView() {
        TextView revObjectListingOptionsTogglerMenuView_TV = revCoreInputFormTextView.getRevSmallNormalTextView();
        revObjectListingOptionsTogglerMenuView_TV.setText("all BAGS");
        revObjectListingOptionsTogglerMenuView_TV.setCompoundDrawablePadding(RevLibGenConstantine.REV_MARGIN_TINY);

        Drawable revObjectListingOptionsTogglerMenuView_TV_DR = mContext.getResources().getDrawable(R.drawable.ic_people_outline_black_48dp);

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
        revObjectListingOptionsTogglerMenuView_TV_DR.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(revObjectListingOptionsTogglerMenuView_TV_DR, ContextCompat.getColor(mContext, R.color.greyDark));
        revObjectListingOptionsTogglerMenuView_TV.setCompoundDrawables(revObjectListingOptionsTogglerMenuView_TV_DR, null, null, null);

        return revObjectListingOptionsTogglerMenuView_TV;
    }
}
