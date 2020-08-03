package decoratorsystem.visitor;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileReader;
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


public class PopulateMyArrayVisitor implements VisitorI{

    private int value;
    private Results result = null;
    private String fileName = null;
    private MyArray array = null;
    private MyArrayListI listOfArrays = null;
    private FileProcessor fileProcessor = null;

    /** Constructor for MissingIntsVisitor
     *  Stores reference to the MyArrayList
     * @exception None
     *
     * @return Void
     */
    public PopulateMyArrayVisitor(MyArrayListI listOfArrays){
        this.listOfArrays = listOfArrays;
    }

    /** Method to set the fileName that populates MyArrayI object
     * 
     * @exception None
     *
     * @return void
     */
    public void setFileName(String fileName){   
        this.fileName = fileName;    
    }

    /** Method to get the fileName that populated MyArrayI object
     * 
     * @exception None
     *
     * @return String
     */
    public String getFileName(){    
        return this.fileName;    
    }


    /** Method to get all the MyArrayI objects populated by visitor
     * 
     * @exception None
     *
     * @return MyArrayListI myArrayList of objects
     */
    public MyArrayListI getListOfArrays(){     
        return this.listOfArrays;   
    }

    /** validate Input  for the given array
     * 
     * @exception NumberFormatException when input is not an integer
     * @exception IllegalArgumentException when number < 0 or number > 99
     *
     * @return Void
     */
    public void validateInput(String line) throws 
        IllegalArgumentException, NumberFormatException{
            try{
                this.value = Integer.parseInt(line);
            }  
            catch (NumberFormatException invalidNumber){ 
                throw new NumberFormatException("Invalid Number :" + line);
            } 

            if ((this.value < 0) || (this.value > 99)){
                throw new IllegalArgumentException("Number out of range : " + line);
            }
            return;
    }

    /** Starts populating the array
     * @exception InvalidPathException On invalid path string.
     * @exception SecurityException On not having necessary read permissions to the input file.
     * @exception FileNotFoundException On input file not found.
     * @exception IOException On any I/O errors while reading lines from input file.
     * @exception ArrayIndexOutOfBoundsException when Index is invalid.
     * @return void
    */
    public void populateArray()throws InvalidPathException, 
        SecurityException, FileNotFoundException, IOException{
            try{
                this.fileProcessor = new FileProcessor(getFileName());
                this.array = new MyArray();
                String line = this.fileProcessor.poll();
                while(line != null){
                    validateInput(line);
                    this.array.accept(this);
                    line = this.fileProcessor.poll();
                }
            }
            catch (Exception fileAccessError){
                fileAccessError.printStackTrace();
            }

            listOfArrays.insert(this.array);
    }


    /** Visit method to visit given Array
     * 
     * @exception None
     *
     * @return Void
     */
    @Override
    public void visit(MyArrayI arrayToVisit){
        arrayToVisit.insert(-1, this.value);
    }


    /** Empty Visitor method to visit MyArrayList
     * 
     * @exception None
     *
     * @return Void
     */
    @Override
    public void visit(MyArrayListI listOfArrays){
        return;    
    }


    /** toString() method for PopulateMyArrayVisitor
     *
     * @exception None
     *
     * @return String of missing inputs
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
    public void finalize(){
        return;
    }
}