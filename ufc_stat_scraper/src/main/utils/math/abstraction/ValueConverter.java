package main.utils.math.abstraction;

import java.util.HashMap;

public interface ValueConverter
{
    HashMap<String, Float> conversion_map = new HashMap<String, Float>();
    HashMap<String, Float> same_metric_conversion_map = new HashMap<String, Float>();
    public Float convert(String unit, Float value, String target_unit);
    public void instantiate_conversion_map();
    public void instantiate_same_metric_conversion_map();
}
