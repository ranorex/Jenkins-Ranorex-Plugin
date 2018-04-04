package com.ranorex.jenkinsranorexplugin.util;

import java.util.List;

public abstract class StringUtil {

    private StringUtil() {
    }

    /**
     * Appends quote to the input string
     *
     * @param value The string which should be modified
     * @return The initial string with appended quotes
     */
    public static String appendQuote(String value) {
        if (!(value.charAt(0) == '"') && !(value.charAt(value.length() - 1) == '"')) {
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
     * Converts the input list into a single string separated by " "
     *
     * @param args A list of Strings that should be connected
     * @return A connected String
     */
    public static String concatString(List<String> args) {
        StringBuilder buf = new StringBuilder();
        for (String arg : args) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append(arg);
        }
        return buf.toString();
    }

    /**
     * Splits the input string by "/"
     *
     * @param value input string
     * @return String array
     */
    public static String[] splitPath(String value) {
        String[] splittedName = value.split("/");
        if (splittedName.length == 1 && value.contains("\\")) {
            splittedName = value.split("\\\\");
        }
        return splittedName;
    }

    /**
     * Appends backslash to the input string if not already given
     *
     * @param value input string
     * @return input string + \
     */
    public static String appendBackslash(String value) {
        // int length = value.length() - 1;
        char charAtLastPosition = value.charAt(value.length() - 1);
        if (charAtLastPosition != '\\') {
            value = String.format("%s\\", value);
        }
        return value;
    }
}
