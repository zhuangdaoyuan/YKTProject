cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)
set(can_use_assembler TRUE)
enable_language(ASM)

set(SOURCES)

file(GLOB_RECURSE SOURCES "*.c")

if((${ANDROID_ABI} STREQUAL "armeabi") OR (${ANDROID_ABI} STREQUAL "armeabi-v7a") OR (${ANDROID_ABI} STREQUAL "x86_64") )
    list(APPEND SOURCES	memset.arm.S)
endif((${ANDROID_ABI} STREQUAL "armeabi") OR (${ANDROID_ABI} STREQUAL "armeabi-v7a") OR (${ANDROID_ABI} STREQUAL "x86_64"))

add_library(nativeLib SHARED ${SOURCES})

set(LIBS)
list(APPEND LIBS
	jnigraphics
    android
    GLESv2
    log
)

target_link_libraries(nativeLib ${LIBS})
