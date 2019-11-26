package rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_actions;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;

public class RevPublishRevTimelineEntityAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevPublishRevTimelineEntityAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        if (revEntity == null) return null;

        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_timeline");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revEntity.get_revEntityGUID());
        revPersEntity.set_revEntityChildableStatus(3);

        return new RevPersJava().saveRevEntity(revPersEntity);
    }
}
