package rev.ca.rev_plugin_loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;
import rev.ca.rev_lib_gen_functions.RevLangStrings;

public class LoadedPlugin {

    final private Context baseContext;
    final private PluginContext pluginContext;
    final private String apkPath;
    final private DexClassLoader dexClassLoader;
    AssetManager assetManager;

    final private AbstractRevStartPlugin viewCreator;

    public LoadedPlugin(Context context, String _apkPath)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        this.baseContext = context;
        this.apkPath = _apkPath;
        assetManager = baseContext.getAssets();

        File codeCacheDir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            codeCacheDir = context.getCodeCacheDir();
        } else {
            codeCacheDir = context.getCacheDir();
        }

        dexClassLoader = new DexClassLoader(apkPath, codeCacheDir.getAbsolutePath(), null, context.getClassLoader());
        pluginContext = new PluginContext(baseContext, apkPath);

        File apkFile = new File(apkPath);

        String apkName = apkFile.getName().replace(".apk", "").toLowerCase();
        String viewCreatorClassName = "com.lelloman." + apkName + ".MyViewCreator";
        Class myClass = dexClassLoader.loadClass(viewCreatorClassName);
        Constructor constructor = myClass.getConstructor(Context.class);

        viewCreator = (AbstractRevStartPlugin) constructor.newInstance(pluginContext);
    }

    public AbstractRevStartPlugin getViewCreator() {
        return viewCreator;
    }
}
