package textdecorators.decorators;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import textdecorators.adt.InputDetailsI;
import textdecorators.decorators.AbstractTextDecorator;

public class SpellCheckDecorator extends AbstractTextDecorator{
    private String result = null;
    private InputDetailsI inputADT = null;
    private AbstractTextDecorator atd = null;

    /** Constructor for SpellCheckDecorator class
     * @exception None
     */
    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

    /** Processor for SpellCheckDecorator class that decorates words
     * @exception None
     * @return void
     */
    @Override
    public void processInputDetails() {
        boolean fullStop = false;
        boolean mostFreq = false;
        boolean keyword = false;
        List<String> misSpelled = this.inputADT.getMisSpelled();

        String word = this.inputADT.getNextWord();
        while (word != null){
            if (word.contains("."))
                fullStop = true;
            if (word.contains("MOST_FREQUENT"))
                mostFreq = true;
            if (word.contains("KEYWORD"))
                keyword = true;

            word = word.replace(".", "");
            word = word.replace("MOST_FREQUENT_", "");
            word = word.replace("_MOST_FREQUENT", "");
            word = word.replace("KEYWORD_", "");
            word = word.replace("_KEYWORD", "");

            if (misSpelled.contains(word))
                word = "SPELLCHECK_"+word+"_SPELLCHECK";

            if (keyword){
                word = "KEYWORD_"+word+"_KEYWORD";
                keyword = false;
            }

            if (mostFreq){
                word = "MOST_FREQUENT_" + word + "_MOST_FREQUENT";
                mostFreq = false;
            }

            if (fullStop){
                word = word + ".";
                fullStop = false;
            }

            this.inputADT.writeNextWord(word, false);
            word = this.inputADT.getNextWord();
        }
        if (word == null)
            this.inputADT.writeNextWord("", true);

        this.result = this.inputADT.getParagraph();
        String temp = "--------SpellCheckDecorator--------\n"+this.result+"\n--------END--------\n";
        writeLog(temp);

        // Forward to the next decorator, if any.
        if (null != atd) {
            atd.processInputDetails();
        }
    }

    /** Write the processesed result
     * @exception IOException if failed to write result
     * @return void
     */
    public void writeLog(String text){
        try{
            File logFile = new File("log.txt");
            if (logFile.createNewFile())
                System.out.println("log File created");

            FileWriter fileWritter = new FileWriter(logFile.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(text);
            bw.close();
        }
        catch (IOException failedToWriteLogFile){
            failedToWriteLogFile.printStackTrace();
        }
    }

    /** Get the result of this decorator
     * @exception None
     * @return String current result
     */
    @Override
    public String getResult(){
        return this.result;
    }
}