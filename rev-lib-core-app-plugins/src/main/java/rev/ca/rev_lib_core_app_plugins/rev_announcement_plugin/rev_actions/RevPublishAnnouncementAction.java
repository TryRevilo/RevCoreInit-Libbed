package rev.ca.rev_lib_core_app_plugins.rev_announcement_plugin.rev_actions;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

/**
 * Created by rev on 1/3/18.
 */

public class RevPublishAnnouncementAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevPublishAnnouncementAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        Long revEntityGUID = (Long) new RevPersJava().saveRevEntity(revEntity);
        new RevPersLibCreate().revPersRelationship(new RevEntityRelationship("announcement_of", revEntityGUID, revEntity.get_revEntityContainerGUID()));

        return revEntityGUID;
    }
}
