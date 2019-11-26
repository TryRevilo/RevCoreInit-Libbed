package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override;

import android.view.View;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 1/13/18.
 */

public class RevEntityListingViewOverrideVOM {

    private String overrideName;
    private RevEntity revEntity;
    private View overrideView;
    private RevVarArgsData revVarArgsData = new RevVarArgsData();

    public String getOverrideName() {
        return overrideName;
    }

    public void setOverrideName(String overrideName) {
        this.overrideName = overrideName;
    }

    public RevEntity getRevEntity() {
        return revEntity;
    }

    public void setRevEntity(RevEntity revEntity) {
        this.revEntity = revEntity;
    }

    public View getOverrideView() {
        return overrideView;
    }

    public void setOverrideView(View overrideView) {
        this.overrideView = overrideView;
    }

    public RevVarArgsData getRevVarArgsData() {
        return revVarArgsData;
    }

    public void setRevVarArgsData(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
    }

}
