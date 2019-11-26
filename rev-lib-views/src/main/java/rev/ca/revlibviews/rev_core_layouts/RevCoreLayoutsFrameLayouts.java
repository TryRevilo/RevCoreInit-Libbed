package rev.ca.revlibviews.rev_core_layouts;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by rev on 10/24/17.
 */

public class RevCoreLayoutsFrameLayouts {

    private Context mContext;
    private FrameLayout frameLayout;

    public RevCoreLayoutsFrameLayouts(Context mContext) {
        this.mContext = mContext;
    }

    public FrameLayout getRevCoreLayoutsFrameLayout() {
        frameLayout = new FrameLayout(mContext);
        return frameLayout;
    }

    public FrameLayout getRevCoreLayoutsFrameLayout_MATCH_ALL() {
        frameLayout = this.getRevCoreLayoutsFrameLayout();
        frameLayout.setLayoutParams(RevCoreFrameLayoutLayoutParams.getRev_MATCH_ALL_LP());
        return frameLayout;
    }

    public FrameLayout getRevCoreLayoutsFrameLayout_WRAP_ALL_LL_LP() {
        frameLayout = this.getRevCoreLayoutsFrameLayout();
        frameLayout.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP());
        return frameLayout;
    }

    public FrameLayout getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP() {
        frameLayout = this.getRevCoreLayoutsFrameLayout();
        frameLayout.setLayoutParams(RevCoreFrameLayoutLayoutParams.getRev_MATCH_PARENT_WRAP_CONTENT_FL_LP());
        return frameLayout;
    }
}
