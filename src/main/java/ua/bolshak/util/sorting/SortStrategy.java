package ua.bolshak.util.sorting;

import java.util.List;

public interface SortStrategy<T> {
    /**
     * @param list which must be sort
     */
    void executeStrategy(List<T> list);
}
