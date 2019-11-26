package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_actions;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersEntityInfoWrapperModel;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs.RevLocalEntityInfoWrapperModel;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_lib_gen_functions.RevViewsBaseFunctions;
import rev.ca.revlibviews.rev_core_layouts.RevCoreFrameLayoutLayoutParams;

import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER;

/**
 * Created by rev on 12/20/17.
 */

public class RevCreateUserLoginAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevCreateUserLoginAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.setLayoutParams(RevCoreFrameLayoutLayoutParams.getRev_centerContentView_LL_LP());

        if (revEntity != null) {
            RevViewsBaseFunctions.hideKeyboard(RevLibGenConstantine.REV_ACTIVITY);

            REV_SESSION_SETTINGS.setIsNotLoggedIn(false);
            REV_SESSION_SETTINGS.setRevLoggedInEntityGuid(revEntity.get_revEntityGUID());
            REV_SESSION_SETTINGS.setRevLoggedInRemoteEntityGuid(revEntity.get_remoteRevEntityGUID());

            REV_SESSION_SETTINGS.REV_RELOAD_MAIN_APP_ACTIVITY();

            RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevLocalEntityInfoWrapperModel().revGetLocalEntityInfoWrapperModel(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
            RevVarArgsData passRVD = new RevVarArgsData(RevLibGenConstantine.REV_CONTEXT);
            passRVD.setRevEntity(revPersEntityInfoWrapperModel.getRevEntity());
            passRVD.setRevPersEntityInfoWrapperModel(revPersEntityInfoWrapperModel);

            REV_SESSION_SETTINGS.setRevLoggedInPageRevVarArgsData(passRVD);

            return revEntity;
        }

        return null;
    }
}
