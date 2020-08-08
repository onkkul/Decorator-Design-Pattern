package decoratorsystem.driver;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;

import decoratorsystem.util.Results;
import decoratorsystem.util.FileProcessor;

import decoratorsystem.adt.InputDetails;
import decoratorsystem.adt.InputDetailsI;


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


            // System.out.println(inputADT.getParagraph());
            // System.out.println(inputADT.getMostFrequent());
            // System.out.println(Arrays.toString(inputADT.getMisSpelled()));
            // System.out.println(Arrays.toString(inputADT.getKeyWords()));
            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
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
