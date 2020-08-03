package decoratorsystem.util;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;


public class Results implements FileDisplayInterface, StdoutDisplayInterface{

    private File outputFile1;
    private File outputFile2;
    private String commonLine;
    private String missingLine;
    private BufferedWriter outputWriter1;
    private BufferedWriter outputWriter2;

    /** Constructor for Results class, initializes empty output file
     * 
     * @exception IOException
     *
     * @return void
     */
    public Results(String outputFile1, String outputFile2){
        try {
            
            this.outputFile1 = new File(outputFile1);
            this.outputFile2 = new File(outputFile2);

            if (this.outputFile1.createNewFile()) {
                System.out.println("File created: " + this.outputFile1.getName());
            }
            else {
                new FileWriter(this.outputFile1, false).close();
            }

            if (this.outputFile2.createNewFile()) {
                System.out.println("File created: " + this.outputFile2.getName());
            }
            else {
                new FileWriter(this.outputFile2, false).close();
            }

        }
        catch (IOException fileCreationError) {
          fileCreationError.printStackTrace();
        } 
    }


    /** Stores each single line for later persistance.
     * 
     * @exception None
     *
     * @return void
     */
    public void writeLine(String line, String file){
        if (file == "common")
            this.commonLine = this.commonLine + line + "\n";
        if (file == "missing"){
            this.missingLine = this.missingLine + line + "\n";
        }
    }

    /** Writes results in the commonintsout.txt file
     * 
     * @exception IOException
     *
     * @return
     */
    @Override
    public void writeCommonIntsFile(){
        try{
            this.outputWriter1 = new BufferedWriter(new FileWriter(this.outputFile1, true));
            this.outputWriter1.write(this.commonLine);
            this.outputWriter1.close();
        }

        catch(IOException failedToWriteFile){
            failedToWriteFile.printStackTrace();
        }
        return;
    }

    /** Writes common results to the display
     * 
     * @exception None
     *
     * @return void
     */
    @Override
    public void displayCommonIntsFile(){
        System.out.println(this.commonLine);
    }

    /** Writes results in the missingintsout.txt file
     * 
     * @exception IOException
     *
     * @return
     */
    @Override
    public void writeMissingIntsFile(){
        try{
            this.outputWriter2 = new BufferedWriter(new FileWriter(this.outputFile2, true));
            this.outputWriter2.write(this.missingLine);
            this.outputWriter2.close();
        }

        catch(IOException failedToWriteFile){
            failedToWriteFile.printStackTrace();
        }
        return;
    }

    /** Writes missing results to display
     * 
     * @exception IOException
     *
     * @return
     */
    @Override
    public void displayMissingIntsFile(){
        System.out.println(this.missingLine);
    }

    /** Wrapper function to write results
     * 
     * @exception None
     *
     * @return void
     */
    public void persistResult(){
        this.commonLine = this.commonLine.replace("null", "");
        this.missingLine = this.missingLine.replace("null", "");

        writeCommonIntsFile();
        displayCommonIntsFile();
        
        displayMissingIntsFile();
        writeMissingIntsFile();
    }
}