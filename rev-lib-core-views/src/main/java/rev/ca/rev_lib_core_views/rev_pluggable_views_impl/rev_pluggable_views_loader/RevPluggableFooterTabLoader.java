package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/21/17.
 */

public class RevPluggableFooterTabLoader {

    public static View revLoadRevFooterTab(Context mContext) {
        LinearLayout pluggableRevtViewLL = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout();

        for (Object revPluggableViewObject : RevPluggableServices.getRevPluggableViewsObjects(new RevVarArgsData(mContext.getResources().getString(R.string.createRevFooterTab)))) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            if (revPluggableView instanceof IRevPluggableViews) {
                ArrayList<View> revViews = revPluggableView.createRevFooterTab();

                for (View revView : revViews) {
                    if (revView != null) {
                        pluggableRevtViewLL.addView(revView);
                    }
                }
            }
        }

        if (pluggableRevtViewLL != null) {
            return pluggableRevtViewLL;
        }

        return null;
    }
}
