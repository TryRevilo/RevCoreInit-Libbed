package rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_actions;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

/**
 * Created by rev on 1/12/18.
 */

public class RevCreateNoticiasEntityAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevCreateNoticiasEntityAction";
    }

    @Override
    public Object initRevAction(final RevEntity revEntity) {

        RevPersLibRead revPersLibRead = new RevPersLibRead();

        long revNoticiasEntityGUID;
        long revEntityContainerGUID = revEntity.get_revEntityContainerGUID().longValue();

        if (revPersLibRead.revEntitySubtypeExists_BY_CONTAINER_GUID(revEntityContainerGUID, "rev_noticias") > -1) {
            revNoticiasEntityGUID = revPersLibRead.getRevEntityGUIDByRevEntityContainerGUID_Subtype(revEntityContainerGUID, "rev_noticias");
        } else {

            RevEntity revEntityNoticias = new RevEntity();
            revEntityNoticias.set_revEntityType("rev_object");
            revEntityNoticias.set_revEntitySubType("rev_noticias");
            revEntityNoticias.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

            revEntityNoticias.set_revEntityMetadataList(Arrays.asList(
                    new RevEntityMetadata("kiwi_switch", "Noticias ITEM")
            ));

            RevObjectEntity revObjectEntity = new RevObjectEntity();
            revObjectEntity.set_revObjectName("Noticias");
            revObjectEntity.set_revObjectDescription("Noticias");
            revEntityNoticias.setRevObjectEntity(revObjectEntity);

            revNoticiasEntityGUID = (long) new RevPersJava().saveRevEntity(revEntityNoticias);
        }

        return Long.valueOf(revNoticiasEntityGUID);
    }
}
