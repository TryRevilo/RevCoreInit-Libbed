package rev.ca.rev_plugin_loader;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

public abstract class AbstractRevStartPlugin {

    private final Context context;

    public AbstractRevStartPlugin(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    public ArrayList<Class> getViewsPluginServices() {
        ArrayList<Class> views = new ArrayList<>();
        return views;
    }

    public View createView() {
        return null;
    }
}
