package textdecorators.adt;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import textdecorators.util.FileProcessor;

public class InputDetails implements InputDetailsI{
    private String inputFile;
    private String misSpelledFile;
    private String keyWordsFile;

    private Scanner scanner = null;
    private FileProcessor fileProcessor = null;

    private int maxFreq = 0;
    private String paragraph;
    private String result = "";
    private String mostFreqWord = "";

    private List<String> keywords = null;
    private List<String> misSpelled = null;
    private HashMap<String, Integer> frequency = new HashMap<String, Integer>();

    /** Constructor for InputDetails class
     * @exception FileNotFoundException when input files are not present
     * @exception IOException if unable to read input files
     */
    public InputDetails(String[] fileNames) throws
        FileNotFoundException, IOException{
            this.inputFile = fileNames[0];
            this.misSpelledFile = fileNames[1];
            this.keyWordsFile = fileNames[2];
            readInput();
            readKeyWords();
            readMisSpelled();
            this.scanner = new Scanner(this.paragraph);
    }

    /** Finds the most freq. word in the passage
     * @exception None
     * @return void
     */
    public void countFrequency(String word){
        word = word.replace(".", "");
        int previous = this.frequency.getOrDefault(word.toLowerCase(), 0);
        previous = previous + 1;
        this.frequency.put(word.toLowerCase(), previous);

        if (previous >= this.maxFreq){
            this.maxFreq = previous;
            this.mostFreqWord = word;
        }
    }


    /** Read input.txt file
     * @exception FileNotFoundException when file is not present
     * @exception IOException if unable to read file
     * @return void
     */
    @Override
    public void readInput()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.inputFile);
        this.paragraph = "";
        String word = fileProcessor.poll();
        while (word != null){
            countFrequency(word);
            this.paragraph = this.paragraph + word + " ";
            word = fileProcessor.poll();
        }
    }

    /** Read misspelled.txt file
     * @exception FileNotFoundException when file is not present
     * @exception IOException if unable to read file
     * @return void
     */
    @Override
    public void readMisSpelled()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.misSpelledFile);
        this.misSpelled = new ArrayList<String>();
        String word = fileProcessor.poll();
        while (word != null){
            this.misSpelled.add(word);
            word = fileProcessor.poll();
        }
    }


    /** Read keywords.txt file
     * @exception FileNotFoundException when file is not present
     * @exception IOException if unable to read file
     * @return void
     */
    @Override
    public void readKeyWords()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.keyWordsFile);
        this.keywords = new ArrayList<String>();
        String word = fileProcessor.poll();
        while (word != null){
            this.keywords.add(word);
            word = fileProcessor.poll();
        }
    }

    /** Get the most freq. word in the passage
     * @exception None
     * @return String most freq word
     */
    @Override
    public String getMostFrequent(){
        return this.mostFreqWord;
    }

    /** Get the list of misspelled words
     * @exception None
     * @return List list(mispelled words)
     */
    @Override
    public List<String> getMisSpelled(){
        return this.misSpelled;
    }

    /** Get the list of key words
     * @exception None
     * @return List list(key words)
     */
    @Override
    public List<String> getKeyWords(){
        return this.keywords;
    }

    /** Get the next word in the passage
     * @exception None
     * @return String
     */
    @Override
    public String getNextWord(){
        if (scanner.hasNext())
            return scanner.next();
        return null;
    }

    /** Write the in-place edited (decorated) word
     * @exception None
     * @return void
     */
    @Override
    public void writeNextWord(String word, boolean done){
        if (done){
            this.paragraph = this.result;
            this.result = null;
            scanner = new Scanner(this.paragraph);
        }
        else{
            this.result = this.result + word + " ";
        }
    }

    /** Get the passage
     * @exception None
     * @return String the passage
     */
    @Override
    public String getParagraph(){
        this.paragraph = this.paragraph.replace("null", "");
        return this.paragraph;
    }

    /** Set the passage
     * @exception None
     * @return void
     */
    @Override
    public void setParagraph(String paragraph){
        this.paragraph = paragraph;
    }
}