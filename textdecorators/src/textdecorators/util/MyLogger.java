// FIXME: replace XYZ with the package name for the assignment
package textdecorators.util;

public class MyLogger{

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {
        NONE, DRIVER, FILE_PROCESSOR, INPUTDETAILS,
        SENTENCEDECORATOR, SPELLCHECKDECORATOR, KEYWORDDECORATOR,
        MOSTFREQUENTWORDDECORATOR
    };

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 2: debugLevel = DebugLevel.DRIVER;                     break;
            case 1: debugLevel = DebugLevel.FILE_PROCESSOR;             break;
            case 3: debugLevel = DebugLevel.INPUTDETAILS;               break;
            case 4: debugLevel = DebugLevel.SENTENCEDECORATOR;          break;
            case 5: debugLevel = DebugLevel.SPELLCHECKDECORATOR;        break;
            case 6: debugLevel = DebugLevel.KEYWORDDECORATOR;           break;
            default: debugLevel = DebugLevel.MOSTFREQUENTWORDDECORATOR; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
    debugLevel = levelIn;
    }

    public static void writeMessage(String message, DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}