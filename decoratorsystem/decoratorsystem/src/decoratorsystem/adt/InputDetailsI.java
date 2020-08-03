package decoratorsystem.adt;

public import java.util.HashMap;

public interface InputDetailsI{
    // public void setInputFile(String fileName);
    public String getLine();

    // public void setMissspelledFile(String fileName);
    public HashMap<String, String> getMissspelled();

    // public void setKeyWordFile(String fileName);
    public HashMap<String, String> getKeyWords();
}