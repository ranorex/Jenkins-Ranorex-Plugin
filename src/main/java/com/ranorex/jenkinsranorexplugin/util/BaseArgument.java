package com.ranorex.jenkinsranorexplugin.util;

public abstract class BaseArgument {
    protected final static String SEPARATOR = ":";
    protected String flag;
    protected String name;
    protected String value;

    public String getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

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

    public void trim() {
        try {
            flag.trim();
            name.trim();
            value.trim();
        } catch (NullPointerException e) {
            System.out.println("[WARNING] [CmdArgument] - Method trim() threw NullPointerException because part of the Argument where null or empty");
        }
    }
}
