package rev.ca.rev_lib_sqlite;

public class RevNativeLibWrapperSQLite3 {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("sqlite3-native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    public native String revLibVersion();
}
