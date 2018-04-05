/*
 * The MIT License
 *
 * Copyright 2018 mstoegerer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ranorex.jenkinsranorexplugin.util;

import hudson.FilePath;

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mstoegerer
 */
public class FileUtilTest {

    public FileUtilTest() {
    }

    /**
     * Test of getExecutableFromTestSuite method, of class FileUtil. Input is a
     * .rxtst file Expected output is a .exe file
     */
    @Test
    public void testGetExecutableFromTestSuite_1() {
        System.out.println("\nTest :'getExecutableFromTestSuite' started");
        String TestSuiteFile = "TestSuite.rxtst";
        String expResult = "TestSuite.exe";
        String result = FileUtil.getExecutableFromTestSuite(TestSuiteFile);
        assertEquals(expResult, result);
        System.out.println("Input = '" + TestSuiteFile + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'getExecutableFromTestSuite_1' ended\n");
    }

    /**
     * Test of getExecutableFromTestSuite method, of class FileUtil. Input is a
     * .exe file Expected output is a .exe file
     */
    @Test
    public void testGetExecutableFromTestSuite_2() {
        System.out.println("\nTest :'getExecutableFromTestSuite_2' started");
        String TestSuiteFile = "C:\\Temp\\TestSuite.exe";
        String expResult = "TestSuite.exe";
        String result = FileUtil.getExecutableFromTestSuite(TestSuiteFile);
        assertEquals(expResult, result);
        System.out.println("Input = '" + TestSuiteFile + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'getExecutableFromTestSuite_2' ended\n");
    }

    /**
     * Test of getExecutableFromTestSuite method, of class FileUtil. Input is a
     * .banana file Expected output is "Input was not a valid Test Suite File"
     */
    @Test
    public void testGetExecutableFromTestSuite_3() {
        System.out.println("\nTest :'getExecutableFromTestSuite_3' started");
        String TestSuiteFile = "TestSuite.banana";
        String expResult = "Input was not a valid Test Suite File";
        String result = FileUtil.getExecutableFromTestSuite(TestSuiteFile);
        assertEquals(expResult, result);

        System.out.println("Input = '" + TestSuiteFile + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'getExecutableFromTestSuite_3' ended\n");
    }

    @Test
    public void testGetRanorexWorkingDirectory_relative() {
        try{
        System.out.println("\nTest :'testGetRanorexWorkingDirectory_relative' started");
        FilePath jenkinsDirectory = new FilePath(new File("C:\\Users\\user\\.jenkins\\workspace\\Test"));
        String testSuiteFile = ".\\bin\\Debug\\JenkinsPluginTest.rxtst";
        FilePath expResult = new FilePath(new File("C:\\Users\\user\\.jenkins\\workspace\\Test\\bin\\Debug"));
        FilePath result = FileUtil.getRanorexWorkingDirectory(jenkinsDirectory, testSuiteFile);
        assertEquals(expResult, result);

        System.out.println("jenkinsDirectory = '" + jenkinsDirectory + "'");
        System.out.println("testSuiteFile = '" + testSuiteFile + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");

        System.out.println("Test :'testGetRanorexWorkingDirectory_relative' ended\n");
        }catch(Exception e)
        {
            System.out.println("Error: " + e.toString());
        }
    }

    @Test
    public void testGetRanorexWorkingDirectory_absolute() {
        try{
        System.out.println("\nTest :'testGetRanorexWorkingDirectory_absolute' started");
        FilePath jenkinsDirectory = new FilePath(new File("C:\\Users\\user\\.jenkins\\workspace\\Test"));
        String testSuiteFile = "C:\\Temp\\JenkinsPluginTest.rxtst";
        FilePath expResult = new FilePath(new File("C:\\Temp"));
        FilePath result = FileUtil.getRanorexWorkingDirectory(jenkinsDirectory, testSuiteFile);
        assertEquals(expResult, result);

        System.out.println("jenkinsDirectory = '" + jenkinsDirectory + "'");
        System.out.println("testSuiteFile = '" + testSuiteFile + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");

        System.out.println("Test :'testGetRanorexWorkingDirectory_absolute' ended\n");
        }catch(Exception e)
        {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     * Test of isAbsolutePath method, of class FileUtil.
     */
    @Test
    public void testIsAbsolutePath_true() {
        System.out.println("\nTest :'testIsAbsolutePath_true' started");
        String value = "C:\\Temp";
        boolean expResult = true;
        boolean result = FileUtil.isAbsolutePath(value);
        assertEquals(expResult, result);

        System.out.println("Input = '" + value + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");

        System.out.println("Test :'testIsAbsolutePath_true' ended\n");
    }

    /**
     * Test of isAbsolutePath method, of class FileUtil.
     */
    @Test
    public void testIsAbsolutePath_false() {
        System.out.println("\nTest :'testIsAbsolutePath_false' started");
        String value = ".\\Temp";
        boolean expResult = false;
        boolean result = FileUtil.isAbsolutePath(value);
        assertEquals(expResult, result);

        System.out.println("Input = '" + value + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'testIsAbsolutePath_false' ended\n");
    }

    /**
     * Test of combinePath method, of class FileUtil.
     */
    @Test
    public void testCombinePath_withDot() {
        System.out.println("\nTest :'testCombinePath_withDot' started");
        String WorkSpace = "C:\\Temp";
        String relPath = ".\\relative\\file";
        String expResult = "C:\\Temp\\relative\\file";
        String result = FileUtil.combinePath(WorkSpace, relPath);
        assertEquals(expResult, result);
        System.out.println("Input [Workspace] = '" + WorkSpace + "'");
        System.out.println("Input [relPath] = '" + relPath + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'testCombinePath_withDot' started\n");
    }

    /**
     * Test of combinePath method, of class FileUtil.
     */
    @Test
    public void testCombinePath_withoutDot() {
        System.out.println("\nTest :'testCombinePath_withoutDot' started");
        String WorkSpace = "C:\\Temp";
        String relPath = "\\relative\\file";
        String expResult = "C:\\Temp\\relative\\file";
        String result = FileUtil.combinePath(WorkSpace, relPath);
        assertEquals(expResult, result);
        System.out.println("Input [Workspace] = '" + WorkSpace + "'");
        System.out.println("Input [relPath] = '" + relPath + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'testCombinePath_withoutDot' started\n");
    }

    /**
     * Test of getAbsoluteReportDirectory method, of class FileUtil.
     */
    @Test
    public void testGetAbsoluteReportDirectory_absoluteInput() {
        System.out.println("\nTest :'testGetAbsoluteReportDirectory_absoluteInput' started");
        String workSpace = "C:\\Users\\user\\.jenkins\\workspace\\Test";
        String reportDirectory = "C:\\Temp\\ReportDir";
        String expResult = "C:\\Temp\\ReportDir";
        String result = FileUtil.getAbsoluteReportDirectory(workSpace, reportDirectory);
        assertEquals(expResult, result);

        System.out.println("Input [Workspace] = '" + workSpace + "'");
        System.out.println("Input [reportDirectory] = '" + reportDirectory + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'testGetAbsoluteReportDirectory_absoluteInput' ended\n");
    }

    /**
     * Test of getAbsoluteReportDirectory method, of class FileUtil.
     */
    @Test
    public void testGetAbsoluteReportDirectory_relativeInput() {
        System.out.println("\nTest :'testGetAbsoluteReportDirectory_relativeInput' started");
        String workSpace = "C:\\Users\\user\\.jenkins\\workspace\\Test";
        String reportDirectory = ".\\ReportDir";
        String expResult = "C:\\Users\\user\\.jenkins\\workspace\\Test\\ReportDir";
        String result = FileUtil.getAbsoluteReportDirectory(workSpace, reportDirectory);
        assertEquals(expResult, result);

        System.out.println("Input [Workspace] = '" + workSpace + "'");
        System.out.println("Input [reportDirectory] = '" + reportDirectory + "'");
        System.out.println("Expected output = '" + expResult + "'");
        System.out.println("Result = '" + result + "'");
        System.out.println("Test :'testGetAbsoluteReportDirectory_relativeInput' ended\n");
    }
}
