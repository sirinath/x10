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

import harness.x10Test;

/**
 * @author bdlucas 10/2008
 */

class XTENLANG_8 extends x10Test {

    class It implements Iterator[int] {
        public def hasNext(): boolean { throw new RuntimeException(); }
        public def next(): int { throw new RuntimeException(); }
        public def remove(): void { throw new RuntimeException(); }
    }

    public def run(): boolean {
        return true;
    }

    public static def main(Array[String](1)) {
        new XTENLANG_8().execute();
    }
}
