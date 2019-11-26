package rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.rev_pre_pers.RevPersSetRelsRemoteGUIDs;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckConnectivity;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_data_services.RevRegCallbackIPOnRemoteServer;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_data_services.revPers_rev_entity_annotations.RevSaveRevEntitiesAnnotationsToServerResolver;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_data_services.revPers_rev_entity_metadata.RevResolvePartialMetadataUploads;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.RevDownloadRemoteRelIds_By_Subject_Target_RelId;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.RevDownloadRevEntityRels_By_RemoteRevEntityGUID_RelValueIds;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.RevDownloadRevEntityRels_By_ResStatus_SubjectGUID;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.RevPersUploadAcceptedRels;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.RevSetRemoteRelResStatus_By_RemoteRelId;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_data.rev_entity_rel.rev_upload.RevResolvePartialRelsUploads;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevPersDownloadRequestingRevEntityRels_By_RemoteRevEntityTargetGUID;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevSaveRevEntitiesToServerResolver;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.I_POST_REV_SERVER_DATA_SYNC;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_gen_functions.I_REV_PROCESS_FINISH;
import rev.ca.rev_lib_gen_functions.RevLangStrings;

public class RevPostPersServices {

    private Context mContext;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();
    private RevPersLibUpdate revPersLibUpdate = new RevPersLibUpdate();

    public RevPostPersServices(Context mContext) {
        this.mContext = mContext;
    }

    public void revPostLocalPersRemoteSyncServices() {
        if (!RevCheckConnectivity.isIsRevNetworkConnected()) return;

        new RevRegCallbackIPOnRemoteServer(mContext).revRegCallbackIPOnRemoteServer();

        new RevSaveRevEntitiesToServerResolver(mContext, new RevSaveRevEntitiesToServerResolver.IRevSaveRevEntitiesToServerResolver() {
            @Override
            public void revProcessFinish() {
                if (!REV_SESSION_SETTINGS.isIsNotLoggedIn()) {
                    new RevDownloadRevEntityRels_By_RemoteRevEntityGUID_RelValueIds(mContext)
                            .revDownloadRevEntityRels_By_RemoteRevEntityGUID_RelValueIds(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid(), "0,1,2,3,4,5,6,7");
                    new RevDownloadRevEntityRels_By_ResStatus_SubjectGUID(mContext).revDownloadRevEntityRels_By_ResStatus_SubjectGUID(1, REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());
                    new RevPersDownloadRequestingRevEntityRels_By_RemoteRevEntityTargetGUID(mContext).revPersReadRevEntityRelsTargetEntities_By_RemoteRevEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());
                }

                new RevPersSetRelsRemoteGUIDs(new I_REV_PROCESS_FINISH() {
                    @Override
                    public void REV_PROCESS_FINISH(Object o) {
                        new RevResolvePartialRelsUploads(new RevVarArgsData(mContext), new I_REV_PROCESS_FINISH() {
                            @Override
                            public void REV_PROCESS_FINISH(Object o) {
                                revResParts();

                                new RevDownloadRemoteRelIds_By_Subject_Target_RelId(mContext, new I_REV_PROCESS_FINISH() {
                                    @Override
                                    public void REV_PROCESS_FINISH(Object o) {
                                        List<RevEntityRelationship> revEntityRelationshipList = revPersLibRead.revPersGetRevEntityRels_By_ResStatus(-8);
                                        List<Long> revRemoteRelIdList = new ArrayList<>();

                                        for (RevEntityRelationship revEntityRelationship : revEntityRelationshipList) {
                                            long revRemoteRelId = revEntityRelationship.get_remoteRevEntityRelationshipId();

                                            if (revRemoteRelId < 0) {
                                                revPersLibUpdate.revPersUpdateRelResStatus_By_RelId(revEntityRelationship.get_revEntityRelationshipId(), -809);
                                                continue;
                                            }

                                            revRemoteRelIdList.add(revRemoteRelId);
                                        }

                                        new RevSetRemoteRelResStatus_By_RemoteRelId(mContext, 0, revRemoteRelIdList, new I_REV_PROCESS_FINISH() {
                                            @Override
                                            public void REV_PROCESS_FINISH(Object o) {
                                                new RevPersUploadAcceptedRels(mContext);
                                                new RevSaveRevEntitiesAnnotationsToServerResolver(mContext).syncNewRevEntitiesAnnotations();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });

                /** REV POST SP NOTIFY **/
                for (I_POST_REV_SERVER_DATA_SYNC iPostRevServerDataSync : new ServicesLoaderIPostRevServerDataSync().initRevPluginsIPostRevServerDataSyncRegistry()) {
                    iPostRevServerDataSync.I_POST_REV_SERVER_DATA_SYNC();
                }
            }
        });
    }

    private void revResParts() {
        new RevResolvePartialMetadataUploads(new RevVarArgsData(mContext), new I_REV_PROCESS_FINISH() {
            @Override
            public void REV_PROCESS_FINISH(Object o) {
                /** REV ENTITY **/
                final List<Long> revEntityGUIDsList = revPersLibRead.revPersGetALLRevEntityGUIDs_By_ResStatus(-101);
                List<Long> revPausedRevEntityGUIDsList = revPersLibRead.revPersGetALLRevEntityGUIDs_By_ResStatus(-107);
                revEntityGUIDsList.addAll(revPausedRevEntityGUIDsList);

                for (long revPausedRevEntityGUID : revPausedRevEntityGUIDsList) {
                    revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(-101, revPausedRevEntityGUID);
                }

                /** REV METADATA **/
                final List<Long> revMetadataIdsList = revPersLibRead.revPersGetALLRevEntityMetadataIds_By_ResStatus(-101);
                List<Long> revPausedMetadataList = revPersLibRead.revPersGetALLRevEntityMetadataIds_By_ResStatus(-107);
                revMetadataIdsList.addAll(revPausedMetadataList);

                for (long revMetadataId : revPausedMetadataList) {
                    revPersLibUpdate.setMetadataResolveStatus_BY_METADATA_ID(-101, revMetadataId);
                }

                /** REV RELS **/
                final List<RevEntityRelationship> revUnresolvedEntityRelationshipList = revPersLibRead.revPersGetRevEntityRels_By_ResStatus(-101);
                List<RevEntityRelationship> revPausedRelsList = revPersLibRead.revPersGetRevEntityRels_By_ResStatus(-107);
                revUnresolvedEntityRelationshipList.addAll(revPausedRelsList);

                for (RevEntityRelationship revEntityRelationship : revPausedRelsList) {
                    revPersLibUpdate.revPersUpdateRelResStatus_By_RelId(revEntityRelationship.get_revEntityRelationshipId(), -101);
                }

                if (revEntityGUIDsList.size() > 0 || revMetadataIdsList.size() > 0 || revUnresolvedEntityRelationshipList.size() > 0) {
                    // revPostLocalPersRemoteSyncServices();
                }
            }
        });
    }
}
