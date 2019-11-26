package rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.RevPostPersServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

public class RevCheckConnectivity extends BroadcastReceiver {

    private Context mContext;

    private static boolean isRevNetworkConnected = false;

    public static boolean isIsRevNetworkConnected() {
        return isRevNetworkConnected;
    }

    public RevCheckConnectivity(RevVarArgsData revVarArgsData) {
        mContext = revVarArgsData.getmContext();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getExtras() != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isInternetConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            if (isInternetConnected) {
                new RevCheckRemoteServerConnAsyncTaskService(new RevCheckRemoteServerConnAsyncTaskService.IRevCheckConnAsync() {
                    @Override
                    public void processFinishIRevCheckConnAsync(Boolean output) {
                        Log.v("MyApp", "Rev Server Alive . . . " + output);
                        isRevNetworkConnected = output;

                        if (output)
                            new RevPostPersServices(mContext).revPostLocalPersRemoteSyncServices();
                    }
                }).execute();
            } else Log.v("MyApp", "Rev WIF On . . . " + false);
        }

        Intent i = new Intent("broadCastName");
        // Data you need to pass to activity
        i.putExtra("message", "HELLO WORLD FROM RECEIVER");
        i.putExtra("isRevNetworkConnected", isRevNetworkConnected);

        context.sendBroadcast(i);
    }
}
