package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class KeywordDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd = null;
    private InputDetailsI inputADT = null;

    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetailsI inputADT) {
        this.atd = atdIn;
        this.inputADT = inputADT;
    }

    @Override
    public void processInputDetails() {
        boolean fullStop = false;
        boolean mostFreq = false;

        List<String> keywords = this.inputADT.getKeyWords();
        String word = this.inputADT.getNextWord();
        while (word != null){
            if (word.contains("."))
                fullStop = true;
            if (word.contains("MOST_FREQUENT"))
                mostFreq = true;

            word = word.replace(".", "");
            word = word.replace("MOST_FREQUENT_", "");
            word = word.replace("_MOST_FREQUENT", "");

            if (keywords.contains(word.toLowerCase()))
                word = "KEYWORD_"+word+"_KEYWORD";

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

        System.out.println(this.inputADT.getParagraph());

        // // Forward to the next decorator, if any.
        if (this.atd != null) {
            this.atd.processInputDetails();
        }
    }
}