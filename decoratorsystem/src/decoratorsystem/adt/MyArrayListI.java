package decoratorsystem.adt;

import java.util.ArrayList;
import java.util.Collections;

import decoratorsystem.util.Results;
import decoratorsystem.util.FileProcessor;

import decoratorsystem.adt.MyArray;
import decoratorsystem.adt.MyArrayI;
import decoratorsystem.adt.MyArrayList;
import decoratorsystem.adt.MyArrayListI;

import decoratorsystem.visitor.VisitorI;

public interface MyArrayListI extends Element{
    
    public MyArrayI getMyArray(int index)
        throws ArrayIndexOutOfBoundsException;

    public void setMyArray(int index, MyArrayI myArray)
        throws ArrayIndexOutOfBoundsException;

    public void insert(MyArrayI myArray)
        throws ArrayIndexOutOfBoundsException;

    public int getSize();

    public String toString();

    public void finalize();
}