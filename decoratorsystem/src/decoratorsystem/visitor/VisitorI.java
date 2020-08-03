package decoratorsystem.visitor;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Collections;

import decoratorsystem.util.Results;
import decoratorsystem.util.FileProcessor;

import decoratorsystem.adt.MyArray;
import decoratorsystem.adt.MyArrayI;
import decoratorsystem.adt.MyArrayList;
import decoratorsystem.adt.MyArrayListI;

import decoratorsystem.visitor.VisitorI;
import decoratorsystem.visitor.CommonIntsVisitor;
import decoratorsystem.visitor.MissingIntsVisitor;
import decoratorsystem.visitor.PopulateMyArrayVisitor;

public interface VisitorI{

    public void visit(MyArrayI array);

    public void visit(MyArrayListI arrayList);

    public String toString();

}