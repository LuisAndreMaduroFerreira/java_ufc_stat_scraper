package main.utils.extraction.model;

import main.utils.extraction.abstraction.Extractor;
import main.utils.math.abstraction.ValueConverter;
import main.utils.math.factory.ValueConverterFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class FighterInfoExtractor implements Extractor
{
    @Override
    public String[] extract(Document doc)
    {
            String[] result = new String[20];
            String name = doc.getElementsByClass("b-content__title-highlight").text();
            String[] record_split = doc.getElementsByClass("b-content__title-record").text().split("-");
            String wins = record_split[0].replace("Record: ", "");
            String losses = record_split[1];
            String draws = record_split[2];
            String nickname = doc.getElementsByClass("b-content__nickname").text();

            List<Element> stat_list = doc.getElementsByClass("b-list__info-box b-list__info-box_style_small-width js-guide");
            if (stat_list.isEmpty())
            {
                throw new RuntimeException("Empty stat list");
            }

            String[] bio_array = stat_list.get(0).text().split(" ");
            for(String st : bio_array)
            {
                    System.out.println(st);
            }
            Float weight_pounds, height_feet, height_inches, reach_inches;
            String day, month_abbr, year, stance;
            try {
                    if (bio_array[1].equals("--")) {
                            height_feet = 0.0f;
                            height_inches = 0.0f;
                            weight_pounds = bio_array[3].equals("--") ?
                                    0.0f :
                                    Float.parseFloat(bio_array[3]);

                            reach_inches = bio_array[6].equals("--") ?
                                    0.0f :
                                    Float.parseFloat(bio_array[6].replace("\"", ""));

                            if (bio_array[8].equalsIgnoreCase("DOB:")
                                    || bio_array[9].equalsIgnoreCase("DOB")) {
                                    stance = "--";
                                    if (bio_array[9].equalsIgnoreCase("--")
                                            || bio_array[10].equalsIgnoreCase("--")) {
                                            day = "0";
                                            month_abbr = "0";
                                            year = "0";
                                    } else {
                                            day = bio_array[10].replace(",", "");
                                            month_abbr = bio_array[9];
                                            year = bio_array[11];
                                    }

                            } else {
                                    stance = bio_array[8];
                                    day = bio_array[11].replace(",", "");
                                    month_abbr = bio_array[10];
                                    year = bio_array[12];
                            }
                    } else {
                            height_feet =
                                    Float.parseFloat(bio_array[1].replace("'", ""));

                            height_inches =
                                    Float.parseFloat(bio_array[2].replace("\"", ""));

                            weight_pounds = bio_array[4].equals("--") ?
                                    0.0f :
                                    Float.parseFloat(bio_array[4]);

                            reach_inches = bio_array[7].equals("--") ?
                                    0.0f :
                                    Float.parseFloat(bio_array[7].replace("\"", ""));

                            if (bio_array[9].equalsIgnoreCase("DOB:")) {
                                    stance = "--";
                                    if (bio_array[10].equalsIgnoreCase("--")) {
                                            day = "0";
                                            month_abbr = "0";
                                            year = "0";
                                    } else {
                                            day = bio_array[11].replace(",", "");
                                            month_abbr = bio_array[10];
                                            year = bio_array[12];
                                    }

                            } else {
                                    stance = bio_array[9];
                                    if (bio_array[11].equalsIgnoreCase("--")) {
                                            day = "0";
                                            month_abbr = "0";
                                            year = "0";
                                    } else {
                                            day = bio_array[12].replace(",", "");
                                            month_abbr = bio_array[11];
                                            year = bio_array[13];
                                    }
                            }
                    }
                    ValueConverterFactory factory = new ValueConverterFactory();
                    ValueConverter length_converter = factory.getConverter("ImperialToMetricLengthConverter");
                    ValueConverter weight_converter = factory.getConverter("ImperialToMetricWeightConverter");
                    ValueConverter abbreviated_month_converter = factory.getConverter("AbbreviatedMonthNameToNumericConverter");
                    String height = String.valueOf(length_converter.convert("foot",height_feet,"centimetre") +
                            length_converter.convert("inch",height_inches,"centimetre"));
                    String weight = String.valueOf(weight_converter.convert("pound",weight_pounds,"kilogram"));
                    String reach = String.valueOf(length_converter.convert("inch",reach_inches,"centimetre"));
                    String month = String.valueOf(abbreviated_month_converter.convert(month_abbr,0.0f,"").intValue());

                    List<Element> career_statistics = doc.getElementsByClass("b-list__info-box b-list__info-box_style_middle-width js-guide clearfix");
                    if(career_statistics.isEmpty())
                    {
                            throw new RuntimeException("Empty career statistics list");
                    }

                    String[] career_statistics_array = career_statistics.get(0).text().split(" ");
                    String significant_per_minute = career_statistics_array[3];
                    String significant_accuracy_percentage = career_statistics_array[6].replace("%", "");
                    String significant_absorved_minute = career_statistics_array[8];
                    String significant_strike_dodged_percentage = career_statistics_array[11].replace("%", "");

                    String takedown_average = career_statistics_array[14];
                    String takedown_accuracy_percentage = career_statistics_array[17].replace("%", "");
                    String takedown_defense_percentage = career_statistics_array[20].replace("%", "");
                    String submissions_attempted_per_15_minutes = career_statistics_array[23];


                    //name, nickname, day, month, year, wins, draws, losses, height, weight, stance, significant_per_minute
                    //significant_accuracy_percentage, significant_absorved_minute, significant_strike_dodged_percentage
                    //takedown_average, takedown_accuracy_percentage, takedown_defense_percentage, submissions_attempted_per_15_minutes

                    result[0] = name;
                    result[1] = nickname;
                    result[2] = day;
                    result[3] = month;
                    result[4] = year;
                    result[5] = wins;
                    result[6] = losses;
                    result[7] = draws;
                    result[8] = height;
                    result[9] = weight;
                    result[10] = reach;
                    result[11] = stance;
                    result[12] = significant_per_minute;
                    result[13] = significant_accuracy_percentage;
                    result[14] = significant_absorved_minute;
                    result[15] = significant_strike_dodged_percentage;
                    result[16] = takedown_average;
                    result[17] = takedown_accuracy_percentage;
                    result[18] = takedown_defense_percentage;
                    result[19] = submissions_attempted_per_15_minutes;
                    for(String ss : result)
                    {
                            System.out.print(ss + " | ");
                    }
                    return result;
            }catch(Exception e) { }
            result[0] = "na";
            return  result;
    }
}
