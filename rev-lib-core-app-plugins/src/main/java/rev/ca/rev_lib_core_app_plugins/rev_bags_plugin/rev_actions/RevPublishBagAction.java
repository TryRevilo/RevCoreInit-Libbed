package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_actions;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;

/**
 * Created by rev on 1/3/18.
 */

public class RevPublishBagAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevPublishBagAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        Long revEntityGUID = (Long) new RevPersJava().saveRevEntity_NO_REMOTE_SYNC(revEntity);

        RevEntityRelationship revEntityRelationship = new RevEntityRelationship("rev_entity_space_member", REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), revEntityGUID);
        revEntityRelationship.set_remoteRevEntitySubjectGUID(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());

        new RevPersJava().saveRevEntity(revEntityRelationship);

        return revEntityGUID;
    }
}
