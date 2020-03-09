package main.utils.iteration.model;

import main.utils.extraction.abstraction.Extractor;
import main.utils.extraction.factory.ExtractorFactory;
import main.utils.iteration.abstraction.UFCStatIterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

public class FightDataIterator implements UFCStatIterator
{
    @Override
    public List<String[]> getCurrentData()
    {
        List<String[]> result = new ArrayList<String[]>();
        ExtractorFactory factory = new ExtractorFactory();
        Extractor extractor = factory.getExtractor("FightInfoExtractor");
        Document document = null;
        try {
            String link_events = "http://www.ufcstats.com/statistics/events/completed?page=all";
            document = Jsoup.connect(link_events).get();
            Elements links = document.select("a[href]");
            for (Element link : links)
            {
                if(link.attr("abs:href").contains("event-details"))
                {
                    Document doc_event = Jsoup.connect(link.attr("abs:href")).get();
                    Elements links_fight_details_event = doc_event.select("a[href]");
                    for(Element fight_link : links_fight_details_event)
                    {
                        if(fight_link.attr("abs:href").contains("fight-details"))
                        {
                            System.out.println(fight_link.attr("abs:href"));
                            Document fight_document = Jsoup.connect(fight_link.attr("abs:href")).timeout(90000).get();
                            result.add(extractor.extract(fight_document));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String formURL(String url_substring)
    {
        return null;
    }

    @Override
    public List<String[]> getDataOneEvent(String event_string)
    {
        List<String[]> result = new ArrayList<String[]>();
        ExtractorFactory factory = new ExtractorFactory();
        Extractor extractor = factory.getExtractor("FightInfoExtractor");
        Document document = null;
        try
        {
            Document doc_event = Jsoup.connect(event_string).get();
            Elements links_fight_details_event = doc_event.select("a[href]");
            for(Element fight_link : links_fight_details_event)
            {
                if(fight_link.attr("abs:href").contains("fight-details"))
                {
                    System.out.println(fight_link.attr("abs:href"));
                    Document fight_document = Jsoup.connect(fight_link.attr("abs:href")).timeout(90000).get();
                    result.add(extractor.extract(fight_document));
                }
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
