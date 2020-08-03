package decoratorsystem.adt;

import java.util.ArrayList;
import java.util.Collections;
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

public class MyArray implements MyArrayI{

    private int capacity = 10;
    private ArrayList<Integer> numbers = new ArrayList<Integer>();

    /** Empty Constructor for MyArray
     *
     * @exception None
     *
     * @return void
     */
    public void MyArray(){}


    /**  Return the size of MyArray object
     *
     * @exception None
     *
     * @return int
     */
    @Override
    public int getSize(){
        return this.numbers.size();    
    }

    /** Method to insert element at an index
     *  appends by default if index is not provided
     * @exception None
     *
     * @return void
     */
    @Override
    public void insert(int index, int element){
        if ((this.numbers.size() - this.capacity) >= 2)
            setSize(this.capacity + this.capacity/2);

        if (index == -1)
            this.numbers.add(element);
        else
            this.numbers.add(index, element);
        return;
    }

    /** Method to sort elements of array
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void sort(){
        Collections.sort(this.numbers);
    }

    /** Method to get all elements of array
     *
     * @exception None
     *
     * @return ArrayList<Integer> of all numbers
     */
    @Override
    public ArrayList<Integer> getElements(){
        return this.numbers;
    }


    /** Method to accept the visitor
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void accept(VisitorI visitor){
        visitor.visit(this);
    }


    // getters and setters
    /** Method to set size of array
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void setSize(int capacity){
        this.capacity = capacity;
        this.numbers.ensureCapacity(capacity);
    }

    /** Method to get element at index
     *
     * @exception ArrayIndexOutOfBoundsException when index is out of size
     *
     * @return int
     */
    @Override
    public int getElementAt(int index) 
        throws ArrayIndexOutOfBoundsException{
        if (index < getSize())
            return this.numbers.get(index);
        else
            throw new ArrayIndexOutOfBoundsException ("Index" + index + "out of bounds");  
    }

    /** Method to insert element at index
     *
     * @exception ArrayIndexOutOfBoundsException when index is out of size
     *
     * @return void
     */
    @Override
    public void setElementAt(int index, int element)
        throws ArrayIndexOutOfBoundsException{
        if (index < getSize())
            this.numbers.set(index, element);
        else
            throw new ArrayIndexOutOfBoundsException ("Index" + index + "invalid");        
    }

    /** Method to remove element at index
     *
     * @exception ArrayIndexOutOfBoundsException when index is out of size
     *
     * @return void
     */
    @Override
    public void removeAtIndex(int index)
        throws ArrayIndexOutOfBoundsException{
        if (index < getSize())
            this.numbers.remove(index);
        else
            throw new ArrayIndexOutOfBoundsException ("Index" + index + "out of bounds");  
    }


    // Methods related to Element
    /** Method to check if element is present in Array
     *
     * @exception None
     *
     * @return boolean (True if present)
     */
    @Override
    public boolean isMember(int element){
        return this.numbers.contains(element);    
    }

    /** Method to get index of an element
     *
     * @exception IllegalArgumentException when the element is absent
     *
     * @return int index of element
     */
    @Override
    public int getIndexOf(int element) throws IllegalArgumentException{
        if (isMember(element))
            return this.numbers.indexOf(element);
        else
            throw new IllegalArgumentException(element + "Not Found");
    }

    /** Method to remove element from array
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void removeElement(int element){
        removeAtIndex(getIndexOf(element));    
    }


    // Cloning methods
    /** Set method for Array
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void setArray(ArrayList<Integer> inputArray){
        this.capacity = inputArray.size();
        this.numbers = inputArray;
        return;
    }

    /** Method to Clone the array
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public MyArrayI clone(){
        MyArrayI temp = new MyArray();
        temp.setArray(getElements());
        return temp;
    }


    // Debugging methods
    /** toString() method for MyArray
     *
     * @exception None
     *
     * @return String
     */
    @Override
    public String toString(){
        return this.numbers.toString();    
    }

    /** Empty finalize method
     *
     * @exception None
     *
     * @return void
     */
    public void finalize(){
        return;
    }


}