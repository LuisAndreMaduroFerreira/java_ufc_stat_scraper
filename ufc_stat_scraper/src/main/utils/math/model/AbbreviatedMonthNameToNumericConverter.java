package main.utils.math.model;

import main.utils.math.abstraction.ValueConverter;

public class AbbreviatedMonthNameToNumericConverter implements ValueConverter 
{
    @Override
    /**
     * Kept blank for override only
     */
    public void instantiate_same_metric_conversion_map() {}

    /**
     * Conversion for imperial to metric, adjusted to metres
     */
    @Override
    public void instantiate_conversion_map()
    {
        this.conversion_map.put("0",0.0f);
        this.conversion_map.put("Jan", 1.0f);
        this.conversion_map.put("Feb", 2.0f);
        this.conversion_map.put("Mar", 3.0f);
        this.conversion_map.put("Apr", 4.0f);
        this.conversion_map.put("May", 5.0f);
        this.conversion_map.put("Jun", 6.0f);
        this.conversion_map.put("Jul", 7.0f);
        this.conversion_map.put("Aug", 8.0f);
        this.conversion_map.put("Sep", 9.0f);
        this.conversion_map.put("Oct", 10.0f);
        this.conversion_map.put("Nov", 11.0f);
        this.conversion_map.put("Dec", 12.0f);
    }

    /**
     * Conversion of the source imperial value to target metric value
     * @param unit Abbreviated name for the DOB month
     * @param value unused
     * @param target_unit unused
     * @return Numerical representation of the month
     */
    @Override
    public Float convert(String unit, Float value, String target_unit)
    {
        try
        {
            return this.conversion_map.get(unit);
        }catch(Exception e)
        {
            throw new RuntimeException("No such unit");
        }
    }
}
