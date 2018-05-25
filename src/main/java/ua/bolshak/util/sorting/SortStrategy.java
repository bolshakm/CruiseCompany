package ua.bolshak.util.sorting;

import java.util.List;

public interface SortStrategy<T> {
    void executeStrategy(List<T> list);
}
