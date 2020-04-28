/*
 * @(#)ServletException.java	1.19 00/02/02
 *
 * Copyright 1994-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */

package javax.servlet;

/**
 * Signals that a servlet exception of some sort has occurred. This
 * class is the general class of exceptions produced by failed or
 * interrupted servlet operations.
 *
 * @author  Rijk van Haaften
 * @version 1.19, 02/02/00
 * @see     java.io.InputStream
 * @see     java.io.OutputStream
 * @since   JDK1.0
 */
public class ServletException extends Exception {
    /**
     * Constructs an <code>ServletException</code> with <code>null</code>
     * as its error detail message.
     */
    public ServletException() {
        super();
    }

    /**
     * Constructs an <code>ServletException</code> with the specified detail
     * message. The error message string <code>s</code> can later be
     * retrieved by the <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */

    public ServletException(String s) {
        super();
    }
}
