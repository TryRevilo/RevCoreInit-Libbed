package rev.ca.rev_v8;

public class RevNativeLibWrapper {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native void initV8();

    public native String stringFromV8();

    public native String revCallJS();
}
