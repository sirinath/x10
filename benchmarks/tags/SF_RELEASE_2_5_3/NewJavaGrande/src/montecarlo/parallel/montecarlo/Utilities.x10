/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
package montecarlo.parallel.montecarlo;

/**
 * X10 port of montecarlo benchmark from Section 2 of x10 Grande Forum Benchmark Suite (Version 2.0).
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 *
 * Porting issues identified:
 * 1) Variables/fields starting with an upper case letter have to be renamed if not final (DEBUG)
 * 2) Remove x10 package structure
 * 3) Add declaration to extend x10.lang.Object
 */
final public class Utilities {
	public static val debug: Boolean = false;
	private static val className: String = "Utilities";

	/**
	 * Static method which behaves like the Unix `which' command.  OS
	 * specific dependencies are handled by the x10.lang.System
	 * properties.
	 *
	 * @param executable The executable to search for.
	 * @param pathEnv    The list of paths in which to search, in the style of the
	 *                   OS's PATH environment variable.
	 * @return           The full pathname of where the executable lives,
	 *                   or failing that the error message "<executable> not found.".
	 */
	public static def which(var executable: String, var pathEnv: String): String {
		var executablePath: String;
		var paths: Rail[String];

		paths = splitString(System.getProperty("path.separator"), pathEnv);
		for (var i: int = 0; i<paths.size; i++) {
			if (paths(i).length() > 0) {
				var pathFile: x10.io.File = new x10.io.File(paths(i));
				if (pathFile.isDirectory()) {
					var filesInDirectory: Rail[String];
					filesInDirectory = pathFile.list();
					for (var j: int = 0; j<filesInDirectory.size; j++) {
						if (debug) {
							Console.OUT.println("DBG: Matching "+filesInDirectory(j));
						}
						if (filesInDirectory(j).equals(executable)) {
							executablePath = paths(i)+System.getProperty("file.separator")+executable;
							return executablePath;
						}
					}
				} else {
					if (debug) {
						Console.OUT.println("DBG: path "+paths(i)+" is not a directory!");
					}
				}
			}
		} /* for i */
		executablePath = executable+" not found.";
		return executablePath;
	}

	/**
	 * Static method which behaves like the Perl join() function.
	 *
	 * @param joinChar    The character on which to join.
	 * @param stringArray The array of strings to join.
	 * @return            A string of the joined string array.
	 */
	public static def joinString(var joinChar: String, var stringArray: Rail[String]): String {
		return joinString(joinChar, stringArray, 0);
	}

	/**
	 * Static method which behaves like the Perl join() function.
	 *
	 * @param joinChar    The character on which to join.
	 * @param stringArray The array of strings to join.
	 * @param index       The array index on which to start joining.
	 * @return            A string of the joined string array.
	 */
	public static def joinString(var joinChar: String, var stringArray: Rail[String], var index: int): String {
		var methodName: String = "join";
		var tmpString: StringBuffer;

		var nStrings: int = x10.lang.reflect.Array.getLength(stringArray);
		if (nStrings <= index) {
			tmpString = new StringBuffer();
		} else {
			tmpString = new StringBuffer(stringArray(index));
			for (var i: int = (index+1); i < nStrings; i++) {
				tmpString.append(joinChar).append(stringArray(i));
			}
		}
		return tmpString.toString();
	}

	/**
	 * Static method which behaves like the Perl split() function.
	 *
	 * @param splitChar The character on which to split.
	 * @param arg       The string to be split.
	 * @return          A string array of the split string.
	 */
	public static def splitString(var splitChar: String, var arg: String): Rail[String] {
		var methodName: String = "split";

		var myArgs: Rail[String];
		var nArgs: int = 0;
		var foundIndex: int = 0;
		var fromIndex: int = 0;

		while ((foundIndex = arg.indexOf(splitChar, fromIndex)) > -1) {
			nArgs++;
			fromIndex = foundIndex+1;
		}
		if (debug) {
			Console.OUT.println("DBG "+className+"."+methodName+": "+nArgs);
		}
		myArgs = new Array[String](nArgs+1);
		nArgs = 0;
		fromIndex = 0;
		while ((foundIndex = arg.indexOf(splitChar, fromIndex)) > -1) {
			if (debug) {
				Console.OUT.println("DBG "+className+"."+methodName+": "+fromIndex+" "+foundIndex);
			}
			myArgs(nArgs) = arg.substring(fromIndex, foundIndex);
			nArgs++;
			fromIndex = foundIndex+1;
		}
		myArgs(nArgs) = arg.substring(fromIndex);
		return myArgs;
	}
}
