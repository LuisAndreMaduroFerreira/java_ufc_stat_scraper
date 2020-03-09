package main.utils.filegeneration.model;

import com.opencsv.CSVWriter;
import main.utils.filegeneration.abstraction.UFCFileWriter;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CSVUFCFightFileWriter implements UFCFileWriter
{
    @Override
    public void writeFile(String[] header, List<String[]> data, String filepath)
    {
        String DATE_FORMAT_NOW = "yyyy_MM_dd_HH_mm_ss";
        String filename = filepath+"/fight_statistics_"+now(DATE_FORMAT_NOW)+".csv";
        File file = new File(filename);
        Scanner sc = new Scanner(System.in);
        data.add(0, header);
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(data);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String now(String date_format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(date_format);
        return sdf.format(cal.getTime());
    }
}
