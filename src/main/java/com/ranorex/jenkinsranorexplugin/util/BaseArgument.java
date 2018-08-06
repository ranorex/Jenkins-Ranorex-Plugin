package com.ranorex.jenkinsranorexplugin.util;

public abstract class BaseArgument {
    protected final static String SEPARATOR = ":";
    protected String flag;
    protected String name;
    protected String value;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(this.flag);
        if (! StringUtil.isNullOrSpace(this.name)) {
            sb.append(SEPARATOR);
            sb.append(this.name);
        }
        if (! StringUtil.isNullOrSpace(this.value)) {
            sb.append("=");
            sb.append(this.value);
        }
        return sb.toString();
    }

    public static String tryExtractFlag(String argumentString) {
        if (StringUtil.isNullOrSpace(argumentString)) {
            throw new IllegalArgumentException("Argument must not be null or empty");
        }
        int separatorPosition = argumentString.indexOf(SEPARATOR);
        String flag;
        if (separatorPosition > 0) {
            flag = argumentString.substring(0, separatorPosition);
        } else {
            flag = argumentString;
        }
        flag = StringUtil.removeHeadingSlash(flag);
        return flag;
    }

    public static boolean containsValidNameValuePair(String argumentString) {
        int equalPosition = argumentString.indexOf("=");
        return equalPosition > 1 && equalPosition < argumentString.length() - 1;
    }

    public void trim() {
        flag.trim();
        name.trim();
        value.trim();
    }
}
