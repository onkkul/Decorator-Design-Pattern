package textdecorators.decorators;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import textdecorators.adt.InputDetailsI;
import textdecorators.decorators.AbstractTextDecorator;

public class SentenceDecorator extends AbstractTextDecorator{
    private String result = null;
    private InputDetailsI inputADT = null;
    private AbstractTextDecorator atd = null;

    /** Constructor for SentenceDecorator class
     * @exception None
     */
    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

    /** Processor for SentenceDecorator class that decorates words
     * @exception None
     * @return void
     */
    @Override
    public void processInputDetails() {
        boolean newSentence = true;

        String word = this.inputADT.getNextWord();
        while (word != null){
            if (newSentence){
                word = "BEGIN_SENTENCE__" + word;
                newSentence = false;
            }
            if (word.contains(".")){
                word = word.replace(".", "__END_SENTENCE.");
                newSentence = true;
            }
            this.inputADT.writeNextWord(word, false);
            word = this.inputADT.getNextWord();
        }
        if (word == null)
            this.inputADT.writeNextWord("", true);

        this.result = this.inputADT.getParagraph();
        String temp = "--------SentenceDecorator--------\n"+this.result+"\n--------END--------\n";
        writeLog(temp);
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