package textdecorators.adt;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface InputDetailsI{
    public void readInput() throws
        FileNotFoundException, IOException;

    public void readMisSpelled() throws
        FileNotFoundException, IOException;

    public void readKeyWords()throws
        FileNotFoundException, IOException;

    public String getMostFrequent();

    public List<String> getMisSpelled();

    public List<String> getKeyWords();

    public String getNextWord();
    public void writeNextWord(String word, boolean done);

    public String getParagraph();
    public void setParagraph(String paragraph);






}