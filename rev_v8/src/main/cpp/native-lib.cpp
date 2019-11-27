#include <jni.h>

#include <iostream>
#include <cstring>
#include <string>

#include "libplatform/libplatform.h"
#include <v8.h>
#include <android/log.h>
#include "rev-shell.h"

/* the following references need to be around somewhere,
 * either as global (not recommended), or in some object,
 * otherwise they'll get garbage collected by C++
 * and cause a segmentation fault crash
 */

std::unique_ptr<v8::Platform> platform;
v8::Isolate *isolate;
v8::Persistent<v8::Context> persistentContext;

extern "C"
JNIEXPORT void JNICALL
Java_rev_ca_rev_1v8_RevNativeLibWrapper_initV8(JNIEnv *env, jobject instance) {

    // Initialize V8.
    v8::V8::InitializeICU();
    platform = v8::platform::NewDefaultPlatform();
    v8::V8::InitializePlatform(&(*platform.get()));
    v8::V8::Initialize();

    // Create a new Isolate and make it the current one.
    v8::Isolate::CreateParams create_params;
    create_params.array_buffer_allocator = v8::ArrayBuffer::Allocator::NewDefaultAllocator();
    isolate = v8::Isolate::New(create_params);

    v8::Isolate::Scope isolate_scope(isolate);
    // Create a stack-allocated handle scope.
    v8::HandleScope handle_scope(isolate);

    // Create a new context.
    v8::Local<v8::Context> context = v8::Context::New(isolate);

    // attach the context to the persistent context, to avoid V8 GC-ing it
    persistentContext.Reset(isolate, context);
}

void revCallJSFile(v8::Isolate *isolate, v8::Local<v8::Context> context) {
    const char *revFilePath = "/storage/emulated/0/Download/my_js_file.js";
    v8::MaybeLocal<v8::String> source = ReadFile(isolate, revFilePath);

    // Compile the source code.
    v8::Local<v8::Script> script = v8::Script::Compile(context,
                                                       source.ToLocalChecked()).ToLocalChecked();

    // Run the script to get the result.
    v8::Local<v8::Value> result = script->Run(context).ToLocalChecked();

    // from v8 to cpp
    v8::String::Utf8Value str(isolate, result);
    std::string cppStr(*str);
}

void revCallJSFunc(v8::Isolate *isolate, v8::Local<v8::Context> context) {
    revCallJSFile(isolate, context);

    const char *test_function = "test_function";

    v8::Local<v8::String> name = v8::String::NewFromUtf8(
            isolate, "functionName", v8::NewStringType::kInternalized).ToLocalChecked();

    // from v8 to cpp
    v8::String::Utf8Value test_function_str(isolate, name);
    std::string cppStr_test_function_str(*test_function_str);

    v8::Local<v8::Value> obj = context->Global()->Get(context, name).ToLocalChecked();

    if (!obj->IsFunction()) {
        /* someone overwrote it, handle error */
    }

    v8::Local<v8::Function> my_function = v8::Local<v8::Function>::Cast(obj);

    v8::Handle<v8::Value> args[1];
    args[0] = v8::Number::New(isolate, 2.2);

    std::string cppArgsStr("World!");
    args[0] = v8::String::NewFromUtf8(isolate, cppArgsStr.c_str(), v8::String::kNormalString);

    v8::Handle<v8::Value> revResult = my_function->Call(my_function->CreationContext()->Global(), 1,
                                                        args);

    // from v8 to cpp
    v8::String::Utf8Value str(isolate, revResult);
    std::string cppStr(*str);

    __android_log_print(ANDROID_LOG_ERROR, "MyApp", ">> >ERR cppStr  %s", cppStr.c_str());

}

extern "C"
JNIEXPORT jstring JNICALL
Java_rev_ca_rev_1v8_RevNativeLibWrapper_stringFromV8(JNIEnv *env, jobject instance) {

    std::string hello = "Hello v8 from C++!\n";

    v8::Isolate::Scope isolate_scope(isolate);
    v8::HandleScope handle_scope(isolate);

    // Enter the context for compiling and running the hello world script.
    v8::Local<v8::Context> context = v8::Local<v8::Context>::New(isolate, persistentContext);
    v8::Context::Scope context_scope(context);

    // Create a string containing the JavaScript source code.
    v8::Local<v8::String> source = v8::String::NewFromUtf8(isolate,
                                                           "'Hello' + ', from Javascript!'",
                                                           v8::NewStringType::kNormal).ToLocalChecked();

    // Compile the source code.
    v8::Local<v8::Script> script = v8::Script::Compile(context, source).ToLocalChecked();

    // Run the script to get the result.
    v8::Local<v8::Value> result = script->Run(context).ToLocalChecked();

    // Convert the result to an UTF8 string and print it.
    v8::String::Utf8Value utf8(isolate, result);
    printf("%s\n", *utf8);
    hello += *utf8;
    hello += "\nv :  ";
    hello += v8::V8::GetVersion();

    revCallJSFunc(isolate, context);

    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_rev_ca_rev_1v8_RevNativeLibWrapper_revCallJS(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("HELLO WORLD!");
}




