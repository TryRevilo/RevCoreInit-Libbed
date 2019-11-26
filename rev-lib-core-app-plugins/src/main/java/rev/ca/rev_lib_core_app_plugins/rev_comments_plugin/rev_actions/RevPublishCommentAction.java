package rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_actions;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

/**
 * Created by rev on 1/3/18.
 */

public class RevPublishCommentAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevPublishCommentAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        if (revEntity == null) return null;

        Long revEntityGUID = (Long) new RevPersJava().saveRevEntity(revEntity);
        Long relationshipId = new RevPersLibCreate().revPersRelationship(new RevEntityRelationship("rev_comment", revEntityGUID, revEntity.get_revEntityContainerGUID()));

        return relationshipId;
    }
}
