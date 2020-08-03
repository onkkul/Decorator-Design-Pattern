package decoratorsystem.adt;

import decoratorsystem.visitor.VisitorI;

public interface Element{

    public void accept(VisitorI visitor);

}