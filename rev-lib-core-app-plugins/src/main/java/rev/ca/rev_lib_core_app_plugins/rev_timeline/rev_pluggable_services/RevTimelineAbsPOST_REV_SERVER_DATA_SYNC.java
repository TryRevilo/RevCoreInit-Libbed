package rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibUpdate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_server_client.RevJSONEntityClassConstructor;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_pers_file.RevMultiDownloadFileFromURL;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_read_from_server.IRevAsyncJSONResponse;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_read_from_server.RevAsyncGetJSONResponseTaskService;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_resolver.rev_push_to_remote_server.IRevAsyncJSONPostTask;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_resolver.rev_push_to_remote_server.RevAsyncJSONPostTaskService;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.rev_pre_post_server_data_sync_pers.I_POST_REV_SERVER_DATA_SYNC;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.I_REV_PROCESS_FINISH;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

public class RevTimelineAbsPOST_REV_SERVER_DATA_SYNC implements I_POST_REV_SERVER_DATA_SYNC {

    private static Context mContext = RevLibGenConstantine.REV_CONTEXT;

    private RevPersJava revPersJava = new RevPersJava();
    private RevPersLibRead revPersLibRead = new RevPersLibRead();
    private RevPersLibUpdate revPersLibUpdate = new RevPersLibUpdate();

    @Override
    public void I_POST_REV_SERVER_DATA_SYNC() {

        new RevDownloadContainerUploads(new RevVarArgsData(mContext), new I_REV_PROCESS_FINISH() {
            @Override
            public void REV_PROCESS_FINISH(Object o) {
                Log.v(RevLangStrings.REV_TAG, "LAST CALL");

                Map<Object, Object> revreturnObjectsMap = ((Map<Object, Object>) o);
                List<RevEntity> revPicsAlbumList = (List<RevEntity>) revreturnObjectsMap.get("rev_pics_albums");
                revSavePics(revPicsAlbumList);

                Map<Long, Long> revEntity_Container_GUIDsMap = (Map<Long, Long>) revreturnObjectsMap.get("rev_missed_containers_guids");

                if (revEntity_Container_GUIDsMap.isEmpty()) return;

                String postURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/rev_api/get_rev_entity_container_entities_by_rev_entity_guids";

                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();

                try {
                    Iterator iterator = revEntity_Container_GUIDsMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        long _remoteRevEntityGUID = (Long) iterator.next();
                        long _revEntityContainerGUID = revEntity_Container_GUIDsMap.get(_remoteRevEntityGUID);
                        jsonArray.put(new JSONObject()
                                .put("_revReqChildEntityGUID", _remoteRevEntityGUID)
                                .put("_revEntityContainerGUID", _revEntityContainerGUID));
                    }

                    jsonObject.put("filter", jsonArray);

                    new RevAsyncJSONPostTaskService(mContext, postURL, jsonObject).revPostJSON(new IRevAsyncJSONPostTask() {
                        @Override
                        public void processFinishAsyncJSONPostTask(JSONObject jsonObject) {
                            if (jsonObject != null) {
                                try {
                                    JSONArray revRetJSONArray = jsonObject.getJSONArray("filter");

                                    RevJSONEntityClassConstructor revJSONEntityClassConstructor = new RevJSONEntityClassConstructor();

                                    for (int i = 0; i < revRetJSONArray.length(); i++) {
                                        JSONObject revJSONItem = revRetJSONArray.getJSONObject(i);
                                        long revRemoteChildEntityGUID = revJSONItem.getLong("_revReqChildEntityGUID");
                                        long revlocalChildEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revRemoteChildEntityGUID);

                                        RevEntity retChildJSONContainerEntity = revJSONEntityClassConstructor.getClassRevEntity_From_JSON(revJSONItem.getJSONObject("_revContainerEntity"));
                                        RevPersGenFunctions.REV_SET_REV_ENTITY_DATA_RES_STATUS_RESOLVED(retChildJSONContainerEntity);

                                        long revContainerGUID = revPersLibRead.revEntityExistsByRemoteEntityGUID(retChildJSONContainerEntity.get_remoteRevEntityGUID());
                                        if (revContainerGUID < 0) {
                                            revContainerGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(retChildJSONContainerEntity);
                                            retChildJSONContainerEntity.set_revEntityGUID(revContainerGUID);
                                        }

                                        revSaveChildEntities(retChildJSONContainerEntity);

                                        int revPersUpdateStatus = revPersLibUpdate.revPersSetContainerGUID_By_RevEntityGUID(revlocalChildEntityGUID, revContainerGUID);

                                        if (revContainerGUID > 0 && revPersUpdateStatus > 0) {
                                            revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(0, revlocalChildEntityGUID);

                                            long revEntityTimelineGUID = revPersLibRead.revEntitySubtypeExists_BY_CONTAINER_GUID(revContainerGUID, "rev_timeline");
                                            if (revEntityTimelineGUID < 0) {
                                                revEntityTimelineGUID = (Long) RevConstantinePluggableViewsServices
                                                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishRevTimelineEntityAction").initRevAction(revPersLibRead.revPersGetRevEntityByGUID(revContainerGUID));
                                            }

                                            RevEntityRelationship revEntityRelationship = new RevEntityRelationship("rev_timeline_entry", revlocalChildEntityGUID, revEntityTimelineGUID);
                                            revEntityRelationship.set_remoteRevEntitySubjectGUID(revRemoteChildEntityGUID);
                                            revEntityRelationship.set_remoteRevEntityTargetGUID(revPersLibRead.getRemoteRevEntityGUID(revEntityTimelineGUID));
                                            revEntityRelationship.set_revResolveStatus(-1);

                                            long revTimelineEntityrelId = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntityRelationship);

                                            if (revTimelineEntityrelId > 0)
                                                revPersLibUpdate.setRevEntityResolveStatusByRevEntityGUID(-808, revlocalChildEntityGUID);
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            List<Long> revPartialTimeLineResolves = revPersLibRead.revPersGetALLRemoteRevEntityGUIDs_By_ResStatus(-808);

                            String revItems = revPartialTimeLineResolves.toString().replace("[", "").replace("]", "").replace(" ", "");
                            String postURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/rev_api/rev_get_flat_entity_info_wrapper?rev_items=" + revItems;

                            new RevAsyncGetJSONResponseTaskService(mContext, postURL, new IRevAsyncJSONResponse() {
                                @Override
                                public void processFinishAsyncJSONResponse(JSONObject jsonObject) {
                                    if (jsonObject == null) return;
                                }
                            }).execute();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void revSaveChildEntities(RevEntity revParent) {
        List<RevEntity> revChildEntityList = revParent.get_revEntityChildrenList();

        long revParentEntityGUID = revParent.get_revEntityGUID();

        if (!revChildEntityList.isEmpty()) {
            for (RevEntity revChildEntity : revChildEntityList) {
                long revChildContainerGUID = revChildEntity.get_revEntityContainerGUID();

                if (revChildContainerGUID == revParentEntityGUID) {
                    revChildEntity.set_revEntityContainerGUID(revParentEntityGUID);
                    long revChildEntityGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revChildEntity);
                    revChildEntity.set_revEntityGUID(revChildEntityGUID);

                    if (!revChildEntity.get_revEntityChildrenList().isEmpty())
                        revSaveChildEntities(revChildEntity);
                }
            }
        }
    }

    private void revSavePics(List<RevEntity> revPicsAlbumList) {
        for (final RevEntity revPicAlbumEntity : revPicsAlbumList) {
            new RevMultiDownloadFileFromURL(mContext, revPicAlbumEntity.get_revEntityGUID(), revPicAlbumEntity.get_revEntityChildrenList(), new I_REV_PROCESS_FINISH() {
                @Override
                public void REV_PROCESS_FINISH(Object o) {
                    Log.v(RevLangStrings.REV_TAG, "FINISH D-LOADS . . . .");
                }
            }).execute();
        }
    }
}