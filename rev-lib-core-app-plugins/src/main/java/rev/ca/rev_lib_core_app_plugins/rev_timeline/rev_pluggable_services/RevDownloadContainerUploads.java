package rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevAnnotation;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_server_client.RevJSONEntityClassConstructor;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_resolver.rev_push_to_remote_server.IRevAsyncJSONPostTask;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_resolver.rev_push_to_remote_server.RevAsyncJSONPostTaskService;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.register_timeline_object.REV_REGISTER_TIMELINE_ENTITY_SUB_TYPE_LOADER;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.I_REV_PROCESS_FINISH;
import rev.ca.rev_lib_gen_functions.RevLangStrings;

public class RevDownloadContainerUploads {

    private Context mContext;
    private I_REV_PROCESS_FINISH i_rev_process_finish;

    private RevPersJava revPersJava = new RevPersJava();
    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    int revRedoStatus = -1;
    Map<Object, Object> revreturnObjectsMap = new HashMap();
    Map<Long, Long> revEntityMissedContainersGUIDsMap = new HashMap();
    List<RevEntity> revPicsAlbumList = new ArrayList<>();

    public RevDownloadContainerUploads(RevVarArgsData revVarArgsData, final I_REV_PROCESS_FINISH i_rev_process_finish) {
        this.mContext = revVarArgsData.getmContext();
        this.i_rev_process_finish = i_rev_process_finish;

        this.revDownloadContainerUploads();
    }

    private void revDownloadContainerUploads() {
        long revLoggedInRemoteEntityGUID = REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid();

        if (revLoggedInRemoteEntityGUID < 0) return;

        long revLastPublicationDate = 0;
        long revLastcheckRelSubjectGUID = revPersLibRead.revGetLastRelSubjectGUID_By_CreatedDate_RelType("rev_timeline_entry");

        if (revLastcheckRelSubjectGUID > 0) {
            revLastPublicationDate = revPersLibRead.revGetPublicationDate(revLastcheckRelSubjectGUID);
        }

        List<List> longLongMap = revPersLibRead.revPersGetRemoteRelsGUIDs_By_RelTypeValueId_RevEntityGUID_ResolveStatus(5, revLoggedInRemoteEntityGUID, 0);
        longLongMap.addAll(revPersLibRead.revPersGetRemoteRelsGUIDs_By_RelTypeValueId_RevEntityGUID_ResolveStatus(5, revLoggedInRemoteEntityGUID, 1));
        longLongMap.addAll(revPersLibRead.revPersGetRemoteRelsGUIDs_By_RelTypeValueId_RevEntityGUID_ResolveStatus(7, revLoggedInRemoteEntityGUID, 0));
        longLongMap.addAll(revPersLibRead.revPersGetRemoteRelsGUIDs_By_RelTypeValueId_RevEntityGUID_ResolveStatus(7, revLoggedInRemoteEntityGUID, 1));

        List<Long> revRelGUIDs = new ArrayList<>();

        Iterator iterator = longLongMap.listIterator();
        while (iterator.hasNext()) {
            List<Long> longList = (List<Long>) iterator.next();

            long revRemoteSubjectGUID = longList.get(0);
            long revRemoteTargetGUID = longList.get(1);

            if (revRemoteSubjectGUID > 0 && revRemoteSubjectGUID != revLoggedInRemoteEntityGUID) {
                if (!revRelGUIDs.contains(revRemoteSubjectGUID))
                    revRelGUIDs.add(revRemoteSubjectGUID);
            }

            if (revRemoteTargetGUID > 0 && revRemoteTargetGUID != revLoggedInRemoteEntityGUID) {
                if (!revRelGUIDs.contains(revRemoteTargetGUID))
                    revRelGUIDs.add(revRemoteTargetGUID);
            }
        }

        String revContainerGUIDs_S = revRelGUIDs.toString().replace("[", "").replace("]", "").replace(" ", "");

        String revTimelineEntitySubTypes = REV_REGISTER_TIMELINE_ENTITY_SUB_TYPE_LOADER.REV_GET_PLUGGABLE_TIMELINE_ENTITY_SUB_TYPES().toString();
        revTimelineEntitySubTypes = revTimelineEntitySubTypes.replace("[", "").replace("]", "").replace(" ", "");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("revloggedInRemoteEntityGUID", revLoggedInRemoteEntityGUID);
            jsonObject.put("revContainerGUIDs", revContainerGUIDs_S);
            jsonObject.put("revEntitySubTypes", revTimelineEntitySubTypes);
            jsonObject.put("revLastCheckDate", revLastPublicationDate);
            jsonObject.put("revSelectItemslimit", 5);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String postURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/rev_api/rev_get_children_by_container_guids";

        new RevAsyncJSONPostTaskService(mContext, postURL, jsonObject).revPostJSON(new IRevAsyncJSONPostTask() {
            @Override
            public void processFinishAsyncJSONPostTask(JSONObject jsonObject) {
                if (jsonObject == null || jsonObject.length() < 0) {
                    revRedoStatus = 0;
                } else revSaveJSONData(jsonObject);

                if (revRedoStatus == -1) {
                    revDownloadContainerUploads();
                } else {
                    revreturnObjectsMap.put("rev_missed_containers_guids", revEntityMissedContainersGUIDsMap);
                    revreturnObjectsMap.put("rev_pics_albums", revPicsAlbumList);

                    i_rev_process_finish.REV_PROCESS_FINISH(revreturnObjectsMap);
                }
            }
        });
    }

    private void revSaveJSONData(JSONObject jsonObject) {
        JSONArray jArr;

        try {
            jArr = jsonObject.getJSONArray("filter");

            if (jArr.length() == 0) {
                revRedoStatus = 0;
                return;
            }

            RevJSONEntityClassConstructor revJSONEntityClassConstructor = new RevJSONEntityClassConstructor();

            for (int i = 0; i < jArr.length(); i++) {
                Log.v(RevLangStrings.REV_TAG, "Enter : " + i);

                JSONObject revEntityJSONObject = jArr.getJSONObject(i);

                RevEntity revEntity = revJSONEntityClassConstructor.getClassRevEntity_From_JSON(revEntityJSONObject.getJSONObject("revRetEntity"));

                if (revEntity == null) continue;

                if (revEntity.get_remoteRevEntityGUID() < 0) {
                    revEntityMissedContainersGUIDsMap.put(revEntity.get_remoteRevEntityGUID(), revEntity.get_revEntityContainerGUID());
                    continue;
                }

                RevPersGenFunctions.REV_SET_REV_ENTITY_DATA_RES_STATUS_RESOLVED(revEntity);

                long revRemoteTimelineContainerEntityGUID = revEntity.get_revEntityContainerGUID();
                long revLocalTimelineContainerEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revRemoteTimelineContainerEntityGUID);

                long revLocalTimelineEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntity.get_remoteRevEntityGUID());

                if (revLocalTimelineEntityGUID < 0) {
                    revEntity.set_revEntityOwnerGUID(revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntity.get_revEntityOwnerGUID()));
                    revEntity.set_revEntityContainerGUID(revLocalTimelineContainerEntityGUID);
                    revLocalTimelineEntityGUID = this.revSaveRevEntity(revEntity);
                } else continue;

                long revTimelineContainerEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revRemoteTimelineContainerEntityGUID);

                long revEntityTimelineGUID = revPersLibRead.revEntitySubtypeExists_BY_CONTAINER_GUID(revTimelineContainerEntityGUID, "rev_timeline");
                if (revEntityTimelineGUID < 0) {
                    revEntityTimelineGUID = (Long) RevConstantinePluggableViewsServices
                            .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishRevTimelineEntityAction").initRevAction(revPersLibRead.revPersGetRevEntityByGUID(revTimelineContainerEntityGUID));
                }

                JSONObject revRemoteTLRelJSON = revEntityJSONObject.getJSONObject("revRel");

                if (revRemoteTLRelJSON == null
                        || revRemoteTLRelJSON.length() == 0
                        || !revRemoteTLRelJSON.has("_revResolveStatus")
                        || !revRemoteTLRelJSON.has("_revEntityRelationshipRemoteId"))
                    continue;

                if (revPersLibRead.revEntityRelationshipExists("rev_timeline_entry", revLocalTimelineEntityGUID, revEntityTimelineGUID) < 0) {
                    RevEntityRelationship revEntityRelationship = new RevEntityRelationship("rev_timeline_entry", revLocalTimelineEntityGUID, revEntityTimelineGUID);
                    revEntityRelationship.set_remoteRevEntitySubjectGUID(revEntity.get_remoteRevEntityGUID());
                    revEntityRelationship.set_remoteRevEntityTargetGUID(revPersLibRead.getRemoteRevEntityGUID(revEntityTimelineGUID));

                    long _revEntityRelationshipRemoteId = revRemoteTLRelJSON.getLong("_revEntityRelationshipRemoteId");

                    if (_revEntityRelationshipRemoteId > 0) {
                        revEntityRelationship.set_revResolveStatus(-8);
                        revEntityRelationship.set_remoteRevEntityRelationshipId(_revEntityRelationshipRemoteId);
                    } else
                        revEntityRelationship.set_revResolveStatus(-1);

                    revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntityRelationship);
                }
            }
        } catch (JSONException e) {
            Log.v(RevLangStrings.REV_TAG, "REV ERR " + e.getMessage());
        }
    }

    private long revSaveRevEntity(RevEntity revEntity) {
        long revEntityGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntity);

        if (revEntityGUID > 0 && revEntity.get_revEntitySubType().equals("rev_pics_album"))
            revPicsAlbumList.add(revEntity);

        revEntity.set_revEntityGUID(revEntityGUID);
        saveRevEntityChildren(revEntity);

        this.revSaveAnnotation(revEntity);
        this.saveRevEntityChildren(revEntity);

        return revEntityGUID;
    }

    private void saveRevEntityChildren(RevEntity revEntity) {
        List<RevEntity> revEntityChildrenList = revEntity.get_revEntityChildrenList();

        if (!revEntityChildrenList.isEmpty()) {
            for (RevEntity revChildEntity : revEntityChildrenList) {
                if (revChildEntity == null) continue;

                long revChildEntityRemoteGUID = revChildEntity.get_remoteRevEntityGUID();
                long revChildEntityLocalGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revChildEntityRemoteGUID);

                if (revChildEntityLocalGUID < 0) {
                    long revChildEntityOwnerGUID = revChildEntity.get_revEntityOwnerGUID();
                    long revChildEntityContainerGUID = revChildEntity.get_revEntityContainerGUID();

                    if (revChildEntityOwnerGUID > 0) {
                        revChildEntity.set_revEntityOwnerGUID(revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revChildEntityOwnerGUID));

                        if (revChildEntity.get_revEntityOwnerGUID() < 0) {
                            revChildEntity.set_revEntityResolveStatus(-109);
                            revEntityMissedContainersGUIDsMap.put(revChildEntityRemoteGUID, revChildEntityOwnerGUID);
                        }
                    }

                    if (revChildEntityContainerGUID > 0) {
                        revChildEntity.set_revEntityContainerGUID(revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revChildEntityContainerGUID));

                        if (revChildEntity.get_revEntityContainerGUID() < 0) {
                            revChildEntity.set_revEntityResolveStatus(-109);
                            revEntityMissedContainersGUIDsMap.put(revChildEntityRemoteGUID, revChildEntityContainerGUID);
                        }
                    }

                    revChildEntityLocalGUID = revSaveRevEntity(revChildEntity);
                    revChildEntity.set_revEntityGUID(revChildEntityLocalGUID);

                    if (revChildEntityLocalGUID > 0 && revChildEntity.get_revEntitySubType().equals("rev_pics_album"))
                        revPicsAlbumList.add(revChildEntity);
                }

                if (!revChildEntity.get_revEntityChildrenList().isEmpty() && revChildEntityLocalGUID > 0) {
                    revChildEntity.set_revEntityGUID(revChildEntityLocalGUID);
                    this.saveRevEntityChildren(revChildEntity);
                }
            }
        }
    }

    private void revSaveAnnotation(RevEntity revEntity) {
        RevPersLibCreate revPersLibCreate = new RevPersLibCreate();

        for (RevAnnotation revAnnotation : revEntity.get_revAnnotations()) {
            revAnnotation.set_revAnnotationResStatus(0);
            revAnnotation.set_revAnnotationName("rev_entity_like");

            long revEntityGUID = revEntity.get_revEntityGUID();
            long remoteRevEntityGUID = revEntity.get_remoteRevEntityGUID();

            if (revEntityGUID < 1 && remoteRevEntityGUID > 0) {
                revSaveRevEntity(revEntity);
                revEntityGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(remoteRevEntityGUID);

                if (revEntityGUID < 1)
                    continue;
            }

            revAnnotation.set_revAnnotationEntityGUID(revEntityGUID);
            revAnnotation.set_revAnnOwnerEntityGUID(revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revAnnotation.get_revAnnRemoteOwnerEntityGUID()));

            revPersLibCreate.revPersRevEntityAnnotation(revAnnotation);
        }

        List<RevEntity> revChildrenEntityList = revEntity.get_revEntityChildrenList();

        if (revChildrenEntityList.size() > 0) {
            for (int i = 0; i < revChildrenEntityList.size(); i++) {
                revSaveAnnotation(revChildrenEntityList.get(i));
            }
        }
    }
}
