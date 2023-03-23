package org.analyticaltool;

import java.io.File;
import java.util.Scanner;
import org.analyticaltool.service.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final int REPORT_FILE_MAX_RECORDS_NUMBER = 100000;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Parser parser = new Parser();
    private static final Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Please enter the root file for analyze: ");
        String rootPath = scanner.nextLine();
        while (!new File(rootPath).exists()) {
            System.out.println("File doesn't exist! Please try again!");
            rootPath = scanner.nextLine();
        }
        scanner.close();
        log.info("Start work with {} file", rootPath);
    }
}
