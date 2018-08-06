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
        if (StringUtil.isNullOrSpace(argumentString)) {
            throw new IllegalArgumentException("Argument must be not null or empty!");
        }
        //isValid(argumentString) &&
        if (! isIgnored(argumentString)) {
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
        } else {
            throw new InvalidParameterException("Argument '" + argumentString + "' will be ignored");
        }
    }

    //Tested
    protected static boolean isIgnored(String argumentString) {
        if (StringUtil.isNullOrSpace(argumentString)) {
            throw new IllegalArgumentException("Argument must be not null or empty!");
        }
        try {
            String flag = tryExtractFlag(argumentString);
            return IGNORE_ARGUMENTS.contains(flag);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    //Tested
    protected static String tryExtractFlag(String argumentString) {
        if (StringUtil.isNullOrSpace(argumentString)) {
            throw new IllegalArgumentException("Cannot extract flag from empty or null string");
        }
        String flag;
        int separatorPosition = argumentString.indexOf(SEPARATOR);
        if (separatorPosition > 0) {
            flag = argumentString.substring(0, separatorPosition);
        } else {
            flag = argumentString;
        }
        flag = StringUtil.removeHeadingSlash(flag);
        return flag;
    }


    public void trim() {
        try {
            this.argumentFlag.trim();
            this.argumentName.trim();
            this.argumentValue.trim();
        } catch (NullPointerException e) {
            System.out.println("Exception " + e.getMessage());
        }
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
