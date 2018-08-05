package com.ranorex.jenkinsranorexplugin.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class CmdArgument {
    private static final ArrayList<String> IGNORE_ARGUMENTS = new ArrayList<>(Arrays.asList(
            "param", "pa",
            "listconfigparams", "lcp",
            "reportfile", "rf",
            "zipreport", "zr",
            "junit", "ju",
            "listglobalparams", "lp",
            "listtestcaseparams", "ltcpa",
            "runconfig", "rc",
            "testrail",
            "truser",
            "trpass",
            "trrunid",
            "trrunname"
    ));
    private static final String SEPARATOR = ":";

    private String argumentFlag;
    private String argumentName;
    private String argumentValue;

    public String getArgumentFlag() {
        return argumentFlag;
    }

    public String getArgumentName() {
        return argumentName;
    }

    public String getArgumentValue() {
        return argumentValue;
    }

    public CmdArgument(String argumentString) {
        //Check if Argument is in valid format and if argument is ignored
        if (isValid(argumentString) && ! isIgnored(argumentString)) {
        }
        try {
            String splitArgument[] = trySplitArgumentString(argumentString);
            switch (splitArgument.length) {
                case 3:
                    this.argumentValue = splitArgument[2];
                case 2:
                    this.argumentName = splitArgument[1];
                case 1:
                    this.argumentFlag = splitArgument[0];
                    break;
            }
        } catch (InvalidParameterException e) {
            throw e;
        }
    }

    protected static boolean isIgnored(String argumentString) {
        String flag = tryExtractFlag(argumentString);
        return IGNORE_ARGUMENTS.contains(flag);
    }

    protected static String tryExtractFlag(String argumentString) {

        int separatorPosition = argumentString.indexOf(SEPARATOR);
        if (separatorPosition > 0) {
            String flag;
            flag = argumentString.substring(0, separatorPosition);
            flag = StringUtil.removeHeadingSlash(flag);
            return flag;
        } else {
            throw new InvalidParameterException("Test");
        }
    }


    public void trim() {
        this.argumentFlag.trim();
        this.argumentName.trim();
        this.argumentValue.trim();

    }

    protected static boolean isValid(String argumentString) {
        boolean hasValidNameValuePair = hasValidNameValuePair(argumentString);
        boolean hasValiFlagNamePair = hasValidFlagNamePair(argumentString);
        return ! isIgnored(argumentString) && hasValidNameValuePair
                || ! isIgnored(argumentString) && hasValiFlagNamePair;
        //Check if format is valid (/flag:name=value || /flag:name)
    }

    protected static boolean hasValidNameValuePair(String argumentString) {
        int equalPosition = argumentString.indexOf("=");
        return equalPosition > 1 && equalPosition < argumentString.length() - 1;
    }

    protected static boolean hasValidFlagNamePair(String argumentString) {
        try {
            String flag = tryExtractFlag(argumentString);
        } catch (Exception e) {
        }
        return true;
    }

    protected static String[] trySplitArgumentString(String argumentString) {
        if (StringUtil.isNullOrSpace(argumentString)) {
            throw new InvalidParameterException("Can't split empty string");
        }
        String flag;
        String[] splitArgument;

        int separatorPosition = argumentString.indexOf(SEPARATOR);
        if (separatorPosition > 0) {
            int equalsPosition = argumentString.indexOf("=");
            if (equalsPosition < 0) {//No equal sign
                splitArgument = new String[2];
                splitArgument[1] = argumentString.substring(separatorPosition + 1, argumentString.length());
            } else {
                splitArgument = new String[3];
                splitArgument[1] = argumentString.substring(separatorPosition + 1, equalsPosition);
                splitArgument[2] = argumentString.substring(equalsPosition + 1, argumentString.length());
            }
            flag = argumentString.substring(0, separatorPosition);
        } else {//no separator
            splitArgument = new String[1];
            flag = argumentString;
        }
        try {
            flag = StringUtil.removeHeadingSlash(flag);
        } catch (InvalidParameterException e) {
            throw e;
        }
        if (! isValid(flag)) {
            throw new InvalidParameterException("Argument '" + flag + "' is not allowed");
        }
        splitArgument[0] = flag;
        return splitArgument;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(this.argumentFlag);
        if (! StringUtil.isNullOrSpace(this.argumentName)) {
            sb.append(SEPARATOR);
            sb.append(this.argumentName);
        }
        if (! StringUtil.isNullOrSpace(this.argumentValue)) {
            sb.append("=");
            sb.append(this.argumentValue);
        }
        return sb.toString();
    }
}
