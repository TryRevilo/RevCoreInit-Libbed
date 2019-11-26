package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import android.view.View;

import java.util.List;

/**
 * Created by rev on 1/18/18.
 */

public class RevPluggableMenuItemVM {

    private long revEntityGUID, revEntityOwnerGUID;
    private String revPluggableMenuItemName;
    private List<String> revPluggableMenuName;
    private View revPluggableMenuView;
    private List revreturnData;

    public long getRevEntityGUID() {
        return revEntityGUID;
    }

    public void setRevEntityGUID(long revEntityGUID) {
        this.revEntityGUID = revEntityGUID;
    }

    public long getRevEntityOwnerGUID() {
        return revEntityOwnerGUID;
    }

    public void setRevEntityOwnerGUID(long revEntityOwnerGUID) {
        this.revEntityOwnerGUID = revEntityOwnerGUID;
    }

    public String getRevPluggableMenuItemName() {
        return revPluggableMenuItemName;
    }

    public void setRevPluggableMenuItemName(String revPluggableMenuItemName) {
        this.revPluggableMenuItemName = revPluggableMenuItemName;
    }

    public List<String> getRevPluggableMenuName() {
        return revPluggableMenuName;
    }

    public void setRevPluggableMenuName(List<String> revPluggableMenuName) {
        this.revPluggableMenuName = revPluggableMenuName;
    }

    public View getRevPluggableMenuView() {
        return revPluggableMenuView;
    }

    public void setRevPluggableMenuView(View revPluggableMenuView) {
        this.revPluggableMenuView = revPluggableMenuView;
    }

    public List getRevreturnData() {
        return revreturnData;
    }

    public void setRevreturnData(List revreturnData) {
        this.revreturnData = revreturnData;
    }
}
