package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_actions;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

public class RevPublishRevEntityInfoAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevPublishRevEntityInfoAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        if (new RevPersLibRead().revEntitySubtypeExists_BY_OWNER_GUID(revEntity.get_revEntityOwnerGUID(), "rev_entity_info") == -1)
            return new RevPersJava().saveRevEntity(revEntity);

        return -1L;
    }
}