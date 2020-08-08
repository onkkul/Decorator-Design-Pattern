package decoratorsystem.adt;

import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

import decoratorsystem.util.FileProcessor;


public class InputDetails implements InputDetailsI{
    private String inputFile;
    private String misSpelledFile;
    private String keyWordsFile;

    private Scanner scanner = null;
    private FileProcessor fileProcessor = null;

    private String paragraph;
    private String result = "";
    private String[] sentences;
    private String[] words;

    private List<String> keywords = null;
    private List<String> misSpelled = null;
    private HashMap<String, Integer> frequency = new HashMap<String, Integer>();

    private int maxFreq = 0;
    private String mostFreqWord = "";


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

    public String[] getSentences(String prgrph){
        String[] allSentences = prgrph.split("(?<=[.!?])\\s*", -2);
        return allSentences;
    }
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

        this.sentences = getSentences(this.paragraph);
    }


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

    @Override
    public String getMostFrequent(){
        return this.mostFreqWord;
    }


    @Override
    public List<String> getMisSpelled(){
        return this.misSpelled;
    }
    @Override
    public List<String> getKeyWords(){
        return this.keywords;
    }

    @Override
    public String getNextWord(){
        if (scanner.hasNext())
            return scanner.next();
        return null;
    }
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

    @Override
    public String getParagraph(){
        this.paragraph = this.paragraph.replace("null", "");
        return this.paragraph;
    }

    @Override
    public void setParagraph(String paragraph){
        this.paragraph = paragraph;
    }
}