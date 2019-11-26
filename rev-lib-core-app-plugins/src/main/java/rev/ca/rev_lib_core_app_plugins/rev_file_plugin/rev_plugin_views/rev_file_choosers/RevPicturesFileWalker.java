package rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers;

import android.graphics.BitmapFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RevPicturesFileWalker {

    public static List<String> getFiles() {
        return files;
    }

    private static List<String> files = new ArrayList<>();

    public static void walk(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                if (options.outWidth != -1 && options.outHeight != -1) {
                    files.add(f.getAbsolutePath());
                }
            }
        }
    }
}
