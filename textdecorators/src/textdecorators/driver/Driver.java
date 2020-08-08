package textdecorators.driver;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;

import textdecorators.adt.InputDetails;
import textdecorators.adt.InputDetailsI;
import textdecorators.decorators.AbstractTextDecorator;
import textdecorators.decorators.SentenceDecorator;
import textdecorators.decorators.SpellCheckDecorator;
import textdecorators.decorators.KeywordDecorator;
import textdecorators.decorators.MostFrequentWordDecorator;



/**
 * @author John Doe
 *
 */
public class Driver {

	private static final int REQUIRED_ARGS = 5;
    public static String[] fileNames;


    /** Method to validate the command line args
     * @exception None
     * @return void
     */
    private static void validateInputs(String[] args){

        String[] defaults = {"input", "misspelled", "keywords", "output", "debug"};

        if (args.length != REQUIRED_ARGS) {
            System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_ARGS);
            System.exit(0);
        }

        for (int i = 0; i < 5; i++){
            if (args[i].equals(defaults[i])){
                System.err.printf("Error: Incorrect input file.");
                System.exit(0);
            }
            else
                System.out.println("Correct " + defaults[i] + " file :" + args[i]);
        }
    }


    /** Method to perform the tasks
     * @exception None
     * @return void
     */
	private static void executeProcess(String[] fileNames){
        try{
            InputDetailsI inputADT = new InputDetails(fileNames);

            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputADT);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputADT);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputADT);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputADT);
            mostFreqWordDecorator.processInputDetails();

            System.out.println(sentenceDecorator.getResult());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /** Main method to of driver code
     * @exception None
     * @return void
     */
	public static void main(String[] args) throws Exception {

        validateInputs(args);
		executeProcess(args);
	}
}
