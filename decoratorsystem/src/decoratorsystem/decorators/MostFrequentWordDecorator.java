package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;

public class MostFrequentWordDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd = null;
    private InputDetailsI inputADT = null;

    public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

    @Override
    public void processInputDetails() {
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

        System.out.println(this.inputADT.getParagraph());

        // Forward to the next decorator, if any.
        if (this.atd != null) {
            this.atd.processInputDetails();
        }
    }
}