package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views;

import android.view.View;

public class RevPluggableInlineView {

    private View inlineView;
    private String viewName, targetContainerViewName, viewBefore, viewAfter;
    private int viewPriority;

    public View getInlineView() {
        return inlineView;
    }

    public void setInlineView(View inlineView) {
        this.inlineView = inlineView;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getTargetContainerViewName() {
        return targetContainerViewName;
    }

    public void setTargetContainerViewName(String targetContainerViewName) {
        this.targetContainerViewName = targetContainerViewName;
    }

    public String getViewBefore() {
        return viewBefore;
    }

    public void setViewBefore(String viewBefore) {
        this.viewBefore = viewBefore;
    }

    public String getViewAfter() {
        return viewAfter;
    }

    public void setViewAfter(String viewAfter) {
        this.viewAfter = viewAfter;
    }

    public int getViewPriority() {
        return viewPriority;
    }

    public void setViewPriority(int viewPriority) {
        this.viewPriority = viewPriority;
    }
}
