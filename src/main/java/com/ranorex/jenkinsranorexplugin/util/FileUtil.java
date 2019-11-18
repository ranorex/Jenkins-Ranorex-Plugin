/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ranorex.jenkinsranorexplugin.util;

import hudson.FilePath;

import java.io.File;

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
        String ExecutableFile;
        if (TestSuiteFile.contains(".rxtst")) {
            ExecutableFile = TestSuiteFile.replace(".rxtst", ".exe");
        } else if (TestSuiteFile.contains(".exe")) {
            ExecutableFile = TestSuiteFile;
        } else {
            return "Input was not a valid Test Suite File";
        }

        String[] splitPath = StringUtil.splitPath(ExecutableFile);
        return splitPath[splitPath.length - 1];

    }

    /**
     * Get the absolute path to the Ranorex Test Suite file
     *
     * @param jenkinsDirectory The current workspace for the Jenkins Job
     * @param testSuiteFile    The path to the Ranorex Test Suite
     * @return The directory in which the Ranorex Test Suite is located
     */
    public static FilePath getRanorexWorkingDirectory(FilePath jenkinsDirectory, String testSuiteFile) {
        String[] splitName = StringUtil.splitPath(testSuiteFile);
        StringBuilder directory = new StringBuilder();

        //If the Test Suite Path is relative, append it to the Jenkins Workspace
        if (! isAbsolutePath(testSuiteFile)) {
            directory.append(jenkinsDirectory.getRemote());
        }

        for (String name : splitName) {
            if (! ".".equals(name) && ! name.contains(".rxtst") && ! name.contains(".exe")) {
                if (name.toCharArray().length > 1 && (name.toCharArray())[1] == ':') {
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
        if (! StringUtil.isNullOrSpace(value)) {
            char[] chars = value.toCharArray();
            /*
             * we use this instead of file.isAbsolute() because it will provide false
             * negative return values if the master node is a unix based system. Since the
             * execution node must be a Windows system, this check should be ok.
             */

            return (chars[1] == ':' || value.startsWith("\\\\"));
        } else {
            return false;
        }
    }

    /**
     * Combines the current workspace with a relative path
     *
     * @param WorkSpace The current workspace
     * @param relPath   A relative path
     * @return Absolute path
     */
    public static String combinePath(String WorkSpace, String relPath) {
        //Remove '.' from the beginning at relPath
        if (relPath.charAt(0) == '.') {
            relPath = removeFirstCharacterOfString(relPath);
        }
        //Remove '\' from the beginning at relPath
        if (relPath.charAt(0) == '\\' && WorkSpace.charAt(WorkSpace.length() - 1) == '\\') {
            relPath = removeFirstCharacterOfString(relPath);
        }
        return (WorkSpace + (relPath.replace("/", File.separator)));
    }

    private static String removeFirstCharacterOfString(String value) {
        return value.substring(1, value.length());
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

        if (! FileUtil.isAbsolutePath(reportDirectory)) {
            usedDirectory = FileUtil.combinePath(workSpace, reportDirectory);
        } else {
            usedDirectory = reportDirectory;
        }
        return usedDirectory;
    }


    /**
     * @param fileName The filename including the extension
     * @return The filename without extension
     */
    public static String removeFileExtension(String fileName) {
        if (! StringUtil.isNullOrSpace(fileName)) {
            String fileNameWithoutExtension;
            int position = fileName.lastIndexOf(".");
            if (position > 0) {
                fileNameWithoutExtension = fileName.substring(0, position);
                return fileNameWithoutExtension;
            }
        }
        return fileName;
    }
}
