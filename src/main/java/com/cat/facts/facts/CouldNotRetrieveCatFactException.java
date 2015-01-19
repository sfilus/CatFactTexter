/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cat.facts.facts;

/**
 * Exception to be thrown when a cat fact can not be retrieved.
 * @author Steve
 */
public class CouldNotRetrieveCatFactException extends Exception{
    
    public CouldNotRetrieveCatFactException() {}
    
    public CouldNotRetrieveCatFactException(String message)
    {
        super(message);
    }
    
}
