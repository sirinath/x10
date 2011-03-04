/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

//
// A list of interfaces agreed upon with the Toronto Parallel Debugger
// team for passing source program information to the C++ debugger
// back-end.
//
// Based on X10LineNumberDebug Design Document Version 2, Jan 25, 2010
// Updated as agreed with Roger Pett on Feb 11, 2010
//

#ifndef X10AUX_DEBUG_H
#define X10AUX_DEBUG_H

// Each generated C++ file is expected to have the following information:
//
// static const char _X10strings[] = {};
//   // All strings, concatenated, with intervening nulls.
//   // e.g., for the strings "aa", "bb", and "cc" , this variable would contain  "aa\0bb\0ccc".
// static const struct _X10sourceFile _X10sourceList[] = {};
//   // A list of X10 source files that contributed to the generation of the current C++ file.
// static const struct _CPPtoX10xref _CPPtoX10xrefList[] = {};
//   // A cross reference of C++ statements to X10 statements.
//   // Sorted by C++ file index and C++ source file line. 
//   // A line range is used to minimize the storage required.
// static const struct _X10methodName _X10methodNameList[] = {};
//   // A list of the X10 method names.
//   // Sorted by X10 method name.
// static const struct _MetaDebugInfo _MetaDebugInfo __attribute__((used)) = {
//     sizeof(struct _MetaDebugInfo),
//     X10_META_LANG,
//     sizeof(_X10strings),
//     sizeof(_X10sourceList),
//     0,
//     sizeof(_CPPtoX10xrefList),
//     sizeof(_X10methodNameList),
//     _X10strings,
//     _X10sourceList,
//     NULL,
//     _CPPtoX10xrefList,
//     _X10methodNameList
// };
//   // A meta-structure that refers to all of the above

#include <stdint.h>

struct _X10sourceFile
{
    uint32_t _numLines;    // The number of lines in the X10 source file
    uint32_t _stringIndex; // Index in _X10strings of the name of the X10 source file. 
};

struct _X10toCPPxref
{
    uint16_t _X10index;       // Index of X10 file name in _X10sourceList
    uint16_t _X10method;      // Index into _X10methodNameList of the X10 method (see Method Mapping)
    uint32_t _CPPindex;       // Index of C++ file name in _X10strings
    uint32_t _X10line;        // Line number of X10 source file line
    uint32_t _CPPfromLine;    // First line number of C++ line range
    uint32_t _CPPtoline;      // Last line number of C++ line range
};

struct _CPPtoX10xref
{
    uint16_t _X10index;       // Index of X10 file name in _X10sourceList
    uint16_t _X10method;      // Index into _X10methodNameList of the X10 method (see Method Mapping)
    uint32_t _CPPindex;       // Index of C++ file name in _X10strings
    uint32_t _X10line;        // Line number of X10 line
    uint32_t _CPPfromLine;    // First line number of C++ line range
    uint32_t _CPPtoLine;      // Last line number of C++ line range
};

struct _X10methodName
{
    uint32_t _x10class;          // Index of the X10 containing class name in _X10strings
    uint32_t _x10method;         // Index of the X10 method name in _X10strings
    uint32_t _x10returnType;     // Index of the X10 return type in _X10strings
    uint64_t _x10args;           // A pointer to a string that contains binary encodings of the
                                 // argument indices.  Each group of 4 bytes represents the index
                                 // of the corresponding argument in _X10strings
    uint32_t _cppClass;          // Index of the C++ class name in _X10strings
    uint16_t _x10argCount;       // The number of X10 arguments
    uint16_t _lineIndex;         // Index into _CPPtoX10xrefList of the first line of the method
};

struct _X10LocalVarMap
{
	uint32_t _x10name;			// Index of the X10 variable name in _X10strings
	uint32_t _x10type;     		// Index of the X10 type in _X10strings
	uint32_t _cppName;			// Index of the C++ variable name in _X10strings
	uint32_t _startLineIndex;   // Index into _CPPtoX10xrefList of the line where the variable is created
	uint32_t _endLineIndex;     // Index into _CPPtoX10xrefList of the last line where the variable is still in scope
};

// The _X10TypeMap and extenders of it are used in the member variable mappings
struct _X10TypeMap
{
  uint32_t _x10type; // Classification of this type
  // The details of the type follow..
};

struct _X10TypeMember : public _X10TypeMap
{
  uint32_t _x10MemberName; // Index of the X10 member name in _X10strings
  uint32_t _x10MemberOffset; // Offset of the member within the X10 class
};

struct _X10ClassMap : public _X10TypeMap
{
  uint32_t _x10name; // Index of the X10 class type name in _X10 strings
  uint32_t _x10ClassSize; // number of bytes in the class
  uint32_t _x10ClassMemberCount; // number of members in the class
  _X10TypeMember* _x10members; // pointer to an array of individual member types
};

struct _X10ClosureMap : public _X10ClassMap
{
  uint32_t _x10StartLine; // the start line of the closure definition in the X10 source
  uint32_t _x10EndLine; // the end line of the closure definition in the X10 source
};

struct _X10RefMap : public _X10TypeMap
{
  uint32_t _x10ReferredType; // type number of the referred type
};

struct _X10TypedefMap : public _X10RefMap
{
  uint32_t _x10Name; // Offset to the name of the typedef in _X10strings
};

struct _X10ArrayMap : public _X10TypeMap
{
  uint32_t _x10ElementCount; // Number of elements in the array
  uint32_t _x10ElementType;  // type number of the array elements
};



enum _MetaLanguage {
  X10_META_LANG = 0    // Metalanguage 0 is X10
};

#ifndef NULL
#define NULL ((void*)0)
#endif

struct _MetaDebugInfo_t {
  unsigned short structSize;      // size of this structure
  unsigned char metalanguage;     // language (allows the use of this technique for other languages)
  unsigned char debugVersion;     // version of this structure (so we can change it in future)

  // The remainder of this structure is language/version specific
  unsigned x10stringSize;         // the size in bytes of the string table (including the trailing NUL)
  unsigned x10sourceListSize;     // the size in bytes of the X10 source list
  unsigned x10toCPPlistSize;      // the size in bytes of the X10->C++ cross reference
  unsigned cPPtoX10xrefListSize;  // the size in bytes of the C++->X10 cross reference
  unsigned x10methodNameListSize; // the size in bytes of the X10 method name mapping list
// TODO - uncomment these two lines once the compiler starts generating them.
//  unsigned x10localVarListSize;   // the size in bytes of the X10 local variable name mapping list
//  unsigned x10typeListSize;       // the size in bytes of the X10 type mapping list

  const char*                  x10strings;        // The string table
  const struct _X10sourceFile* x10sourceList;     // The list of X10 source files
  const struct _X10toCPPxref*  x10toCPPlist;      // The X10->C++ cross reference
  const struct _CPPtoX10xref*  cPPtoX10xrefList;  // The C++->X10 cross reference
  const struct _X10methodName* x10methodNameList; // The method name mapping list
// TODO - uncomment these two lines once the compiler starts generating them.
//  const struct _X10LocalVarMap* x10localVarList;  // The local variable name mapping list
//  const struct _X10TypeMap*    x10typeList;		  // The type mapping list
};

//extern void _X10_Entry_Hook();     // A hook at the start of every X10 method.
//extern void _X10_Exit_Hook();      // A hook at the end of every X10 method.
extern void _X10_STATEMENT_HOOK();   // A hook at the start of every X10 executable statement.
                                     // Follows any method start hook, and precedes any method end hook.

#endif //X10AUX_DEBUG_H
// vim:tabstop=4:shiftwidth=4:expandtab
