package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.ArrayList;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluggableViewProperties;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

/**
 * Created by rev on 10/23/17.
 */

public class CreatePluggableTopBarMenuViewItems extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    public CreatePluggableTopBarMenuViewItems(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public IRevPluggableViewProperties createIRevPluggableViewProperties() {
        IRevPluggableViewProperties iRevPluggableViewProperties = new IRevPluggableViewProperties(mContext);
        iRevPluggableViewProperties.setViewPlacement(-10000000);
        return iRevPluggableViewProperties;
    }

    @Override
    public ArrayList<View> createPluggableTopBarMenuViewItem() {
        ArrayList<View> views = new ArrayList<>();
        views.add(this.logOutTab());

        return views;
    }

    private View logOutTab() {
        Button tab = new Button(mContext);
        tab.setBackgroundColor(Color.TRANSPARENT);

        Drawable profileBttnImg = mContext.getResources().getDrawable(R.drawable.ic_exit_to_app_black_48dp);
        profileBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(profileBttnImg, ContextCompat.getColor(mContext, R.color.teal_500_dark));
        tab.setCompoundDrawables(profileBttnImg, null, null, null);

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REV_SESSION_SETTINGS.revLogOut();
            }
        });

        return tab;
    }
}
