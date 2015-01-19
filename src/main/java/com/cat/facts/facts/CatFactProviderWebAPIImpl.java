package com.cat.facts.facts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/**
 *  Retrieve cat facts from the api at http://catfacts-api.appspot.com/
 * @author Steve
 */
public class CatFactProviderWebAPIImpl implements CatFactProvider{
    
    private static final String API_URL = "http://catfacts-api.appspot.com/api/facts?number=1";

    /**
     * Retrieve a single cat fact.
     * @return String cat fact
     */
    @Override
    public String getCatFact() throws CouldNotRetrieveCatFactException
    {
        String catFact = null;
        
        try {
            catFact = parseFactFromRawJSONString(getRawJSONFromAPI());
        } catch (IOException ex) {
            throw new CouldNotRetrieveCatFactException(ex.getMessage());
        }
        
        return catFact;
    }
    
    /**
     * Connect to api and retrieve raw JSON results.
     * @return String raw json
     * @throws IOException 
     */
    private String getRawJSONFromAPI() throws IOException
    {
        URL url = new URL(API_URL);
        URLConnection connection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        String rawJSON = "";
        
        while((inputLine = bufferedReader.readLine()) != null)
        {
            rawJSON += inputLine;
        }
        
        bufferedReader.close();
        
        return rawJSON;
    }
    
    private String parseFactFromRawJSONString(String rawJSONString)
    {
        JSONObject jsonObj = new JSONObject(rawJSONString);
        return jsonObj.getJSONArray("facts").getString(0);
    }
    
}
