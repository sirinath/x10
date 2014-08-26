/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

package apgas;

/**
 * A {@link BadPlaceException} is thrown when attempting to reference a
 * {@link Place} that doesn't exist or when attempting to dereference a
 * {@link apgas.util.GlobalRef} that is not defined {@code here}.
 */
public class BadPlaceException extends RuntimeException {
  private static final long serialVersionUID = 8639251079580877933L;

  /**
   * Constructs a new {@link BadPlaceException}.
   */
  public BadPlaceException() {
  }
}
