package rev.ca.rev_lib_core_views.rev_core_views.rev_page;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevObjectListingOptionsTogglerMenu;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 12/13/17.
 */

public class RevObjectFullProfileView {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevObjectFullProfileView(Context context) {
        this.mContext = context;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public View getRevObjectFullProfileView(View revObjectFullProfileView) {
        LinearLayout revObjectFullProfileView_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revObjectFullProfileView_LL.addView(RevObjectListingOptionsTogglerMenu.getRevObjectListingOptionsTogglerMenu());
        revObjectFullProfileView_LL.addView(revObjectFullProfileView);

        return revObjectFullProfileView_LL;
    }
}
