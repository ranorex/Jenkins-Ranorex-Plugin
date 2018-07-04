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
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * @author mstoegerer
 */
public class FileUtilTest {
    private final String _testSuiteFileWithoutSpace = "TestSuite.rxtst";
    private final String _testSuiteFileWithSpace = "Test Suite.rxtst";
    private final String _invalidTestSuiteFileWithoutSpace = "TestSuite.xyz";
    private final String _invalidTestSuiteFileWithSpace = "Test Suite.xyz";
    private final String _testExeFileWithoutSpace = "TestSuite.exe";
    private final String _testExeFileWithSpace = "Test Suite.exe";
    private final String _jenkinsWorkSpaceWithoutSpace = "C:\\Users\\user\\.jenkins\\workspace\\";
    private final String _jenkinsWorkSpaceWithSpace = "C:\\Users\\us er\\.jenkins\\workspace\\";
    private final String _jenkinsJobNameWithoutSpace = "TestJobWithOutSpace";
    private final String _jenkinsJobNameWithSpace = "Test Job with Space";
    private final String _relativeDirectoryWithDotWithoutSpace = ".\\bin\\Debug\\";
    private final String _relativeDirectoryWithDotWithSpace = ".\\bin\\De b ug\\";
    private final String _relativeDirectoryWithouDotWithoutSpace = "\\bin\\Debug\\";
    private final String _relativeDirectoryWithoutDotWithSpace = "\\bin\\D e b u g\\";

    private final String _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace = "C:\\Temp\\";
    private final String _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace = "C:\\Temp Directory\\";

    private final String _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpaceWithoutBackslash = "C:\\Temp";
    private final String _absoluteTestSuiteDirectoryOutsideJenkinsWithSpaceWithoutBackslash = "C:\\Temp Directory\\";

    private final String _FileNameWithoutSpaceWithZippedExtension = "RanorexReport.rxzlog";
    private final String _FileNameWithoutSpaceWithoutExtension = "RanorexReport";
    private final String _FileNameWithSpaceWithZippedExtension = "Ranorex Rep ort.rxzlog";
    private final String _FileNameWithSpaceWithoutExtension = "Ranorex Rep ort";

    /**
     * Combined Constants
     **/
    private final String _absoluteJenkinsJobWithoutSpace = _jenkinsWorkSpaceWithoutSpace + _jenkinsJobNameWithoutSpace; // "C:\\Users\\user\\.jenkins\\workspace\\TestJobWithOutSpace"
    private final String _absoluteJenkinsJobWithSpace = _jenkinsWorkSpaceWithSpace + _jenkinsJobNameWithSpace; //"C:\\Users\\us er\\.jenkins\\workspace\\Test Job with Space"
    private final String _absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithoutSpace = _absoluteJenkinsJobWithoutSpace + "\\bin\\Debug";
    private final String _absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithSpace = _absoluteJenkinsJobWithSpace + "\\bin\\De b ug";


    public FileUtilTest() {
    }

    /**
     * Test getExecutableFromTestSuite method
     *
     * @Input Test Suite without space
     * @ExpectedResult Test Executable without space
     */
    @Test
    public void GetExecutableFromTestSuite_TestSuiteWithoutSpace_ExeWithoutSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testSuiteFileWithoutSpace);
        assertEquals(_testExeFileWithoutSpace, actualResult);
    }

    /**
     * Test getExecutableFromTestSuite method
     *
     * @Input Test Suite with space
     * @ExpectedResult Test Executable with space
     */
    @Test
    public void GetExecutableFromTestSuite_TestSuiteWithSpace_ExeWithSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testSuiteFileWithSpace);
        assertEquals(_testExeFileWithSpace, actualResult);
    }

    /**
     * Test getExecutableFromTestSuite method
     *
     * @Input Test Executable without space
     * @ExpectedResult Test Executable without space
     */
    @Test
    public void GetExecutableFromTestSuite_ExeWithoutSpace_ExeWithoutSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testExeFileWithoutSpace);
        assertEquals(_testExeFileWithoutSpace, actualResult);
    }

    /**
     * Test getExecutableFromTestSuite method
     *
     * @Input Test Executable with space
     * @ExpectedResult Test Executable with space
     */
    @Test
    public void GetExecutableFromTestSuite_ExeWithSpace_ExeWithSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testExeFileWithSpace);
        assertEquals(_testExeFileWithSpace, actualResult);
    }

    /**
     * Test getExecutableFromTestSuite method
     *
     * @Input Invalid Test Suite name without space
     * @ExpectedResult Error message: "Input was not a valid Test Suite File"
     */
    @Test
    public void GetExecutableFromTestSuite_InvalidTestSuiteWithoutSpace_ErrorMessage() {
        String expectedResult = "Input was not a valid Test Suite File";
        String actualResult = FileUtil.getExecutableFromTestSuite(_invalidTestSuiteFileWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getExecutableFromTestSuite method
     *
     * @Input Invalid Test Suite name with space
     * @ExpectedResult Error message: "Input was not a valid Test Suite File"
     */
    @Test
    public void GetExecutableFromTestSuite_InvalidTestSuiteWithSpace_ErrorMessage() {
        String expectedResult = "Input was not a valid Test Suite File";
        String actualResult = FileUtil.getExecutableFromTestSuite(_invalidTestSuiteFileWithSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getRanorexWorkingDirectory method
     *
     * @Input Relative path to Ranorex Test Suite directory without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory without space
     */
    @Test
    public void GetRanorexWorkingDirectory_RelativeTestSuitePathWithoutSpace_AbsoluteTestSuitePathWithoutSpace() {
        FilePath JenkinsJobDirectory = new FilePath(new File(_absoluteJenkinsJobWithoutSpace));
        String relativeTestSuitePath = _relativeDirectoryWithDotWithoutSpace + _testSuiteFileWithoutSpace;
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithoutSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(JenkinsJobDirectory, relativeTestSuitePath);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getRanorexWorkingDirectory method
     *
     * @Input Relative path to Ranorex Test Suite directory with space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space
     */
    @Test
    public void GetRanorexWorkingDirectory_RelativeTestSuitePathWithSpace_AbsoluteTestSuitePathWithSpace() {
        FilePath JenkinsJobDirectory = new FilePath(new File(_absoluteJenkinsJobWithSpace));
        String relativeTestSuitePath = _relativeDirectoryWithDotWithSpace + _testSuiteFileWithSpace;
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(JenkinsJobDirectory, relativeTestSuitePath);
        assertEquals(expectedResult, actualResult);

    }

    /**
     * Test getRanorexWorkingDirectory method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory without space
     */
    @Test
    public void GetRanorexWorkingDirectory_AbsoluteTestSuitePathWithoutSpace_AbsoluteTestSuitePathWithoutSpace() {

        FilePath jenkinsDirectory = new FilePath(new File(_absoluteJenkinsJobWithoutSpace));
        String absoluteTestSuitePath = _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace + _testSuiteFileWithoutSpace;
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(jenkinsDirectory, absoluteTestSuitePath);
        assertEquals(expectedResult, actualResult);

    }

    /**
     * Test getRanorexWorkingDirectory method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory without space
     */
    @Test
    public void GetRanorexWorkingDirectory_AbsoluteTestSuitePathWithSpace_AbsoluteTestSuitePathWithSpace() {
        try {
            FilePath jenkinsDirectory = new FilePath(new File(_absoluteJenkinsJobWithSpace));
            String absoluteTestSuitePath = _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace + _testSuiteFileWithSpace;
            FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace));
            FilePath actualResult = FileUtil.getRanorexWorkingDirectory(jenkinsDirectory, absoluteTestSuitePath);
            assertEquals(expectedResult, actualResult);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     * Test isAbsolutePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @ExpectedResult true
     */
    @Test
    public void IsAbsolutePath_AbsolutePathWithoutSpace_True() {
        String input = _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace;
        boolean actualResult = FileUtil.isAbsolutePath(input);
        assertEquals(true, actualResult);
    }

    /**
     * Test isAbsolutePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory with space
     * @ExpectedResult true
     */
    @Test
    public void IsAbsolutePath_AbsolutePathWithSpace_True() {
        String input = _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace;
        boolean actualResult = FileUtil.isAbsolutePath(input);
        assertEquals(true, actualResult);
    }

    /**
     * Test isAbsolutePath method
     *
     * @Input Relative path to Ranorex Test Suite directory without space
     * @ExpectedResult false
     */
    @Test
    public void IsAbsolutePath_RelativePathWithoutSpace_False() {
        String input = _relativeDirectoryWithDotWithoutSpace;
        boolean actualResult = FileUtil.isAbsolutePath(input);
        assertEquals(false, actualResult);
    }

    /**
     * Test isAbsolutePath method
     *
     * @Input Relative path to Ranorex Test Suite directory wit space
     * @ExpectedResult false
     */
    @Test
    public void IsAbsolutePath_RelativePathWithSpace_False() {
        String input = _relativeDirectoryWithDotWithSpace;
        boolean actualResult = FileUtil.isAbsolutePath(input);
        assertEquals(false, actualResult);
    }

    /**
     * Test combinePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @Input Relative path with dot without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory without space without dot
     */
    @Test
    public void CombinePath_AbsoluePathWithDotWithoutSpace_AbsolutePathWithoutDotWithoutSpace() {
        String expectedResult = "C:\\Temp\\bin\\Debug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, _relativeDirectoryWithDotWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test combinePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @Input Relative path with dot with space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space without dot
     */
    @Test
    public void CombinePath_AbsolutePathWithDotWithSpaceWithoutBackslash_AbsolutePathWithoutDotWithSpace() {
        String expectedResult = "C:\\Temp Directory\\bin\\De b ug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpaceWithoutBackslash, _relativeDirectoryWithDotWithSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test combinePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @Input Relative path with dot with space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space without dot
     */
    @Test
    public void CombinePath_AbsolutePathWithDotWithoutSpaceWithoutBackslash_AbsolutePathWithoutDotWithoutSpace() {
        String expectedResult = "C:\\Temp\\bin\\Debug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpaceWithoutBackslash, _relativeDirectoryWithDotWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test combinePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @Input Relative path with dot with space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space without dot
     */
    @Test
    public void CombinePath_AbsoluePathWithDotWithSpace_AbsolutePathWithoutDotWithSpace() {
        String expectedResult = "C:\\Temp Directory\\bin\\De b ug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, _relativeDirectoryWithDotWithSpace);
        assertEquals(expectedResult, actualResult);
    }


    /**
     * Test combinePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory without space
     * @Input Relative path without dot without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory without space
     */
    @Test
    public void CombinePath_AbsolutePathWithoutDotWithoutSpace_AbsolutePathWithoutDotWithoutSpace() {
        String expectedResult = "C:\\Temp\\bin\\Debug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, _relativeDirectoryWithouDotWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test combinePath method
     *
     * @Input Absolute path to Ranorex Test Suite directory with space
     * @Input Relative path with space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space
     */
    @Test
    public void CombinePath_AbsolutePathWithoutDotWithSpace_AbsolutePathWithoutDotWithSpace() {
        String expectedResult = "C:\\Temp Directory\\bin\\D e b u g\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, _relativeDirectoryWithoutDotWithSpace);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getAbsoluteReportDirectory method
     *
     * @Input Absolute Test Suite directory without space
     * @Input Absolute Test Suite directory without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory without space
     */
    @Test
    public void GetAbsoluteReportDirectory_AbsolutePathWithoutSpace_AbsolutePathWithoutSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, actualResult);
    }

    /**
     * Test getAbsoluteReportDirectory method
     *
     * @Input Absolute Test Suite directory with space
     * @Input Absolute Test Suite directory with space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space
     */
    @Test
    public void GetAbsoluteReportDirectory_AbsolutePathWithSpace_AbsolutePathWithSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, actualResult);
    }

    /**
     * Test getAbsoluteReportDirectory method
     *
     * @Input Relative Directory wihtout dot without space
     * @Input Absolute Test Suite directory without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space
     */
    @Test
    public void GetAbsoluteReportDirectory_RelativePathWithoutSpace_AbsolutePathWithoutSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_relativeDirectoryWithouDotWithoutSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, actualResult);
    }

    /**
     * Test getAbsoluteReportDirectory method
     *
     * @Input Relative Directory wihtout dot without space
     * @Input Absolute Test Suite directory without space
     * @ExpectedResult Absolute path to Ranorex Test Suite directory with space
     */
    @Test
    public void GetAbsoluteReportDirectory_RelativePathWithSpace_AbsolutePathWithSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_relativeDirectoryWithoutDotWithSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, actualResult);
    }

    /**
     * Test ignoreFileExtension method
     *
     * @Input Valid filename without space with extension
     * @Expectedresult Valid filename without space without extension
     */
    @Test
    public void IgnoreFileExtension_ValidFileNameWithoutSpaceWithZippedExtension_ValidFileNameWithoutSpaceWithoutExtension() {
        String actualResult = FileUtil.ignoreFileExtension(_FileNameWithoutSpaceWithZippedExtension);
        assertEquals(_FileNameWithoutSpaceWithoutExtension, actualResult);
    }

    /**
     * Test ignoreFileExtension method
     *
     * @Input Valid filename with space with extension
     * @Expectedresult Valid filename with space without extension
     */
    @Test
    public void IgnoreFileExtension_ValidFileNameWithSpaceWithZippedExtension_ValidFileNameWithoutSpaceWithoutExtension() {
        String actualResult = FileUtil.ignoreFileExtension(_FileNameWithSpaceWithZippedExtension);
        assertEquals(_FileNameWithSpaceWithoutExtension, actualResult);
    }

    /**
     * Test ignoreFileExtension method
     *
     * @Input null
     * @Expectedresult null
     */
    @Test
    public void IgnoreFileExtension_NULL_NULL() {
        String actualResult = FileUtil.ignoreFileExtension(null);
        assertEquals(null, actualResult);
    }

    /**
     * Test ignoreFileExtension method
     *
     * @Input " "
     * @Expectedresult null
     */
    @Test
    public void IgnoreFileExtension_SPACE_SPACE() {
        String actualResult = FileUtil.ignoreFileExtension(" ");
        assertEquals(" ", actualResult);
    }

    /**
     * Test ignoreFileExtension method
     *
     * @Input Valid filename with space and extension
     * @Expectedresult Valid filename with space without extension
     */
    @Test
    public void IgnoreFileExtension_InvalidFileName_NULL() {
        String actualResult = FileUtil.ignoreFileExtension("ThisFileHasNoExtension");
        assertEquals("ThisFileHasNoExtension", actualResult);
    }
}
