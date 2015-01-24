package com.cat.facts.facts;

/**
 * Exception to be thrown when a cat fact can not be retrieved.
 *
 * @author Steve
 */
public class CouldNotRetrieveCatFactException extends Exception {

    public CouldNotRetrieveCatFactException() {
    }

    public CouldNotRetrieveCatFactException(String message) {
        super(message);
    }

}
