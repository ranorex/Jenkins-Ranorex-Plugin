/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ranorex.jenkinsranorexplugin.util;

import java.io.File;

/**
 *
 * @author mstoegerer
 */
public abstract class FileUtil
{

    /**
     * Translate the TestSuitefilename to the TestExecutionfilename
     *
     * @param TestSuiteFile The filepath for the Ranorex Test Suite file
     * @return The filepath for the Ranorex Test Exe file
     */
    public static String getExecutableFromTestSuite(String TestSuiteFile)
    {
        String ExecuteableFile;
        if (TestSuiteFile.contains(".rxtst"))
        {
            ExecuteableFile = TestSuiteFile.replace(".rxtst", ".exe");
        }
        else if (TestSuiteFile.contains(".exe"))
        {
            ExecuteableFile = TestSuiteFile;
        }
        else
        {
            return "Input was not a valid Test Suite File";
        }
        return ExecuteableFile;

    }

    /**
     * Tests whether the file denoted by this abstract pathname is an existing directory.
     *
     * @param value
     * @return true when the given directory exists
     */
    public static Boolean isValidDirectory(String value)
    {
        File file = new File(value);
        if (file.isDirectory())
        {
            return true;
        }
       return false;
    }

    /**
     * Tests whether this abstract pathname is absolute.
     *
     * @param value
     * @return true if and only if the file denoted by this abstract pathname
     * exists and is a directory; false otherwise
     */
    public static Boolean isAbsolutePath(String value)
    {
        File file = new File(value);
        return file.isAbsolute();
    }

    /**
     * Combines the current workspace with a relative path
     *
     * @param WorkSpace The current workspace
     * @param relPath A relative path
     * @return Absolute path
     */
    public static String combinePath(String WorkSpace, String relPath)
    {
        File pwd = new File(WorkSpace);
        File combinedPath = new File(pwd, relPath);
        return combinedPath.getAbsolutePath();
    }

    /**
     * Tests if the given reportDirectory is Absolute,
     *
     * @param workSpace
     * @param reportDirectory
     * @return Absolute Path to the ReportDirectory
     */
    public static String getAbsoluteReportDirectory(String workSpace, String reportDirectory)
    {
        String usedDirectory = "NULL";

        if (!FileUtil.isAbsolutePath(reportDirectory))
        {
            usedDirectory = FileUtil.combinePath(workSpace, reportDirectory);
        }
        else
        {
            usedDirectory = reportDirectory;
        }
        return usedDirectory;
    }
}
