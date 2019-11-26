package rev.ca.revlibviews.rev_core_layouts;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;

/**
 * Created by rev on 10/17/17.
 */

public class RevCoreLayoutsLinearLayout {

    private Context mContext;

    private LinearLayout linearLayout;

    public RevCoreLayoutsLinearLayout(Context context) {
        this.mContext = context;
    }

    public LinearLayout getRevLinearLayout() {
        linearLayout = new LinearLayout(mContext);
        return linearLayout;
    }

    public LinearLayout getVerticalRevLinearLayout() {
        linearLayout = this.getRevLinearLayout();
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        return linearLayout;
    }

    public LinearLayout getVerticalRevLinearLayout_MATCH_ALL() {
        linearLayout = this.getVerticalRevLinearLayout();
        linearLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_MATCH_ALL_LP());
        return linearLayout;
    }

    public LinearLayout getVerticalRevLinearLayout_WRAP_ALL() {
        linearLayout = this.getVerticalRevLinearLayout();
        linearLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP());
        return linearLayout;
    }

    public LinearLayout get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT() {
        linearLayout = this.getHorizontalRevLinearLayout();
        linearLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP());
        return linearLayout;
    }

    public LinearLayout getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT() {
        linearLayout = this.getVerticalRevLinearLayout();
        linearLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP());
        return linearLayout;
    }

    public LinearLayout getHorizontalRevLinearLayout() {
        linearLayout = this.getRevLinearLayout();
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }

    public LinearLayout getHorizontalRevLinearLayout_WRAPPED_ALL() {
        linearLayout = this.getHorizontalRevLinearLayout();
        linearLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP());
        return linearLayout;
    }

    public LinearLayout getVerticalFormRevLinearLayout() {
        linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke((int) (RevLibGenConstantine.REV_MARGIN_TINY * .2), mContext.getResources().getColor(R.color.actionBarDrawerToggleColor));
        drawable.setCornerRadius(0);
        drawable.setColor(mContext.getResources().getColor(R.color.revWhite));
        linearLayout.setBackgroundDrawable(drawable);

        return linearLayout;
    }

    public LinearLayout getHorizontalFormFooterRevLinearLayout() {
        linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getInputFormFooter_LP());
        return linearLayout;
    }

    public LinearLayout getRevCoreLayouts_CENTER_CONTENT_VIEW_LL() {
        linearLayout = this.getVerticalRevLinearLayout();
        linearLayout.setId(R.id.centerContentView_LL);
        linearLayout.setLayoutParams(RevCoreFrameLayoutLayoutParams.getRev_centerContentView_LL_LP());
        return linearLayout;
    }
}
