#include <jni.h>
#include <string>
#include "gif.h"

extern "C"
JNIEXPORT jlong JNICALL
Java_com_mm_zdy_yktproject_GifInfoHandle_openFile(JNIEnv *env, jobject instance, jstring path_) {
    const char *path = env->GetStringUTFChars(path_, 0);

    // TODO
    return  openFile(env,path_);
}


JNIEXPORT jlong JNICALL
Java_com_mm_zdy_yktproject_GifInfoHandle_renderFrame(JNIEnv *env, jobject instance,
                                                     jlong gifInfoPtr, jobject bitmap) {

    // TODO
    return renderFrame(env,gifInfoPtr,bitmap);

}

JNIEXPORT jint JNICALL
Java_com_mm_zdy_yktproject_GifInfoHandle_getWidth(JNIEnv *env, jobject instance, jlong *gifInfoPtr) {

    // TODO
    return getWidth(gifInfoPtr);
}

JNIEXPORT jint JNICALL
Java_com_mm_zdy_yktproject_GifInfoHandle_getHeight(JNIEnv *env, jobject instance,
                                                   jlong gifInfoPtr) {

    // TODO
    return getHeight(gifInfoPtr);

}