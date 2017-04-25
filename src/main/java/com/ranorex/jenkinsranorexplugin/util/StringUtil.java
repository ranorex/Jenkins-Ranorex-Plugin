package com.ranorex.jenkinsranorexplugin.util;

import java.io.File;
import java.util.List;

public abstract class StringUtil
{

    private StringUtil()
    {
    }

    /**
     *
     * @param value The string which should be modified
     * @return The initial string with appended quotes
     */
    public static String appendQuote(String value)
    {
        if (!(value.charAt(0) == '"') && !(value.charAt(value.length() - 1) == '"'))
        {
            return String.format("\"%s\"", value);
        }
        return value;
    }

    /**
     * Null or Space
     *
     * @param value The string which should be checked
     * @return True if the String is null or Space
     */
    public static boolean isNullOrSpace(String value)
    {
        return (value == null || value.trim().length() == 0);
    }

    /**
     *
     * @param args A list of Strings that should be connected
     * @return A connected String
     */
    public static String concatString(List<String> args)
    {
        StringBuilder buf = new StringBuilder();
        for (String arg : args)
        {
            if (buf.length() > 0)
            {
                buf.append(' ');
            }
            buf.append(arg);
        }
        return buf.toString();
    }

    public static String appendBackslash(String value)
    {
        //int length = value.length() - 1;
        char charAtLastPosition = value.charAt(value.length() - 1);
        if (charAtLastPosition != '\\')
        {
            value = String.format("%s\\", value);
        }
        return value;
    }

    public static Boolean isValidDirectory(String value)
    {
        File file = new File(value);
        if (file.isDirectory())
        {
            return true;
        }
        return false;
    }

    public static Boolean isAbsolutePath(String value)
    {
        File file = new File(value);
        return file.isAbsolute();
    }
}
