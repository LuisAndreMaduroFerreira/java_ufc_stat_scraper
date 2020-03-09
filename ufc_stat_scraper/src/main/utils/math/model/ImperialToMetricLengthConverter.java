package main.utils.math.model;

import main.utils.math.abstraction.ValueConverter;

public class ImperialToMetricLengthConverter implements ValueConverter {

    @Override
    /**
     * Conversion rates for target metric, adjusted to metres
     */
    public void instantiate_same_metric_conversion_map()
    {
        this.same_metric_conversion_map.put("kilometre", 0.001f);
        this.same_metric_conversion_map.put("hectometre", 0.01f);
        this.same_metric_conversion_map.put("decametre", 0.1f);
        this.same_metric_conversion_map.put("metre", 1.0f);
        this.same_metric_conversion_map.put("decimetre", 10.0f);
        this.same_metric_conversion_map.put("centimetre", 100.0f);
        this.same_metric_conversion_map.put("milimetre", 1000.0f);
        this.same_metric_conversion_map.put("micrometre", 100000.0f);
        this.same_metric_conversion_map.put("nanometre", 100000000.0f);
    }

    /**
     * Conversion rates for imperial to metric, adjusted to metres
     */
    @Override
    public void instantiate_conversion_map()
    {
        this.conversion_map.put("thou", 0.0000254f);
        this.conversion_map.put("inch", 0.0254f);
        this.conversion_map.put("foot", 0.3048f);
        this.conversion_map.put("yard", 0.9144f);
        this.conversion_map.put("chain", 20.1168f);
        this.conversion_map.put("furlong", 201.168f);
        this.conversion_map.put("mile", 1609.344f);
        this.conversion_map.put("league", 4828.032f);
        this.conversion_map.put("fathom", 1.852f);
        this.conversion_map.put("cable", 185.2f);
        this.conversion_map.put("nautical mile", 1852.0f);
        this.conversion_map.put("link", 0.201168f);
        this.conversion_map.put("rod", 5.0292f);
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
            throw new RuntimeException("No such unit");
        }
    }
}
