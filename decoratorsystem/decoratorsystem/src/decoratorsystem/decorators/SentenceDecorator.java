package decoratorsystem.decorators;

import decoratorsystem.decorators.AbstractTextDecorator;
import decoratorsystem.adt.InputDetails;

public class SentenceDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd = null;
    private InputDetails id = null;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
    }
    @Override
    public void processInputDetails() {
        // Decorate input details.

        // Forward to the next decorator, if any.
        // if (null != atd) {
        //     atd.processInputDetails();
        // }
    }
}