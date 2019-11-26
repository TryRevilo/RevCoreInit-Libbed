package rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RevVideoFilesFileWalker {

    public static List<String> getFiles() {
        return files;
    }

    private static List<String> files = new ArrayList<>();

    public static void walk(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        for (File file : list) {
            if (file.isDirectory()) {
                walk(file.getAbsolutePath());
            } else if (file.getAbsolutePath().contains(".mp4")) {
                files.add(file.getAbsolutePath());
            }
        }
    }
}
