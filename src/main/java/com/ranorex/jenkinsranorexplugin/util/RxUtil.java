package com.ranorex.jenkinsranorexplugin.util;

import hudson.EnvVars;
import hudson.Util;
import hudson.model.AbstractBuild;

import java.security.InvalidParameterException;


public class RxUtil {


    /**
     * Replace environment variables and buildvariables with values
     *
     * @param value String that contains variable names
     * @param build
     * @param env   Environment variables
     * @return String with values instead of variable names
     */
    public static String replaceVariables(String value, AbstractBuild<?, ?> build, EnvVars env) {
        if (StringUtil.isNullOrSpace(value)) {
            throw new InvalidParameterException("Value is null or empty");
        }

        value = Util.replaceMacro(value, env);
        value = Util.replaceMacro(value, build.getBuildVariables());
        return value;
    }
}