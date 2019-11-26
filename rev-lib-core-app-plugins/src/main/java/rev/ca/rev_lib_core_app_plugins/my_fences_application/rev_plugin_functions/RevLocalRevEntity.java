package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_functions;

import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;

public class RevLocalRevEntity {

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    public RevEntity revGetLocalLoggedinEntity(long revEntityGUID) {
        RevEntity revEntity = revPersLibRead.revPersGetRevEntityByGUID(revEntityGUID);
        revEntity.set_revEntityMetadataList(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntity.get_revEntityGUID()));

        RevEntity revInfoEntity = revPersLibRead.getRevEntityByRevEntityOwnerGUID_Subtype(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_entity_info");

        if (revInfoEntity != null) {
            List<RevEntityMetadata> revEntityInfoMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revInfoEntity.get_revEntityGUID());

            if (revEntityInfoMetadataList != null) {
                revInfoEntity.get_revEntityMetadataList().addAll(revEntityInfoMetadataList);
            }

            long revProfilePicALbumEntityGUID = revPersLibRead.revPersGetALLRevEntityRelationshipsSubjects("rev_pics_album_of", revInfoEntity.get_revEntityGUID()).get(0);
            RevEntity revProfilePicALbumEntity = revPersLibRead.revPersGetRevEntityByGUID(revProfilePicALbumEntityGUID);

            List<Long> revPicsChildrenGUIDList = revPersLibRead.revPersGetALLRevEntityRelationshipsSubjects("rev_picture_of", revProfilePicALbumEntityGUID);
            for (long revFileEntityGUID : revPicsChildrenGUIDList) {
                RevEntity revFileEntity = revPersLibRead.revPersGetRevEntityByGUID(revFileEntityGUID);

                if (revFileEntity == null || revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revFileEntityGUID) == null)
                    continue;

                revFileEntity.set_revEntityMetadataList(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revFileEntityGUID));
                revProfilePicALbumEntity.get_revEntityChildrenList().add(revFileEntity);
            }

            revInfoEntity.get_revEntityChildrenList().add(revProfilePicALbumEntity);
            revEntity.set_revInfoEntity(revInfoEntity);
        }

        return revEntity;
    }
}
