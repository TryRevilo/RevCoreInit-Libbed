package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_plugin_views.rev_pluggable_menus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_plugin_views.rev_listings.RevInstalledPluginsListingView;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluggableViewProperties;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

/**
 * Created by rev on 10/23/17.
 */

public class CreatePluggableTopBarMenuViewItems_PLUGINS extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    public CreatePluggableTopBarMenuViewItems_PLUGINS(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public IRevPluggableViewProperties createIRevPluggableViewProperties() {
        IRevPluggableViewProperties iRevPluggableViewProperties = new IRevPluggableViewProperties(mContext);
        iRevPluggableViewProperties.setViewPlacement(4);
        return iRevPluggableViewProperties;
    }

    @Override
    public ArrayList<View> createPluggableTopBarMenuViewItem() {
        ArrayList<View> views = new ArrayList<>();
        views.add(this.revInstalledPluginsTab());

        return views;
    }

    private View revInstalledPluginsTab() {
        Button tab = new Button(mContext);
        tab.setBackgroundColor(Color.TRANSPARENT);

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Drawable profileBttnImg = mContext.getResources().getDrawable(R.drawable.icons8_module_40);
        profileBttnImg.setBounds(0, 0, imageSize, imageSize);
        tab.setCompoundDrawables(profileBttnImg, null, null, null);

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData revPassRVD = REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData();
                REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(revPassRVD).revResetPageOwnerProfileContent(new RevInstalledPluginsListingView(revPassRVD).getRevInstalledPluginsListingView()));
            }
        });

        return tab;
    }
}
