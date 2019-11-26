cmake_minimum_required(VERSION 3.4.1)

add_library( v8_base STATIC IMPORTED )
set_target_properties( v8_base PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/libs/${ANDROID_ABI}/libv8.a )

add_library( native-lib SHARED ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/native-lib.cpp)
target_include_directories( native-lib PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}/libs/include )

##  7.2.5.502.24

target_link_libraries( native-lib v8_base log )