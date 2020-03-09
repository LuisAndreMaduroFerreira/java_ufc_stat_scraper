package main.utils.math.factory;

import main.utils.math.model.AbbreviatedMonthNameToNumericConverter;
import main.utils.math.model.ImperialToMetricLengthConverter;
import main.utils.math.abstraction.ValueConverter;
import main.utils.math.model.ImperialToMetricWeightConverter;

public class ValueConverterFactory
{
    public ValueConverter getConverter(String type)
    {
        if(type == null)
        {
            return null;
        }
        else if(type.equalsIgnoreCase("ImperialToMetricWeightConverter"))
        {
            ImperialToMetricWeightConverter converter = new ImperialToMetricWeightConverter();
            converter.instantiate_conversion_map();
            converter.instantiate_same_metric_conversion_map();
            return converter;
        }
        else if(type.equalsIgnoreCase("ImperialToMetricLengthConverter"))
        {
            ImperialToMetricLengthConverter converter = new ImperialToMetricLengthConverter();
            converter.instantiate_conversion_map();
            converter.instantiate_same_metric_conversion_map();
            return converter;
        }
        else if(type.equalsIgnoreCase("AbbreviatedMonthNameToNumericConverter"))
        {
            AbbreviatedMonthNameToNumericConverter converter = new AbbreviatedMonthNameToNumericConverter();
            converter.instantiate_conversion_map();
            return converter;
        }
        return null;
    }
}
