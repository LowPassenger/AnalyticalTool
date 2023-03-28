package org.analyticaltool.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Storage {
    private final List<String[]> waitingTimeLines = new ArrayList<>();
    private final List<Map<String[], Integer>> queries = new ArrayList<>();

    public List<String[]> getWaitingTimeLines() {
        return waitingTimeLines;
    }

    public List<Map<String[], Integer>> getQueries() {
        return queries;
    }
}
