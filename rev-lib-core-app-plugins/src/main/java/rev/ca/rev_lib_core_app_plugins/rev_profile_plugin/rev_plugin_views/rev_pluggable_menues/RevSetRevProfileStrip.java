package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.widget.LinearLayout;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs.RevLocalEntityInfoWrapperModel;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.RevInfoDetailsWidget;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.RevProfileFloatingTabViewWidget;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/28/17.
 */

public class RevSetRevProfileStrip extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    public RevSetRevProfileStrip(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public ArrayMap<View, View> createRevMerryllStripMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();

            LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            linearLayout.setPadding(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_LARGE);

            RevVarArgsData passRVD = new RevVarArgsData(mContext);
            passRVD.setRevEntity(new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid()).getRevEntity());

            linearLayout.addView(new RevInfoDetailsWidget(passRVD).getRevDetailsWidget());

            arrayMap.put(new RevProfileFloatingTabViewWidget(revVarArgsData).createRevPluggableCenterMainContentOverlayFloatingView(), linearLayout);
        }
        return arrayMap;
    }
}
