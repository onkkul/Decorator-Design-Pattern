package decoratorsystem.adt;

import java.util.HashMap;

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
    public String getParagraph() throws
        FileNotFoundException, IOException;

    public HashMap<String, String> getMisSpelled() throws
        FileNotFoundException, IOException;

    public HashMap<String, String> getKeyWords()throws
        FileNotFoundException, IOException;

    public String getMostFrequent();
}