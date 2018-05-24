package ua.bolshak.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StatisticMap {
    private HashMap<String, Double> cruisesComesMoney;
    private Iterator<Map.Entry<String, Double>> it;

    public StatisticMap(HashMap<String, Double> cruisesComesMoney){
     this.cruisesComesMoney = cruisesComesMoney;
     it = this.cruisesComesMoney.entrySet().iterator();
    }

    public int getSize(){
        return cruisesComesMoney.size();
    }

    public String getStatistic(){
        if (it.hasNext()){
            Map.Entry<String, Double> map = it.next();
            return map.getKey() + "</td><td> " + map.getValue();
        } else {
            return null;
        }
    }
}
