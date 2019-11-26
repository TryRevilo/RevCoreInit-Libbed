package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_libs;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersEntityInfoWrapperModel;

public class RevLocalEntityInfoWrapperModel {

    RevPersLibRead revPersLibRead = new RevPersLibRead();

    public RevPersEntityInfoWrapperModel revGetLocalEntityInfoWrapperModel(long revEntityGUID) {
        RevPersEntityInfoWrapperModel revPersEntityInfoWrapperModel = new RevPersEntityInfoWrapperModel();

        RevEntity revEntity = revPersLibRead.revPersGetRevEntityByGUID(revEntityGUID);
        revSetRevEntityPublisher(revEntity);

        if (revEntity == null) return null;

        List<RevEntityMetadata> revEntityMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntityGUID);

        if (revEntityMetadataList != null)
            revEntity.get_revEntityMetadataList().addAll(revEntityMetadataList);

        revPersEntityInfoWrapperModel.setRevEntity(revEntity);

        RevEntity revEntityInfo = null;

        if (revEntity.get_revEntityType().equals("rev_user_entity")) {
            revEntityInfo = revPersLibRead.getRevEntity_By_RevEntityContainerGUID_Subtype(revEntityGUID, "rev_entity_info");
        } else if (revEntity.get_revEntityType().equals("rev_group_entity")) {
            revEntityInfo = revPersLibRead.getRevEntity_By_RevEntityContainerGUID_Subtype(revEntityGUID, "rev_space_entity_info");
        }

        if (revEntityInfo != null) {
            revSetRevEntityPublisher(revEntityInfo);

            List<RevEntityMetadata> revEntityInfoMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntityInfo.get_revEntityGUID());

            if (revEntityInfoMetadataList != null) {
                revEntityInfo.get_revEntityMetadataList().addAll(revEntityInfoMetadataList);
            }

            revEntity.set_revInfoEntity(revEntityInfo);

            List<Long> revProfilePicsAlbumsGUIDs = revPersLibRead.revPersGetALLRevEntityRelationshipsSubjects("rev_pics_album_of", revEntityInfo.get_revEntityGUID());
            if (!revProfilePicsAlbumsGUIDs.isEmpty()) {
                long revEntityInfoPicsAlbumGUID = revProfilePicsAlbumsGUIDs.get(0);

                RevEntity revEntityInfoPicsAlbum = revPersLibRead.revPersGetRevEntityByGUID(revEntityInfoPicsAlbumGUID);
                revSetRevEntityPublisher(revEntityInfoPicsAlbum);
                List<Long> revEntityInfoPicsAlbumChildGUIDs = revPersLibRead.revPersGetALLRevEntityRelationshipsSubjects("rev_picture_of", revEntityInfoPicsAlbum.get_revEntityGUID());

                if (revEntityInfoPicsAlbumChildGUIDs != null && revEntityInfoPicsAlbumChildGUIDs.size() > 0) {
                    for (Long revFileEntityGUID : revEntityInfoPicsAlbumChildGUIDs) {
                        RevEntity revFileEntity = revPersLibRead.revPersGetRevEntityByGUID(revFileEntityGUID);

                        if (revFileEntity == null) continue;

                        revSetRevEntityPublisher(revFileEntity);
                        revFileEntity.get_revEntityMetadataList().addAll(revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revFileEntityGUID));
                        revEntityInfoPicsAlbum.get_revEntityChildrenList().add(revFileEntity);
                    }

                    for (int i = 0; i < revEntityInfoPicsAlbum.get_revEntityChildrenList().size(); i++) {
                        List<RevEntityMetadata> revPicFileMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataByRevEntityGUID(revEntityInfoPicsAlbum.get_revEntityChildrenList().get(i).get_revEntityGUID());
                        if (revPicFileMetadataList != null) {
                            revEntityInfoPicsAlbum.get_revEntityChildrenList().get(i).get_revEntityMetadataList().addAll(revPicFileMetadataList);
                        }
                    }

                    revEntityInfo.get_revEntityChildrenList().add(revEntityInfoPicsAlbum);
                }
            }
        }

        /** START REV ENTITY CONNS **/

        List<RevEntity> revConnectionEntitiesList = new ArrayList<>();

        List<Long> revConnRelsGUIDS = new ArrayList<>();

        if (revEntity.get_revEntityType().equals("rev_user_entity")) {
            revConnRelsGUIDS = revPersLibRead.revPersGetALLRevEntityRelationshipsSubjects("rev_entity_connect_members", revEntityGUID);
            revConnRelsGUIDS.addAll(revPersLibRead.revPersGetALLRevEntityRelationshipsTargets("rev_entity_connect_members", revEntityGUID));
        } else if (revEntity.get_revEntityType().equals("rev_group_entity")) {
            revConnRelsGUIDS = revPersLibRead.revPersGetALLRevEntityRelationshipsSubjects("rev_entity_space_member", revEntityGUID);
        }

        /** END REV ENTITY CONNS **/

        /** END REV ENTITY SUBS **/

        List<RevEntity> revEntitySubscriptionsList = new ArrayList<>();

        List<Long> revEntitySubscriptionsTargetGUIDsList = revPersLibRead.revPersGetALLRevEntityRelationshipsTargets("rev_entity_space_member", revEntityGUID);

        for (long revRelTargetGUID : revEntitySubscriptionsTargetGUIDsList) {
            revEntitySubscriptionsList.add(revPersLibRead.revPersGetRevEntityByGUID(revRelTargetGUID));
        }

        revPersEntityInfoWrapperModel.getRevEntitySubscriptionsList().addAll(revEntitySubscriptionsList);

        /** END REV ENTITY SUBS **/

        for (long revConnRelGUID : revConnRelsGUIDS)
            revConnectionEntitiesList.add(revPersLibRead.revPersGetRevEntityByGUID(revConnRelGUID));

        revPersEntityInfoWrapperModel.getRevEntityConnections().addAll(revConnectionEntitiesList);

        /** REV TIMELINE ENTITIES **/
        List<Long> revTimelineEntityGUIDsList = revPersLibRead.revPersGetALLRevEntityGUIDs_By_ContainerGUID(revEntityGUID);

        List<RevEntity> revTimelineEntityList = new ArrayList<>();

        for (Long revTimelineEntityGUID : revTimelineEntityGUIDsList) {
            RevEntity revTimelineEntity = revPersLibRead.revPersGetRevEntityByGUID(revTimelineEntityGUID);
            revSetRevEntityPublisher(revTimelineEntity);
            this.revSetRevEntityAnnotations(revTimelineEntity);

            if (revTimelineEntity.get_revEntityChildableStatus() == -1 || revTimelineEntity.get_revEntityChildableStatus() == 301)
                continue;

            for (long revEntitySubjectGUID : revPersLibRead.revPersGetALLRelSubjectGUIDs_By_TargetGUID(revTimelineEntityGUID)) {
                RevEntity revEntitySubject = revPersLibRead.revPersGetRevEntityByGUID(revEntitySubjectGUID);
                this.revSetRevEntityAnnotations(revEntitySubject);
                revTimelineEntity.get_revEntityChildrenList().add(revEntitySubject);
            }

            if (revTimelineEntity.get_revEntityChildableStatus() == 3) {
                List<Long> revEntityContainerChildrenGUIDs = revPersLibRead.revPersGetALLRevEntityGUIDs_By_ContainerGUID(revTimelineEntity.get_revEntityGUID());

                for (long revEntityContainerChildGUID : revEntityContainerChildrenGUIDs) {
                    RevEntity revEntityContainerChild = revPersLibRead.revPersGetRevEntityByGUID(revEntityContainerChildGUID);
                    revSetRevEntityPublisher(revEntityContainerChild);
                    this.revSetRevEntityAnnotations(revEntityContainerChild);
                    revTimelineEntity.get_revEntityChildrenList().add(revEntityContainerChild);
                }
            }

            revTimelineEntityList.add(revTimelineEntity);
        }

        revPersEntityInfoWrapperModel.getRevTimelineEntities().addAll(revTimelineEntityList);

        revPersEntityInfoWrapperModel.setRevEntity(revEntity);

        return revPersEntityInfoWrapperModel;
    }

    private void revSetRevEntityPublisher(RevEntity revEntity) {
        if (revEntity == null || revEntity.get_revEntityOwnerGUID() < 1) return;

        revEntity.set_revPublisherEntity(revPersLibRead.revPersGetRevEntityByGUID(revEntity.get_revEntityOwnerGUID()));
    }

    private void revSetRevEntityAnnotations(RevEntity revEntity) {
        if (revEntity == null) return;

        List<Long> revEntityAnnotationGUIDsList = revPersLibRead.getAllRevEntityAnnoationIds_By_RevEntityGUID(revEntity.get_revEntityGUID());

        for (Long aLong : revEntityAnnotationGUIDsList) {
            revEntity.get_revAnnotations().add(revPersLibRead.revPersGetRevEntityAnn_By_LocalAnnId(aLong));
        }
    }
}
