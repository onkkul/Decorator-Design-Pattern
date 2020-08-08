package decoratorsystem.adt;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

public interface InputDetailsI{
    // public void setInputFile(String fileName);
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