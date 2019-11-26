package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages;

import android.view.View;

import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersEntityInfoWrapperModel;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs.RevLocalEntityInfoWrapperModel;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;

/**
 * Created by rev on 11/3/17.
 */

public class RevFencesLoggedIn extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;

    public RevFencesLoggedIn(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);

        this.revVarArgsData = revVarArgsData;
    }

    @Override
    public View createPluggableRevMainCenterCctView_LL() {
        RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revVarArgsData.setRevEntity(revPersEntityInfoWrapperModel.getRevEntity());
        revVarArgsData.setRevPersEntityInfoWrapperModel(revPersEntityInfoWrapperModel);

        REV_SESSION_SETTINGS.setRevLoggedInPageRevVarArgsData(revVarArgsData);

        REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(revVarArgsData).getUserMainCenterCctViewLL());
        return null;
    }
}
