package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_actions.rev_post_actions;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.AbstractIPostRevPersAction;

/**
 * Created by rev on 1/12/18.
 */

public class RevCreateNewUserPostActions extends AbstractIPostRevPersAction {

    @Override
    public void initPostPersRevAction(RevEntity revEntity) {
        if (revEntity == null) return;

        if (revEntity.get_revEntitySubType().equals("rev_user_entity")) {
            Long revEntityGUID = revEntity.get_revEntityGUID();
            new RevPersLibUpdate().resetRevEntityOwnerGUIG(-1l, revEntityGUID);
        }
    }
}
