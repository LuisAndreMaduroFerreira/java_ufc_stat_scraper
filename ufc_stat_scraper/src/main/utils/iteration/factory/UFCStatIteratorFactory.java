package main.utils.iteration.factory;

import main.utils.extraction.abstraction.Extractor;
import main.utils.extraction.model.FighterInfoExtractor;
import main.utils.iteration.abstraction.UFCStatIterator;
import main.utils.iteration.model.FightDataIterator;
import main.utils.iteration.model.FighterDataIterator;

public class UFCStatIteratorFactory
{

    public UFCStatIterator getIterator(String type) {

        if (type == null) {
            return null;
        } else if (type.equalsIgnoreCase("FighterDataIterator"))
        {
            FighterDataIterator iterator = new FighterDataIterator();
            return iterator;
        } else if (type.equalsIgnoreCase("FightDataIterator"))
        {
            FightDataIterator iterator = new FightDataIterator();
            return iterator;
        }

        return null;
    }

}