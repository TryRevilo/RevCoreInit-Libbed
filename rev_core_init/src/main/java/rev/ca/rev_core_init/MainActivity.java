package rev.ca.rev_core_init;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevSQLiteLib;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers.RevDbSet;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckConnectivity;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_local_server.RevLocalServer;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers.RevPicturesFileWalker;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers.RevVideoFilesFileWalker;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevPageView;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_lib_gen_functions.RevReqPerms;
import rev.ca.rev_lib_sqlite.RevNativeLibWrapperSQLite3;
import rev.ca.rev_v8.RevNativeLibWrapper;
import rev.ca.revlibpersistence.rev_persistence.rev_services.RevConstantineMake;
import rev.ca.revlibviews.rev_core_layouts.RevConstantineViews;

public class MainActivity extends FragmentActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    RevPersLibCreate revPersLibCreate = new RevPersLibCreate();

    RevCheckConnectivity revCheckConnectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new RevSQLiteLib();

        REV_SESSION_SETTINGS.setRevServerIPAddress("http://192.168.0.10");
        REV_SESSION_SETTINGS.setRevRemoteServer(REV_SESSION_SETTINGS.getRevServerIPAddress() + ":3000");
        REV_SESSION_SETTINGS.setRevRemoteImageFilesRoot(REV_SESSION_SETTINGS.getRevServerIPAddress() + "/rev_wip/rev_node/rev_uploads");

        revPersLibCreate.initRevDb(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        revPersLibCreate.revTablesInit();

        RevLibGenConstantine.setRevContext(MainActivity.this);
        RevLibGenConstantine.setMainActivityClass(this.getClass());

        RevLibGenConstantine.initRevLibGenConstantine();
        RevConstantineViews.initRevConstantineViews();
        RevConstantineMake.initRevConstantineMakeServices();

        RevReqPerms.requestPermission();

        RevPersConstantine.initRevPersConstantine();

        new RevDbSet();

        new Thread(() -> {
            List<String> list = Arrays.asList(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString());

            for (String path : list) {
                RevPicturesFileWalker.walk(path);
            }
        }).start();

        /** LOAD VIDEO FILES **/

        new Thread(() -> {
            List<String> list = Arrays.asList(
                    Environment.getExternalStorageDirectory().getAbsolutePath());

            for (String path : list) {
                RevVideoFilesFileWalker.walk(path);
            }
        }).start();

        adjustFontScale(getResources().getConfiguration());

        revCheckConnectivity = new RevCheckConnectivity(new RevVarArgsData(this));

        RevLibGenConstantine.REV_CONN_SOCKET = 1024;
        new RevLocalServer(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Log.v(RevLangStrings.REV_TAG, "Build.CPU_AB >>> " + Build.SUPPORTED_ABIS[0]);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(revCheckConnectivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(revCheckConnectivity, filter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private static final int REQUEST_WRITE_PERMISSION = 786;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            this.initStatusBar();
            setContentView(RevPageView.getRevPageView());
            RevPersConstantine.REV_INIT_DIRS();
        } else {
            RevReqPerms.requestPermission();
        }
    }

    private void initStatusBar() {
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintColor(ContextCompat.getColor(this, R.color.teal_dark));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(RevLibGenConstantine.REV_CONTEXT, R.color.teal_dark));
        }
    }

    public void adjustFontScale(Configuration configuration) {
        if (configuration.fontScale > 0.9) {
            Log.v("MyApp", "fontScale=" + configuration.fontScale); //Custom Log class, you can use Log.w
            Log.v("MyApp", "font too big. scale down..."); //Custom Log class, you can use Log.w
            configuration.fontScale = (float) 0.9;
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
    }

    /**
     * SHORTCUT ICON
     **/

    private void shortcutAdd(String name, int number) {

        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);

        // Create bitmap with number in it -> very default. You probably want to give it a more stylish look
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(0xFF808080); // gray
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        new Canvas(bitmap).drawText("" + number, 50, 50, paint);
        ImageView revIcon_IV = new ImageView(RevLibGenConstantine.REV_CONTEXT);
        revIcon_IV.setImageBitmap(bitmap);

        // Decorate the shortcut
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);

        // Inform launcher to create shortcut
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }

    private void shortcutDel(String name) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);

        // Decorate the shortcut
        Intent delIntent = new Intent();
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // Inform launcher to remove shortcut
        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(delIntent);
    }
}

