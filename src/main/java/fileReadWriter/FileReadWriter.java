package fileReadWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileReadWriter {

    private static final Logger LOG = LogManager.getLogger(FileReadWriter.class);

    private static String getStringFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        LOG.trace("inside getStringFromFile method " + filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            LOG.error("Error reading file", e);
        }
        return sb.toString().toLowerCase();
    }

    private static void writeFileWithUniqueWords(String filePath, int numUniqueWords) {
        LOG.trace("inside writeFileWithUniqueWords method");
        String msg =
            System.lineSeparator() + "There are " + numUniqueWords + " unique words in this file.";
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(msg);
            writer.close();
            LOG.info("Text written to file successfully.");
        } catch (IOException e) {
            LOG.error("An error occurred while writing to the file: " + e.getMessage());
        }
    }

// Using FileUtils

    private static String getStringFromFileUtils(File file) {
        String text = "";
        LOG.trace("inside getStringFromFileUtils method");
        try {
            text = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            LOG.error("Error reading file", e);
        }
        return text.toLowerCase();
    }

    private static void writeFileWithUniqueWordsWithUtils(File file,
        int numUniqueWords) {
        LOG.trace("inside writeFileWithUniqueWordsWithUtils method");

        String msg =
            System.lineSeparator() + "There are " + numUniqueWords + " unique words in this file.";
        try {
            FileUtils.writeStringToFile(file, msg, "UTF-8", true);
            LOG.info("Text written to file successfully.");
        } catch (IOException e) {
            LOG.error("Error reading file", e);
        }
    }

    private static int getNumUniqueWords(String text) {
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(StringUtils.split(text, " ")));
        return wordSet.size();
    }

    public static void runFileWrite(String filePath) {
        LOG.trace("inside runFileWrite method");
        String text = getStringFromFile(filePath);
        int numUniqueWords = getNumUniqueWords(text);
        writeFileWithUniqueWords(filePath, numUniqueWords);
    }

    public static void runFileWriteWithUtils(String filePath) {
        LOG.trace("inside runFileWriteWithUtils method");
        File file = new File(filePath);
        String textFromFileUtils = getStringFromFileUtils(file);
        int numUniqueWords = getNumUniqueWords(textFromFileUtils);
        writeFileWithUniqueWordsWithUtils(file, numUniqueWords);
    }
}
