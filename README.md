# CatFactTexter
Java command line application for texting cat facts using google voice.

This application consumes cat facts in JSON format from the web api at http://catfacts-api.appspot.com/
and then uses the google voice to text them out.


java -jar CatFactTexter-1.0-jar-with-dependencies.jar -help
usage: CatFactTexter

 -e <arg>   Login password stored in environment variable
 
 -help      Print this message
 
 -m <arg>   Text a custom message
 
 -r <arg>   Recipient's phone number
 
 -u <arg>   Username used to login into the sending service

