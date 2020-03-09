package main.utils.extraction.model;

import main.utils.extraction.abstraction.Extractor;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FightInfoExtractor implements Extractor
{
    @Override
    public String[] extract(Document doc)
    {
        String winner = "";
        String loser = "";
        String no_contest = "False";
        String draw = "False";

        String bout = "";
        String method = "";
        String round = "";
        String details = "";

        int count = 0;

        String fighter1 = "", fighter2 = "";

        Elements contenders = doc.getElementsByClass("b-fight-details__person");
        for(Element contender : contenders)
        {
            String fighter_string = contender.text().replaceAll("\\\".*?\\\"", "");

            if(fighter_string.split(" ")[0].contains("NC"))
            {
                no_contest = "True";
                if(count == 0) fighter1 = this.extract_all_elements_besides_first(fighter_string);
                else fighter2 = this.extract_all_elements_besides_first(fighter_string);
                count++;
            }
            if(fighter_string.split(" ")[0].contains("D"))
            {
                draw = "True";
                if(count == 0) fighter1 = this.extract_all_elements_besides_first(fighter_string);
                else fighter2 = this.extract_all_elements_besides_first(fighter_string);
                count++;
            }
            if(fighter_string.split(" ")[0].contains("W"))
            {
                winner = this.extract_all_elements_besides_first(fighter_string);
                if(count == 0) fighter1 = this.extract_all_elements_besides_first(fighter_string);
                else fighter2 = this.extract_all_elements_besides_first(fighter_string);
                count++;
            }
            if(fighter_string.split(" ")[0].contains("L"))
            {
                loser = this.extract_all_elements_besides_first(fighter_string);
                if(count == 0) fighter1 = this.extract_all_elements_besides_first(fighter_string);
                else fighter2 = this.extract_all_elements_besides_first(fighter_string);
                count++;
            }
        }

        Elements title = doc.getElementsByClass("b-fight-details__fight-title");
        Elements contest_method_elements = doc.getElementsByClass("b-fight-details__text-item_first");
        Elements contest_info_elements = doc.getElementsByClass("b-fight-details__text-item");
        Elements details_elements = doc.getElementsByClass("b-fight-details__text");
        for(Element info : title)
        {
            if(info.text().contains("Bout"))
            {
                bout = info.text();
            }
        }

        for(Element info : contest_method_elements)
        {
            if(info.text().contains("Method"))
            {
                method = this.extract_all_elements_besides_first(info.text());
            }
        }

        for(Element info : contest_info_elements)
        {
            if(info.text().contains("Round"))
            {
                round = this.extract_all_elements_besides_first(info.text());
            }
        }

        for(Element info : details_elements)
        {
            if(info.text().contains("Details") && !info.text().contains("-"))
            {
                details = this.extract_all_elements_besides_first(info.text());
            }
        }

        String[] result = new String[10];

        result[0] = bout;
        result[1] = draw;
        result[2] = no_contest;
        result[3] = round;
        result[4] = method;
        result[5] = details;
        result[6] = fighter1;
        result[7] = fighter2;
        result[8] = loser;
        result[9] = winner;

        return ArrayUtils.
                addAll(result, this.extract_significant_strike_information(doc.getElementsByClass("b-fight-details__table-col").text(),
                        winner,loser,fighter1,fighter2));
    }

    private String extract_all_elements_besides_first(String string)
    {
        String[] array = string.split(" ");
        String result = "";
        for(int i = 1; i < array.length; i++)
        {
            result += " " + array[i];
        }
        return result;
    }

    private String[] extract_significant_strike_information(String info, String winner, String loser, String fighter1, String fighter2)
    {

        try
        {
            if (fighter1.equalsIgnoreCase(winner))
            {
                return ArrayUtils.
                        addAll(this.get_second_fighter_fight_info(info.replace(fighter1, "").replace(fighter2, "").split(" ")),
                                this.get_first_fighter_fight_info(info.replace(fighter1, "").replace(fighter2, "").split(" ")));
            } else
                {
                return ArrayUtils.
                        addAll(this.get_first_fighter_fight_info(info.replace(fighter1, "").replace(fighter2, "").split(" ")),
                                this.get_second_fighter_fight_info(info.replace(fighter1, "").replace(fighter2, "").split(" ")));
            }
        }catch(Exception e)
        { }
        return new String[66];
    }

    private int get_index_of_Head_String(String[] split)
    {
        for(int i = 0; i < split.length; i++)
        {
            if(split[i].equalsIgnoreCase("Head")) return i;
        }
        return 0;
    }

    private String[] get_first_fighter_fight_info(String[] info)
    {
        String[] result = new String[28];

        result[0] = info[16];
        result[1] = info[32];
        result[2] = info[34];
        result[3] = info[38].replace("%", "");
        result[4] = info[40];
        result[5] = info[42];
        result[6] = info[44];

        int head_index = this.get_index_of_Head_String(info);

        result[7] = info[head_index + 8];
        result[8] = info[head_index + 6];
        result[9] = info[head_index + 12].replace("%","");

        result[10] = info[head_index+16];
        result[11] = info[head_index+14];
        result[12] = this.get_stringified_value_of_division(result[10], result[11]);
        if(result[12].equalsIgnoreCase("Nan")) result[12] = "0";

        result[13] = info[head_index+22];
        result[14] = info[head_index+20];
        result[15] = this.get_stringified_value_of_division(result[13], result[14]);
        if(result[15].equalsIgnoreCase("Nan")) result[15] = "0";

        result[16] = info[head_index+28];
        result[17] = info[head_index+26];
        result[18] = this.get_stringified_value_of_division(result[16], result[17]);
        if(result[18].equalsIgnoreCase("Nan")) result[18] = "0";

        result[19] = info[head_index+34];
        result[20] = info[head_index+32];
        result[21] = this.get_stringified_value_of_division(result[19], result[20]);
        if(result[21].equalsIgnoreCase("Nan")) result[21] = "0";

        result[22] = info[head_index+40];
        result[23] = info[head_index+38];
        result[24] = this.get_stringified_value_of_division(result[22], result[23]);
        if(result[24].equalsIgnoreCase("Nan")) result[24] = "0";

        result[25] = info[head_index+46];
        result[26] = info[head_index+44];
        result[27] = this.get_stringified_value_of_division(result[25], result[26]);
        if(result[27].equalsIgnoreCase("Nan")) result[27] = "0";

        for(String res : result)
        {
            System.out.print(res + " | ");
        }

        return result;
    }

    private String[] get_second_fighter_fight_info(String[] info)
    {
        String[] result = new String[28];

        result[0] = info[17];
        result[1] = info[35];
        result[2] = info[37];
        result[3] = info[39].replace("%", "");
        result[4] = info[41];
        result[5] = info[43];
        result[6] = info[45];

        int head_index = this.get_index_of_Head_String(info);

        result[7] = info[head_index + 11];
        result[8] = info[head_index + 9];
        result[9] = info[head_index + 13].replace("%","");

        result[10] = info[head_index+19];
        result[11] = info[head_index+17];
        result[12] = this.get_stringified_value_of_division(result[10], result[11]);
        if(result[12].equalsIgnoreCase("Nan")) result[12] = "0";

        result[13] = info[head_index+25];
        result[14] = info[head_index+23];
        result[15] = this.get_stringified_value_of_division(result[13], result[14]);
        if(result[15].equalsIgnoreCase("Nan")) result[15] = "0";

        result[16] = info[head_index+31];
        result[17] = info[head_index+29];
        result[18] = this.get_stringified_value_of_division(result[16], result[17]);
        if(result[18].equalsIgnoreCase("Nan")) result[18] = "0";

        result[19] = info[head_index+37];
        result[20] = info[head_index+35];
        result[21] = this.get_stringified_value_of_division(result[19], result[20]);
        if(result[21].equalsIgnoreCase("Nan")) result[21] = "0";

        result[22] = info[head_index+43];
        result[23] = info[head_index+41];
        result[24] = this.get_stringified_value_of_division(result[22], result[23]);
        if(result[24].equalsIgnoreCase("Nan")) result[24] = "0";

        result[25] = info[head_index+49];
        result[26] = info[head_index+47];
        result[27] = this.get_stringified_value_of_division(result[25], result[26]);
        if(result[27].equalsIgnoreCase("Nan")) result[27] = "0";


        //winner_knockdowns, winner_takedowns_attempted, winner_takedowns_succeed, winner_takedown_success_percentage, winner_submission_attempts, winner_guard_passages, winner_reversals
        //winner_attempted_significant_strikes, winner_landed_significant_strikes, winner_signigicant_strike_landed_percentage,
        //winner_head_attempted_significant_strikes, winner_head_landed_significant_strikes, winner_head_significant_strike_landed_percentage
        //winner_body_attempted_significant_strikes, winner_body_landed_significant_strikes, winner_body_significant_strike_landed_percentage
        //winner_leg_attempted_significant_strikes, winner_leg_landed_significant_strikes, winner_leg_significant_strike_landed_percentage
        //winner_distance_attempted_significant_strikes, winner_distance_landed_significant_strikes, winner_distance_significant_strike_landed_percentage
        //winner_ground_attempted_significant_strikes, winner_ground_landed_significant_strikes, winner_ground_significant_strike_landed_percentage
        //winner_clinch_attempted_significant_strikes, winner_clinch_landed_significant_strikes, winner_clinch_significant_strike_landed_percentage
        for(String res : result)
        {
            System.out.print(res + " | ");
        }
        return result;
    }

    private String get_stringified_value_of_division(String attempted, String succeeded)
    {
        return String.valueOf( Math.ceil( ((double)Integer.parseInt(succeeded) / (Integer.parseInt(attempted))) * 100) );
    }
}

