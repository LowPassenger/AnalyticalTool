package org.analyticaltool.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private final List<String[]> waitingTimeLines = new ArrayList<>();
    private final Map<String[], Integer> queries = new LinkedHashMap<>();

    public List<String[]> getWaitingTimeLines() {
        return waitingTimeLines;
    }

    public Map<String[], Integer> getQueries() {
        return queries;
    }
}
