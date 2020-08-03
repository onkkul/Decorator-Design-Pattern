package decoratorsystem.adt;

import java.util.HashMap;
import java.util.Arrays;

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

    private HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    private HashMap<String, String> keywords = new HashMap<String, String>();
    private HashMap<String, String> misSpelled = new HashMap<String, String>();

    private int maxFreq = 0;
    private String mostFreqWord = "";


    public InputDetails(String[] fileNames){
        this.inputFile = fileNames[0];
        this.misSpelledFile = fileNames[1];
        this.keyWordsFile = fileNames[2];

    }

    public void countFreq(String sentence){
        sentence = sentence.replace(".", "");
        words = sentence.split(" ");
        for(String word:words){
            int previous = this.frequency.getOrDefault(word.toLowerCase(), 0);
            previous = previous + 1;
            this.frequency.put(word.toLowerCase(), previous);

            if (previous >= this.maxFreq){
                this.maxFreq = previous;
                this.mostFreqWord = word;
            }
        }
    }

    public String[] getSentences(String prgrph){
        String[] allSentences = prgrph.split("(?<=[.!?])\\s*", -2);
        for (String eachSentence : allSentences){
            countFreq(eachSentence);
        }
        return sentences;
    }

    @Override
    public String getParagraph()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.inputFile);
        String line = fileProcessor.poll();
        while (line != null){
            this.paragraph = this.paragraph + line;
            line = fileProcessor.poll();
        }
        this.paragraph = this.paragraph.replace("null", "");

        this.sentences = getSentences(this.paragraph);
        return this.paragraph;
    }

    @Override
    public HashMap<String, String> getMisSpelled()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.misSpelledFile);
        String word = fileProcessor.poll();
        while (word != null){
            misSpelled.put(word, word);
            word = fileProcessor.poll();
        }

        return misSpelled;
    }

    @Override
    public HashMap<String, String> getKeyWords()throws
        FileNotFoundException, IOException{
        fileProcessor = new FileProcessor(this.keyWordsFile);
        String word = fileProcessor.poll();
        while (word != null){
            keywords.put(word, word);
            word = fileProcessor.poll();
        }
        return keywords;
    }

    @Override
    public String getMostFrequent(){
        return this.mostFreqWord + " " + Integer.toString(this.maxFreq);
    }

}