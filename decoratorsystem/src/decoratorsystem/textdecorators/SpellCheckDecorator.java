package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SpellCheckDecorator extends AbstractTextDecorator{
    private String result = null;
    private InputDetailsI inputADT = null;
    private AbstractTextDecorator atd = null;


    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

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

    @Override
    public String getResult(){
        return this.result;
    }
}