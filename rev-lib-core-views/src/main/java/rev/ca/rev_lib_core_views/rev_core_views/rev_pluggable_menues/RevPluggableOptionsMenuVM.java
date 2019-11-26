package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import android.view.View;

/**
 * Created by rev on 1/18/18.
 */

public class RevPluggableOptionsMenuVM {

    private long revEntityGUID;
    private String revPluggableOptionsMenueName;
    private View revPluggableOptionsMenueView;
    private String menuItemsViewType;
    private String parentMenu = "";

    public long getRevEntityGUID() {
        return revEntityGUID;
    }

    public void setRevEntityGUID(long revEntityGUID) {
        this.revEntityGUID = revEntityGUID;
    }

    public View getRevPluggableOptionsMenueView() {
        return revPluggableOptionsMenueView;
    }

    public void setRevPluggableOptionsMenueView(View revPluggableOptionsMenueView) {
        this.revPluggableOptionsMenueView = revPluggableOptionsMenueView;
    }

    public String getRevPluggableOptionsMenueName() {
        return revPluggableOptionsMenueName;
    }

    public void setRevPluggableOptionsMenueName(String revPluggableOptionsMenueName) {
        this.revPluggableOptionsMenueName = revPluggableOptionsMenueName;
    }

    public String getMenuItemsViewType() {
        return menuItemsViewType;
    }

    public void setMenuItemsViewType(String menuItemsViewType) {
        this.menuItemsViewType = menuItemsViewType;
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }
}
