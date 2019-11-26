package rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_actions.rev_pre_actions;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_pluggable_services.RegisterNoticiasEntityTypeLoader;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevAnnotation;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.AbstractIPostRevPersAction;

/**
 * Created by rev on 1/12/18.
 */

public class RevNoticiasPostActions extends AbstractIPostRevPersAction {

    @Override
    public void initPostPersRevAction(RevEntity revEntity) {
        if (RegisterNoticiasEntityTypeLoader.getRevPluggableNoticiasEntityTypes().contains(revEntity.get_revEntitySubType())) {

            if (!revEntity.get_revEntitySubType().equals("rev_noticias")) {

                RevPersLibRead revPersLibRead = new RevPersLibRead();
                RevPersLibCreate revPersLibCreate = new RevPersLibCreate();

                long revNoticiasEntityGUID;
                long revEntityOwnerGUID = REV_SESSION_SETTINGS.getRevLoggedInEntityGuid().longValue();

                if (revPersLibRead.revEntitySubtypeExists_BY_OWNER_GUID(revEntityOwnerGUID, "rev_noticias") > -1) {
                    revNoticiasEntityGUID = revPersLibRead.getRevEntityGUID_By_RevEntityOwnerGUID_Subtype(revEntityOwnerGUID, "rev_noticias");
                } else {

                    RevEntity revEntityNoticias = new RevEntity();
                    revEntityNoticias.set_revEntityType("rev_object");
                    revEntityNoticias.set_revEntitySubType("rev_noticias");
                    revEntityNoticias.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                    RevObjectEntity revObjectEntity = new RevObjectEntity();
                    revObjectEntity.set_revObjectName("Noticias");
                    revObjectEntity.set_revObjectDescription("Noticias");
                    revEntityNoticias.setRevObjectEntity(revObjectEntity);

                    revNoticiasEntityGUID = (long) new RevPersJava().saveRevEntity(revEntityNoticias);
                }

                RevEntityRelationship revEntityRelationship = new RevEntityRelationship();
                revEntityRelationship.set_revEntitySubjectGUID(revEntity.get_revEntityGUID());
                revEntityRelationship.set_revEntityTargetGUID(revNoticiasEntityGUID);

                if (revEntity.get_revAnnotations() != null && revEntity.get_revAnnotations().size() > 0) {
                    for (RevAnnotation revAnnotation : revEntity.get_revAnnotations()) {
                        revAnnotation.set_revAnnotationEntityGUID(revNoticiasEntityGUID);
                        revPersLibCreate.revPersRevEntityAnnotation(revAnnotation);
                    }
                }

            }
        }
    }
}
