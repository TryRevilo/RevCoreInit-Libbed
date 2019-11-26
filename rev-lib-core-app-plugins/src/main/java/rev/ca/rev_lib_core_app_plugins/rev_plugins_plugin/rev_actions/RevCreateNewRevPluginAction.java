package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_actions;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

public class RevCreateNewRevPluginAction implements IRevPersAction {
    @Override
    public String registerRevActionName() {
        return "RevCreateNewRevPluginAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        return new RevPersJava().saveRevEntity_NO_REMOTE_SYNC(revEntity);
    }
}
