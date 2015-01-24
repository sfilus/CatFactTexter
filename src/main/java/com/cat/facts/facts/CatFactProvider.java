package com.cat.facts.facts;

/**
 * Interface defining classes capable of providing cat facts.
 *
 * @author Steve
 */
public interface CatFactProvider {

    /**
     * Return a cat fact.
     *
     * @return String cat fact
     * @throws com.cat.facts.facts.CouldNotRetrieveCatFactException
     */
    public String getCatFact() throws CouldNotRetrieveCatFactException;
}
