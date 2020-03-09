package main.utils.extraction.abstraction;

import org.jsoup.nodes.Document;

public interface Extractor
{
    public String[] extract(Document doc);
}
