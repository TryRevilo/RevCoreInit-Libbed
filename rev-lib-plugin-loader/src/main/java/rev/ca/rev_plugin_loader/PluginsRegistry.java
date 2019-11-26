package rev.ca.rev_plugin_loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import rev.ca.rev_lib_gen_functions.RevFileFunctions;

/**
 * Created by rev on 10/10/17.
 */

public class PluginsRegistry {

    Context mContext;
    AssetManager assetManager;

    public PluginsRegistry(Context mContext) {
        this.mContext = mContext;
        assetManager = mContext.getAssets();
    }

    public ArrayList<String> revAssetFileExploder() {
        ArrayList<String> pluginsClassPathsList = new ArrayList<>();

        ArrayList<String> pluginFiles = RevFileFunctions.listAssetFiles(mContext, "META-INF/services");

        for (String pluginFileUrl : pluginFiles) {
            BufferedReader reader = null;
            try {
                InputStream is = assetManager.open("META-INF/services/" + pluginFileUrl);
                reader = new BufferedReader(new InputStreamReader(is));

                // do reading, usually loop until end of file reading
                String pluginClassPath;
                while ((pluginClassPath = reader.readLine()) != null) {
                    pluginsClassPathsList.add(pluginClassPath);
                }
            } catch (IOException e) {
                Log.e("MyApp", "exception", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("MyApp", "exception", e);
                    }
                }
            }
        }

        return pluginsClassPathsList;
    }

    public ArrayList<Class> getClasses(ArrayList<String> classUrls) {
        ArrayList<Class> classList = new ArrayList<>();

        if (classUrls == null) {
            classUrls = revAssetFileExploder();

            // Load APKs
            RevPluginLoader revPluginLoader = new RevPluginLoader(mContext);
            ArrayList<ArrayList<Class>> revPluginClasses = revPluginLoader.getExtClasses();

            for (ArrayList<Class> arrayLists : revPluginClasses) {
                if (arrayLists != null) {
                    for (Class aClass : arrayLists) {
                        classList.add(aClass);
                    }
                }
            }

            for (String url : classUrls) {
                Class myClass = null;
                try {
                    myClass = Class.forName(url);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                classList.add(myClass);
            }
        }

        return classList;
    }
}