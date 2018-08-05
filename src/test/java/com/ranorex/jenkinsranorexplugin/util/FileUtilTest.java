package com.ranorex.jenkinsranorexplugin.util;

import hudson.FilePath;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {
    private final String _testSuiteFileWithoutSpace = "TestSuite.rxtst";
    private final String _testSuiteFileWithSpace = "Test Suite.rxtst";
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

    /**
     * Combined Values
     **/
    private final String _absoluteJenkinsJobWithoutSpace = _jenkinsWorkSpaceWithoutSpace + _jenkinsJobNameWithoutSpace; // "C:\\Users\\user\\.jenkins\\workspace\\TestJobWithOutSpace"
    private final String _absoluteJenkinsJobWithSpace = _jenkinsWorkSpaceWithSpace + _jenkinsJobNameWithSpace; //"C:\\Users\\us er\\.jenkins\\workspace\\Test Job with Space"


    @Test
    void GetExecutableFromTestSuite_TestSuiteWithoutSpace_ExeWithoutSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testSuiteFileWithoutSpace);
        assertEquals(_testExeFileWithoutSpace, actualResult);
    }

    @Test
    void GetExecutableFromTestSuite_TestSuiteWithSpace_ExeWithSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testSuiteFileWithSpace);
        assertEquals(_testExeFileWithSpace, actualResult);
    }

    @Test
    void GetExecutableFromTestSuite_ExeWithoutSpace_ExeWithoutSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testExeFileWithoutSpace);
        assertEquals(_testExeFileWithoutSpace, actualResult);
    }

    @Test
    void GetExecutableFromTestSuite_ExeWithSpace_ExeWithSpace() {
        String actualResult = FileUtil.getExecutableFromTestSuite(_testExeFileWithSpace);
        assertEquals(_testExeFileWithSpace, actualResult);
    }


    @Test
    void GetExecutableFromTestSuite_InvalidTestSuiteWithoutSpace_ErrorMessage() {
        String expectedResult = "Input was not a valid Test Suite File";
        String _invalidTestSuiteFileWithoutSpace = "TestSuite.xyz";
        String actualResult = FileUtil.getExecutableFromTestSuite(_invalidTestSuiteFileWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void GetExecutableFromTestSuite_InvalidTestSuiteWithSpace_ErrorMessage() {
        String expectedResult = "Input was not a valid Test Suite File";
        String _invalidTestSuiteFileWithSpace = "Test Suite.xyz";
        String actualResult = FileUtil.getExecutableFromTestSuite(_invalidTestSuiteFileWithSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void GetRanorexWorkingDirectory_RelativeTestSuitePathWithoutSpace_AbsoluteTestSuitePathWithoutSpace() {
        FilePath JenkinsJobDirectory = new FilePath(new File(_absoluteJenkinsJobWithoutSpace));
        String relativeTestSuitePath = _relativeDirectoryWithDotWithoutSpace + _testSuiteFileWithoutSpace;
        String _absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithoutSpace = _absoluteJenkinsJobWithoutSpace + "\\bin\\Debug";
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithoutSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(JenkinsJobDirectory, relativeTestSuitePath);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void GetRanorexWorkingDirectory_RelativeTestSuitePathWithSpace_AbsoluteTestSuitePathWithSpace() {
        FilePath JenkinsJobDirectory = new FilePath(new File(_absoluteJenkinsJobWithSpace));
        String relativeTestSuitePath = _relativeDirectoryWithDotWithSpace + _testSuiteFileWithSpace;
        String _absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithSpace = _absoluteJenkinsJobWithSpace + "\\bin\\De b ug";
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryInJenkinsWorkSpaceWithSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(JenkinsJobDirectory, relativeTestSuitePath);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void GetRanorexWorkingDirectory_AbsoluteTestSuitePathWithoutSpace_AbsoluteTestSuitePathWithoutSpace() {

        FilePath jenkinsDirectory = new FilePath(new File(_absoluteJenkinsJobWithoutSpace));
        String absoluteTestSuitePath = _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace + _testSuiteFileWithoutSpace;
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(jenkinsDirectory, absoluteTestSuitePath);
        assertEquals(expectedResult, actualResult);

    }


    @Test
    void GetRanorexWorkingDirectory_AbsoluteTestSuitePathWithSpace_AbsoluteTestSuitePathWithSpace() {
        FilePath jenkinsDirectory = new FilePath(new File(_absoluteJenkinsJobWithSpace));
        String absoluteTestSuitePath = _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace + _testSuiteFileWithSpace;
        FilePath expectedResult = new FilePath(new File(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace));
        FilePath actualResult = FileUtil.getRanorexWorkingDirectory(jenkinsDirectory, absoluteTestSuitePath);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void IsAbsolutePath_AbsolutePathWithoutSpace_True() {
        boolean actualResult = FileUtil.isAbsolutePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace);
        assertTrue(actualResult);
    }


    @Test
    void IsAbsolutePath_AbsolutePathWithSpace_True() {
        boolean actualResult = FileUtil.isAbsolutePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace);
        assertTrue(actualResult);
    }


    @Test
    void IsAbsolutePath_RelativePathWithoutSpace_False() {
        boolean actualResult = FileUtil.isAbsolutePath(_relativeDirectoryWithDotWithoutSpace);
        assertFalse(actualResult);
    }


    @Test
    void IsAbsolutePath_RelativePathWithSpace_False() {
        boolean actualResult = FileUtil.isAbsolutePath(_relativeDirectoryWithDotWithSpace);
        assertFalse(actualResult);
    }


    @Test
    void CombinePath_AbsoluePathWithDotWithoutSpace_AbsolutePathWithoutDotWithoutSpace() {
        String expectedResult = "C:\\Temp\\bin\\Debug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, _relativeDirectoryWithDotWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void CombinePath_AbsolutePathWithDotWithSpaceWithoutBackslash_AbsolutePathWithoutDotWithSpace() {
        String expectedResult = "C:\\Temp Directory\\bin\\De b ug\\";
        String _absoluteTestSuiteDirectoryOutsideJenkinsWithSpaceWithoutBackslash = "C:\\Temp Directory\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpaceWithoutBackslash, _relativeDirectoryWithDotWithSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void CombinePath_AbsolutePathWithDotWithoutSpaceWithoutBackslash_AbsolutePathWithoutDotWithoutSpace() {
        String expectedResult = "C:\\Temp\\bin\\Debug\\";
        String _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpaceWithoutBackslash = "C:\\Temp";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpaceWithoutBackslash, _relativeDirectoryWithDotWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void CombinePath_AbsoluePathWithDotWithSpace_AbsolutePathWithoutDotWithSpace() {
        String expectedResult = "C:\\Temp Directory\\bin\\De b ug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, _relativeDirectoryWithDotWithSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void CombinePath_AbsolutePathWithoutDotWithoutSpace_AbsolutePathWithoutDotWithoutSpace() {
        String expectedResult = "C:\\Temp\\bin\\Debug\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, _relativeDirectoryWithouDotWithoutSpace);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void CombinePath_AbsolutePathWithoutDotWithSpace_AbsolutePathWithoutDotWithSpace() {
        String expectedResult = "C:\\Temp Directory\\bin\\D e b u g\\";
        String actualResult = FileUtil.combinePath(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, _relativeDirectoryWithoutDotWithSpace);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void GetAbsoluteReportDirectory_AbsolutePathWithoutSpace_AbsolutePathWithoutSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, actualResult);
    }


    @Test
    void GetAbsoluteReportDirectory_AbsolutePathWithSpace_AbsolutePathWithSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, actualResult);
    }


    @Test
    void GetAbsoluteReportDirectory_RelativePathWithoutSpace_AbsolutePathWithoutSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_relativeDirectoryWithouDotWithoutSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithoutSpace, actualResult);
    }


    @Test
    void GetAbsoluteReportDirectory_RelativePathWithSpace_AbsolutePathWithSpace() {
        String actualResult = FileUtil.getAbsoluteReportDirectory(_relativeDirectoryWithoutDotWithSpace, _absoluteTestSuiteDirectoryOutsideJenkinsWithSpace);
        assertEquals(_absoluteTestSuiteDirectoryOutsideJenkinsWithSpace, actualResult);
    }

    @Test
    void IgnoreFileExtension_ValidFileNameWithoutSpaceWithZippedExtension_ValidFileNameWithoutSpaceWithoutExtension() {
        String _FileNameWithoutSpaceWithZippedExtension = "RanorexReport.rxzlog";
        String actualResult = FileUtil.removeFileExtension(_FileNameWithoutSpaceWithZippedExtension);
        String _FileNameWithoutSpaceWithoutExtension = "RanorexReport";
        assertEquals(_FileNameWithoutSpaceWithoutExtension, actualResult);
    }


    @Test
    void IgnoreFileExtension_ValidFileNameWithSpaceWithZippedExtension_ValidFileNameWithoutSpaceWithoutExtension() {
        String _FileNameWithSpaceWithZippedExtension = "Ranorex Rep ort.rxzlog";
        String actualResult = FileUtil.removeFileExtension(_FileNameWithSpaceWithZippedExtension);
        String _FileNameWithSpaceWithoutExtension = "Ranorex Rep ort";
        assertEquals(_FileNameWithSpaceWithoutExtension, actualResult);
    }

    @Test
    void IgnoreFileExtension_NULL_NULL() {
        String actualResult = FileUtil.removeFileExtension(null);
        assertNull(actualResult);
    }


    @Test
    void IgnoreFileExtension_SPACE_SPACE() {
        String actualResult = FileUtil.removeFileExtension(" ");
        assertEquals(" ", actualResult);
    }


    @Test
    void IgnoreFileExtension_InvalidFileName_NULL() {
        String actualResult = FileUtil.removeFileExtension("ThisFileHasNoExtension");
        assertEquals("ThisFileHasNoExtension", actualResult);
    }
}
