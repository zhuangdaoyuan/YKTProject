#include <jni.h>
#include <string>

extern "C" JNIEXPORT
jstring JNICALL
Java_com_mm_zdy_yktproject_JNIUtil_fromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
 }

