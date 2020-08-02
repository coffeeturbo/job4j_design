package io.search;

import java.nio.file.Path;
import java.util.function.Predicate;

public class SearchArgs {
    private final String[] args;

    static private final String FILENAME_KEY = "-n";
    static private final String DIRECTORY_KEY = "-d";
    static private final String OUTPUT_KEY = "-o";
    static private final String SEARCH_TYPE_MASK_KEY = "-m";
    static private final String SEARCH_TYPE_REGULAR_KEY = "-r";
    static private final String SEARCH_TYPE_FULL_MATCH_KEY = "-f";

    private String directory;
    private String fileName;
    private String searchType;
    private String outputFile;

    public SearchArgs(String[] args) {
        this.args = args;
    }

    public boolean validate() {
        for (String arg: args) {
           if (arg == null || arg.isEmpty()) {
               return false;
           }
        }
        return true;
    }

    public String getOutputFile() {
        if (outputFile == null) {
            outputFile = fetchParam(args, SearchArgs.OUTPUT_KEY);
        }
        return outputFile;
    }

    public String getSearchType() {
        if (searchType == null) {
            for (String arg: args) {
                if (arg.contains(SEARCH_TYPE_FULL_MATCH_KEY)) {
                   searchType = SEARCH_TYPE_FULL_MATCH_KEY;
                   break;
                } else if (arg.contains(SEARCH_TYPE_MASK_KEY)) {
                    searchType = SEARCH_TYPE_MASK_KEY;
                    break;
                } else if (arg.contains(SEARCH_TYPE_REGULAR_KEY)) {
                    searchType = SEARCH_TYPE_REGULAR_KEY;
                    break;
                }
            }
        }
        return searchType;
    }

    public String getFileName() {
        if (fileName == null) {
            fileName = fetchParam(args, SearchArgs.FILENAME_KEY);
        }
        return fileName;
    }

    public String getDirectory() {
        if (directory == null) {
            directory = fetchParam(args, SearchArgs.DIRECTORY_KEY);
        }
        return directory;
    }

    public Predicate<Path> getFilter() {
        Predicate<Path> test;

        switch (getSearchType()) {
            case SEARCH_TYPE_FULL_MATCH_KEY:
                test = path -> path.getFileName().toString().equals(getFileName());
            break;

            case SEARCH_TYPE_MASK_KEY:
                test = path -> path.getFileName().toString().endsWith(getFileName());
            break;

            case SEARCH_TYPE_REGULAR_KEY:
                test = path -> path.getFileName().toString().matches(getRegularExpr());
            break;
            default:
                test = null;
        }

        return test;
    }

    private String getRegularExpr() {
        return getFileName()
            .replace(".", "\\.")
            .replace("?", ".?")
            .replace("*", ".*");
    }

    private String fetchParam(String[] strings, String key) {
        String param = null;
        for (String arg: strings) {
            param = fetchParam(arg, key);
            if (param != null) {
                break;
            }
        }
        return param;
    }

    private String fetchParam(String str, String param) {
        String rsl = null;
        if (str.contains(param)) {
            int start = str.indexOf("=");
            rsl = start < 0 ? param : str.substring(++start);
        }
        return rsl;
    }

}
