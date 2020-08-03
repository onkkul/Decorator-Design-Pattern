package decoratorsystem.adt;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Exception;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;


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



public class MyArrayList implements MyArrayListI{
 
    private ArrayList<MyArrayI> listOfArrays = new ArrayList<MyArrayI>();
    private int size = 2;

    /**  Constructor for MyArrayList
     *
     * @exception None
     *
     * @return void
     */
    public void MyArrayList(){
        this.listOfArrays.ensureCapacity(size);
    }


    /**  Get the array in the list
     *
     * @exception ArrayIndexOutOfBoundsException when provided index is out of range
     *
     * @return MyArrayI the array in the list
     */
    @Override
    public  MyArrayI getMyArray(int index)
        throws ArrayIndexOutOfBoundsException{
            if (index < size)
                return this.listOfArrays.get(index);
            else
                throw new ArrayIndexOutOfBoundsException("Invalid index for list of size 2");
    }

    /**  Store the array in the list at an index
     *
     * @exception ArrayIndexOutOfBoundsException when provided index is out of range
     *
     * @return MyArrayI the array in the list
     */
    @Override
    public void setMyArray(int index, MyArrayI myArray)
        throws ArrayIndexOutOfBoundsException{
            if (this.listOfArrays.size() < index)
                this.listOfArrays.set(index, myArray);
            else
                throw new ArrayIndexOutOfBoundsException("Invalid index for list of size 2");
    }


    /** Apped the array in the list
     *
     * @exception ArrayIndexOutOfBoundsException when arrayList size is out of limits
     *
     * @return MyArrayI the array in the list
     */
    @Override
    public void insert(MyArrayI myArray)
        throws ArrayIndexOutOfBoundsException{
            if (this.listOfArrays.size() < this.size)
                this.listOfArrays.add(myArray);
            else
                throw new ArrayIndexOutOfBoundsException("Invalid index for list of size 2");
    }


    /**  Accept the visitor for MyArrayList
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void accept(VisitorI visitor){
        visitor.visit(this);    
    }


    /**  Return the size of MyArrayList
     *
     * @exception None
     *
     * @return int
     */
    @Override
    public int getSize(){   
        return this.size;       
    }


    /**  toString method for debugging.
     *
     * @exception None
     *
     * @return String
     */
    @Override
    public String toString(){
        return "";
    }

    /** Empty finalize method
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void finalize(){   
        return;                 
    }
}