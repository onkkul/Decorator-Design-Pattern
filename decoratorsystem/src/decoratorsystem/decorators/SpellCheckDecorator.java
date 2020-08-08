package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class SpellCheckDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd = null;
    private InputDetailsI inputADT = null;

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

        System.out.println(this.inputADT.getParagraph());
        // Forward to the next decorator, if any.
        if (null != atd) {
            atd.processInputDetails();
        }
    }
}