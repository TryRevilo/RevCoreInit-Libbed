#include <jni.h>
#include <sqlite3.h>
#include <cstring>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_rev_ca_rev_1lib_1sqlite_RevNativeLibWrapperSQLite3_revLibVersion(JNIEnv *env, jobject instance) {
    const char *returnValue = sqlite3_libversion();

    std::string revRetBuf_Str(returnValue);
    revRetBuf_Str.append(" <<<");

    return env->NewStringUTF(revRetBuf_Str.c_str());
}



