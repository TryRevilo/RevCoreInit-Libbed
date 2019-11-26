package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.ArrayList;

import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersEntityInfoWrapperModel;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs.RevLocalEntityInfoWrapperModel;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluggableViewProperties;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/23/17.
 */

public class CreatePluggableTopBarMenuViewItemsHome extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    public CreatePluggableTopBarMenuViewItemsHome(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public IRevPluggableViewProperties createIRevPluggableViewProperties() {
        IRevPluggableViewProperties iRevPluggableViewProperties = new IRevPluggableViewProperties(mContext);
        iRevPluggableViewProperties.setViewPlacement(3);
        return iRevPluggableViewProperties;
    }

    @Override
    public ArrayList<View> createPluggableTopBarMenuViewItem() {
        ArrayList<View> views = new ArrayList<>();
        views.add(this.revSiteHomeTab());

        return views;
    }

    private View revSiteHomeTab() {
        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(R.color.teal_primary));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -2, -2, -2, 1);

        Button tab = new Button(mContext);
        tab.setBackgroundColor(Color.TRANSPARENT);
        tab.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(tab, layerDrawable);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(tab);

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                RevVarArgsData passRVD = new RevVarArgsData(mContext);
                passRVD.setRevPersEntityInfoWrapperModel(revPersEntityInfoWrapperModel);
                passRVD.setRevEntity(revPersEntityInfoWrapperModel.getRevEntity());

                REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(passRVD).getUserMainCenterCctViewLL());
            }
        });

        Drawable profileBttnImg = mContext.getResources().getDrawable(R.drawable.ic_home_black_48dp);
        profileBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(profileBttnImg, ContextCompat.getColor(mContext, R.color.revWhite));
        tab.setCompoundDrawables(profileBttnImg, null, null, null);

        return tab;
    }
}
