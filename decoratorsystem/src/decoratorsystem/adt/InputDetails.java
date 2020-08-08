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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

import decoratorsystem.util.FileProcessor;


public class InputDetails implements InputDetailsI{
    private String inputFile;
    private String misSpelledFile;
    private String keyWordsFile;
    private FileProcessor fileProcessor = null;

    private String[] words;
    private String[] sentences;
    private String paragraph;

    private List<String> list;
    private String[] keywords = null;
    private String[] misSpelled = null;
    private HashMap<String, Integer> frequency = new HashMap<String, Integer>();

    private int maxFreq = 0;
    private String mostFreqWord = "";


    public InputDetails(String[] fileNames){
        this.inputFile = fileNames[0];
        this.misSpelledFile = fileNames[1];
        this.keyWordsFile = fileNames[2];

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
    public String getParagraph()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.inputFile);
        String word = fileProcessor.poll();
        while (word != null){
            System.out.println(word);
            countFrequency(word);
            this.paragraph = this.paragraph + " " + word;
            word = fileProcessor.poll();
        }
        this.paragraph = this.paragraph.replace("null", "");

        this.sentences = getSentences(this.paragraph);
        return this.paragraph;
    }

    @Override
    public String[] getMisSpelled()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.misSpelledFile);
        list = new ArrayList<String>();
        String word = fileProcessor.poll();
        while (word != null){
            list.add(word);
            word = fileProcessor.poll();
        }

        this.misSpelled = new String[list.size()];
        this.misSpelled = list.toArray(this.misSpelled);
        return this.misSpelled;
    }

    @Override
    public String[] getKeyWords()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.keyWordsFile);
        list = new ArrayList<String>();
        String word = fileProcessor.poll();
        while (word != null){
            list.add(word);
            word = fileProcessor.poll();
        }

        this.keywords = new String[list.size()];
        this.keywords = list.toArray(this.keywords);
        return this.keywords;
    }

    @Override
    public String getMostFrequent(){
        return this.mostFreqWord + " " + Integer.toString(this.maxFreq);
    }

}