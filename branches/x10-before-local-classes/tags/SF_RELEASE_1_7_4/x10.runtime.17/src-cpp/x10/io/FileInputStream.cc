#include <x10/io/FileInputStream.h>

using namespace x10aux;
using namespace x10::lang;
using namespace x10::io;

x10aux::ref<FileInputStream> FileInputStream::STANDARD_IN
    = new (x10aux::alloc<FileInputStream>()) FileInputStream(stdin);

RTT_CC_DECLS1(FileInputStream, "x10.io.FileReader.FileInputStream", NativeInputStream)

// vim:tabstop=4:shiftwidth=4:expandtab
