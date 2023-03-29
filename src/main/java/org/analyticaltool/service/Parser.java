package org.analyticaltool.service;

import java.util.Map;
import java.util.Set;
import org.analyticaltool.file.OutputFileWriter;
import org.analyticaltool.utils.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {
    private static final Logger log = LogManager.getLogger(Parser.class);
    private final OutputFileWriter writer = new OutputFileWriter();

    public void reportCreation(Storage storage) {
        Set<Map.Entry<String[], Integer>> entries = storage.getQueries().entrySet();
        for (Map.Entry<String[], Integer> entry : entries) {

        }
    }

    private String[]

}
