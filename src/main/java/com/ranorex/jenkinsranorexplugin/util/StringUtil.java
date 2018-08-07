package com.ranorex.jenkinsranorexplugin.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class StringUtil {

    /**
     * Appends quote to the input string
     *
     * @param value The string which should be modified
     * @return The initial string with appended quotes
     */
    public static String appendQuote(String value) {
        if (! (value.charAt(0) == '"') && ! (value.charAt(value.length() - 1) == '"')) {
            return String.format("\"%s\"", value);
        }
        return value;
    }

    /**
     * Checks if the input string is Null or Space
     *
     * @param value The string which should be checked
     * @return True if the String is null or Space
     */
    public static boolean isNullOrSpace(String value) {
        return (value == null || value.trim().length() == 0);
    }

    /**
     * Splits the input string by "/" or "\\"
     *
     * @param value input string
     * @return String array
     */
    //TODO: Move to fileUtil
    public static String[] splitPath(String value) {
        String[] splitName = value.split("/");
        if (splitName.length == 1 && value.contains("\\")) {
            splitName = value.split("\\\\");
        }
        return splitName;
    }

    /**
     * Appends backslash to the input string if not already given
     *
     * @param value input string
     * @return input string + \
     */
    public static String appendBackslash(String value) {
        char charAtLastPosition = value.charAt(value.length() - 1);
        if (charAtLastPosition != '\\') {
            value = String.format("%s\\", value);
        }
        return value;
    }


    public static List<String> splitBy(String input, String delimiters) {
        StringTokenizer valuesToknzr = new StringTokenizer(input, delimiters);
        ArrayList<String> splitInput = new ArrayList<>();
        while (valuesToknzr.hasMoreTokens()) {
            String value = valuesToknzr.nextToken();
            if (! StringUtil.isNullOrSpace(value)) {
                splitInput.add(value);
            }
        }
        return splitInput;

    }

    /**
     * Remove the heading slash of given string
     *
     * @param value String containing a '/' at the beginning
     * @return String without '/' at the beginning
     */
    public static String removeHeadingSlash(String value) {
        if (StringUtil.isNullOrSpace(value)) {
            throw new InvalidParameterException("Argument is empty");
        }
        int positionOfSlash = value.indexOf('/');
        if (positionOfSlash >= 0) {
            return value.replace("/", "");
        }
        return value;
    }
}
