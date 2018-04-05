/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ranorex.jenkinsranorexplugin.util;

import java.io.File;

import hudson.FilePath;

/**
 * @author mstoegerer
 */
public abstract class FileUtil {

    /**
     * Translate the TestSuitefilename to the TestExecutionfilename
     *
     * @param TestSuiteFile The filepath for the Ranorex Test Suite file
     * @return The filepath for the Ranorex Test Exe file
     */
    public static String getExecutableFromTestSuite(String TestSuiteFile) {
        String ExecuteableFile;
        if (TestSuiteFile.contains(".rxtst")) {
            ExecuteableFile = TestSuiteFile.replace(".rxtst", ".exe");
        } else if (TestSuiteFile.contains(".exe")) {
            ExecuteableFile = TestSuiteFile;
        } else {
            return "Input was not a valid Test Suite File";
        }

        String[] splittedPath = StringUtil.splitPath(ExecuteableFile);
        return splittedPath[splittedPath.length - 1];
    }

    /**
     * Get the absolute path to the Ranorex Test Suite file
     *
     * @param jenkinsDirectory The current workspace for the Jenkins Job
     * @param testSuiteFile    The path to the Ranorex Test Suite
     * @return The directory in which the Ranorex Test Suite is located
     */
    public static FilePath getRanorexWorkingDirectory(FilePath jenkinsDirectory, String testSuiteFile) {
        String[] splittedName = StringUtil.splitPath(testSuiteFile);
        StringBuilder directory = new StringBuilder();

        //If the Test Suite Path is relative, append it to the Jenkins Workspace
        if (!isAbsolutePath(testSuiteFile)) {
            directory.append(jenkinsDirectory.getRemote());
        }

        for (String name : splittedName) {
            if (!".".equals(name) && !name.contains(".rxtst")) {
                if ((name.toCharArray())[1] == ':') {
                    directory.append(name);
                } else {
                    directory.append("\\").append(name);
                }
            }
        }
        return new FilePath(new File(directory.toString()));
    }

    /**
     * Tests whether this abstract pathname is absolute.
     *
     * @param value Input path
     * @return true if and only if the file denoted by this abstract pathname
     * exists and is a directory; false otherwise
     */
    public static boolean isAbsolutePath(String value) {
        char[] chars = value.toCharArray();
        /*
         * we use this instead of file.isAbsolute() because it will provide false
         * negative return values if the master node is a unix based system. Since the
         * execution node must be a Windows system, this check should be ok.
         */
        return chars[1] == ':' || value.startsWith("\\\\");
    }

    /**
     * Combines the current workspace with a relative path
     *
     * @param WorkSpace The current workspace
     * @param relPath   A relative path
     * @return Absolute path
     */
    public static String combinePath(String WorkSpace, String relPath) {
        String substring;
        //Remove '.\' from the beginning at relPath
        if (relPath.toCharArray()[0] == '.') {
            substring = relPath.substring(1, relPath.length());
            relPath = substring;
        }
       /* //Remove '\' from the beginning at relPath
        if (relPath.toCharArray()[0] == '\\') {
            substring = relPath.substring(1, relPath.length());
            relPath = substring;
        }*/
        return (WorkSpace + (relPath.replace("/", File.separator)));
    }

    /**
     * Tests if the given reportDirectory is Absolute,
     *
     * @param workSpace       The current Jenkins Job workspace
     * @param reportDirectory The Ranorex Report directory
     * @return Absolute Path to the ReportDirectory
     */
    public static String getAbsoluteReportDirectory(String workSpace, String reportDirectory) {
        String usedDirectory;

        if (!FileUtil.isAbsolutePath(reportDirectory)) {
            usedDirectory = FileUtil.combinePath(workSpace, reportDirectory);
        } else {
            usedDirectory = reportDirectory;
        }
        return usedDirectory;
    }
}
