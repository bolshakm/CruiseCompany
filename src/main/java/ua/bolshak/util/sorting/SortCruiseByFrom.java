package ua.bolshak.util.sorting;

import ua.bolshak.model.entity.Cruise;

import java.util.Comparator;
import java.util.List;

public class SortCruiseByFrom implements SortStrategy<Cruise> {
    @Override
    public void executeStrategy(List<Cruise> list) {
        list.sort(new Comparator<Cruise>() {
            @Override
            public int compare(Cruise o1, Cruise o2) {
                return o1.getFrom().compareTo(o2.getFrom());
            }
        });
    }
}
