package rev.ca.rev_lib_core_views.rev_core_views.rev_page;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevFooterMenuToggler;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableFooterTabLoader;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

/**
 * Created by rev on 10/8/17.
 */

public class RevFooterView {

    private LinearLayout revFooterViewLL;

    public RevFooterView(Context mContext) {
        revFooterViewLL = new LinearLayout(mContext);
        revFooterViewLL.setOrientation(LinearLayout.VERTICAL);

        FrameLayout.LayoutParams revFooterViewLL_LP = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        revFooterViewLL_LP.gravity = Gravity.BOTTOM;
        revFooterViewLL_LP.setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        revFooterViewLL.setLayoutParams(revFooterViewLL_LP);

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        revFooterViewLL.addView(space);

        // revFooterViewLL.addView(new RevFooterMenuToggler(mContext).getRevMenueTogglerLL());
        revFooterViewLL.addView(RevPluggableFooterTabLoader.revLoadRevFooterTab(mContext));
    }

    public LinearLayout getRevFooterViewLL() {
        return revFooterViewLL;
    }
}
