package rev.ca.rev_lib_core_app_plugins.rev_like_plugin.rev_actions;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevAnnotation;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;

/**
 * Created by rev on 1/3/18.
 */

public class RevPublishLikeAction implements IRevPersAction {

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    @Override
    public String registerRevActionName() {
        return "RevPublishLikeAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        if (revEntity == null) return null;

        RevAnnotation revAnnotation = new RevAnnotation();
        revAnnotation.set_revAnnotationResStatus(-1);
        revAnnotation.set_revAnnotationName("rev_entity_like");
        revAnnotation.set_revAnnotationValue(String.valueOf(1));

        long revEntityGUID = revEntity.get_revEntityGUID();
        long remoteRevEntityGUID = revEntity.get_remoteRevEntityGUID();

        if (revEntityGUID < 1 && remoteRevEntityGUID > 0) {
            revEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(remoteRevEntityGUID);
        }

        revAnnotation.set_revAnnotationEntityGUID(revEntityGUID);
        revAnnotation.set_revAnnotationRemoteEntityGUID(revEntity.get_remoteRevEntityGUID());
        revAnnotation.set_revAnnOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revAnnotation.set_revAnnRemoteOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());

        return new RevPersLibCreate().revPersRevEntityAnnotation(revAnnotation);
    }
}
