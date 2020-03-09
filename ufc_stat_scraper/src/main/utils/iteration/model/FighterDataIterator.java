package main.utils.iteration.model;

import main.utils.extraction.abstraction.Extractor;
import main.utils.extraction.factory.ExtractorFactory;
import main.utils.iteration.abstraction.UFCStatIterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FighterDataIterator implements UFCStatIterator
{
    @Override
    public List<String[]> getCurrentData()
    {
        List<String[]> result = new ArrayList<String[]>();
        String[] abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z"};
        ExtractorFactory factory = new ExtractorFactory();
        Extractor extractor = factory.getExtractor("FighterInfoExtractor");
        Document doc = null;
        try
        {
            for(String letter : abc)
            {
                doc = Jsoup.connect(this.formURL(letter)).get();
                Elements links = doc.select("a[href]");
                System.out.println(links.size());
                String temp = "";
                for (Element link : links)
                {
                    if(link.attr("abs:href").contains("-details")  && !temp.equals(link.attr("abs:href")))
                    {
                        try {
                            Document fighter_document = Jsoup.connect(link.attr("abs:href")).get();
                            String[] info = extractor.extract(fighter_document);
                            if (!info[0].equals("na")) result.add(extractor.extract(fighter_document));
                            temp = link.attr("abs:href");
                        }catch(Exception e){}
                    }
                }
            }
            return result;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String formURL(String url_substring)
    {
        return "http://www.ufcstats.com/statistics/fighters?char="+url_substring+"&page=all";
    }

    @Override
    public List<String[]> getDataOneEvent(String event_string) {
        return null;
    }
}
