package rev.ca.rev_plugin_loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lelloman.androidplugins.AbstractViewCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

public class RevPluginLoader {

    Context mContext;
    List<String> output = new ArrayList<>();

    public RevPluginLoader(Context context) {
        mContext = context;
    }

    public void revLoadView(ViewGroup pluginsContainer) {
        List<String> plugins = copyPlugins();

        for (String fileName : plugins) {
            LoadedPluginAbstract loadedPlugin = loadPluginAbs(fileName);
            if (loadedPlugin != null) {
                AbstractViewCreator viewCreator = loadedPlugin.getViewCreator();
                if (viewCreator != null) {
                    View view = viewCreator.createView();
                    pluginsContainer.addView(view);
                }
            }
        }
    }

    public ArrayList<ArrayList<Class>> getExtClasses() {
        List<String> plugins = copyPlugins();
        ArrayList<ArrayList<Class>> loadedPluginClasses = new ArrayList<>();

        for (String fileName : plugins) {

            LoadedPlugin loadedPlugin = loadPlugin(fileName);
            if (loadedPlugin != null) {
                AbstractRevStartPlugin viewCreator = loadedPlugin.getViewCreator();
                loadedPluginClasses.add(viewCreator.getViewsPluginServices());
            }
        }

        return loadedPluginClasses;
    }

    private List<String> copyPlugins() {
        this.listAssetFiles("plugins");
        AssetManager assetManager = mContext.getAssets();
        try {
            for (String filename : output) {
                if (filename.toLowerCase().endsWith(".apk")) {
                    InputStream in = assetManager.open("plugins/" + filename);
                    File outFile = new File(mContext.getFilesDir(), filename);

                    Log.v(RevLangStrings.REV_TAG, "In >> " + filename);
                    Log.v(RevLangStrings.REV_TAG, "Out >> " + mContext.getFilesDir());

                    if (outFile.exists()) {
                        outFile.delete();
                    }

                    OutputStream out = new FileOutputStream(outFile);
                    copyFile(in, out);
                    in.close();
                    out.flush();
                    out.close();
                    output.add(filename);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    private boolean listAssetFiles(String path) {
        String[] list;
        try {
            list = mContext.getAssets().list(path);
            if (list.length > 0) {
                // This is a folder
                for (String file : list) {
                    if (!listAssetFiles(path + "/" + file))
                        return false;
                    else {
                        // This is a file
                        output.add(file);
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private LoadedPlugin loadPlugin(String assetsFileName) {
        try {
            String apkPath = new File(mContext.getFilesDir(), assetsFileName).getAbsolutePath();
            return new LoadedPlugin(mContext, apkPath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private LoadedPluginAbstract loadPluginAbs(String assetsFileName) {
        try {
            String apkPath = new File(RevLibGenConstantine.REV_CONTEXT.getApplicationContext().getFilesDir(), assetsFileName).getAbsolutePath();
            return new LoadedPluginAbstract(RevLibGenConstantine.REV_CONTEXT.getApplicationContext(), apkPath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
