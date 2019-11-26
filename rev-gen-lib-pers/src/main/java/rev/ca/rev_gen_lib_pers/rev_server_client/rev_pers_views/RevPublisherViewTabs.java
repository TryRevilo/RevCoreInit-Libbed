package rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers_views;

import android.content.Context;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckRemoteServerConnAsyncTaskService;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_resolver.rev_push_to_remote_server.IRevAsyncJSONPostTask;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_resolver.rev_push_to_remote_server.RevAsyncJSONPostTaskService;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;

public class RevPublisherViewTabs {

    private Context mContext;

    public RevPublisherViewTabs(Context mContext) {
        this.mContext = mContext;
    }

    public void deleteRevEntity() {
        new RevCheckRemoteServerConnAsyncTaskService(new RevCheckRemoteServerConnAsyncTaskService.IRevCheckConnAsync() {
            @Override
            public void processFinishIRevCheckConnAsync(Boolean output) {
                List<Long> revGUIDs = new RevPersLibRead().revPersGetALLRevEntityRelationshipsTargets("rev_entity_connect_members", REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                String apiURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/rev_api/delete_rev_entity?rev_entity_guids="
                        + StringUtils.join(revGUIDs, ',');

                if (output) {

                    new RevAsyncJSONPostTaskService(mContext, apiURL, new JSONObject()).revPostJSON(new IRevAsyncJSONPostTask() {
                        @Override
                        public void processFinishAsyncJSONPostTask(JSONObject jsonObject) {
                            Iterator<String> keys = jsonObject.keys();

                            while (keys.hasNext()) {
                                String key = keys.next();
                                try {
                                    Log.v(RevLangStrings.REV_TAG, "key : " + key + " value : " + jsonObject.get(key));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }).execute();
    }
}
