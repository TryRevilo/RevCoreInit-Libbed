package rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_actions.rev_pre_actions;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.register_timeline_object.REV_REGISTER_TIMELINE_ENTITY_SUB_TYPE_LOADER;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevTimeline;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.AbstractIPostRevPersAction;

/**
 * Created by rev on 1/12/18.
 */

public class RevTimelinePostActions extends AbstractIPostRevPersAction {

    @Override
    public void initPostPersRevAction(RevEntity revEntity) {

        int revTimelineEntityExists = new RevPersLibRead().revTimelineEntityExists_BY_RevEntityGUID(revEntity.get_revEntityGUID());

        if (REV_REGISTER_TIMELINE_ENTITY_SUB_TYPE_LOADER.REV_GET_PLUGGABLE_TIMELINE_ENTITY_SUB_TYPES().contains(revEntity.get_revEntitySubType())) {
            if (!revEntity.get_revEntitySubType().equals("rev_timeline") && revTimelineEntityExists > -1) {

                RevTimeline revTimeline = new RevTimeline();

                revTimeline.set_revEntityGUID(revEntity.get_revEntityGUID());
                revTimeline.set_revEntityContainerGUID(revEntity.get_revEntityContainerGUID());
                revTimeline.set_revEntityType(revEntity.get_revEntityType());
                revTimeline.set_revEntitySubType(revEntity.get_revEntitySubType());

                // new RevPersLibCreate().revPersInitRevTimeline(revTimeline);
            }
        }
    }
}
