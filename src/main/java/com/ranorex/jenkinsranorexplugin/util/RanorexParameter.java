package com.ranorex.jenkinsranorexplugin.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class RanorexParameter {
    private static final ArrayList<String> WHITELIST_PARAM_FLAGS = new ArrayList<>(Arrays.asList(
            "pa", "param"
    ));
    private static final String SEPARATOR = ":";

    private String parameterFlag;
    private String parameterName;
    private String parameterValue;

    public RanorexParameter(String parameterString) {
        if (isValid(parameterString)) {
            try {
                String[] splitParam = trySplitParameterString(parameterString);
                this.parameterFlag = splitParam[0];
                this.parameterName = splitParam[1];
                this.parameterValue = splitParam[2];
            } catch (InvalidParameterException e) {
                throw e;
            }
        } else {
            throw new InvalidParameterException("'" + parameterString + "' is not a valid Parameter");
        }
    }

    protected static String[] trySplitParameterString(String parameterString) {
        if (StringUtil.isNullOrSpace(parameterString)) {
            throw new InvalidParameterException("parameterString is null or empty");
        }

        String splitParam[] = new String[3];
        try {
            splitParam[0] = tryExtractFlag(parameterString);
        } catch (InvalidParameterException e) {
            System.out.println("[INFO] [RanorexParameter]: Method tryExtractFlag() threw an InvalidParameterException \n\t" + e.getMessage() + "\n\tParameterflag 'pa' will be used as default");
            splitParam[0] = "pa";
        }
        if (containsValidNameValuePair(parameterString)) {
            int equalsPosition = parameterString.indexOf("=");
            int separatorPosition = parameterString.indexOf(SEPARATOR);
            splitParam[1] = parameterString.substring(separatorPosition + 1, equalsPosition);
            splitParam[2] = parameterString.substring(equalsPosition + 1, parameterString.length());
        } else {
            throw new InvalidParameterException("Parameter is not valid");
        }
        return splitParam;
    }

    public static boolean isValidFlag(String parameterFlag) {
        return WHITELIST_PARAM_FLAGS.contains(parameterFlag.trim());
    }

    public static boolean containsValidNameValuePair(String parameterString) {
        int equalPosition = parameterString.indexOf("=");
        return equalPosition > 0 && equalPosition < parameterString.length() - 1;
    }

    public static boolean isValid(String parameterString) {
        String flag = "";
        try {
            flag = tryExtractFlag(parameterString);
        } catch (Exception e) {
        }
        boolean isValidNameValuePair = containsValidNameValuePair(parameterString);
        return isValidFlag(flag) && isValidNameValuePair
                || StringUtil.isNullOrSpace(flag) && isValidNameValuePair;
    }

    public static String tryExtractFlag(String parameterString) {
        int separatorPosition = parameterString.indexOf(SEPARATOR);
        if (separatorPosition > 0) {
            String flag = parameterString.substring(0, separatorPosition);
            flag = StringUtil.removeHeadingSlash(flag);
            return flag;
        } else {
            throw new InvalidParameterException("Parameter '" + parameterString + "' does not contain a separator!");
        }
    }

    public String getParameterFlag() {
        return parameterFlag;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void trim() {
        if (StringUtil.isNullOrSpace(parameterFlag) || StringUtil.isNullOrSpace(parameterName) || StringUtil.isNullOrSpace(parameterValue)) {
            throw new InvalidParameterException("Parameter is null or empty");
        }
        parameterFlag = parameterFlag.trim();
        parameterName = parameterName.trim();
        parameterValue = parameterValue.trim();
    }

    @Override
    public String toString() {
        if (StringUtil.isNullOrSpace(parameterFlag) || StringUtil.isNullOrSpace(parameterName) || StringUtil.isNullOrSpace(parameterValue)) {
            throw new InvalidParameterException("Parameter is not valid");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(this.parameterFlag);
        sb.append(SEPARATOR);
        sb.append(this.parameterName);
        sb.append("=");
        sb.append(this.parameterValue);
        return sb.toString();
    }
}
