package main.utils.extraction.factory;

import main.utils.extraction.abstraction.Extractor;
import main.utils.extraction.model.FightInfoExtractor;
import main.utils.extraction.model.FighterInfoExtractor;

public class ExtractorFactory
{
    public Extractor getExtractor(String type)
    {
        if (type == null)
        {
            return null;
        } else if (type.equalsIgnoreCase("FighterInfoExtractor"))
        {
            Extractor extractor = new FighterInfoExtractor();
            return extractor;
        } else if (type.equalsIgnoreCase("FightInfoExtractor"))
        {
            Extractor extractor = new FightInfoExtractor();
            return extractor;
        }
        return null;
    }
}