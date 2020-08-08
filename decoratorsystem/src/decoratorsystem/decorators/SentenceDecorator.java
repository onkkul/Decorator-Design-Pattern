package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetailsI;

public class SentenceDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd = null;
    private InputDetailsI inputADT = null;

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

        System.out.println(this.inputADT.getParagraph());
        // Decorate input details.

        // Forward to the next decorator, if any.
        // if (null != atd) {
        //     atd.processInputDetails();
        // }
    }
}