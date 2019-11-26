package rev.ca.rev_lib_core_views;

import android.content.Context;

/**
 * Created by rev on 11/4/17.
 */

public class IRevPluggableViewProperties {

    private Context mContext;

    private String viewName;
    private int viewPlacement = -10000;
    private boolean isRipped = true;

    public IRevPluggableViewProperties(Context mContext) {
        this.mContext = mContext;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public int getViewPlacement() {
        return viewPlacement;
    }

    public void setViewPlacement(int viewPlacement) {
        this.viewPlacement = viewPlacement;
    }

    public boolean isRipped() {
        return isRipped;
    }

    public void setRipped(boolean ripped) {
        isRipped = ripped;
    }
}
