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

package x10.compiler;

import x10.lang.annotations.StatementAnnotation;
// pattern 0 is the default implementation
// pattern 1 : all async are in the same place as finish
// pattern 2: remote activities don't spawn other remote oens
// pattern 3: ateach with unique distribution, place= the number of places
public interface FinishAsync(arity:int,place:int,isLast:boolean,pattern:Int) extends StatementAnnotation { }
