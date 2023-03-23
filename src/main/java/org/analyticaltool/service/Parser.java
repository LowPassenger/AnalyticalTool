package org.analyticaltool.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {
    private static final Logger log = LogManager.getLogger(Parser.class);
    private final List<String> waitingTimeLines = new ArrayList<>();
    private final List<String> queries = new ArrayList<>();
    private int inputLinesNumber = 0;
    private int waitingTimeLinesNumber = 0;
    private int inputQueryesNumber = 0;


}
