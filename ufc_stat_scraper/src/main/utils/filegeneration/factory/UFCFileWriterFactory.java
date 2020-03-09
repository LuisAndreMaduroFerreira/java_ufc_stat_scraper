package main.utils.filegeneration.factory;

import main.utils.filegeneration.abstraction.UFCFileWriter;
import main.utils.filegeneration.model.CSVUFCFightFileWriter;
import main.utils.filegeneration.model.CSVUFCFighterFileWriter;
import main.utils.math.abstraction.ValueConverter;
import main.utils.math.model.ImperialToMetricWeightConverter;

public class UFCFileWriterFactory
{
    public UFCFileWriter getUFCFileWriter(String type)
    {
        if (type == null)
        {
            return null;
        } else if (type.equalsIgnoreCase("CSVUFCFileWriter"))
        {
            CSVUFCFighterFileWriter writer = new CSVUFCFighterFileWriter();
            return writer;
        } else if (type.equalsIgnoreCase("CSVUFCFightFileWriter"))
        {
            CSVUFCFightFileWriter writer = new CSVUFCFightFileWriter();
            return writer;
        }
        return null;
    }
}
