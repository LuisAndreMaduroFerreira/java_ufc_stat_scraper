package main.utils.filegeneration.abstraction;

import java.util.List;

public interface UFCFileWriter
{
    public void writeFile(String[] header, List<String[]> data, String filepath);
}
