package rev.ca.rev_plugin_loader;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

/**
 * Created by rev on 1/12/18.
 */

public class RevPluginsObjectsRegistry {

    public static List getRevPluginsObjectsRegistry() {
        Context mContext = RevLibGenConstantine.REV_CONTEXT;
        List<Object> objectList = new ArrayList<>();

        for (Class cp : new PluginsRegistry(mContext).getClasses(null)) {
            try {
                Constructor constructor = cp.getConstructor(Context.class);
                objectList.add(constructor.newInstance(mContext));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return objectList;
    }

    public static List<Class> getRevPluginsClassesRegistry() {
        return new PluginsRegistry(RevLibGenConstantine.REV_CONTEXT).getClasses(null);
    }
}
