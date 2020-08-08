package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SentenceDecorator extends AbstractTextDecorator{
    private String result = null;
    private InputDetailsI inputADT = null;
    private AbstractTextDecorator atd = null;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

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