package com.cat.facts.ui;

import com.cat.facts.facts.CatFactProvider;
import com.cat.facts.facts.CatFactProviderWebAPIImpl;
import com.cat.facts.facts.CouldNotRetrieveCatFactException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *  This class provides the user with an interface via the command line.
 * @author Steve
 */
public class CommandLineUI {
        
    public static void main (String[] args) throws CouldNotRetrieveCatFactException {
        CommandLine cmd;
        Options options = new Options();
        CommandLineParser parser = new BasicParser();
        
        loadCLIOptions(options);
        
        
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            System.out.println("Error parsing command line arguments: \n" 
                    + ex.getMessage());
            return;
        }
        
       if(cmd.hasOption("m")) {
           System.out.println(cmd.getOptionValue("m"));
       } else {
           System.out.println("No Message provided.");
       }
       
        CatFactProvider catFactProvider = new CatFactProviderWebAPIImpl();
       
        System.out.println(catFactProvider.getCatFact());
        
    }

    /**
     * Establish the available command line options, and add them to the passed
     * in Options collection. 
     * @param pOptions Collection of options
     */
    private static void loadCLIOptions(Options pOptions)
    {
        Option help = new Option("help", "print this message");
        Option message = new Option("m", true, "Text a custom message");
        
        pOptions.addOption(help);
        pOptions.addOption(message);
    }
}
