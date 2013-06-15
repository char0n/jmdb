/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.codescale.util.imdb;

/**
 *
 * @author char0n
 */
public class JmdbException extends Exception {

    public JmdbException(Throwable cause) {
        super(cause);
    }

    public JmdbException(String message, Throwable cause) {
        super(message, cause);
    }

    public JmdbException(String message) {
        super(message);
    }
}
