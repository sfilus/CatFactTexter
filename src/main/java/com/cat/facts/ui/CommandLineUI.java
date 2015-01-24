package com.cat.facts.ui;

import com.cat.facts.facts.CatFactProvider;
import com.cat.facts.facts.CatFactProviderWebAPIImpl;
import com.cat.facts.facts.CouldNotRetrieveCatFactException;
import com.cat.facts.senders.GoogleVoiceMessageSender;
import com.cat.facts.senders.MessageSender;
import java.io.IOException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class provides the user with an interface via the command line.
 *
 * @author Steve
 */
public class CommandLineUI {

    public static void main(String[] args) throws CouldNotRetrieveCatFactException {
        Options options = new Options();
        loadCLIOptions(options);

        CommandLine cmd;
        try {
            cmd = parseCLArguments(options, args);
        } catch (ParseException ex) {
            System.out.println("Could not parse command line arguments.");
            return;
        }

        if (cmd.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("CatFactTexter", options);
            return;
        }

        String username = getUserName(cmd);
        String password = getPassword(cmd);

        MessageSender sender;
        try {
            sender = new GoogleVoiceMessageSender(username, password);
        } catch (IOException ex) {
            System.out.println("Coult not connect to Google Voice account: " + username);
            return;
        }

        String recipient = getRecipient(cmd);
        String message = getMessage(cmd);

        try {
            sender.send(recipient, message);
        } catch (IOException ex) {
            System.out.println("Could not send message to " + recipient + ":" + message);
            return;
        }

        System.out.println("From Google voice account: " + username);
        System.out.println("To Recipient: " + recipient);
        System.out.println("Message: " + message);
    }

    /**
     * Establish the available command line options, and add them to the passed
     * in Options collection.
     *
     * @param pOptions Collection of options
     */
    private static void loadCLIOptions(Options pOptions) {
        Option help = new Option("help", "Print this message");
        Option message = new Option("m", true, "Text a custom message");
        Option userName = new Option("u", true, "Username used to login into the sending service");
        Option environmentPass = new Option("e", true, "Login password stored in environment variable");
        Option recipient = new Option("r", true, "Recipient's phone number");

        pOptions.addOption(help);
        pOptions.addOption(message);
        pOptions.addOption(userName);
        pOptions.addOption(environmentPass);
        pOptions.addOption(recipient);
    }

    /**
     * Parse the command line objects.
     *
     * @param options available options
     * @param args string array from command line
     * @return CommandLine object
     * @throws ParseException
     */
    private static CommandLine parseCLArguments(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        return parser.parse(options, args);
    }

    /**
     * Retrieve the username of the texting service.
     *
     * @param cmd CommandLine object
     * @return string
     */
    private static String getUserName(CommandLine cmd) {
        String username;
        if (cmd.hasOption("u")) {
            username = cmd.getOptionValue("u");
        } else {
            System.out.println("Enter your google voice username");
            username = System.console().readLine();
        }

        return username;
    }

    /**
     * Retrieve the password of the texting service.
     *
     * @param cmd
     * @return
     */
    private static String getPassword(CommandLine cmd) {
        String password;
        if (cmd.hasOption("e")) {
            password = System.getenv("CATFACT");
        } else {
            System.out.println("Enter your google voice password");
            char[] passwordChars = System.console().readPassword();
            password = new String(passwordChars);
        }
        return password;
    }

    /**
     * Retrieve the message to be texted.
     *
     * @param cmd CommandLine object
     * @return string
     * @throws CouldNotRetrieveCatFactException
     */
    private static String getMessage(CommandLine cmd) throws CouldNotRetrieveCatFactException {
        String message;
        if (cmd.hasOption("m")) {
            message = cmd.getOptionValue("m");
        } else {
            message = getCatFactFromProvider();
        }
        return message;
    }

    /**
     * Get the recipients phone number
     *
     * @param cmd CommandLine object
     * @return phone number
     */
    private static String getRecipient(CommandLine cmd) {
        String recipient;
        if (cmd.hasOption("r")) {
            recipient = cmd.getOptionValue("r");
        } else {
            System.out.println("Enter a recipients phone number");
            recipient = System.console().readLine();
        }
        return recipient;
    }

    /**
     * Retrieve a cat fact from web api provider
     *
     * @return string
     * @throws CouldNotRetrieveCatFactException
     */
    private static String getCatFactFromProvider() throws CouldNotRetrieveCatFactException {
        CatFactProvider provider = new CatFactProviderWebAPIImpl();
        return provider.getCatFact();
    }
}
