package decoratorsystem.decorators;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;

public class MostFrequentWordDecorator extends AbstractTextDecorator{
    private String result = null;
    private InputDetailsI inputADT = null;
    private AbstractTextDecorator atd = null;

    public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

    @Override
    public void processInputDetails(){
        boolean fullStop = false;
        String mostFreq = this.inputADT.getMostFrequent();
        String word = this.inputADT.getNextWord();
        while (word != null){
            if (word.contains("."))
                fullStop = true;
            word = word.replace(".", "");

            if (word.toLowerCase().equals(mostFreq))
                word = "MOST_FREQUENT_"+word+"_MOST_FREQUENT";

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
        String temp = "--------MostFrequentWordDecorator--------\n"+this.result+"\n--------END--------\n";
        writeLog(temp);

        // Forward to the next decorator, if any.
        if (this.atd != null) {
            this.atd.processInputDetails();
        }
    }

    public void writeLog(String text){
        try{
            File logFile = new File("log.txt");
            if (logFile.createNewFile())
                System.out.println("log File created");
            else
                new FileWriter(logFile, false).close();
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