import main.utils.extraction.abstraction.Extractor;
import main.utils.extraction.factory.ExtractorFactory;
import main.utils.filegeneration.abstraction.UFCFileWriter;
import main.utils.filegeneration.factory.UFCFileWriterFactory;
import main.utils.iteration.abstraction.UFCStatIterator;
import main.utils.iteration.factory.UFCStatIteratorFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import main.utils.iteration.model.FightDataIterator.*;

public class main {

    public static void main(String[] args)
    {
        test_scrape_one_event("http://www.ufcstats.com/event-details/0b5b6876c2a4723f");
    }

    public static void test_one_fighter_scrape()
    {
       ExtractorFactory factory = new ExtractorFactory();
       Extractor extractor = factory.getExtractor("FighterInfoExtractor");
        try {
            Document doc = Jsoup.connect("http://www.ufcstats.com/fighter-details/44aa652b181bcf68").get();
            extractor.extract(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test_scrape_one_event(String event_string)
    {
        UFCStatIteratorFactory factory = new UFCStatIteratorFactory();
        UFCStatIterator iterator = factory.getIterator("FightDataIterator");
        List<String[]> result = iterator.getDataOneEvent(event_string);
        UFCFileWriterFactory writer_factory = new UFCFileWriterFactory();
        String[] header = {"Bout Information","Draw","No Contest", "Round Finished", "Method", "Details","Fighter1","Fighter2",
                "Loser","Winner",
                "Loser Knockdowns","Loser Attempted Takedowns", "Loser Takedowns Succeed","Loser Takedown Success Percentage",
                "Loser Submission Attempts", "Loser Guard Passages", "Loser Reversals",
                "Loser Attempted Significant Strikes", "Loser Landed Significant Strikes", "Loser Significant Landed Strikes Percentage",
                "Loser Head Attempted Significant Strikes", "Loser Head Landed Significant Strikes", "Loser Head Landed Significant Strikes Percentage",
                "Loser Body Attempted Significant Strikes", "Loser Body Landed Significant Strikes", "Loser Body Landed Significant Strikes Percentage",
                "Loser Leg Attempted Significant Strikes", "Loser Leg Landed Significant Strikes", "Loser Leg Landed Significant Strikes Percentage",
                "Loser At Distance Attempted Significant Strikes", "Loser At Distance Landed Significant Strikes", "Loser At Distance Landed Significant Strikes Percentage",
                "Loser On Ground Attempted Significant Strikes", "Loser On Ground Landed Significant Strikes", "Loser On Ground Landed Significant Strikes Percentage",
                "Loser In Clinch Attempted Significant Strikes", "Loser In Clinch Landed Significant Strikes", "Loser In Clinch Landed Significant Strikes Percentage",

                "Winner Knockdowns","Winner Attempted Takedowns","Winner Takedowns Succeed", "Winner Takedown Success Percentage", "Winner Submission Attempts",
                "Winner Guard Passages", "Winner Reversals", "Winner Attempted Significant Strikes", "Winner Landed Significant Strikes",
                "Winner Significant Landed Strikes Percentage",
                "Winner Head Attempted Significant Strikes", "Winner Head Landed Significant Strikes", "Winner Head Landed Significant Strikes Percentage",
                "Winner Body Attempted Significant Strikes", "Winner Body Landed Significant Strikes", "Winner Body Landed Significant Strikes Percentage",
                "Winner Leg Attempted Significant Strikes", "Winner Leg Landed Significant Strikes", "Winner Leg Landed Significant Strikes Percentage",
                "Winner At Distance Attempted Significant Strikes", "Winner At Distance Landed Significant Strikes", "Winner At Distance Landed Significant Strikes Percentage",
                "Winner On Ground Attempted Significant Strikes", "Winner On Ground Landed Significant Strikes", "Winner On Ground Landed Significant Strikes Percentage",
                "Winner In Clinch Attempted Significant Strikes", "Winner In Clinch Landed Significant Strikes", "Winner In Clinch Landed Significant Strikes Percentage"};

        UFCFileWriter writer = writer_factory.getUFCFileWriter("CSVUFCFightFileWriter");
        writer.writeFile(header, result, ".");
    }

    public static void test_all_fights_write()
    {
        UFCStatIteratorFactory factory = new UFCStatIteratorFactory();
        UFCStatIterator iterator = factory.getIterator("FightDataIterator");
        List<String[]> result = iterator.getCurrentData();
        UFCFileWriterFactory writer_factory = new UFCFileWriterFactory();
        String[] header = {"Bout Information","Draw","No Contest", "Round Finished", "Method", "Details","Fighter1","Fighter2",
                "Loser","Winner",
                "Loser Knockdowns","Loser Attempted Takedowns", "Loser Takedowns Succeed","Loser Takedown Success Percentage",
                "Loser Submission Attempts", "Loser Guard Passages", "Loser Reversals",
                "Loser Attempted Significant Strikes", "Loser Landed Significant Strikes", "Loser Significant Landed Strikes Percentage",
                "Loser Head Attempted Significant Strikes", "Loser Head Landed Significant Strikes", "Loser Head Landed Significant Strikes Percentage",
                "Loser Body Attempted Significant Strikes", "Loser Body Landed Significant Strikes", "Loser Body Landed Significant Strikes Percentage",
                "Loser Leg Attempted Significant Strikes", "Loser Leg Landed Significant Strikes", "Loser Leg Landed Significant Strikes Percentage",
                "Loser At Distance Attempted Significant Strikes", "Loser At Distance Landed Significant Strikes", "Loser At Distance Landed Significant Strikes Percentage",
                "Loser On Ground Attempted Significant Strikes", "Loser On Ground Landed Significant Strikes", "Loser On Ground Landed Significant Strikes Percentage",
                "Loser In Clinch Attempted Significant Strikes", "Loser In Clinch Landed Significant Strikes", "Loser In Clinch Landed Significant Strikes Percentage",

                "Winner Knockdowns","Winner Attempted Takedowns","Winner Takedowns Succeed", "Winner Takedown Success Percentage", "Winner Submission Attempts",
                "Winner Guard Passages", "Winner Reversals", "Winner Attempted Significant Strikes", "Winner Landed Significant Strikes",
                "Winner Significant Landed Strikes Percentage",
                "Winner Head Attempted Significant Strikes", "Winner Head Landed Significant Strikes", "Winner Head Landed Significant Strikes Percentage",
                "Winner Body Attempted Significant Strikes", "Winner Body Landed Significant Strikes", "Winner Body Landed Significant Strikes Percentage",
                "Winner Leg Attempted Significant Strikes", "Winner Leg Landed Significant Strikes", "Winner Leg Landed Significant Strikes Percentage",
                "Winner At Distance Attempted Significant Strikes", "Winner At Distance Landed Significant Strikes", "Winner At Distance Landed Significant Strikes Percentage",
                "Winner On Ground Attempted Significant Strikes", "Winner On Ground Landed Significant Strikes", "Winner On Ground Landed Significant Strikes Percentage",
                "Winner In Clinch Attempted Significant Strikes", "Winner In Clinch Landed Significant Strikes", "Winner In Clinch Landed Significant Strikes Percentage"};

        UFCFileWriter writer = writer_factory.getUFCFileWriter("CSVUFCFightFileWriter");
        writer.writeFile(header, result, ".");
    }

    public static void test_all_scrape()
    {
        UFCStatIteratorFactory factory = new UFCStatIteratorFactory();
        UFCStatIterator iterator = factory.getIterator("FighterDataIterator");
        List<String[]> result = iterator.getCurrentData();
        UFCFileWriterFactory writer_factory = new UFCFileWriterFactory();

        //name, nickname, day, month, year, wins, draws, losses, height, weight, stance, significant_per_minute
        //significant_accuracy_percentage, significant_absorved_minute, significant_strike_dodged_percentage
        //takedown_average, takedown_accuracy_percentage, takedown_defense_percentage, submissions_attempted_per_15_minutes

        String[] header = {"Name", "Nickname", "Day of Birth", "Month of Birth", "Year of Birth", "Wins", "Losses", "Draws",
        "Height", "Weight", "Stance", "Signigicant Strikes Attempted Per Minute", "Significant Strike Accuracy Percentage",
        "Significant Strikes Absorved Per Minute", "Signigicant Strikes Dodged Percentage", "Takedown Average", "Takedown Accuracy Percentage",
        "Takedown Defense Percentage", "Submissions Attempted per 15 Minutes"};
        UFCFileWriter writer = writer_factory.getUFCFileWriter("CSVUFCFileWriter");
                writer.writeFile(header, result, ".");
    }

    public static void test_single_fight_scrape()
    {
        ExtractorFactory factory_extractor = new ExtractorFactory();
        Extractor extractor = factory_extractor.getExtractor("FightInfoExtractor");
        try {
            Document doc = Jsoup.connect("http://www.ufcstats.com/fight-details/9bc18b63b07bb328").get();
            String[] header = {"Bout Information","Draw","No Contest", "Round Finished", "Method", "Details","Fighter1","Fighter2",
                    "Loser","Winner",
                    "Loser Knockdowns","Loser Attempted Takedowns", "Loser Takedowns Succeed","Loser Takedown Success Percentage",
                    "Loser Submission Attempts", "Loser Guard Passages", "Loser Reversals",
                    "Loser Attempted Significant Strikes", "Loser Landed Significant Strikes", "Loser Significant Landed Strikes Percentage",
                    "Loser Head Attempted Significant Strikes", "Loser Head Landed Significant Strikes", "Loser Head Landed Significant Strikes Percentage",
                    "Loser Body Attempted Significant Strikes", "Loser Body Landed Significant Strikes", "Loser Body Landed Significant Strikes Percentage",
                    "Loser Leg Attempted Significant Strikes", "Loser Leg Landed Significant Strikes", "Loser Leg Landed Significant Strikes Percentage",
                    "Loser At Distance Attempted Significant Strikes", "Loser At Distance Landed Significant Strikes", "Loser At Distance Landed Significant Strikes Percentage",
                    "Loser On Ground Attempted Significant Strikes", "Loser On Ground Landed Significant Strikes", "Loser On Ground Landed Significant Strikes Percentage",
                    "Loser In Clinch Attempted Significant Strikes", "Loser In Clinch Landed Significant Strikes", "Loser In Clinch Landed Significant Strikes Percentage",

                    "Winner Knockdowns","Winner Attempted Takedowns","Winner Takedowns Succeed", "Winner Takedown Success Percentage", "Winner Submission Attempts",
                    "Winner Guard Passages", "Winner Reversals", "Winner Attempted Significant Strikes", "Winner Landed Significant Strikes",
                    "Winner Significant Landed Strikes Percentage",
                    "Winner Head Attempted Significant Strikes", "Winner Head Landed Significant Strikes", "Winner Head Landed Significant Strikes Percentage",
                    "Winner Body Attempted Significant Strikes", "Winner Body Landed Significant Strikes", "Winner Body Landed Significant Strikes Percentage",
                    "Winner Leg Attempted Significant Strikes", "Winner Leg Landed Significant Strikes", "Winner Leg Landed Significant Strikes Percentage",
                    "Winner At Distance Attempted Significant Strikes", "Winner At Distance Landed Significant Strikes", "Winner At Distance Landed Significant Strikes Percentage",
                    "Winner On Ground Attempted Significant Strikes", "Winner On Ground Landed Significant Strikes", "Winner On Ground Landed Significant Strikes Percentage",
                    "Winner In Clinch Attempted Significant Strikes", "Winner In Clinch Landed Significant Strikes", "Winner In Clinch Landed Significant Strikes Percentage"};
            String[] test = extractor.extract(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
