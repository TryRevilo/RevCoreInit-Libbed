package rev.ca.rev_plugin_loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;

import com.lelloman.androidplugins.AbstractViewCreator;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;

public class LoadedPluginAbstract {

    final private Context baseContext;
    final private PluginContext pluginContext;
    final private String apkPath;
    final private DexClassLoader dexClassLoader;
    AssetManager assetManager;

    final private AbstractViewCreator viewCreator;

    public LoadedPluginAbstract(Context context, String _apkPath)
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
        Class myClass = dexClassLoader.loadClass("com.lelloman." + apkFile.getName().replace(".apk", "").toLowerCase() + ".MyViewCreator");
        Constructor constructor = myClass.getConstructor(Context.class);
        viewCreator = (AbstractViewCreator) constructor.newInstance(pluginContext);
    }

    public AbstractViewCreator getViewCreator() {
        return viewCreator;
    }
}
