package rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;

public class RevCheckRemoteServerConnAsyncTaskService extends AsyncTask<String, String, Boolean> {

    public interface IRevCheckConnAsync {
        void processFinishIRevCheckConnAsync(Boolean output);
    }

    private IRevCheckConnAsync delegate;

    public RevCheckRemoteServerConnAsyncTaskService(IRevCheckConnAsync iRevCheckConnAsync) {
        this.delegate = iRevCheckConnAsync;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        ConnectivityManager cm = (ConnectivityManager) RevLibGenConstantine.REV_ACTIVITY.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL(REV_SESSION_SETTINGS.getRevRemoteServer());   // Change to "http://CampAnn.com" for www  test.
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(10 * 1000);          // 10 s.
                urlc.connect();
                if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                    Log.v("MyApp", "Connection to CampAnn Successful!");
                    return true;
                } else {
                    return false;
                }
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        delegate.processFinishIRevCheckConnAsync(result);
        // runs on the UI thread
        // do something with the result
    }
}
