package decoratorsystem.adt;

import java.util.HashMap;

public class InputDetails implements InputDetailsI{
    private String inputFile;
    private String missSpelledFile;
    private String keyWordsFile;
    private HashMap<String, Integer>count = new HashMap<String, Integer>();

    public InputDetails(String[] fileNames[]){
        this.inputFile = fileNames[0];
        this.missSpelledFile = fileNames[1];
        this.keyWordsFile = fileNames[2];
    }


    public boolean isInteger(String s){
        try {
            Integer.parseInt(s);
        }
        catch(NumberFormatException invalidNumber){
            return false;
        }
        catch(NullPointerException invalidNumber){
            return false;
        }
        return true;
    }


    public void validateInput(String sentense)throws
        IllegalArgumentException{
        String[] words = sentense.split(" ")
        for(String word:words){
            if isInteger(word){
                throw new IllegalArgumentException("Sentense Contains integers");
            }

            int previous = count.getOrDefault(word, 0);
            this.count.put(word, previous++);
        }
    }



}