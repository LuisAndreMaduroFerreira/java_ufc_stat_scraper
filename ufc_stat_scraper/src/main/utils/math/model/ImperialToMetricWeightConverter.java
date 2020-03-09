package main.utils.math.model;

import main.utils.math.abstraction.ValueConverter;

public class ImperialToMetricWeightConverter implements ValueConverter
{
    @Override
    /**
     * Conversion rates for target metric, adjusted to grams
     */
    public void instantiate_same_metric_conversion_map()
    {
        this.same_metric_conversion_map.put("kilogram", 0.001f);
        this.same_metric_conversion_map.put("gram", 1.0f);
    }

    /**
     * Conversion rates for imperial to metric, adjusted to grams
     */
    @Override
    public void instantiate_conversion_map()
    {
        this.conversion_map.put("grain", 0.06479891f);
        this.conversion_map.put("drachm", 1.7718451953125f);
        this.conversion_map.put("ounce", 28.349523125f);
        this.conversion_map.put("pound", 453.59237f);
        this.conversion_map.put("stone", 6350.29318f);
        this.conversion_map.put("quarter", 12700.58636f);
        this.conversion_map.put("hundredweight", 50802.34544f);
        this.conversion_map.put("ton", 1016046.9088f);
        this.conversion_map.put("slug", 14593.90294f);
    }

    /**
     * Conversion of the source imperial value to target metric value
     * @param unit Name of the source imperial unit
     * @param value Value in imperial
     * @param target_unit Name of the target metric unit
     * @return Converted (rate multiplied) value in target metric units
     */
    @Override
    public Float convert(String unit, Float value, String target_unit)
    {
        try
        {
            return this.conversion_map.get(unit) * value * this.same_metric_conversion_map.get(target_unit);
        }catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("No such unit");
        }
    }
}
