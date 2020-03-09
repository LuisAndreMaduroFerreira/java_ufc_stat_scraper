package main.utils.iteration.abstraction;

import java.util.List;

public interface UFCStatIterator
{
    public List<String[]> getCurrentData();
    public String formURL(String url_substring);
    public List<String[]> getDataOneEvent(String event_string);
}
