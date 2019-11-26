package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations.RevSlideUpDown;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableFooterMenueTogglerLoader;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 10/21/17.
 */

public class RevFooterMenuToggler {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    LinearLayout slideContainerLL;
    LinearLayout revSlideToggleContainerLL;
    private Button footerViewMorePullBttn;

    public RevFooterMenuToggler(Context mContext) {
        this.mContext = mContext;
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public LinearLayout getRevMenueTogglerLL() {
        slideContainerLL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout();

        LinearLayout.LayoutParams slideContainerLL_LP = RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP();
        slideContainerLL_LP.gravity = Gravity.BOTTOM;
        slideContainerLL.setLayoutParams(slideContainerLL_LP);

        slideContainerLL.addView(this.getFooterViewMorePull());

        return slideContainerLL;
    }

    private View hiddenContainer() {
        revSlideToggleContainerLL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout();
        revSlideToggleContainerLL.setVisibility(View.INVISIBLE);

        LinearLayout.LayoutParams slideLL_LP = RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP();
        revSlideToggleContainerLL.setLayoutParams(slideLL_LP);

        revSlideToggleContainerLL.addView(this.getFooterMorePullHide());
        revSlideToggleContainerLL.addView(RevPluggableFooterMenueTogglerLoader.revLoadRevFooterMenuItem(mContext));

        return revSlideToggleContainerLL;
    }

    public View getFooterViewMorePull() {
        int imageSize = mContext.getResources().getDimensionPixelSize(R.dimen.rev_core_views__image_size_large);

        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_expand_less_black_48dp);
        listedVehsBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.lime_dark));

        footerViewMorePullBttn = new Button(mContext);
        footerViewMorePullBttn.setBackgroundColor(Color.TRANSPARENT);
        footerViewMorePullBttn.setCompoundDrawables(listedVehsBttnImg, null, null, null);

        LinearLayout.LayoutParams footerViewMorePullBttn_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        footerViewMorePullBttn_LP.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;

        footerViewMorePullBttn.setLayoutParams(footerViewMorePullBttn_LP);

        footerViewMorePullBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideContainerLL.addView(hiddenContainer());
                onSlideViewButtonClick(revSlideToggleContainerLL, footerViewMorePullBttn);
            }
        });

        return footerViewMorePullBttn;
    }

    public View getFooterMorePullHide() {
        int imageSize = mContext.getResources().getDimensionPixelSize(R.dimen.rev_core_views__image_size_large);

        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_expand_more_black_48dp);
        listedVehsBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.lime_dark));

        final Button footerViewLessPullBttn = new Button(mContext);
        footerViewLessPullBttn.setBackgroundColor(Color.TRANSPARENT);
        footerViewLessPullBttn.setCompoundDrawables(listedVehsBttnImg, null, null, null);

        LinearLayout.LayoutParams footerViewMorePullBttn_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        footerViewMorePullBttn_LP.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        footerViewLessPullBttn.setLayoutParams(footerViewMorePullBttn_LP);

        footerViewLessPullBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideContainerLL.removeViewAt(1);
                onSlideViewButtonClick(footerViewMorePullBttn, null);
            }
        });

        return footerViewLessPullBttn;
    }

    public void onSlideViewButtonClick(View view, View hideView) {
        if (hideView != null)
            RevSlideUpDown.RevTopDownSlide.slideDown(hideView);

        RevSlideUpDown.RevTopDownSlide.slideUp(view);
    }
}
