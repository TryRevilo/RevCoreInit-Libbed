package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.WrapLL;

/**
 * Created by rev on 10/21/17.
 */

public class RevPluggableFooterMenueTogglerLoader {

    public static View revLoadRevFooterMenuItem(Context mContext) {
        LinearLayout pluggableRevtViewLL = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout();

        LinearLayout.LayoutParams contentView_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pluggableRevtViewLL.setLayoutParams(contentView_LP);

        for (Object revPluggableViewObject : RevPluggableServices.getRevPluggableViewsObjects(new RevVarArgsData(mContext.getResources().getString(R.string.createRevPluggableFooterMenueTogglerTab)))) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            if (revPluggableView instanceof IRevPluggableViews) {
                new WrapLL(mContext).populateTabLinks(pluggableRevtViewLL, revPluggableView.createRevPluggableFooterMenueTogglerTab());
            }
        }

        if (pluggableRevtViewLL != null) {
            return pluggableRevtViewLL;
        }

        return null;
    }
}
