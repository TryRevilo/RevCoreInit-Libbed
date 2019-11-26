package rev.ca.rev_lib_core_app_plugins.rev_contact_us_plugin.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

/**
 * Created by rev on 10/24/17.
 */

public class RevContactUsPluggableFooterTabLoader extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreColoredTabs revCoreColoredTabs;

    public RevContactUsPluggableFooterTabLoader(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();
        revCoreColoredTabs = new RevCoreColoredTabs(mContext);
    }

    @Override
    public ArrayList<View> createRevFooterTab() {
        ArrayList<View> views = new ArrayList<>();

        TextView infoTab = revCoreColoredTabs.getRevColoredTab();
        infoTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        infoTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_100_dark));
        infoTab.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecStringWhite(mContext.getResources().getString(R.string.contact_us)));

        views.add(infoTab);

        return views;
    }
}
